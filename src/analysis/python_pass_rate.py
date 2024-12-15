import re
import os
import shutil
import subprocess
import time
import json
import tempfile

from src.analysis.python_coverage_computation import get_coverage


def run_tests(test_file, timeout=30):
    original_dir = os.getcwd()
    temp_dir = tempfile.mkdtemp(dir=os.path.abspath(os.path.join(original_dir, "../../..")))
    temp_file_path = os.path.join(temp_dir, os.path.basename(test_file))

    try:
        # Copy the test file to the temporary directory
        shutil.copy2(test_file, temp_file_path)
        current_dir = os.path.dirname(test_file)
        for file_name in os.listdir(current_dir):
            if file_name.endswith(".py") and not file_name.startswith("test"):
                source_path = os.path.join(current_dir, file_name)
                shutil.copy2(source_path, temp_dir)

        # Change to the temporary directory
        os.chdir(temp_dir)

        # Run pytest in the copied test file with subprocess
        args = ["pytest", temp_file_path, "--tb=short", "-q", "--json-report"]
        process = subprocess.Popen(
            args,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE
        )

        start_time = time.time()

        # Wait for the process to complete or terminate it if it exceeds the timeout
        while process.poll() is None:
            if time.time() - start_time > timeout:
                process.terminate()
                print(f"Test execution exceeded {timeout} seconds and was terminated.")
                return None, True

        exec_time = time.time() - start_time
        stdout, stderr = process.communicate()

        line_coverage = get_coverage(temp_file_path)
        branch_coverage = get_coverage(temp_file_path, branch=True)

        print("Line coverage: ", line_coverage)
        print("Branch coverage: ", branch_coverage)

        runtime_errors = 0

        print("STDOUT:")
        print(stdout.decode())
        print("STDERR:")
        print(stderr.decode())

        # Regex pattern for runtime errors (excluding AssertionError)
        runtime_error_pattern = re.compile(
            r"(?<=Traceback \(most recent call last\):\n).*?\n(\w+Error): .+", re.DOTALL
        )

        # Parse stdout and stderr for runtime errors
        for output in (stdout, stderr):
            matches = runtime_error_pattern.findall(output.decode())
            for error in matches:
                if error != "AssertionError":  # Explicitly exclude AssertionError
                    runtime_errors += 1

        print("Runtime errors: ", runtime_errors)
        # Parse the pytest json report if available
        json_report_path = '.report.json'  # This is the default pytest json-report output file
        if os.path.exists(json_report_path):
            with open(json_report_path, 'r') as json_file:
                test_report = json.load(json_file)
                total_tests = test_report["summary"].get("total", None)
                passed_tests = test_report["summary"].get("passed", None)
                failed_tests = test_report["summary"].get("failed", None)
                if total_tests is None or passed_tests is None:
                    pass_percentage = None
                else:
                    pass_percentage = (passed_tests / total_tests) * 100 if total_tests > 0 else 0

                result = {
                    'total_tests': total_tests if total_tests is not None else None,
                    'passed_tests': passed_tests,
                    'failed_tests': failed_tests,
                    'pass_percentage': round(pass_percentage, 2) if pass_percentage is not None else None,
                    'execution_time': exec_time,
                    'runtime_errors': runtime_errors,
                    'timeout': False,
                    'branch_coverage': branch_coverage,
                    'line_coverage': line_coverage
                }
                print("Result: ", result)

                return result, False
        else:
            print("Test report not found.")
            return None, False
    except Exception as e:
        print("Error occurred: ", e)
    finally:
        os.chdir(original_dir)
        shutil.rmtree(temp_dir)



