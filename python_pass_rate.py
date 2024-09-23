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

def run_tests_and_compute_pass_rate(test_file, timeout=30):
    """
    Runs tests in the specified test file, computes the number of passed tests,
    total tests, and the percentage of passed tests.

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
    print("==========PASS RATE START")

    # Run pytest in the specified test file with subprocess
    args = ["pytest", test_file, "--tb=short", "-q", "--json-report"]

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
            return None
        time.sleep(1)

    stdout, stderr = process.communicate()

    print(stdout.decode())
    print(stderr.decode())

    # Parse the pytest json report if available
    json_report_path = '.report.json'  # This is the default pytest json-report output file
    if os.path.exists(json_report_path):
        with open(json_report_path, 'r') as json_file:
            test_report = json.load(json_file)
            total_tests = test_report["summary"].get("total", 0)
            passed_tests = test_report["summary"].get("passed", 0)
            failed_tests = test_report["summary"].get("failed", 0)  # Use get to avoid KeyError
            pass_percentage = (passed_tests / total_tests) * 100 if total_tests > 0 else 0

            print("==========PASS RATE END")

            return {
                'total_tests': total_tests,
                'passed_tests': passed_tests,
                'failed_tests': failed_tests,
                'pass_percentage': round(pass_percentage, 2)
            }
    else:
        print("Test report not found.")
        return None

#
# # Example usage:
# test_file = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/python/abundant_odd_numbers/test_gpt_3_5_turbo_abundant_odd_numbers.py"
# result = run_tests_and_compute_pass_rate(test_file)
#
# if result:
#     print(f"Total Tests: {result['total_tests']}")
#     print(f"Passed Tests: {result['passed_tests']}")
#     print(f"Failed Tests: {result['failed_tests']}")
#     print(f"Pass Percentage: {result['pass_percentage']}%")
