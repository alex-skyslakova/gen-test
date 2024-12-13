import json
import os.path
import signal
import subprocess
import threading
import time
import pytest


def run_pytest_with_timeout(filename, timeout=60):
    def run():
        pytest.main([filename, '--tb=short'])

    thread = threading.Thread(target=run)
    thread.start()
    thread.join(timeout)
    if thread.is_alive():
        raise TimeoutError("Test execution exceeded the time limit of 60 seconds")


def timeout_handler(a, b):
    raise Exception("Timeout")


@pytest.mark.timeout(30, signal)  # Optional timeout for individual tests
def get_coverage(filename, branch=False):
    print("==========COVERAGE")

    directory = os.path.dirname(filename)

    if branch:
        args = ["pytest", directory, "--tb=short", "--timeout=30", "--timeout-method=signal",
                "--cov", directory, "--cov-report=json:./coverage.json", "--cov-branch", filename]
    else:
        args = ["pytest", directory, "--tb=short", "--timeout=30", "--timeout-method=signal",
                "--cov", directory, "--cov-report=json:./coverage.json", filename]

    process = subprocess.Popen(
        args,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE
    )

    start_time = time.time()

    # Wait for the process to complete or terminate it if it exceeds the timeout
    while process.poll() is None:
        if time.time() - start_time > 30:
            # Kill the process if the timeout is exceeded
            process.terminate()  # Kill all processes in the group
            print(f"Test execution exceeded {30} seconds and was terminated.")
            return None

    # Collect stdout and stderr after the process completes
    stdout, stderr = process.communicate()

    # Print the output of pytest
    print(stdout.decode())
    print(stderr.decode())

    # Parse the coverage JSON report
    json_report_path = 'coverage.json'
    if os.path.exists(json_report_path):
        with open(json_report_path, 'r') as json_file:
            coverage_data = json.load(json_file)
            # Extract the overall coverage percentage
            coverage_percentage = round(coverage_data['totals']['percent_covered'], 2)
            return coverage_percentage
    else:
        print("Coverage report not found.")
        return None
