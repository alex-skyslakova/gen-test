import functools
import os.path
from pathlib import Path

import pandas as pd
from datasets import load_dataset
from pandas import read_csv

from go_analysis import analyze_go_tests
from kotlin_analysis import analyze_kotlin_tests, run_ktlint
from present_results import compute_metric_scores
from python_assertion_ratios import assertions_density_python, assertions_mccabe_ratio_python
from python_coverage_computation import get_coverage
from deepseek import generate_test_deepseek_coder
from gemini import generate_test_gemini_1_5_pro, generate_test_gemini_1_5_flash
from gpt import generate_test_codex, generate_test_gpt35, generate_test_gpt4o_mini, generate_test_gpt4o
from helpers import save_generated_test, save_content, read_generated_test
from java_analysis import run_checkstyle, process_java_files_and_run_test_analysis
from language import LanguageEnum
from models import Model
from python_pass_rate import run_tests
from python_code_quality import pylint_check
from python_validation import CompileStatus, check_syntax_string, check_syntax_file

LANGUAGES_TO_KEEP = {"Python", "Java", "Kotlin", "Go"}

GENERATED_DIR = "./data/generated/docs_python/"
STATS_DIR = "./data/generated/docs_stats/"


def download_and_validate_dataset():
    dataset = {
        "Python": read_csv("raw_python_dataset.csv"),
        "Kotlin": read_csv("raw_kotlin_dataset.csv"),
        "Java": read_csv("raw_java_dataset.csv"),
        "Go": read_csv("raw_go_dataset.csv")
    }

    ds = load_dataset("christopher/rosetta-code")
    train_df = ds.data['train'].to_pandas()

    lang_dfs = {}
    for l in LANGUAGES_TO_KEEP:
        lang_df = train_df[train_df['language_name'] == l]
        lang_dfs[l] = lang_df

    # Update the PATH inside the script
    os.environ["PATH"] += os.pathsep + "/usr/local/golang/bin"

    # dict_per_language = {}
    # for l, filtered_df in lang_dfs.items():
    #     filtered_df['code_length'] = filtered_df['code'].str.len()
    #     filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))
    #     if l == "Python":
    #         filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: check_syntax_string(x.replace('\u00A0', ' ')))
    #     if l == "Java":
    #         filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_java_code(x.replace('\u00A0', ' ')))
    #     if l == "Kotlin":
    #         filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_kotlin_code(x.replace('\u00A0', ' ')))
    #     if l == "Go":
    #         filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_go_code_with_build(x.replace('\u00A0', ' ')))
    #
    #     filtered_df.to_csv("raw_" + l.lower() + "_dataset.csv")
    dict_per_language = {}
    for l, filtered_df in dataset.items():

        # Print the statistics of the "code_syntax" column
        syntax_stats = filtered_df["code_syntax"].value_counts()

        # Print the results
        print("Code Syntax Statistics: " + l + ":")
        for status, count in syntax_stats.items():
            print(f"{status}: {count} occurrences")

        # Optionally, you could print percentages as well
        total = filtered_df["code_syntax"].count()
        print("\nCode Syntax Percentage Statistics:")
        for status, count in syntax_stats.items():
            percentage = (count / total) * 100
            print(f"{status}: {percentage:.2f}%")

        dict_per_language[l] = filtered_df[(filtered_df["code_syntax"] == 'CompileStatus.OK')]
    return dict_per_language


def filter_dataset(dict_per_language, size=200):
    syntactically_correct = {}
    for l, df in dict_per_language.items():
        syntactically_correct[l] = df.loc[(df["code_syntax"] == "CompileStatus.OK")]

    common_tasks = functools.reduce(
        lambda x, y: x & y,  # set intersection
        [set(df['task_name']) for df in syntactically_correct.values()]  # list of task_name sets for each language
    )

    filtered_dict = {}
    for l, df in syntactically_correct.items():
        filtered_dict[l] = df[df['task_name'].isin(common_tasks)]
        filtered_dict[l] = filtered_dict[l].sort_values(by=['task_name']).head(size)
        filtered_dict[l].to_csv(os.path.join(STATS_DIR, "filtered_" + l + ".csv"), index=False, header=True)
    return filtered_dict

def generate_tests(model, df, lang):
    if model == Model.GPT_3_5_turbo:
        model_string = "gpt_3_5_turbo"
        generate = generate_test_gpt35
    elif model == Model.GPT_4o_mini:
        model_string = "gpt_4o_mini"
        generate = generate_test_gpt4o_mini
    elif model == Model.GPT_4o:
        model_string = "gpt_4o_2024_08_06"
        generate = generate_test_gpt4o
    elif model == Model.GEMINI_1_5_pro:
        model_string = "gemini_1_5_pro_002"
        generate = generate_test_gemini_1_5_pro
    elif model == Model.GEMINI_1_5_flash:
        model_string = "gemini_1_5_flash_002"
        generate = generate_test_gemini_1_5_flash
    elif model == Model.DEEPSEEK_CODER:
        model_string = "deepseek_coder"
        generate = generate_test_deepseek_coder

    else:
        raise ValueError("Invalid model specified.")

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
    df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)
    return df
    # df = read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/test_filtered_Python_stats_gpt_3_5_turbo.csv")
    # run_analysis(df, model_string)
    # run_analysis_python(df, model_string)


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
            result = analyze_kotlin_tests(dir)

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

    # df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
    #           header=True)
    #
    # df = compute_metric_scores(df)

    df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
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
        #try:
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
    # df.to_csv(os.path.join(STATS_DIR, n), index=False,
    #           header=True)
    #
    # df = compute_metric_scores(df)
    df.to_csv(os.path.join(STATS_DIR, n), index=False,
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

    # df.to_csv(os.path.join(STATS_DIR, "test_filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
    #           header=True)
    #
    # df = compute_metric_scores(df)

    df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
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

    # df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
    #           header=True)
    #
    # df = compute_metric_scores(df)

    df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)), index=False,
              header=True)

    return df

if __name__ == '__main__':
    # df = read_csv(
    #     "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/filtered_Python_stats_deepseek_coder.csv")
    #
    # run_analysis(LanguageEnum.Python, df, "deepseek_coder'")

    # dataset = download_and_validate_dataset()
    # or
    languages = []
    dataset = {
        "Python": read_csv("raw_python_dataset.csv"),
        "Kotlin": read_csv("raw_kotlin_dataset.csv"),
        "Java": read_csv("raw_java_dataset.csv"),
        "Go": read_csv("raw_go_dataset.csv")
    }

    filtered = filter_dataset(dataset)
    print(filtered)
    filtered[LanguageEnum.Kotlin.name]["docs_length"] = filtered[LanguageEnum.Kotlin.name]['task_description'].str.len()

    ALL = False
    if ALL:
        for l in languages:
            for m in Model:
                generate_tests(m, filtered[l.name], l)
    else:
        language = LanguageEnum.Python
        model = Model.GPT_4o
        #generated_df = generate_tests(model, filtered[language.name], language)
        generated_df = read_csv("data/generated/docs_stats/filtered_Python_stats_gpt_4o_2024_08_06.csv")
        run_analysis_python(generated_df, model.value, language)
        # print("acb")
