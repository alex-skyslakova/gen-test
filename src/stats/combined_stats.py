import glob
import re
from src.config import Config
from src.analysis.python_validation import CompileStatus
import pandas as pd
import os


def find_csv_files_with_word(directory, word):
    """
    Finds all CSV files in a given directory that contain a specific word in their file name.

    Parameters:
        directory (str): Path to the directory to search.
        word (str): Word to look for in the file names.

    Returns:
        list: List of matching CSV file paths.
    """
    matching_files = []
    try:
        # Iterate through all files in the directory
        for root, _, files in os.walk(directory):
            for file in files:
                # Check if the file is a CSV and contains the word
                if file.endswith(".csv") and word.lower() in file.lower():
                    matching_files.append(os.path.join(root, file))
    except Exception as e:
        print(f"Error while searching for files: {e}")

    return matching_files
#
# input_files = [
#     'filtered_Python_stats_gpt_4o_2024_08_06.csv',
#     'filtered_Python_stats_gemini_1_5_pro_002.csv',
#     'filtered_Python_stats_deepseek_coder.csv',
#     'filtered_Java_stats_gpt_4o_2024_08_06.csv',
#     'filtered_Java_stats_gemini_1_5_pro_002.csv',
#     'filtered_Java_stats_deepseek_coder.csv',
#     'filtered_Kotlin_stats_gpt_4o_2024_08_06.csv',
#     'filtered_Kotlin_stats_gemini_1_5_pro_002.csv',
#     'filtered_Kotlin_stats_deepseek_coder.csv',
#     'filtered_Go_stats_gpt_4o_2024_08_06.csv',
#     'filtered_Go_stats_gemini_1_5_pro_002.csv',
#     'filtered_Go_stats_deepseek_coder.csv',
# ]


def extract_llm_model(path):
    llm_model_match = re.search(r"stats_(\w+)", os.path.basename(path))
    return llm_model_match.group(1) if llm_model_match else "Unknown"


def compute_metric_scores(dfs):
    # Create a copy of the dataframe to work on

    df_scores = pd.concat(dfs, ignore_index=True)

    df_scores['llm_model'] = df_scores['stats_file_path'].apply(extract_llm_model)

    df_scores['correct_response_score'] = df_scores['generated_code'].apply(lambda x: 0 if x == 'error' else 1)

    # M1: Syntactical correctness
    df_scores['compilation_status_score'] = df_scores['compilation_status'].apply(lambda x: 1 if x == 'CompileStatus.OK' or x == CompileStatus.OK else 0)

    # M2: Test is executable (no runtime errors)
    if 'runtime_errors_count' in df_scores.columns:
        df_scores['runtime_errors_score'] = df_scores['runtime_errors_count'].apply(lambda x: 1 if x == 0 else 0)
        df_scores['runtime_errors_score'] = df_scores['runtime_errors_score'].fillna(0)

    # M3: Time of execution (normalized inverse execution time)
    # Assuming execution time data is present in 'execution_time' column (if not, adjust accordingly)
    if 'execution_time_sec' in df_scores.columns:
        min_time = 0
        max_time = 30
        df_scores['execution_time_sec'] = df_scores['execution_time_sec'].clip(min_time, max_time)
        df_scores['execution_time_score'] = 1 - (df_scores['execution_time_sec'] - min_time) / (max_time - min_time)
        df_scores['execution_time_score'] = df_scores['execution_time_score'].fillna(0)
    else:
        df_scores['execution_time_score'] = None  # Placeholder if no execution_time column exists

    # M4: Coverage percentage
    df_scores['line_coverage_score'] = df_scores['line_coverage_percent'] / 100
    df_scores['line_coverage_score'] = df_scores['line_coverage_score'].fillna(0)

    df_scores['branch_coverage_score'] = df_scores['branch_coverage_percent'] / 100
    df_scores['branch_coverage_score'] = df_scores['branch_coverage_score'].fillna(0)


    # M5: Assertions-McCabe ratio (normalized)
    min_amr = df_scores['assertions_mccabe_ratio'].min()
    max_amr = df_scores['assertions_mccabe_ratio'].max()
    print("mccabe: ", min_amr, " ", max_amr)
    df_scores['assertions_mccabe_ratio_score'] = (df_scores['assertions_mccabe_ratio'] - min_amr) / (max_amr - min_amr)
    df_scores['assertions_mccabe_ratio_score'] = df_scores['assertions_mccabe_ratio_score'].fillna(0)

    # M6: Assertions Density (normalized)
    min_density = df_scores['assertions_density'].min()
    max_density = df_scores['assertions_density'].max()
    print("density: ", min_density, " ", max_density)
    df_scores['assertions_density_score'] = (df_scores['assertions_density'] - min_density) / (max_density - min_density)
    df_scores['assertions_density_score'] = df_scores['assertions_density_score'].fillna(0)

    # M7: Number of warnings (normalized)
    min_warnings = df_scores['warnings_count'].min()
    max_warnings = df_scores['warnings_count'].max()
    df_scores['warnings_count_score'] = 1 - (df_scores['warnings_count'] - min_warnings) / (max_warnings - min_warnings)
    df_scores['warnings_count_score'] = df_scores['warnings_count_score'].fillna(0)

    df_scores["exec_score_sum"] = df_scores["execution_time_score"] + df_scores["compilation_status_score"] + df_scores["runtime_errors_score"]
    df_scores["coverage_score_sum"] = df_scores['branch_coverage_score'] + df_scores['line_coverage_score'] + df_scores['assertions_mccabe_ratio_score'] + df_scores['assertions_density_score']
    df_scores["code_quality_score_sum"] = df_scores["warnings_count_score"]
    df_scores["score_total"] = df_scores["exec_score_sum"] + df_scores["coverage_score_sum"] + df_scores["code_quality_score_sum"]

    df_scores = df_scores[[
        'task_name', 'language_name', 'llm_model', 'compilation_status', 'runtime_errors_count', 'correct_response_score' ,'compilation_status_score',
        'runtime_errors_score', 'execution_time_sec', 'execution_time_score', 'line_coverage_score', 'branch_coverage_score', 'assertions_mccabe_ratio_score',
        'assertions_density_score', 'warnings_count_score', 'timeout_occurred',
    ]]
    return df_scores



def create_combined_score_stats():
    # Collect all CSV files from the given directory
    directory = Config.get_stats_input_dir()
    print(directory)
    all_files = glob.glob(os.path.join(directory, "*.csv"))

    dfs = []
    for path in all_files:
        if "_stats_" in os.path.basename(path):
            df = pd.read_csv(path)
            df["stats_file_path"] = path
            print(path)
            dfs.append(df)

    # Compute metric scores from the collected DataFrames
    df_scores = compute_metric_scores(dfs)

    # Save the combined stats to a CSV file in the same directory
    df_scores.to_csv(os.path.join(Config.get_stats_output_dir(), "combined_stats.csv"), index=False)
