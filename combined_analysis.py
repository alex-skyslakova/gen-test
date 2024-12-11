import pandas as pd
import os


def combine_files_with_attributes(file_paths, attributes, output_file):
    """
    Combines multiple files into one, adding columns for the original file name and a specified attribute.

    Args:
        file_paths (list): List of file paths to combine.
        attributes (list): List of attributes corresponding to each file.
        output_file (str): Path to the output file.

    Returns:
        None: Saves the combined file as a CSV.
    """
    if len(file_paths) != len(attributes):
        raise ValueError("The number of files and attributes must match.")

    combined_data = []

    for file_path, attribute in zip(file_paths, attributes):
        # Load each file
        if not os.path.exists(file_path):
            print(f"File {file_path} not found. Skipping.")
            continue

        data = pd.read_csv(file_path)

        # Add the original file name column
        data['original_stats_file'] = os.path.basename(file_path)

        # Add the attribute column
        data['llm_model'] = attribute

        combined_data.append(data)

    # Concatenate all dataframes
    combined_df = pd.concat(combined_data, ignore_index=True)

    # Save to the output file
    combined_df.to_csv(output_file, index=False)
    print(f"Combined data saved to {output_file}.")


# combine_files_with_attributes(['data/generated/docs_stats/filtered_Python_stats_gpt_4o_2024_08_06.csv', 'data/generated/docs_stats/filtered_Python_stats_gemini_1_5_pro_002.csv', 'data/generated/docs_stats/filtered_Python_stats_deepseek_coder.csv',
#                'data/generated/docs_stats/filtered_Java_stats_gpt_4o_2024_08_06.csv', 'data/generated/docs_stats/filtered_Java_stats_gemini_1_5_pro_002.csv', 'data/generated/docs_stats/filtered_Java_stats_deepseek_coder.csv'
# ], ['gpt', 'gemini', 'deepseek', 'gpt', 'gemini', 'deepseek'], "test123.csv")