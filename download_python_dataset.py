import functools
import models
import os.path
from pathlib import Path

from datasets import load_dataset
from pandas import read_csv
from python_coverage_computation import get_coverage
from deepseek import generate_test_deepseek_coder
from gemini import generate_test_gemini_1_5_pro, generate_test_gemini_1_5_flash
from go_validation import validate_go_code_with_build
from gpt import generate_test_codex, generate_test_gpt35, generate_test_gpt4o_mini, generate_test_gpt4o
from helpers import save_generated_test, save_content
from java_analysis import run_checkstyle
from java_validation import validate_java_code
from kotlin_validation import validate_kotlin_code
from language import LanguageEnum
from models import Model
from python_pass_rate import run_tests_and_compute_pass_rate
from python_pylint_check import pylint_check, get_warnings_errors_fatals_count
from python_validation import CompileStatus, check_syntax_string, check_syntax_file

LANGUAGES_TO_KEEP = {"Python", "Java", "Kotlin", "Go"}

GENERATED_DIR="./data/generated/go/"
STATS_DIR="./data/generated/stats/"


def download_and_validate_dataset():
    ds = load_dataset("christopher/rosetta-code")
    train_df = ds.data['train'].to_pandas()

    lang_dfs = {}
    for l in LANGUAGES_TO_KEEP:
        lang_df = train_df[train_df['language_name'] == l]
        lang_dfs[l] = lang_df

    # Update the PATH inside the script
    os.environ["PATH"] += os.pathsep + "/usr/local/go/bin"

    dict_per_language = {}
    for l, filtered_df in lang_dfs.items():
        filtered_df['code_length'] = filtered_df['code'].str.len()
        filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))
        if l == "Python":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: check_syntax_string(x.replace('\u00A0', ' ')))
        if l == "Java":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_java_code(x.replace('\u00A0', ' ')))
        if l == "Kotlin":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_kotlin_code(x.replace('\u00A0', ' ')))
        if l == "Go":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_go_code_with_build(x.replace('\u00A0', ' ')))

        filtered_df.to_csv("raw_" + l.lower() + "_dataset.csv")

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


        dict_per_language[l] = filtered_df[filtered_df["code_syntax"] == CompileStatus.OK]

def filter_dataset(dict_per_language, size=200):
    common_tasks = functools.reduce(
        lambda x, y: x & y,  # set intersection
        [set(df['task_name']) for df in dict_per_language.values()]  # list of task_name sets for each language
    )

    filtered_dict = {}
    for l, df in dict_per_language.items():
        filtered_dict[l] = df[df['task_name'].isin(common_tasks)]
        filtered_dict[l] = filtered_dict[l].sort_values(by=['task_name']).head(size)
        filtered_dict[l].to_csv(os.path.join(STATS_DIR, "test_filtered_" + l + ".csv"))
    return filtered_dict

def main():
    ds = load_dataset("christopher/rosetta-code")
    train_df = ds.data['train'].to_pandas()
    # common_tasks = functools.reduce(
    #     lambda x, y: x & y,  # set intersection
    #     [set(df['task_name']) for df in filtered_dfs.values()]  # list of task_name sets for each language
    # )

    lang_dfs = {}
    for l in LANGUAGES_TO_KEEP:
        lang_df = train_df[train_df['language_name'] == l]
        lang_dfs[l] = lang_df

    # common_tasks = functools.reduce(
    #     lambda x, y: x & y,  # set intersection
    #     [set(df['task_name']) for df in filtered_dfs.values()]  # list of task_name sets for each language
    # )

    dict_per_language = {}
    for l, filtered_df in lang_dfs.items():
        # Keep only the rows where the task_name is in the common tasks
        #filtered_df = filtered_df[filtered_df['task_name'].isin(common_tasks)]

        #filtered_df['code'] = filtered_df['code'].apply(lambda x: x.replace('\u00A0', ' '))

        # Compute code length and line count
        filtered_df['code_length'] = filtered_df['code'].str.len()
        filtered_df['line_count'] = filtered_df['code'].apply(lambda x: len(x.split('\n')))
        if l == "Python":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: check_syntax_string(x.replace('\u00A0', ' ')))
        if l == "Java":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_java_code(x.replace('\u00A0', ' ')))
        if l == "Kotlin":
            filtered_df["code_syntax"] = filtered_df["code"].apply(lambda x: validate_kotlin_code(x.replace('\u00A0', ' ')))

        # Sort the dataframe by line count
        dict_per_language[l] = filtered_df.sort_values(by=['task_name'], ascending=False).head(200)

        # Save the filtered dataframe to a CSV file
        filtered_df.to_csv(os.path.join(STATS_DIR, "test_filtered_" + l + ".csv"))

    #model = Model.GPT_3_5_turbo
    #generate_tests(model, dict_per_language[LanguageEnum.Java.name], LanguageEnum.Java)
    #df = read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/filtered_Python_stats_davinci_002.csv")
    #run_analysis(LanguageEnum.Python, df, "davinci_002")


def generate_tests(model, df, lang):
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
    elif model == Model.GEMINI_1_5_flash:
        model_string = "gemini_1_5_flash"
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
        if len(code) > 20000:
            print("TOO BIG, SKIPPING: ", task_name)
            generated_codes.append(None)
            filenames.append(None)

        generated = generate(task_name, code, lang)
        if generated is None:
            print("could not parse, skipping")
            generated_codes.append(None)
            filenames.append(None)
        else:
            # replace non parsable space character
            parsed_code = generated.replace('\u00A0', ' ')
            generated_codes.append(parsed_code)

            save_content(task_name, code.replace('\u00A0', ' '), lang.value)
            filename = save_generated_test(task_name, model_string, parsed_code, lang.value)

            filenames.append(filename)

    df["generated_code"] = generated_codes
    df["file_path"] = filenames
    df.to_csv(os.path.join(STATS_DIR, "filtered_{}_stats_{}.csv".format(lang.name, model_string)))
    return df
    # df = read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/test_filtered_Python_stats_gpt_3_5_turbo.csv")
    #run_analysis(df, model_string)
    #run_analysis_python(df, model_string)

def run_analysis(lang, df, model_string):
    if lang == LanguageEnum.Python:
        return run_analysis_python(df, model_string)
    else:
        print("Analysis for language " + lang.name + " is not yet supported")


def run_analysis_java(df, model_string):
    correct_syntax = validate_java_code()
    java_file = ""
    errors = run_checkstyle(java_file)
    NotImplemented()


def run_analysis_python(df, model_string):
    compilation_statuses = []
    line_coverages = []
    branch_coverages = []
    pylint_error_count = []
    pylint_findings = []
    pass_percentages = []

    for index, row in df.copy(deep=True).iterrows():
        filename = row['file_path']
        print("Filename: ", filename)

        if isinstance(filename, float):
            pylint_findings.append(None)
            pylint_error_count.append(None)
            branch_coverages.append(None)
            line_coverages.append(None)
            pass_percentages.append(None)
            compilation_statuses.append(None)
            continue

        print("Filename: ", filename)
        compilation_status = check_syntax_file(filename)
        compilation_statuses.append(compilation_status)

        file = Path(filename).name.replace("test_" + model_string + "_", "")
        print(file)

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
        pass_percentages.append(None if pass_percentage is None else pass_percentage["pass_percentage"])
    df["compilation_statuses"] = compilation_statuses
    df["line_coverages"] = line_coverages
    df["branch_coverages"] = branch_coverages
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

#df = read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/filtered_Python_stats_davinci_002.csv")
#run_analysis(LanguageEnum.Python, df, "davinci_002")
#main()

if __name__ == '__main__':
    # df = read_csv(
    #     "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/stats/filtered_Python_stats_deepseek_coder.csv")
    #
    # run_analysis(LanguageEnum.Python, df, "deepseek_coder'")



    #dataset = download_and_validate_dataset()
    # or
    languages = []
    dataset = {
        "Python": read_csv("raw_python_dataset.csv"),
        "Kotlin": read_csv("raw_kotlin_dataset.csv"),
        "Java": read_csv("raw_java_dataset.csv"),
        "Go": read_csv("raw_go_dataset.csv")
    }

    dataset_2 = {}
    for l in ["Python", "Java", "Kotlin", "Go"]:
        t = dataset[l].copy(deep = True)
        t.info()
        dataset_2[l] = t.loc[(t["code_syntax"] == "CompileStatus.OK") | (t["code_syntax"] == True)]

    filtered = filter_dataset(dataset_2)

    ALL = False
    if ALL:
        for l in languages:
            for m in Model:
                generate_tests(m, filtered[l.name], l)
    else:
        language = LanguageEnum.Python
        model = Model.GPT_4o
        #generated_df = generate_tests(model, filtered[language.name], language)
        generated_df = read_csv("data/generated/stats/filtered_Python_stats_gpt_4o_2024_08_06.csv")
        run_analysis_python(generated_df, "gpt_4o_2024_08_06")
