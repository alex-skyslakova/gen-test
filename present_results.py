import re

import pandas as pd

from python_validation import CompileStatus


import pandas as pd
import matplotlib.pyplot as plt
import os
def analyze_csv_and_plot_columns(file_paths):
    """
    Analyze the 'generated_code' column in multiple CSV files and create a grouped bar chart.

    Parameters:
        file_paths (list): List of paths to CSV files.

    Creates:
        A grouped bar chart showing counts of generated tests, "None" values, and errors for each file.
    """
    # Data storage for plotting
    file_names = []
    generated_counts = []
    none_counts = []
    error_counts = []

    for file_path in file_paths:
        try:
            # Load the CSV
            df = pd.read_csv(file_path, na_values=[""])

            # Check if 'generated_code' column exists
            if 'generated_code' not in df.columns:
                print(f"'generated_code' column not found in {file_path}")
                continue

            # Extract the column
            column = df['generated_code'].astype(str)

            # Count null/None/NaN
            error_count = (column == "error").sum()

            # Count string "None"
            none_count = (column == "none").sum()

            # Count all other values
            other_values_count = len(column) - (none_count + error_count)

            # Append data for the plot
            file_names.append(os.path.basename(file_path))
            generated_counts.append(other_values_count)
            none_counts.append(none_count)
            error_counts.append(error_count)

            # Print the counts for each file
            print(f"File: {os.path.basename(file_path)}")
            print(f"Correctly generated tests: {other_values_count}")
            print(f'"None" value - LLM could not generate test: {none_count}')
            print(f"Null values - error during request or parsing occurred: {error_count}")
            print("-" * 40)

        except Exception as e:
            print(f"Error processing file {file_path}: {e}")

    # Plotting
    if file_names:
        x = range(len(file_names))
        bar_width = 0.25

        fig, ax = plt.subplots(figsize=(12, 6))

        # Grouped bar chart
        ax.bar(
            [i - bar_width for i in x],
            generated_counts,
            width=bar_width,
            label="Generated Tests",
            color="#4CAF50",
        )
        ax.bar(
            x,
            none_counts,
            width=bar_width,
            label="Could Not Generate (None)",
            color="#FFC107",
        )
        ax.bar(
            [i + bar_width for i in x],
            error_counts,
            width=bar_width,
            label="Failed to Extract Test (Errors)",
            color="#F44336",
        )

        # Chart labels and formatting
        ax.set_xticks(x)
        ax.set_xticklabels(file_names, rotation=45, ha="right")
        ax.set_ylabel("Number of Tests")
        ax.set_title("Test Generation Analysis by File")
        ax.legend()

        plt.tight_layout()
        plt.show()
    else:
        print("No valid files processed.")

# Example usage with a list of file paths
# analyze_csv_and_plot_columns(["file1.csv", "file2.csv", "file3.csv"])  # Uncomment and replace with actual file paths.

# Example usage with a list of file paths


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


# # Example usage
# directory_path = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats"
# search_word = "_stats_"
# csv_files = find_csv_files_with_word(directory_path, search_word)
#

input_files = [
    'data/generated/docs_stats/filtered_Python_stats_gpt_4o_2024_08_06.csv',
    'data/generated/docs_stats/filtered_Python_stats_gemini_1_5_pro_002.csv',
    'data/generated/docs_stats/filtered_Python_stats_deepseek_coder.csv',
    'data/generated/docs_stats/filtered_Java_stats_gpt_4o_2024_08_06.csv',
    'data/generated/docs_stats/filtered_Java_stats_gemini_1_5_pro_002.csv',
    'data/generated/docs_stats/filtered_Java_stats_deepseek_coder.csv',
    'data/generated/docs_stats/filtered_Kotlin_stats_gpt_4o_2024_08_06.csv',
    'data/generated/docs_stats/filtered_Kotlin_stats_gemini_1_5_pro_002.csv',
    'data/generated/docs_stats/filtered_Kotlin_stats_deepseek_coder.csv',
    'data/generated/docs_stats/filtered_Go_stats_gpt_4o_2024_08_06.csv',
    'data/generated/docs_stats/filtered_Go_stats_gemini_1_5_pro_002.csv',
    'data/generated/docs_stats/filtered_Go_stats_deepseek_coder.csv',
]



def extract_llm_model(path):
    llm_model_match = re.search(r"stats_(\w+)", os.path.basename(path))
    return llm_model_match.group(1) if llm_model_match else "Unknown"



def compute_metric_scores(dfs):
    # Create a copy of the dataframe to work on

    df_scores = pd.concat(dfs, ignore_index=True)

    df_scores = df_scores.drop(columns=[
        'compilation_status_score',
        'runtime_errors_score', 'execution_time_score', 'line_coverage_score', 'branch_coverage_score',
        'execution_time_score', 'assertions_mccabe_ratio_score',
        'assertions_density_score', 'warnings_count_score'
    ])

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
#
# dfs = []
# for path in input_files:
#     df = pd.read_csv(path)
#     df["stats_file_path"] = path
#     dfs.append(df)
#

# df_scores = compute_metric_scores(dfs)
# print(df_scores)
# df_scores.to_csv("./data/generated/docs_stats/combined_stats.csv", index=False)

# df = pd.read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Java_stats_gpt_4o_2024_08_06.csv")
# df = df.iloc[:, : 14]
# df.to_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_stats/filtered_Java_stats_gpt_4o_2024_08_06.csv", index=False)
