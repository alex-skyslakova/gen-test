import re

import pytest
import signal
from functools import wraps

# def timeout(seconds=60, error_message="Test execution timed out"):
#     """Timeout decorator to abort a function if it takes too long."""
#     def decorator(func):
#         def _handle_timeout(signum, frame):
#             raise TimeoutError(error_message)
#
#         @wraps(func)
#         def wrapper(*args, **kwargs):
#             signal.signal(signal.SIGALRM, _handle_timeout)
#             signal.alarm(seconds)
#             try:
#                 result = func(*args, **kwargs)
#             finally:
#                 signal.alarm(0)  # Disable the alarm
#             return result
#
#         return wrapper
#
#     return decorator
#
# @timeout(seconds=60)
# def run_tests_and_compute_pass_rate(test_file):
#     """
#     Runs tests in the specified directory, computes the number of passed tests,
#     total tests, and the percentage of passed tests.
#
#     Args:
#         test_directory (str): The directory containing the test files.
#
#     Returns:
#         dict or None: A dictionary containing the results, or None if timeout occurs:
#             - 'total_tests': Total number of tests run.
#             - 'passed_tests': Number of tests that passed.
#             - 'failed_tests': Number of tests that failed.
#             - 'pass_percentage': Percentage of tests that passed.
#     """
#     class TestResult:
#         def __init__(self):
#             self.passed = 0
#             self.failed = 0
#
#         def pytest_runtest_logreport(self, report):
#             if report.when == 'call':
#                 if report.passed:
#                     self.passed += 1
#                 elif report.failed:
#                     self.failed += 1
#
#     test_result = TestResult()
#     print("==========PASS RATE START")
#
#
#     # pytest.main([test_directory, "--tb=short", "-q", "--timeout=30"], plugins=[test_result])
#
#     # Define a timeout handler
#     def timeout_handler(signum, frame):
#         raise TimeoutError("Test execution exceeded the 30-second limit.")
#
#     # Set the timeout handler to trigger after 30 seconds
#     signal.signal(signal.SIGALRM, timeout_handler)
#     signal.alarm(30)
#
#     try:
#         # Run all tests in a specific test file (replace 'test_file.py' with your file name)
#         pytest.main([test_file, "--tb=short", "-q"], plugins=[test_result])
#     finally:
#         # Disable the alarm
#         signal.alarm(0)
#
#
#     total_tests = test_result.passed + test_result.failed
#     pass_percentage = (test_result.passed / total_tests) * 100 if total_tests > 0 else 0
#
#     print("==========PASS RATE END")
#
#     return {
#         'total_tests': total_tests,
#         'passed_tests': test_result.passed,
#         'failed_tests': test_result.failed,
#         'pass_percentage': round(pass_percentage, 2)
#     }
import subprocess
import os
import time
import json
import os
import shutil
import subprocess
import time
import json
import tempfile

from python_coverage_computation import get_coverage


def run_tests(test_file, timeout=30):
    """
    Runs tests in the specified test file, computes the number of passed tests,
    total tests, and the percentage of passed tests.

    This method creates a temporary directory one level higher than the current directory,
    copies the test file there, executes the tests, and cleans up afterward.

    Args:
        test_file (str): The path to the test file.
        timeout (int): Timeout in seconds for the test run.

    Returns:
        dict or None: A dictionary containing the results, or None if timeout occurs:
            - 'total_tests': Total number of tests run.
            - 'passed_tests': Number of tests that passed.
            - 'failed_tests': Number of tests that failed.
            - 'pass_percentage': Percentage of tests that passed.
    """
    original_dir = os.getcwd()
    temp_dir = tempfile.mkdtemp(dir=os.path.abspath(os.path.join(original_dir, "..")))
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
        print("==========PASS RATE START")
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

        print("LINE_COVERAGE: ", line_coverage)
        print("BRANCH_COVERAGE: ", branch_coverage)

        runtime_errors = 0

        # Print outputs for debugging (optional)
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

        print("RUNTIME ERRORS> ", runtime_errors)
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

                print({
                    'total_tests': total_tests if total_tests is not None else None,
                    'passed_tests': passed_tests,
                    'failed_tests': failed_tests,
                    'pass_percentage': round(pass_percentage, 2) if pass_percentage is not None else None,
                    'execution_time': exec_time,
                    'runtime_errors': runtime_errors,
                    'timeout': False,
                    'branch_coverage': branch_coverage,
                    'line_coverage': line_coverage
                })
                print("==========PASS RATE END")

                return {
                    'total_tests': total_tests,
                    'passed_tests': passed_tests,
                    'failed_tests': failed_tests,
                    'pass_percentage': round(pass_percentage, 2) if pass_percentage is not None else None,
                    'execution_time': exec_time,
                    'runtime_errors': runtime_errors,
                    'timeout': False,
                    'line_coverage': line_coverage,
                    'branch_coverage': branch_coverage
                }, False
        else:
            print("Test report not found.")
            return None, False
    except Exception as e:
        print("ERROR OCCURRED AFTER COVERAGE: ", e)
    finally:
        # Change back to the original directory
        os.chdir(original_dir)

        # Clean up the temporary directory
        shutil.rmtree(temp_dir)



