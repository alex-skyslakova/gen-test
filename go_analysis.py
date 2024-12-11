import json
import shutil
import os
import tempfile
import subprocess
import re
from collections import Counter

from go_assertion_ratios import assertions_density_go, assertions_mccabe_ratio_go
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
        print(os.getcwd())
        print("running go build for file: ", file)
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
        print("cwd:", os.getcwd())
        print("content:", os.listdir())
        print("passed to command: ", project_dir)
        process = subprocess.Popen(
            vet_cmd,
            shell=True,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True  # Ensures the output is in string format
        )
        print("done with go vet")
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
    runtime_errors = 0

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
        stderr = result.stderr
        print(stderr)

        # Check if the subprocess exited with a non-zero return code
        if result.returncode != 0:
            # Parse output for timeout messages
            if "test timed out after" in output or "panic: test timed out" in output:
                print("Tests failed due to timeout")
                # Handle timeout scenario
                return {
                    "test_pass_rate": 0,
                    "execution_time_sec": None,
                    "line_coverage_percent": None,
                    "branch_coverage_percent": None,
                    "timeout": True,
                    "internal_error_occurred": False,
                    "runtime_errors_count": None
                }
            else:
                print("FAILUREEEEE")
                runtime_error_pattern = re.compile(
                    r"(panic:|fatal error:|runtime error:)", re.IGNORECASE
                )
                assertion_error_pattern = re.compile(r"assert")

                # Search for runtime errors in stdout and stderr
                for o in (output, stderr):
                    for line in o.splitlines():
                        # Match lines with runtime errors but exclude assertions
                        if runtime_error_pattern.search(line) and not assertion_error_pattern.search(line):
                            print("RUNTIME ERROR>>> ", line)
                            runtime_errors = runtime_errors + 1

        # Parse the JSON output
        passed_tests = 0
        failed_tests = 0
        total_time = 0.0
        coverage = None
        summary_time = None

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

            if (data.get('Action') == 'pass' or data.get('Action') == 'fail') and 'Test' not in data and 'Elapsed' in data:
                summary_time = data["Elapsed"]

            # Check for coverage information
            if data.get('Action') == 'output' and 'Output' in data:
                output_line = data['Output'].strip()
                if output_line.startswith("coverage:"):
                    print("HEREEEEEEEE")
                    # Extract the coverage percentage
                    match = re.search(r'coverage: ([\d\.]+)%', output_line)
                    if match:
                        coverage = float(match.group(1))

            # Alternative: Some versions include 'Coverage' field
            elif data.get('Action') == 'pass' and 'Coverage' in data:
                print("THEREEEEEEEEEEEEE")
                coverage = data['Coverage']

        #compute_branch_coverage(project_dir)
        total_tests = passed_tests + failed_tests
        pass_percentage = round((passed_tests / total_tests) * 100, 2) if total_tests > 0 else 0

        print("TOTAL TIME:", total_time)
        print("SUMMARY TIME:", summary_time)
        v = {
            "test_pass_rate": pass_percentage,
            "execution_time_sec": summary_time if summary_time is not None else total_time,
            "line_coverage_percent": coverage,
            "branch_coverage_percent": None, # TODO
            "timeout": False,
            "internal_error_occurred": False,
            "runtime_errors_count": runtime_errors
        }
        print(str(v))
        return v
    except Exception as e:
        print(f"Error running Go tests: {e}")
        return {
            "test_pass_rate": 0,
            "execution_time_sec": None,
            "line_coverage_percent": None,
            "branch_coverage_percent": None,
            "timeout": True,
            "internal_error_occurred": True,
            "runtime_errors_count": runtime_errors
        }
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
    print(os.getcwd())
    base_temp_dir = "./data/generated/docs_golang/"
    os.makedirs(base_temp_dir, exist_ok=True)
    temp_dir = tempfile.mkdtemp(dir=base_temp_dir)
    print("temporary:", temp_dir)

    # Save the current working directory
    original_cwd = os.getcwd()
    print("Current>", original_cwd)

    try:
        # Copy the Go file and the specified test file to the temporary directory
        new_go_file_path = shutil.copy(go_file_path, temp_dir)
        print("New go file:", new_go_file_path)
        new_test_file_path = shutil.copy(test_file_path, temp_dir)
        print("New test file:", new_test_file_path)


        # Change to temporary directory
        os.chdir(temp_dir)

        # Check syntax
        syntax_build = check_syntax(os.path.basename(test_file_path))
        syntax_vet, warnings = check_for_warnings(temp_dir)
        syntax = determine_syntax_result(syntax_build, syntax_vet)
        assertions_density = assertions_density_go(os.path.basename(test_file_path))
        mccabe = assertions_mccabe_ratio_go(os.path.basename(new_go_file_path), os.path.basename(new_test_file_path))
        print("Syntax build: ", syntax_build)
        print("Syntax vet: ", syntax_vet)
        print("Final syntax: ", syntax)
        print("Warnings: ", warnings)


        if syntax != CompileStatus.OK:
            print("Syntax check failed.")
            return {
                "syntax": syntax,
                "test_pass_rate": None,
                "execution_time_sec": None,
                "line_coverage_percent": None,
                "branch_coverage_percent": None,
                "warnings": warnings,
                "warnings_count": len(warnings) if warnings is not None else None,
                "timeout": False,
                "internal_error_occurred": False,
                "assertions_density": assertions_density,
                "assertions_mccabe_ratio": mccabe,
                "runtime_errors_count": None
            }

        # Run tests and get results
        test_results = run_tests(".")

        if test_results:
            test_results["warnings"] = warnings
            test_results["warnings_count"] = len(warnings) if warnings is not None else None
            test_results["syntax"] = syntax
            test_results["assertions_density"] = assertions_density
            test_results["assertions_mccabe_ratio"] = mccabe
            print(f"Percentage of passed tests: {test_results['test_pass_rate']}")
            print(f"Total time: {test_results['execution_time_sec']} seconds")
            if test_results is not None and test_results['line_coverage_percent'] is not None:
                print(f"Coverage: {test_results['line_coverage_percent']}%")
            else:
                print("Coverage information not available.")
            return test_results
        else:
            print("Failed to execute Go tests.")
            return {
                "syntax": syntax,
                "test_pass_rate": None,
                "execution_time_sec": None,
                "timeout": False,
                "line_coverage_percent": None,
                "branch_coverage_percent": None,
                "warnings": warnings,
                "warnings_count": len(warnings) if warnings is not None else None,
                "internal_error_occurred": True,
                "runtime_errors_count": None,
                "assertions_density": assertions_density,
                "assertions_mccabe_ratio": mccabe,
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

#
# def compute_branch_coverage(directory):
#     os.environ["PATH"] += os.pathsep + "/usr/local/golang/bin"
#     os.environ["PATH"] += os.pathsep + "/Users/alex/go/bin"
#
#
#     # export PATH="/usr/local/golang/bin:$PATH"
#     # export PATH =$(go env GOPATH) / bin:$PATH
#     try:
#         # Run `gocov test` command and capture the JSON output
#         print("RUNNING GOBCO")
#         result = subprocess.run(
#             ["gobco", directory],
#             stdout=subprocess.PIPE,
#             stderr=subprocess.PIPE,
#             text=True,
#             check=True
#         )
#
#         # Parse the JSON output
#         coverage_data = json.loads(result.stdout)
#
#         # Calculate branch coverage
#         total_branches = 0
#         covered_branches = 0
#
#         for pkg in coverage_data["packages"]:
#             for func in pkg["functions"]:
#                 total_branches += func.get("branches", 0)
#                 covered_branches += func.get("covered_branches", 0)
#
#         branch_coverage = (
#             (covered_branches / total_branches) * 100 if total_branches > 0 else 0
#         )
#
#         return {
#             "total_branches": total_branches,
#             "covered_branches": covered_branches,
#             "branch_coverage": branch_coverage,
#         }
#
#     except subprocess.CalledProcessError as e:
#         print(f"Error while running gocov test: {e.stderr}")
#         return None
#     except json.JSONDecodeError:
#         print("Failed to parse gocov output.")
#         return None


if __name__ == "__main__":
    # Paths to your Go file and the specific test file
    # go_file = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/abbreviations_automatic/abbreviations_automatic.go"
    # test_file = "data/generated/golang/abbreviations_automatic/gemini_1_5_flash_002_abbreviations_automatic_test.go"

    #build_project("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/tmp1v_oax0s")
    check_for_warnings("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/docs_golang/list_rooted_trees")
