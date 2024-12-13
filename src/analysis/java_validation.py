from src.helpers import convert_to_java_filename
from src.analysis.python_validation import CompileStatus
import subprocess
import os

def validate_java_code(java_code):
    try:
        name = convert_to_java_filename("Temp.java", "", data=java_code)
        temp_file_path = os.path.join("/data/javaSetup/src/main/java/org/example/package", name)
        with open(temp_file_path, "w") as temp_file:
            temp_file.write(java_code)

        # Run javac to check for syntax errors
        result = subprocess.run(['javac', temp_file_path], capture_output=True, text=True)

        # Check the return code to determine if there was a syntax error
        if result.returncode == 0:
            # No syntax errors
            return CompileStatus.OK
        else:
            # Syntax errors occurred
            print(f"Syntax Error:\n{result.stderr}")
            return CompileStatus.SYNTAX_ERROR

    except Exception as e:
        # Catch any other unexpected exceptions
        print(f"Exception occurred: {e}")
        return CompileStatus.EXCEPTION_OCCURRED

#
# #Iterate through each subdirectory and validate Java files\
# import os
# import pandas as pd
# # Path to the single CSV file
# from datasets import load_dataset
# ds = load_dataset("christopher/rosetta-code")
# train_df = ds.data['train'].to_pandas()
# lang_df = train_df[train_df['language_name'] == "Java"]
#
#
# # Initialize failure counter
# failed = 0
#
# # Load the CSV file
# try:
#     df = lang_df
#
#     # Check if the 'code' column exists
#     if 'code' in df.columns:
#         for code in df['code']:
#             # Validate each code snippet in the 'code' column
#             status = validate_java_code(code.replace('\u00A0', ' '))
#             if status != CompileStatus.OK:
#                 failed += 1
#             print(f"------Code snippet validation status: {status}")
#     else:
#         print("Error: 'code' column not found in the CSV file.")
#
# except Exception as e:
#     print(f"Error loading {csv_file_path}: {e}")
#
# # Print total failed validations
# print(f"Total failed validations: {failed}")
#
# def validate_java_code(java_code):
#     try:
#         name = convert_to_java_filename("Temp.java", "", data=java_code)
#         temp_file_path = os.path.join(
#             "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/javaSetup/src/main/java/org/example/package",
#             name
#         )
#         temp_test_path
#         with open(temp_file_path, "w") as temp_file:
#             temp_file.write(java_code)
#
#         # Change directory to your project's root
#         project_root = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/javaSetup"
#
#         # Run Maven compile
#         result = subprocess.run(
#             ['mvn', 'compile', '-f', os.path.join(project_root, 'pom.xml')],
#             cwd=project_root,
#             capture_output=True,
#             text=True
#         )
#
#         if result.returncode == 0:
#             return CompileStatus.OK
#         else:
#             print(f"Syntax Error:\n{result.stdout}\n{result.stderr}")
#             return CompileStatus.SYNTAX_ERROR
#
#     except Exception as e:
#         print(f"Exception occurred: {e}")
#         return CompileStatus.EXCEPTION_OCCURRED