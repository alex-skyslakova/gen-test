import os.path
import signal
import subprocess
import threading

import pytest
import coverage

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

def get_coverage(filename, branch=False):
    directory = os.path.dirname(filename)

    cov = coverage.Coverage(source=[directory], omit=[f'{directory}/**/test_*.py'], branch=branch)
    cov.start()
    # Set up the timeout
    signal.signal(signal.SIGALRM, timeout_handler)
    signal.alarm(10)  # Set the alarm for 60 seconds

    try:
        # Run pytest in the specified directory
        pytest.main([directory, '--tb=short', filename])
    except TimeoutError:
        print("Test execution exceeded the time limit and was terminated")
    finally:
        # Cancel the alarm
        signal.alarm(0)

    # Stop coverage measurement
    cov.stop()
    cov.save()

    # Calculate total lines and executed lines
    total_lines = 0
    executed_lines = 0

    for file in cov.get_data().measured_files():
        print("Computing for " + file)
        _, executable_lines, executed, _ = cov.analysis(file)
        total_lines += len(executable_lines)
        executed_lines += len(executed)

    # Report coverage summary
    return round(cov.report(), 2)
