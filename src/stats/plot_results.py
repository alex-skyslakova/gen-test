import math

import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import os
import re

from src.stats.go_code_quality import analyze_code_quality_go
from src.config import Config
from src.stats.java_code_quality import analyze_code_quality_java
from src.stats.kotlin_code_quality import analyze_code_quality_kotlin
from src.stats.python_code_quality import analyze_code_quality_python

input_files = [
    'data/generated/docs_stats/filtered_Python_stats_gpt_4o_2024_08_06.csv',
    'data/generated/docs_stats/filtered_Python_stats_gemini_1_5_pro_002.csv',
    'data/generated/docs_stats/filtered_Python_stats_deepseek_coder.csv',
    'data/generated/docs_stats/filtered_Java_stats_gpt_4o_2024_08_06.csv',
    os.path.join(Config.get_stats_output_dir(), 'filtered_Java_stats_gemini_1_5_pro_002.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Java_stats_deepseek_coder.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Kotlin_stats_gpt_4o_2024_08_06.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Kotlin_stats_gemini_1_5_pro_002.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Kotlin_stats_deepseek_coder.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Go_stats_gpt_4o_2024_08_06.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Go_stats_gemini_1_5_pro_002.csv'),
    os.path.join(Config.get_stats_output_dir(), 'filtered_Go_stats_deepseek_coder.csv'),
]

# Required columns
columns_to_select = [
     'language_name', 'correct_response_score', 'compilation_status_score',
    'runtime_errors_score', 'line_coverage_score', 'branch_coverage_score',
    'execution_time_score', 'assertions_mccabe_ratio_score',
    'assertions_density_score', 'warnings_count_score'
]

group_columns = ["language_name", "llm_model"]  # Columns to group by

def plot_all_metrics_heatmap():
    combined_df = pd.read_csv(os.path.join(Config.get_stats_output_dir(), "combined_stats.csv"))

    first_three_columns = ['correct_response_score', 'compilation_status_score',
        'runtime_errors_score']
    rest_columns = ['line_coverage_score', 'branch_coverage_score',
        'execution_time_score', 'assertions_mccabe_ratio_score',
        'assertions_density_score', 'warnings_count_score']

    # Compute passed_compilation_and_runtime condition
    combined_df['passed_compilation_and_runtime'] = (
        (combined_df['compilation_status_score'] == 1) &
        (combined_df['runtime_errors_score'] == 1)
    )

    grouped_first_three = combined_df.groupby(group_columns)[first_three_columns]
    first_three_means = grouped_first_three.mean()
    first_three_medians = grouped_first_three.median()

    # Filter the subset where passed_compilation_and_runtime is True
    filtered_df = combined_df[combined_df['passed_compilation_and_runtime'] == True]

    # Compute statistics for the rest of the columns from the filtered subset
    grouped_rest = filtered_df.groupby(group_columns)[rest_columns]
    rest_means = grouped_rest.mean()
    rest_medians = grouped_rest.median()

    mean_results = pd.concat([first_three_means, rest_means], axis=1)
    mean_results['total_score'] = mean_results.sum(axis=1)
    median_results = pd.concat([first_three_medians, rest_medians], axis=1)
    median_results['total_score'] = median_results.sum(axis=1)

    mask_mean = np.zeros(mean_results.shape, dtype=bool)
    mask_mean[:, mean_results.columns.get_loc('total_score')] = True

    mask_median = np.zeros(median_results.shape, dtype=bool)
    mask_median[:, median_results.columns.get_loc('total_score')] = True

    # Plot the heatmap for medians
    plt.figure(figsize=(22, 20))
    ax = sns.heatmap(median_results, annot=True, cmap="YlOrBr", cbar=True, fmt=".2f", annot_kws={"size": 25}, mask=mask_median)
    plt.title("Heatmap of Medians by LLM-Language", fontsize=30)
    plt.ylabel("LLM-Language Tuple", fontsize=30)
    plt.xlabel("Metrics", fontsize=30)
    ax.tick_params(axis='x', labelsize=25)
    ax.tick_params(axis='y', labelsize=25)

    for i in range(median_results.shape[0]):  # Loop through rows
        for j in range(median_results.shape[1]):  # Loop through columns
            if mask_median[i, j]:  # Check if the cell is masked
                value = median_results.iloc[i, j]  # Get the value from the original DataFrame
                ax.text(j + 0.5, i + 0.5, f"{value:.3f}",  # Write the value
                        ha="center", va="center", color="black", fontsize=25)

    plt.tight_layout()
    plt.savefig("data/plots/all_metrics_median.png")
    #plt.show()

    #Plot the mean heatmap
    plt.figure(figsize=(22, 20))
    ax = sns.heatmap(mean_results, annot=True, cmap="YlOrBr", cbar=True, fmt=".2f", annot_kws={"size": 25}, mask=mask_mean)
    plt.title("Heatmap of Means by LLM-Language", fontsize=25)
    plt.ylabel("LLM-Language Tuple", fontsize=30)
    plt.xlabel("Metrics", fontsize=30)
    ax.tick_params(axis='x', labelsize=25)
    ax.tick_params(axis='y', labelsize=25)

    for i in range(mean_results.shape[0]):  # Loop through rows
        for j in range(mean_results.shape[1]):  # Loop through columns
            if mask_mean[i, j]:  # Check if the cell is masked
                value = mean_results.iloc[i, j]  # Get the value from the original DataFrame
                ax.text(j + 0.5, i + 0.5, f"{value:.3f}",  # Write the value
                        ha="center", va="center", color="black", fontsize=25)

    plt.tight_layout()
    plt.savefig(os.path.join(Config.get_plots_dir(), "all_metrics_mean.png"))


def analyze_and_plot_with_model(input_files):
    analysis_summary = pd.DataFrame(columns=["Language-LLM_Model",  "Faulty Code In Response", "Could Not Generate Test",
                                             "Passed Compilation and Runtime",
                                             "Failed on Runtime", "Failed on Compilation/Syntax"])

    for file in input_files:
        llm_model_match = re.search(r"stats_(\w+)", os.path.basename(file))
        llm_model = llm_model_match.group(1) if llm_model_match else "Unknown"

        df = pd.read_csv(file)

        # Ensure required columns exist
        if 'language_name' not in df.columns or 'compilation_status' not in df.columns or 'runtime_errors_count' not in df.columns:
            print(f"Required columns missing in file: {file}")
            continue

        # Create the combination column for graph labels
        language_name = df['language_name'].iloc[0] if not df['language_name'].isnull().all() else "Unknown"
        column_label = f"{language_name} {llm_model}"

        df['failed_on_runtime'] = (df['compilation_status'] == 'CompileStatus.OK') & (
                    (df['runtime_errors_count'] > 0) | (df['runtime_errors_count'].isna()))
        df['passed_compilation_and_runtime'] = (df['compilation_status'] == 'CompileStatus.OK') & (
                df['runtime_errors_count'] == 0)

        could_not_generate_test = (df['generated_code'] == 'none').sum()
        failed_to_parse_response = (df['generated_code'] == 'error').sum()

        passed_comp_and_runtime = df['passed_compilation_and_runtime'].sum()
        failed_on_runtime = df['failed_on_runtime'].sum()
        compilation_error_or_exception = (
                (df['compilation_status'] == 'CompileStatus.SYNTAX_ERROR') |
                (df['compilation_status'] == 'CompileStatus.EXCEPTION_OCCURRED')
        ).sum()

        row_data = pd.DataFrame([{
            "Language-LLM_Model": column_label,
            "Faulty Code In Response": failed_to_parse_response,
            "Could Not Generate Test": could_not_generate_test,
            "Passed Compilation and Runtime": passed_comp_and_runtime,
            "Failed on Runtime": failed_on_runtime,
            "Failed on Compilation/Syntax": compilation_error_or_exception
        }])

        # Concatenate the new row with the summary DataFrame
        analysis_summary = pd.concat([analysis_summary, row_data], ignore_index=True)

    custom_colors = {
        "Faulty Code In Response": "purple",
        "Could Not Generate Test": "grey",
        "Passed Compilation and Runtime": "green",
        "Failed on Runtime": "orange",
        "Failed on Compilation/Syntax": "red"
    }

    analysis_summary.set_index("Language-LLM_Model", inplace=True)
    ax = analysis_summary.plot(
        kind='bar',
        stacked=True,
        figsize=(12, 8),
        title="Compilation and Runtime Analysis by Language and LLM Model",
        color=[custom_colors[col] for col in analysis_summary.columns]
    )

    for bar_group in ax.containers:
        labels = [f"{int(v)}" if v > 0 else "" for v in bar_group.datavalues]
        ax.bar_label(bar_group, labels=labels, label_type='center', fontsize=12, color='black', weight='bold')

    analysis_summary.to_csv("data/plots/compilation_results.csv")

    plt.ylabel("Count")
    plt.xlabel("Language-LLM Model")
    plt.xticks(rotation=45, ha='right')
    plt.legend(loc="upper right")
    plt.tight_layout()
    plt.savefig(os.path.join(Config.get_plots_dir(), "compilation_and_runtime.png"))

    return analysis_summary


def analyze_timeout(files):
    timeout_results = []
    exec_time_results = []

    for file in files:
        print(file)
        llm_model_match = re.search(r"stats_(\w+)", os.path.basename(file))
        llm_model = llm_model_match.group(1) if llm_model_match else "Unknown"

        df = pd.read_csv(file)

        if 'language_name' not in df.columns or 'compilation_status' not in df.columns or 'runtime_errors_count' not in df.columns:
            print(f"Required columns missing in file: {file}")
            continue

        language_name = df['language_name'].iloc[0] if not df['language_name'].isnull().all() else "Unknown"
        column_label = f"{language_name} {llm_model}"

        timeout_count = df['timeout_occurred'].sum()

        task_names = df.loc[df['timeout_occurred'] == True, 'task_name'].tolist()

        # Append the result as a dictionary to the timeout_results list
        timeout_results.append({
            "analysis": column_label,
            "path": file,
            "timeout_count": timeout_count,
            "task_names": task_names
        })

        df['passed_compilation_and_runtime'] = (df['compilation_status'] == 'CompileStatus.OK') & (
                df['runtime_errors_count'] == 0)

        df = df[df['passed_compilation_and_runtime'] == True]
        mean = df["execution_time_sec"].mean()
        exec_time_results.append({
            "analysis": column_label,
            "execution_time_sec": round(mean, 2) if (mean is not None and mean != math.nan) else mean
        })

    # Convert the timeout_results list to a DataFrame
    summary_df = pd.DataFrame(timeout_results)
    print(summary_df)

    exec_time_df = pd.DataFrame(exec_time_results)

    plt.figure(figsize=(10, 6))
    bars = plt.bar(summary_df['analysis'], summary_df['timeout_count'], color='skyblue')

    plt.title("Timeout Occurrences", fontsize=16)
    plt.xlabel("Language-LLM", fontsize=12)
    plt.ylabel("Timeout Count", fontsize=12)
    plt.xticks(rotation=45, ha="right")  # Rotate x-axis labels for better readability

    for bar in bars:
        height = bar.get_height()
        plt.text(
            bar.get_x() + bar.get_width() / 2,  # X position
            height,  # Y position
            f'{int(height)}',  # Text to display
            ha='center', va='bottom', fontsize=12  # Center align the text
        )

    plt.tight_layout()
    plt.savefig(os.path.join(Config.get_plots_dir(), "timeout.png"))

    plt.figure(figsize=(10, 6))
    bars = plt.bar(exec_time_df['analysis'], exec_time_df['execution_time_sec'], color='green')

    # Add labels and title
    plt.title("Mean Execution Time", fontsize=16)
    plt.xlabel("Language-LLM", fontsize=12)
    plt.ylabel("Average Execution Time (s)", fontsize=12)
    plt.xticks(rotation=45, ha="right")  # Rotate x-axis labels for better readability

    # Add rounded float values above each bar
    for bar in bars:
        height = bar.get_height()
        plt.text(
            bar.get_x() + bar.get_width() / 2,  # X position
            height,  # Y position
            f'{height:.2f}',  # Rounded float to display
            ha='center', va='bottom', fontsize=12  # Center align the text
        )

    plt.tight_layout()
    plt.savefig(os.path.join(Config.get_plots_dir(), "time_exec_mean.png"))


def plot_coverage(files):
    columns_to_select = ['language_name', 'llm_model', 'line_coverage_score', 'branch_coverage_score',
                         'assertions_density_score', 'assertions_mccabe_ratio_score']

    group_columns = ["language_name", "llm_model"]  # Columns to group by

    # Combine data from all files
    combined_df = pd.DataFrame()

    for file in files:
        try:
            df = pd.read_csv(file)
            print(f"Processing {file}...")

            if not all(col in df.columns for col in columns_to_select):
                print(f"Skipping file {file}: Required columns not found.")
                continue

            df['passed_compilation_and_runtime'] = (df['compilation_status'] == 'CompileStatus.OK') & (
                        df['runtime_errors_count'] == 0)
            df = df[df['passed_compilation_and_runtime'] == True]
            df = df[columns_to_select]

            combined_df = pd.concat([combined_df, df], ignore_index=True)
        except Exception as e:
            print(f"Error processing file {file}: {e}")

    if combined_df.empty:
        print("No valid data found in input files.")
    else:

        grouped = combined_df.groupby(group_columns)
        grouped_medians = grouped.median()
        grouped_medians['count of values'] = grouped.size()

        grouped_means = grouped.mean()
        grouped_means['count of values'] = grouped.size()

        mask = np.zeros(grouped_medians.shape, dtype=bool)
        mask[:, grouped_medians.columns.get_loc('count of values')] = True

        # Plot the median heatmap
        plt.figure(figsize=(14, 18))
        ax = sns.heatmap(grouped_medians, annot=True, cmap="YlOrBr", cbar=True, fmt=".2f", annot_kws={"size": 25}, vmin=0, vmax=1, mask=mask)

        # Manually annotate masked cells
        for i in range(grouped_medians.shape[0]):  # Loop through rows
            for j in range(grouped_medians.shape[1]):  # Loop through columns
                if mask[i, j]:  # Check if the cell is masked
                    value = grouped_medians.iloc[i, j]  # Get the value from the original DataFrame
                    ax.text(j + 0.5, i + 0.5, f"{value:.1f}",  # Write the value
                            ha="center", va="center", color="black", fontsize=25)

        ax.tick_params(axis='x', labelsize=18)
        ax.tick_params(axis='y', labelsize=18)
        plt.title("Heatmap of Coverage Metric Score Medians by LLM-Language", fontsize=20)
        plt.ylabel("LLM-Language", fontsize=20)
        plt.xlabel("Metrics", fontsize=20)
        plt.tight_layout()
        plt.savefig(os.path.join(Config.get_plots_dir(), "coverage_median_heatmap.png"))

        mask = np.zeros(grouped_means.shape, dtype=bool)
        mask[:, grouped_means.columns.get_loc('count of values')] = True

        plt.figure(figsize=(14, 18))
        ax = sns.heatmap(grouped_means, annot=True, cbar=True, fmt=".2f", annot_kws={"size": 25}, vmin=0, vmax=1, mask=mask, cmap="YlOrBr")

        # Manually annotate masked cells
        for i in range(grouped_means.shape[0]):  # Loop through rows
            for j in range(grouped_means.shape[1]):  # Loop through columns
                if mask[i, j]:  # Check if the cell is masked
                    value = grouped_means.iloc[i, j]  # Get the value from the original DataFrame
                    ax.text(j + 0.5, i + 0.5, f"{value:.1f}",  # Write the value
                            ha="center", va="center", color="black", fontsize=25)

        ax.tick_params(axis='x', labelsize=18)
        ax.tick_params(axis='y', labelsize=18)
        plt.title("Heatmap of Coverage Metric Score Means by LLM-Language", fontsize=20)
        plt.ylabel("LLM-Language", fontsize=20)
        plt.xlabel("Metrics", fontsize=20)
        plt.tight_layout()
        plt.savefig(os.path.join(Config.get_plots_dir(), "coverage_mean_heatmap.png"))


def quality_analysis(input_files):
    python_files = [file for file in input_files if "filtered_Python_stats_" in file]
    java_files = [file for file in input_files if "filtered_Java_stats_" in file]
    kotlin_files = [file for file in input_files if "filtered_Kotlin_stats_" in file]
    go_files = [file for file in input_files if "filtered_Go_stats_" in file]
    analyze_code_quality_python(python_files)
    analyze_code_quality_java(java_files)
    analyze_code_quality_kotlin(kotlin_files)
    analyze_code_quality_go(go_files)


def present_results_as_plots():  # TODO add choice between DP stats and own stats
    plot_all_metrics_heatmap()
    analyze_and_plot_with_model(input_files)
    analyze_timeout(input_files)
    plot_coverage([os.path.join(Config.get_stats_output_dir(), "combined_stats.csv")])
    quality_analysis(input_files)
