import os
import shutil
import subprocess
import tempfile

from src.analysis.python_validation import CompileStatus

#
# def validate_go_code_with_build(go_code, go_file='temp.go'):
#     try:
#         with open(go_file, 'w') as f:
#             f.write(go_code)
#
#         # Run 'golang build' to validate syntax (this does not produce a binary)
#         return validate_go_file(go_file)
#     except Exception as e:
#         print("Exception occurred during validation: ", e)
#         return CompileStatus.EXCEPTION_OCCURRED
#
#
# def validate_go_file(path):
#     try:
#
#
#         # Run 'golang build' to validate syntax (this does not produce a binary)
#         result = subprocess.run(['go', 'build', path],
#                                 stdout=subprocess.PIPE, stderr=subprocess.PIPE)
#
#         if result.returncode == 0:
#             print("Go code is valid.")
#             return CompileStatus.OK
#         else:
#             print("Go code has syntax errors:")
#             print(result.stderr.decode())
#             return CompileStatus.SYNTAX_ERROR
#     except Exception as e:
#         print("Exception occurred during validation: ", e)
#         return CompileStatus.EXCEPTION_OCCURRED

def validate_go_code_with_build(go_code):
    try:
        """Create a temporary folder, save the code from string into temp dir, and run validation."""
        # Base directory for temporary files
        base_temp_dir = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/"
        os.makedirs(base_temp_dir, exist_ok=True)
        temp_dir = tempfile.mkdtemp(dir=base_temp_dir)

        # Save the current working directory
        original_cwd = os.getcwd()

        try:
            # Change to temporary directory
            os.chdir(temp_dir)

            # Write the code to a file in the temp directory
            go_file_name = 'temp.go'
            with open(go_file_name, 'w') as f:
                f.write(go_code)

            # Run validation on the temp directory
            return validate_go_directory(temp_dir)
        finally:
            # Change back to the original working directory
            os.chdir(original_cwd)
            # Clean up the temporary directory
            shutil.rmtree(temp_dir)
    except Exception as e:
        print("Exception occurred during validation:", e)
        return CompileStatus.EXCEPTION_OCCURRED


def validate_go_directory(temp_dir):
    try:
        # Run 'go build' in the temp directory to validate syntax
        result_build = subprocess.run(
            ['go', 'build'],
            cwd=temp_dir,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE
        )

        if result_build.returncode == 0:
            print("Go code is syntactically valid.")

            # Run 'go vet' in the temp directory to check for issues
            result_vet = subprocess.run(
                ['go', 'vet'],
                cwd=temp_dir,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE
            )

            if result_vet.returncode == 0:
                print("Go code passed 'go vet' checks.")
                return CompileStatus.OK
            else:
                print("Go code has 'go vet' warnings/errors:")
                print(result_vet.stderr.decode())
                return CompileStatus.SYNTAX_ERROR
        else:
            print("Go code has syntax errors:")
            print(result_build.stderr.decode())
            return CompileStatus.SYNTAX_ERROR
    except Exception as e:
        print("Exception occurred during validation:", e)
        return CompileStatus.EXCEPTION_OCCURRED


#
# def run_test_file(path):
#     try:
#         # Run 'golang build' to validate syntax (this does not produce a binary)
#         result = subprocess.run(['go', 'test', path],
#                                 stdout=subprocess.PIPE, stderr=subprocess.PIPE)
#
#         if result.returncode == 0:
#             print("Go code is valid.")
#             return CompileStatus.OK
#         else:
#             print("Go code has syntax errors:")
#             print(result.stderr.decode())
#             return CompileStatus.SYNTAX_ERROR
#     except Exception as e:
#         print("Exception occurred during validation: ", e)
#         return CompileStatus.EXCEPTION_OCCURRED

# df = pandas.read_csv("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/stats/filtered_Go_stats_deepseek_coder.csv")
#
# df["syntax_2"] = df["code"].apply(lambda x: validate_go_code_with_build(x.replace('\u00A0', ' ')))
# print(df)