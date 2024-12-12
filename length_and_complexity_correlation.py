import os
import tempfile

import pandas as pd
from scipy.stats import mannwhitneyu, spearmanr

from config import Config
from python_assertion_ratios import compute_complexity


def analyze_correlations():
    gpt = pd.read_csv(os.path.join(Config.get_stats_input_dir(), "filtered_Python_stats_gpt_4o_2024_08_06.csv"))
    gemini = pd.read_csv(os.path.join(Config.get_stats_input_dir(), "filtered_Python_stats_gemini_1_5_pro_002.csv"))
    deepseek = pd.read_csv(os.path.join(Config.get_stats_input_dir(), "filtered_Python_stats_deepseek_coder.csv"))
    combined_stats = pd.read_csv(os.path.join(Config.get_stats_input_dir(), "combined_stats.csv"))

    models_data = {"gpt_4o_2024_08_06": gpt, "gemini_1_5_pro_002": gemini, "deepseek_coder": deepseek}
    for model, model_data in models_data.items():
        print("Python, " + model + ":")
        model_data['passed_compilation_and_runtime'] = (model_data['compilation_status'] == 'CompileStatus.OK') & (
                model_data['runtime_errors_count'] == 0)

        executable_code_lengths = model_data[model_data['passed_compilation_and_runtime'] == True]["code_length"]
        nonexecutable_code_lengths = model_data[model_data['passed_compilation_and_runtime'] == False]["code_length"]
        stat, p_value = mannwhitneyu(executable_code_lengths, nonexecutable_code_lengths, alternative='two-sided')
        print("Mann-Whitney U statistic:", stat)
        print("p-value:", p_value)

        if p_value < 0.05:
            print("Statistically significant difference between True and False groups.")
        else:
            print("No statistically significant difference between True and False groups.")

        # Perform inner join on 'task_name' and 'language_name'
        merged_df = pd.merge(model_data[["task_name", "language_name", "line_count", "code"]], combined_stats, on=["task_name", "language_name"], how="inner")

        # Filter rows where 'llm_model' == llm_model_value
        filtered_df = merged_df[merged_df["llm_model"] == model]

        # Select the specified columns
        columns_to_select = [
            "line_count",
            "code",
            "compilation_status_score",
            "runtime_errors_score",
            "execution_time_score",
            "line_coverage_score",
            "branch_coverage_score",
            "assertions_mccabe_ratio_score",
            "assertions_density_score",
            "warnings_count_score"
        ]

        result_df = filtered_df[columns_to_select]

        score_columns = [col for col in columns_to_select if col not in ["line_count", "code"]]
        total_score = result_df[score_columns].sum(axis=1)

        # Compute Spearman correlation
        corr, p_value = spearmanr(result_df["line_count"], total_score)

        print("Spearman correlation:", corr)
        print("P-value:", p_value)

        if p_value < 0.05:
            print("There is a statistically significant monotonic relationship.")
        else:
            print("No statistically significant monotonic relationship.")

        complexity = result_df["code"].apply(get_complexity_from_code)

        print()
        print()
        print("Python, " + model + ", relationship between complexity and total score:")
        corr, p_value = spearmanr(complexity, total_score)
        print("Spearman correlation:", corr)
        print("P-value:", p_value)
        if p_value < 0.05:
            print("There is a statistically significant monotonic relationship.")
        else:
            print("No statistically significant monotonic relationship.")
        print()
        print()


def get_complexity_from_code(code_snippet: str) -> float:
    # Create a named temporary file
    with tempfile.NamedTemporaryFile(mode='w', suffix=".py", delete=False) as tmp_file:
        # Write the code snippet into the temp file
        tmp_file.write(code_snippet)
        temp_file_path = tmp_file.name

    try:
        # Compute the complexity using the provided function
        complexity = compute_complexity(temp_file_path)
    finally:
        # Remove the temporary file after computation
        os.remove(temp_file_path)

    return complexity
