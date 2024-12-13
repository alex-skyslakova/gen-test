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
#         return wrapper
#     return decorator


# @timeout(seconds=60)
# def run_tests_and_compute_pass_rate(test_directory):
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
#
#     pytest.main([test_directory, "--tb=short", "-q"], plugins=[test_result])
#
#     total_tests = test_result.passed + test_result.failed
#     pass_percentage = (test_result.passed / total_tests) * 100 if total_tests > 0 else 0
#
#     return {
#         'total_tests': total_tests,
#         'passed_tests': test_result.passed,
#         'failed_tests': test_result.failed,
#         'pass_percentage': pass_percentage
#     }
