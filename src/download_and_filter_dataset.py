import functools
import os.path
from pathlib import Path

import pandas as pd
from datasets import load_dataset
from pandas import read_csv

from src.config import Config
from src.analysis.java_validation import validate_java_code
from src.analysis.kotlin_analysis import analyze_kotlin_tests, run_ktlint
from src.analysis.kotlin_validation import validate_kotlin_code
from src.analysis.python_assertion_ratios import assertions_density_python, assertions_mccabe_ratio_python
from src.models.deepseek import generate_test_deepseek_coder
from src.models.gemini import generate_test_gemini_1_5_pro, generate_test_gemini_1_5_flash
from src.models.gpt import generate_test_gpt35, generate_test_gpt4o_mini, generate_test_gpt4o
from src.helpers import save_generated_test, save_content
from src.analysis.java_analysis import run_checkstyle, process_java_files_and_run_test_analysis
from src.language import LanguageEnum
from src.models.models import Model
from src.analysis.python_pass_rate import run_tests
from src.stats.python_code_quality import pylint_check
from src.analysis.python_validation import CompileStatus, check_syntax_string, check_syntax_file
from src.analysis.go_analysis import analyze_go_tests
from src.analysis.go_validation import validate_go_code_with_build

LANGUAGES_TO_KEEP = {"Python", "Java", "Kotlin", "Go"}


def download_and_validate_dataset(use_existing_generated_data=True):
    if use_existing_generated_data:
        language_data_dict = {
            "Python": read_csv(os.path.join(Config.get_raw_dir(), "raw_python_dataset.csv")),
            "Kotlin": read_csv(os.path.join(Config.get_raw_dir(), "raw_kotlin_dataset.csv")),
            "Java": read_csv(os.path.join(Config.get_raw_dir(), "raw_java_dataset.csv")),
            "Go": read_csv(os.path.join(Config.get_raw_dir(), "raw_go_dataset.csv"))
        }

    else:
        ds = load_dataset("christopher/rosetta-code")
        train_df = ds.data['train'].to_pandas()

        language_data_dict = {}
        for lang in LANGUAGES_TO_KEEP:
            filtered_df = train_df[train_df['language_name'] == lang].head(30)
            filtered_df['code_length'] = filtered_df['code'].str.len()
            filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))
            if lang == "Python":
                filtered_df["code_syntax"] = filtered_df["code"].apply(
                    lambda x: check_syntax_string(x.replace('\u00A0', ' ')))
            if lang == "Java":
                filtered_df["code_syntax"] = filtered_df["code"].apply(
                    lambda x: validate_java_code(x.replace('\u00A0', ' ')))
            if lang == "Kotlin":
                filtered_df["code_syntax"] = filtered_df["code"].apply(
                    lambda x: validate_kotlin_code(x.replace('\u00A0', ' ')))
            if lang == "Go":
                filtered_df["code_syntax"] = filtered_df["code"].apply(
                    lambda x: validate_go_code_with_build(x.replace('\u00A0', ' ')))

            filtered_df.to_csv(os.path.join(Config.get_raw_dir(), ("raw_" + lang.lower() + "_dataset.csv")))
            language_data_dict[lang] = filtered_df

    lang_dict_correct_syntax = {}
    for lang, filtered_df in language_data_dict.items():
        lang_dict_correct_syntax[lang] = filtered_df[(filtered_df["code_syntax"] == 'CompileStatus.OK')]

    return lang_dict_correct_syntax


def filter_dataset(dict_per_language, size=200):
    syntactically_correct = {}
    for l, df in dict_per_language.items():
        syntactically_correct[l] = df.loc[(df["code_syntax"] == "CompileStatus.OK")]

    common_tasks = functools.reduce(
        lambda x, y: x & y, # set intersection
        [set(df['task_name']) for df in syntactically_correct.values()]  # list of task_name sets for each language
    )

    filtered_dict = {}
    for l, df in syntactically_correct.items():
        filtered_dict[l] = df[df['task_name'].isin(common_tasks)]
        filtered_dict[l] = filtered_dict[l].sort_values(by=['task_name']).head(size)
        filtered_dict[l].to_csv(os.path.join(Config.get_stats_output_dir(), "filtered_" + l + ".csv"), index=False, header=True)
    return filtered_dict


def generate_tests(model: Model, df: pd.DataFrame, lang: LanguageEnum):
    if model == Model.GPT_3_5_turbo:
        generate = generate_test_gpt35
    elif model == Model.GPT_4o_mini:
        generate = generate_test_gpt4o_mini
    elif model == Model.GPT_4o:
        generate = generate_test_gpt4o
    elif model == Model.GEMINI_1_5_pro:
        generate = generate_test_gemini_1_5_pro
    elif model == Model.GEMINI_1_5_flash:
        generate = generate_test_gemini_1_5_flash
    elif model == Model.DEEPSEEK_CODER:
        generate = generate_test_deepseek_coder
    else:
        raise ValueError("Invalid model specified.")

    model_string = model.value
    generated_codes = []
    filenames = []
    for index, row in df.copy(deep=True).iterrows():
        task_name = row['task_name']
        code = row['code']
        generated = generate(task_name, code, lang, row['task_description'])
        if generated == "none" or generated == "error":
            print("could not parse, skipping")
            generated_codes.append(None)
            filenames.append(None)

        else:
            # replace non parsable space character
            parsed_code = generated.replace('\u00A0', ' ')
            generated_codes.append(parsed_code)

            save_content(task_name, model_string, code.replace('\u00A0', ' '), lang)
            filename = save_generated_test(task_name, model_string, parsed_code, lang)

            filenames.append(filename)

    df["generated_code"] = generated_codes
    df["file_path"] = filenames
    df.to_csv(os.path.join(Config.get_stats_output_dir(), "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)
    return df


def run_analysis(lang, df, model_string):
    if lang == LanguageEnum.Python:
        return run_analysis_python(df, model_string, lang)
    if lang == LanguageEnum.Go:
        return run_analysis_go(df, model_string, lang)
    if lang == LanguageEnum.Kotlin:
        return run_analysis_kotlin(df, model_string, lang)
    if lang == LanguageEnum.Java:
        return run_analysis_java(df, model_string, lang)
    else:
        print("Analysis for language " + lang.name + " is not yet supported")


def run_analysis_kotlin(df, model_string, lang):
    compilation_statuses = []
    warnings = []
    warnings_count = []
    pass_percentages = []
    execution_time_sec = []
    timeout_occurred = []
    line_coverage_percentages = []
    branch_coverage_percentages = []
    internal_error_occurred = []
    assertions_density = []
    assertions_mccabe_ratio = []
    runtime_errors = []
    syntax_maven_output = []
    test_maven_output = []
    count = 0

    for index, row in df.copy(deep=True).iterrows():
        count += 1
        try:
            file_path = row['file_path']
            if isinstance(file_path, float):
                warnings_count.append(None)
                warnings.append(None)
                line_coverage_percentages.append(None)
                branch_coverage_percentages.append(None)
                pass_percentages.append(None)
                compilation_statuses.append(None)
                execution_time_sec.append(None)
                timeout_occurred.append(False)
                internal_error_occurred.append(False)
                assertions_density.append(None)
                assertions_mccabe_ratio.append(None)
                runtime_errors.append(None)
                syntax_maven_output.append(None)
                test_maven_output.append(None)
                continue
            dir = os.path.dirname(file_path)
            found_warnings = run_ktlint(file_path)
            result = analyze_kotlin_tests(dir, file_path)

            compilation_statuses.append(result["syntax"])
            warnings.append(found_warnings)
            warnings_count.append(len(found_warnings["errors"]) if found_warnings != None else None)
            pass_percentages.append(result["test_pass_rate"])
            execution_time_sec.append(result["execution_time_sec"])
            timeout_occurred.append(result["timeout_occurred"])
            line_coverage_percentages.append(result["line_coverage_percentage"])
            branch_coverage_percentages.append(result["branch_coverage_percentage"])
            internal_error_occurred.append(result["internal_error_occurred"])
            assertions_density.append(result["assertion_density"])
            assertions_mccabe_ratio.append(result["assertions_mccabe_ratio"])
            runtime_errors.append(result["runtime_errors"])
            syntax_maven_output.append(result["syntax_maven_output"])
            test_maven_output.append(result["test_maven_output"])

        except Exception as e:
            print("ERROR OCCURRED: ", e)
            append_if_not_included(count, warnings_count, None)
            append_if_not_included(count, warnings, None)
            append_if_not_included(count, line_coverage_percentages, None)
            append_if_not_included(count, branch_coverage_percentages, None)
            append_if_not_included(count, pass_percentages, None)
            append_if_not_included(count, compilation_statuses, None)
            append_if_not_included(count, execution_time_sec, None)
            append_if_not_included(count, timeout_occurred, False)
            append_if_not_included(count, assertions_density, None)
            append_if_not_included(count, assertions_mccabe_ratio, None)
            append_if_not_included(count, internal_error_occurred, True)
            append_if_not_included(count, runtime_errors, None)
            append_if_not_included(count, syntax_maven_output, None)
            append_if_not_included(count, test_maven_output, None)

    df["compilation_status"] = compilation_statuses
    df["syntax_maven_output"] = syntax_maven_output
    df["runtime_errors_count"] = runtime_errors
    df["internal_error_occurred"] = internal_error_occurred
    df["line_coverage_percent"] = line_coverage_percentages
    df["branch_coverage_percent"] = branch_coverage_percentages
    df["assertions_density"] = assertions_density
    df["assertions_mccabe_ratio"] = assertions_mccabe_ratio
    df["execution_time_sec"] = execution_time_sec
    df["timeout_occurred"] = timeout_occurred
    df["test_pass_rate"] = pass_percentages
    df["test_maven_output"] = test_maven_output
    df["warnings"] = warnings
    df["warnings_count"] = warnings_count

    df.to_csv(os.path.join(Config.get_stats_output_dir(), "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)


def run_analysis_java(df, model_string, lang):
    compilation_statuses = []
    warnings = []
    warnings_count = []
    pass_percentages = []
    execution_time_sec = []
    timeout_occurred = []
    line_coverage_percentages = []
    branch_coverage_percentages = []
    internal_error_occurred = []
    assertions_density = []
    assertions_mccabe_ratio = []
    runtime_errors = []
    syntax_maven_output = []
    test_maven_output = []
    count = 0

    for index, row in df.copy(deep=True).iterrows():
        count += 1
        # try:
        file_path = row['file_path']
        print("FIle path from csv: ", file_path)
        if isinstance(file_path, float) or file_path is None:
            warnings_count.append(None)
            warnings.append(None)
            line_coverage_percentages.append(None)
            branch_coverage_percentages.append(None)
            pass_percentages.append(None)
            compilation_statuses.append(None)
            execution_time_sec.append(None)
            timeout_occurred.append(False)
            internal_error_occurred.append(False)
            assertions_density.append(None)
            assertions_mccabe_ratio.append(None)
            runtime_errors.append(None)
            syntax_maven_output.append(None)
            test_maven_output.append(None)
            continue
        dir = os.path.dirname(file_path)
        found_warnings = run_checkstyle(file_path)
        result = process_java_files_and_run_test_analysis(dir, file_path)

        compilation_statuses.append(result["syntax"])
        warnings.append(found_warnings)
        warnings_count.append(len(found_warnings) if found_warnings != None else None)
        pass_percentages.append(result["test_pass_rate"])
        execution_time_sec.append(result["execution_time_sec"])
        timeout_occurred.append(result["timeout_occurred"])
        line_coverage_percentages.append(result["line_coverage_percent"])
        branch_coverage_percentages.append(result["branch_coverage_percent"])
        internal_error_occurred.append(result["internal_error_occurred"])
        assertions_density.append(result["assertion_density"])
        assertions_mccabe_ratio.append(result["assertions_mccabe_ratio"])
        runtime_errors.append(result["runtime_errors"])
        syntax_maven_output.append(result["syntax_maven_output"])
        test_maven_output.append(result["test_maven_output"])

        # except Exception as e:
        #     append_if_not_included(count, warnings_count, None)
        #     append_if_not_included(count, warnings, None)
        #     append_if_not_included(count, line_coverage_percentages, None)
        #     append_if_not_included(count, branch_coverage_percentages, None)
        #     append_if_not_included(count, pass_percentages, None)
        #     append_if_not_included(count, compilation_statuses, None)
        #     append_if_not_included(count, execution_time_sec, None)
        #     append_if_not_included(count, timeout_occurred, False)
        #     append_if_not_included(count, assertions_density, None)
        #     append_if_not_included(count, assertions_mccabe_ratio, None)
        #     append_if_not_included(count, internal_error_occurred, True)
        #     append_if_not_included(count, runtime_errors, None)
        #     append_if_not_included(count, syntax_maven_output, None)
        #     append_if_not_included(count, test_maven_output, None)

    print("COMPILATION_STATUSES: ", compilation_statuses)

    df["compilation_status"] = compilation_statuses
    df["syntax_maven_output"] = syntax_maven_output
    df["runtime_errors_count"] = runtime_errors
    df["internal_error_occurred"] = internal_error_occurred
    df["line_coverage_percent"] = line_coverage_percentages
    df["branch_coverage_percent"] = branch_coverage_percentages
    df["assertions_density"] = assertions_density
    df["assertions_mccabe_ratio"] = assertions_mccabe_ratio
    df["execution_time_sec"] = execution_time_sec
    df["timeout_occurred"] = timeout_occurred
    df["test_pass_rate"] = pass_percentages
    df["test_maven_output"] = test_maven_output
    df["warnings"] = warnings
    df["warnings_count"] = warnings_count

    n = "filtered_{}_stats_{}.csv".format(lang.name, model_string)
    print("FILE WITH STATS: ", n)

    df.to_csv(os.path.join(Config.get_stats_output_dir(), n), index=False,
              header=True)


def run_analysis_go(df, model_string, lang):
    compilation_statuses = []
    execution_time_sec = []
    timeout_occurred = []
    line_coverage_percentages = []
    branch_coverage_percentages = []
    warnings = []
    warnings_count = []
    pass_percentages = []
    internal_error_occurred = []
    assertions_density = []
    assertions_mccabe_ratio = []
    runtime_errors_count = []
    syntax_output = []
    count = 0

    for index, row in df.copy(deep=True).iterrows():
        count += 1
        # try:
        filename = row['file_path']

        print("Filename: ", filename)

        if isinstance(filename, float):
            warnings_count.append(None)
            warnings.append(None)
            line_coverage_percentages.append(None)
            branch_coverage_percentages.append(None)
            pass_percentages.append(None)
            compilation_statuses.append(None)
            execution_time_sec.append(None)
            timeout_occurred.append(False)
            internal_error_occurred.append(False)
            assertions_density.append(None)
            assertions_mccabe_ratio.append(None)
            runtime_errors_count.append(None)
            syntax_output.append(None)
            continue
        go_file = os.path.join(os.path.dirname(filename),
                               Path(filename).name.replace("_test.go", ".go").replace(model_string + "_",
                                                                                      "")) if filename is not None else None
        test_file = filename
        print("Test file: ", test_file)
        print("Go file: ", go_file)

        result = analyze_go_tests(go_file, test_file)

        compilation_statuses.append(result["syntax"])
        line_coverage_percentages.append(result["line_coverage_percent"])
        branch_coverage_percentages.append(result["branch_coverage_percent"])
        warnings.append(result["warnings"])
        warnings_count.append(result["warnings_count"])
        pass_percentages.append(result["test_pass_rate"])
        execution_time_sec.append(result["execution_time_sec"])
        timeout_occurred.append(result["timeout"])
        internal_error_occurred.append(result["internal_error_occurred"])
        assertions_density.append(result["assertions_density"])
        assertions_mccabe_ratio.append(result["assertions_mccabe_ratio"])
        runtime_errors_count.append(result["runtime_errors_count"])
        syntax_output.append(result["syntax_output"])

        # except Exception as e:
        #     print("ERROR: ", e)
        #     append_if_not_included(count, warnings_count, None)
        #     append_if_not_included(count, warnings, None)
        #     append_if_not_included(count, line_coverage_percentages, None)
        #     append_if_not_included(count, pass_percentages, None)
        #     append_if_not_included(count, compilation_statuses, None)
        #     append_if_not_included(count, execution_time_sec, None)
        #     append_if_not_included(count, timeout_occurred, False)
        #     append_if_not_included(count, assertions_density, None)
        #     append_if_not_included(count, assertions_mccabe_ratio, None)
        #     append_if_not_included(count, internal_error_occurred, True)
        #     append_if_not_included(count, branch_coverage_percentages, None)
        #     append_if_not_included(count, runtime_errors_count, None)
        #     append_if_not_included(count, syntax_output, None)
        #     continue

    df["compilation_status"] = compilation_statuses
    df["runtime_errors_count"] = runtime_errors_count
    df["line_coverage_percent"] = line_coverage_percentages
    df["branch_coverage_percent"] = branch_coverage_percentages
    df["assertions_density"] = assertions_density
    df["assertions_mccabe_ratio"] = assertions_mccabe_ratio
    df["test_pass_percentage"] = pass_percentages
    df["execution_time_sec"] = execution_time_sec
    df["warnings_count"] = warnings_count
    df["warnings"] = warnings
    df["timeout"] = timeout_occurred
    df["internal_error_occurred"] = internal_error_occurred
    df["syntax_output"] = syntax_output

    df.to_csv(os.path.join(Config.get_stats_output_dir(), "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)
    return df


def append_if_not_included(count, list, value):
    if len(list) != count:
        return list.append(value)
    return list


def run_analysis_python(df, model_string, lang):
    compilation_statuses = []
    execution_time_sec = []
    timeout_occurred = []
    line_coverage_percentages = []
    branch_overage_percentages = []
    warnings = []
    warnings_count = []
    pass_percentages = []
    internal_error_occurred = []
    assertions_density = []
    assertions_mccabe_ratio = []
    runtime_errors_count = []

    for index, row in df.copy(deep=True).iterrows():
        error = False
        test_file_path = row['file_path']

        print("Filename: ", test_file_path)

        if isinstance(test_file_path, float):
            warnings.append(None)
            warnings_count.append(None)
            branch_overage_percentages.append(None)
            line_coverage_percentages.append(None)
            pass_percentages.append(None)
            compilation_statuses.append(None)
            internal_error_occurred.append(True)
            execution_time_sec.append(None)
            timeout_occurred.append(None)
            assertions_mccabe_ratio.append(None)
            assertions_density.append(None)
            runtime_errors_count.append(None)
            continue

        print("Filename: ", test_file_path)
        compilation_status = check_syntax_file(test_file_path)
        compilation_statuses.append(compilation_status)

        code_file_name = Path(test_file_path).name.replace("test_" + model_string + "_",
                                                           "") if test_file_path is not None else None
        code_file_path = os.path.join(os.path.dirname(test_file_path), code_file_name)

        if compilation_status == CompileStatus.OK:
            try:
                print("RUN TEST AND COMPUTE PASS RATE")
                test_exec_result, timeout = run_tests(test_file_path)
                print("PASS RATE END")
            except Exception as e:
                print(e)
                test_exec_result = None
                timeout = None
                error = True
        else:
            if compilation_status == CompileStatus.EXCEPTION_OCCURRED:
                error = True
            test_exec_result = None
            timeout = None
        try:
            print("PYLINT CHECK")
            count, warning = pylint_check(test_file_path)
            print("END PYLINT CHECK")
            warnings.append(warning)
            warnings_count.append(count)
        except Exception as e:
            print("exception occurred: ", e)
            error = True
            warnings.append(None)
            warnings_count.append(None)

        try:
            density = assertions_density_python(test_file_path)
            assertions_density.append(density)
        except Exception as e:
            print("exception occurred: ", e)
            error = True
            assertions_density.append(None)

        try:
            mccabe = assertions_mccabe_ratio_python(code_file_path, test_file_path)
            assertions_mccabe_ratio.append(mccabe)
        except Exception as e:
            print("exception occurred: ", e)
            error = True
            assertions_mccabe_ratio.append(None)

        timeout_occurred.append(timeout)
        internal_error_occurred.append(error)
        branch_overage_percentages.append(None if test_exec_result is None else test_exec_result["branch_coverage"])
        line_coverage_percentages.append(None if test_exec_result is None else test_exec_result["line_coverage"])
        pass_percentages.append(None if test_exec_result is None else test_exec_result["pass_percentage"])
        execution_time_sec.append(None if test_exec_result is None else test_exec_result["execution_time"])
        runtime_errors_count.append(None if test_exec_result is None else test_exec_result["runtime_errors"])

    df["compilation_status"] = compilation_statuses
    df["runtime_errors_count"] = runtime_errors_count
    df["line_coverage_percent"] = line_coverage_percentages
    df["branch_coverage_percent"] = branch_overage_percentages
    df["assertions_density"] = assertions_density
    df["assertions_mccabe_ratio"] = assertions_mccabe_ratio
    df["warnings"] = warnings
    df["warnings_count"] = warnings_count
    df["tests_pass_rate"] = pass_percentages
    df["execution_time_sec"] = execution_time_sec
    df["timeout"] = timeout_occurred

    df.to_csv(os.path.join(Config.get_stats_output_dir(), "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)

    return df
