import functools
import os.path

from datasets import load_dataset

from testing.coverage_computation import get_coverage
from testing.gemini import generate_test_gemini_1_5_pro
from testing.gpt import generate_test_codex, generate_test_gpt35, generate_test_gpt4o_mini, generate_test_gpt4o
from testing.helpers import save_generated_test, save_content
from testing.models import Model
from testing.pass_rate import run_tests_and_compute_pass_rate
from testing.pylint_check import pylint_check, get_warnings_errors_fatals_count
from testing.syntax import check_syntax, CompileStatus

LANGUAGES_TO_KEEP = {"Python", "Java", "C#", "JavaScript", "Kotlin", "Go"}

GENERATED_DIR="./data/generated/"
STATS_DIR="./data/stats"


def main():
    ds = load_dataset("christopher/rosetta-code") #claudios/code_search_net
    train_df = ds.data['train'].to_pandas()

    # Filter the dataframe to keep only rows where language_name is in the specified set

    filtered_dfs = {}
    for l in LANGUAGES_TO_KEEP:
        filtered_df = train_df[train_df['language_name'] == l]
        filtered_dfs[l] = filtered_df


    dict_per_language = {}
    #
    # for l in LANGUAGES_TO_KEEP:
    #     filtered_df = train_df[train_df['language_name'] == l]
    #     dict_per_language[l] = filtered_df.sort_values(by='line_count', ascending=False)

    common_tasks = functools.reduce(
        lambda x, y: x & y,  # set intersection
        [set(df['task_name']) for df in filtered_dfs.values()]  # list of task_name sets for each language
    )


        #
        # filtered_df['code_length'] = filtered_df['code'].str.len()
        # filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))
        #
        #
        #
        # filtered_df.to_csv(os.path.join(STATS_DIR, "filtered_" + l +".csv"))

    for l, filtered_df in filtered_dfs.items():
        # Keep only the rows where the task_name is in the common tasks
        filtered_df = filtered_df[filtered_df['task_name'].isin(common_tasks)]

        # Compute code length and line count
        filtered_df['code_length'] = filtered_df['code'].str.len()
        filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))

        # Sort the dataframe by line count
        dict_per_language[l] = filtered_df.sort_values(by=['task_name'], ascending=False).head(20)

        # Save the filtered dataframe to a CSV file
        filtered_df.to_csv(os.path.join(STATS_DIR, "test_filtered_" + l + ".csv"))

    model = Model.GPT_3_5_turbo
    generate_tests_python(model, dict_per_language["Python"])

def generate_tests_python(model, df):
    if model == Model.GPT_3_5_turbo:
        model_string = "gpt_3_5_turbo"
        generate = generate_test_gpt35
    elif model == Model.GPT_CODEX:
        model_string = "davinci_002"
        generate = generate_test_codex
    elif model == Model.GPT_4o_mini:
        model_string = "gpt_4o_mini"
        generate = generate_test_gpt4o_mini
    elif model == Model.GPT_4o:
        model_string = "gpt_4o_2024_08_06"
        generate = generate_test_gpt4o
    elif model == Model.GEMINI_1_5_pro:
        model_string = "gemini_1_5_pro_001"
        generate = generate_test_gemini_1_5_pro
    else:
        raise ValueError("Invalid model specified.")

    c = 0
    generated_codes = []
    filenames = []
    compilation_statuses = []

    line_coverages = []
    branch_coverages = []
    method_coverages = []
    pylint_error_count = []
    pylint_findings = []
    pass_percentages = []

    for index, row in df.copy(deep=True).iterrows():

        task_name = row['task_name']
        code = row['code']
        if len(code) > 20000:
            print("TOO BIG, SKIPPING: ", task_name)
            generated_codes.append(None)
            compilation_statuses.append(None)
            line_coverages.append(None)
            branch_coverages.append(None)
            method_coverages.append(None)
            pylint_findings.append(None)
            pylint_error_count.append(None)
            pass_percentages.append(None)
            continue
        generated = generate(task_name, code)
        if generated is None:
            print("could not parse, skipping")
            generated_codes.append(None)
            compilation_statuses.append(None)
            line_coverages.append(None)
            branch_coverages.append(None)
            method_coverages.append(None)
            pylint_findings.append(None)
            pylint_error_count.append(None)
            pass_percentages.append(None)
            continue

        # replace non parsable space character
        parsed_code = generated.replace('\u00A0', ' ')
        generated_codes.append(parsed_code)

        save_content(task_name, code)
        filename = save_generated_test(task_name, model_string, parsed_code)

        filenames.append(filename)

        compilation_status = check_syntax(filename)
        compilation_statuses.append(compilation_status)

        if compilation_status == CompileStatus.OK:
            try:
                directory = os.path.dirname(filename)
                line_coverage = get_coverage(filename)
                branch_coverage = get_coverage(filename, branch=True)
                pass_percentage = run_tests_and_compute_pass_rate(directory)
            except Exception as e:
                print(e)
                line_coverage = None
                branch_coverage = None
                pass_percentage = None
        else:
            line_coverage = None
            branch_coverage = None
            pass_percentage = None

        count, errors = pylint_check(filename)
        pylint_findings.append(errors)
        pylint_error_count.append(count)
        branch_coverages.append(branch_coverage)
        line_coverages.append(line_coverage)
        method_coverages.append(None)  # todo
        pass_percentages.append(None if pass_percentage is None else pass_percentage["pass_percentage"])

        print("-----------")
        print(generated)
        print("-----------")

    generated_codes = generated_codes + [None for i in range(len(df) - c)]
    compilation_statuses = compilation_statuses + [None for i in range(len(df) - c)]
    line_coverages = line_coverages + [None for i in range(len(df) - c)]
    branch_coverages = branch_coverages + [None for i in range(len(df) - c)]
    method_coverages = method_coverages + [None for i in range(len(df) - c)]
    pylint_findings = pylint_findings + [None for i in range(len(df) - c)]
    pylint_error_count = pylint_error_count + [None for i in range(len(df) - c)]
    pass_percentages = pass_percentages + [None for i in range(len(df) - c)]
    df["generated_codes"] = generated_codes
    df["compilation_statuses"] = compilation_statuses
    df["line_coverages"] = line_coverages
    df["branch_coverages"] = branch_coverages
    df["method_coverages"] = method_coverages
    df["pylint_findings"] = pylint_findings
    df["pylint_findings_count"] = pylint_error_count
    df["tests_pass_percentage"] = pass_percentages

    all_warnings, all_errors, all_fatals = [], [], []
    for findings in pylint_findings:
        warnings, errors, fatals = get_warnings_errors_fatals_count(findings)
        all_warnings.append(warnings)
        all_errors.append(errors)
        all_fatals.append(fatals)

    df["pylint_warning_count"] = all_warnings
    df["pylint_error_count"] = all_errors
    df["pylint_fatals_count"] = all_fatals

    df.to_csv(os.path.join(STATS_DIR, "filtered_Python_stats_{}.csv".format(model_string)))


def generate_tests_java(model, df):
    if model == Model.GPT_3_5_turbo:
        model_string = "gpt_3_5_turbo"
        generate = generate_test_gpt35
    elif model == Model.GPT_CODEX:
        model_string = "davinci_002"
        generate = generate_test_codex
    elif model == Model.GPT_4o_mini:
        model_string = "gpt_4o_mini"
        generate = generate_test_gpt4o_mini
    elif model == Model.GPT_4o:
        model_string = "gpt_4o_2024_08_06"
        generate = generate_test_gpt4o
    elif model == Model.GEMINI_1_5_pro:
        model_string = "gemini_1_5_pro_001"
        generate = generate_test_gemini_1_5_pro
    else:
        raise ValueError("Invalid model specified.")

    c = 0
    generated_codes = []
    filenames = []
    compilation_statuses = []

    line_coverages = []
    branch_coverages = []
    method_coverages = []
    pylint_error_count = []
    pylint_findings = []
    pass_percentages = []

    for index, row in df.sort_values(by='line_count', ascending=False).copy(
            deep=True).iterrows():
        if c == 20:
            break
        c += 1
        task_name = row['task_name']
        code = row['code']
        if len(code) > 20000:
            print("TOO BIG, SKIPPING: ", task_name)
            generated_codes.append(None)
            compilation_statuses.append(None)
            line_coverages.append(None)
            branch_coverages.append(None)
            method_coverages.append(None)
            pylint_findings.append(None)
            pylint_error_count.append(None)
            pass_percentages.append(None)
            continue
        generated = generate(task_name, code)
        if generated is None:
            print("could not parse, skipping")
            generated_codes.append(None)
            compilation_statuses.append(None)
            line_coverages.append(None)
            branch_coverages.append(None)
            method_coverages.append(None)
            pylint_findings.append(None)
            pylint_error_count.append(None)
            pass_percentages.append(None)
            continue

        # replace non parsable space character
        parsed_code = generated.replace('\u00A0', ' ')
        generated_codes.append(parsed_code)

        save_content(task_name, code)
        filename = save_generated_test(task_name, model_string, parsed_code)

        filenames.append(filename)

        compilation_status = check_syntax(filename)
        compilation_statuses.append(compilation_status)

        if compilation_status == CompileStatus.OK:
            try:
                directory = os.path.dirname(filename)
                line_coverage = get_coverage(filename)
                branch_coverage = get_coverage(filename, branch=True)
                pass_percentage = run_tests_and_compute_pass_rate(directory)
            except Exception as e:
                print(e)
                line_coverage = None
                branch_coverage = None
                pass_percentage = None
        else:
            line_coverage = None
            branch_coverage = None
            pass_percentage = None

        count, errors = pylint_check(filename)
        pylint_findings.append(errors)
        pylint_error_count.append(count)
        branch_coverages.append(branch_coverage)
        line_coverages.append(line_coverage)
        method_coverages.append(None)  # todo
        pass_percentages.append(None if pass_percentage is None else pass_percentage["pass_percentage"])

        print("-----------")
        print(generated)
        print("-----------")

    generated_codes = generated_codes + [None for i in range(len(df) - c)]
    compilation_statuses = compilation_statuses + [None for i in range(len(df) - c)]
    line_coverages = line_coverages + [None for i in range(len(df) - c)]
    branch_coverages = branch_coverages + [None for i in range(len(df) - c)]
    method_coverages = method_coverages + [None for i in range(len(df) - c)]
    pylint_findings = pylint_findings + [None for i in range(len(df) - c)]
    pylint_error_count = pylint_error_count + [None for i in range(len(df) - c)]
    pass_percentages = pass_percentages + [None for i in range(len(df) - c)]
    df["generated_codes"] = generated_codes
    df["compilation_statuses"] = compilation_statuses
    df["line_coverages"] = line_coverages
    df["branch_coverages"] = branch_coverages
    df["method_coverages"] = method_coverages
    df["pylint_findings"] = pylint_findings
    df["pylint_findings_count"] = pylint_error_count
    df["tests_pass_percentage"] = pass_percentages

    all_warnings, all_errors, all_fatals = [], [], []
    for findings in pylint_findings:
        warnings, errors, fatals = get_warnings_errors_fatals_count(findings)
        all_warnings.append(warnings)
        all_errors.append(errors)
        all_fatals.append(fatals)

    df["pylint_warning_count"] = all_warnings
    df["pylint_error_count"] = all_errors
    df["pylint_fatals_count"] = all_fatals

    df.to_csv(os.path.join(STATS_DIR, "filtered_Java_stats_{}.csv".format(model_string)))


main()
