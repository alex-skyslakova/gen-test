import json
import shutil
import os
import tempfile
import subprocess
import re
from collections import Counter

from python_validation import CompileStatus


def run_command(command, cwd=None):
    """Run a system command using subprocess and return output."""
    try:
        result = subprocess.run(command, shell=True, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=cwd)
        return result.stdout.decode('utf-8'), result.stderr.decode('utf-8'), result.returncode
    except subprocess.CalledProcessError as e:
        return e.stdout.decode('utf-8'), e.stderr.decode('utf-8'), e.returncode


def check_syntax(file):
    """Check Go code syntax using go vet and analyze types of warnings."""
    try:
        # Run 'go build' to validate syntax (this does not produce a binary)
        result = subprocess.run(['go', 'build', file],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE)

        if result.returncode == 0:
            print("Go code is valid.")
            return CompileStatus.OK
        else:
            print("Go code has syntax errors:")
            print(result.stderr.decode())
            return CompileStatus.SYNTAX_ERROR
    except Exception as e:
        print("Exception occurred during validation: ", e)
        return CompileStatus.EXCEPTION_OCCURRED

def check_for_warnings(project_dir, tests=True):
    try:

        vet_cmd = "go vet ./..."

        print("Running go vet...")
        # Run the go vet command and capture output
        process = subprocess.Popen(
            vet_cmd,
            cwd=project_dir,
            shell=True,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True  # Ensures the output is in string format
        )
        stdout, stderr = process.communicate()

        output = stderr.strip()

        # Check the return code to determine if go vet succeeded
        success = process.returncode == 0

        # Parse the warnings from the output
        warnings = []
        pattern = r'^(?:vet: )?(.*):(\d+):(\d+): (.*)$'

        print(output)
        for line in output.splitlines():
            match = re.match(pattern, line)
            if match:
                file_path, line_num, col_num, message = match.groups()
                if tests and "test.go" in file_path:
                    warning = {
                        'file': file_path.strip(),
                        'line': int(line_num),
                        'column': int(col_num),
                        'message': message.strip()
                    }
                    warnings.append(warning)

        # Analyze the types of warnings
        warning_messages = [warning['message'] for warning in warnings]
        warning_counts = Counter(warning_messages)

        # Print the analysis of warning types
        print("\nWarning Types and Counts:")
        for message, count in warning_counts.items():
            print(f"{message}: {count} occurrence(s)")

        # Optionally, return the success status and the list of warnings
        return CompileStatus.OK if success else CompileStatus.SYNTAX_ERROR, warnings
    except:
        return CompileStatus.EXCEPTION_OCCURRED, None


def build_project(project_dir):
    """Build the Go project."""
    build_cmd = "go build ./..."

    print("Building the project...")
    build_out, build_err, return_code = run_command(build_cmd, cwd=project_dir)
    print(build_out)
    print(build_err)
    print(return_code)
    return return_code


def run_tests(project_dir):
    """Run Go tests and return summary with coverage."""
    test_cmd = ["go", "test", "-timeout", "30s", "-json", "-cover", "./..."]

    print("Running tests...")
    try:
        result = subprocess.run(
            test_cmd,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            cwd=project_dir,
            text=True
        )
        output = result.stdout
        print(output)

        # Check if the subprocess exited with a non-zero return code
        if result.returncode != 0:
            # Parse output for timeout messages
            if "test timed out after" in output or "panic: test timed out" in output:
                print("Tests failed due to timeout")
                # Handle timeout scenario
                return {
                    "test_pass_rate": 0,
                    "time": None,
                    "coverage": None,
                    "timeout": True,
                    "internal error": False,
                }
            else:
                # Handle other test failures
                pass

        # Parse the JSON output
        passed_tests = 0
        failed_tests = 0
        total_time = 0.0
        coverage = None

        for line in output.splitlines():
            if not line.strip():
                continue
            try:
                data = json.loads(line)
            except json.JSONDecodeError:
                continue  # Skip lines that aren't valid JSON

            # Check for test results
            if data.get('Action') == 'pass' and 'Test' in data:
                passed_tests += 1
                print('elapsed ', data.get('Elapsed', 0))
                total_time += data.get('Elapsed', 0)
            elif data.get('Action') == 'fail' and 'Test' in data:
                failed_tests += 1
                total_time += data.get('Elapsed', 0)

            # Check for coverage information
            if data.get('Action') == 'output' and 'Output' in data:
                output_line = data['Output'].strip()
                if output_line.startswith("coverage:"):
                    # Extract the coverage percentage
                    import re
                    match = re.search(r'coverage: ([\d\.]+)%', output_line)
                    if match:
                        coverage = float(match.group(1))

            # Alternative: Some versions include 'Coverage' field
            elif data.get('Action') == 'pass' and 'Coverage' in data:
                coverage = data['Coverage']

        total_tests = passed_tests + failed_tests
        pass_percentage = round((passed_tests / total_tests) * 100, 2)if total_tests > 0 else 0

        print(total_time)
        return {
            "test_pass_rate": pass_percentage,
            "time": total_time,
            "coverage": coverage,
            "timeout": False
        }

    except Exception as e:
        print(f"Error running Go tests: {e}")
        return None
#
#
# if __name__ == "__main__":
#     test = "test_name"
#     # Define your Go project directory
#     project_directory = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/15_puzzle_game"
#
#     # Check syntax
#     print(check_syntax(project_directory))
#
#     # Build the project
#     build_project(project_directory)
#
#     # Run tests
#     run_tests(project_directory)
def analyze_go_tests(go_file_path, test_file_path):
    """Create a temporary folder, move the Go file and test file, and run tests."""
    # Create a temporary directory within "/data/generated/golang/"
    base_temp_dir = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/"
    os.makedirs(base_temp_dir, exist_ok=True)
    temp_dir = tempfile.mkdtemp(dir=base_temp_dir)

    # Save the current working directory
    original_cwd = os.getcwd()

    try:
        # Copy the Go file and the specified test file to the temporary directory
        shutil.copy(go_file_path, temp_dir)
        shutil.copy(test_file_path, temp_dir)

        # Change to temporary directory
        os.chdir(temp_dir)

        # Check syntax
        syntax_build = check_syntax(os.path.join(temp_dir, os.path.basename(test_file_path)))
        syntax_vet, warnings = check_for_warnings(temp_dir)
        syntax = determine_syntax_result(syntax_build, syntax_vet)
        print("Syntax build: ", syntax_build)
        print("Syntax vet: ", syntax_vet)
        print("Final syntax: ", syntax)
        print("Warnings: ", warnings)


        if syntax != CompileStatus.OK:
            print("Syntax check failed.")
            return {
                "syntax": syntax,
                "test_pass_rate": None,
                "execution_time": None,
                "coverage": None,
                "warnings": warnings,
                "warnings_count": len(warnings) if warnings is not None else None,
                "timeout": False,
                "internal_error_occurred": False
            }

        # Run tests and get results
        test_results = run_tests(temp_dir)

        if test_results:
            test_results["warnings"] = warnings
            test_results["warnings_count"] = len(warnings) if warnings is not None else None
            test_results["syntax"] = syntax
            print(f"Percentage of passed tests: {test_results['test_pass_rate']}")
            print(f"Total time: {test_results['time']} seconds")
            if test_results['coverage'] is not None:
                print(f"Coverage: {test_results['coverage']}%")
            else:
                print("Coverage information not available.")
            return test_results
        else:
            print("Failed to execute Go tests.")
            return {
                "syntax": syntax,
                "test_pass_rate": None,
                "time": None,
                "timeout": False,
                "coverage": None,
                "warnings": warnings,
                "warnings_count": len(warnings) if warnings is not None else None,
                "internal_error_occurred": True
            }

    finally:
        # Restore the original working directory
        os.chdir(original_cwd)
        # Clean up the temporary directory
        shutil.rmtree(temp_dir)
        print(f"Temporary directory {temp_dir} has been removed.")

def determine_syntax_result(build, vet):
    if build == CompileStatus.SYNTAX_ERROR or build == CompileStatus.EXCEPTION_OCCURRED:
        return build
    if vet == CompileStatus.SYNTAX_ERROR or CompileStatus.EXCEPTION_OCCURRED:
        return vet
    return CompileStatus.OK

if __name__ == "__main__":
    # Paths to your Go file and the specific test file
    go_file = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/abbreviations_automatic/abbreviations_automatic.go"
    test_file = "data/generated/golang/abbreviations_automatic/gemini_1_5_flash_002_abbreviations_automatic_test.go"

    analyze_go_tests(go_file, test_file)
