import os
import shutil
import subprocess

from java_analysis import run_maven_test_compile, run_maven_clean_test, compute_coverage_percentage, \
    parse_report_and_compute_pass_rate
from kotlin_assertion_ratios import assertions_density_kotlin, assertions_mccabe_ratio_kotlin
from python_validation import CompileStatus

KOTLIN_SRC_DIR = 'data/kotlinSetup/src/main/kotlin/org/example/package'
KOTLIN_TEST_DIR = 'data/kotlinSetup/src/test/kotlin/org/example/package'
KOTLIN_PROJECT_ROOT = 'data/kotlinSetup'
KTLINT_PATH = "./ktlint.jar"
CHECKSTYLE_CONFIG = "./checkstyle-config.xml"
TEST_REPORTS = "data/kotlinSetup/target/surefire-reports/*.xml"

os.environ['PATH'] += ':/opt/homebrew/bin/kotlinc'


def run_ktlint(kotlin_file_path):
    """Run ktlint for a Kotlin file and return a list of style errors."""
    try:
        result = subprocess.run(
            ['java', '-jar', KTLINT_PATH, kotlin_file_path],
            stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True
        )

        if result.stdout:
            print("ktlint.jar output:")
            print(result.stdout)

        if result.stderr:
            print("ktlint.jar error output:")
            print(result.stderr)

        # Combine both stdout and stderr for processing
        output = result.stdout + result.stderr
        warnings = parse_ktlint_output(output)

        if warnings:
            print("Found ktlint.jar warnings:")
            for error in warnings:
                print(error)
        else:
            print("No ktlint.jar issues found for this file.")

        return warnings

    except Exception as e:
        print(f"Error running ktlint.jar: {e}")
        return []


import re
from collections import defaultdict


def parse_ktlint_output(output):
    # Initialize result structures
    errors = []
    rule_summary = defaultdict(int)

    # Regex patterns
    error_pattern = re.compile(r'^(.*?):(\d+):(\d+): (.+?) \((.+?)\)$')
    summary_pattern = re.compile(r'^\s*(.+?): (\d+)$')

    lines = output.split("\n")
    parsing_errors = True

    for line in lines:
        if "Summary error count" in line:
            parsing_errors = False  # Start parsing the summary section
            continue

        if parsing_errors:
            match = error_pattern.match(line)
            if match:
                file_path, line_no, col_no, message, rule = match.groups()
                errors.append({
                    "file_path": file_path,
                    "line": int(line_no),
                    "column": int(col_no),
                    "message": message,
                    "rule": rule
                })
        else:
            match = summary_pattern.match(line)
            if match:
                rule, count = match.groups()
                rule_summary[rule] = int(count)

    return {
        "errors": errors,
        "rule_summary": dict(rule_summary)
    }


def get_code_file_path(test_file_path):
    dir = os.path.dirname(test_file_path)
    for file_name in os.listdir(dir):
        if file_name.endswith(".kt") and not file_name.endswith("Test.kt"):
            return os.path.join(dir, file_name)
    return None


def analyze_kotlin_tests(
        input_dir,
        test_input_file_path,
        src_dir=KOTLIN_SRC_DIR,
        test_dir=KOTLIN_TEST_DIR,
        project_dir=KOTLIN_PROJECT_ROOT,
        timeout=30
):
    # Track copied files for cleanup later
    copied_files = []
    timeout_occurred = False
    error = False
    source_file_path = None
    test_file_path = None

    try:
        # Step 1: Copy .java files to the respective directories
        print(input_dir)
        for file_name in os.listdir(input_dir):
            if file_name.endswith(".kt"):
                source_path = os.path.join(input_dir, file_name)
                if file_name == os.path.basename(test_input_file_path):
                    destination_path = os.path.join(test_dir, file_name)
                    test_file_path = destination_path
                else:
                    destination_path = os.path.join(src_dir, file_name)
                    source_file_path = destination_path
                shutil.copy(source_path, destination_path)
                copied_files.append(destination_path)

        # Step 1.5: Run 'mvn test-compile' using the extracted method
        compile_results = run_maven_test_compile(project_dir, timeout)
        syntax_maven_output, syntax, compile_timeout_occurred, compile_error = compile_results

        # Update the overall timeout and error status
        timeout_occurred = timeout_occurred or compile_timeout_occurred
        # error = error or compile_error

        # Calculate assertion density and McCabe ratio
        print(test_file_path)
        assertions_density = assertions_density_kotlin(test_file_path)
        try:
            mccabe = assertions_mccabe_ratio_kotlin(source_file_path, test_file_path)
        except Exception as e:
            print("Error occurred during computation of McCabe ratio: ", e)
            mccabe = None

        if syntax != CompileStatus.OK:
            return {
                "execution_time_sec": None,
                "line_coverage_percentage": None,
                "branch_coverage_percentage": None,
                "timeout_occurred": timeout_occurred,
                "internal_error_occurred": error,
                "syntax": syntax,
                "syntax_maven_output": syntax_maven_output,
                "assertion_density": assertions_density,
                "assertions_mccabe_ratio": mccabe,
                "runtime_errors": None,
                "test_pass_rate": None,
                "test_maven_output": None
            }

        # Run 'mvn clean test' using the extracted method
        test_results = run_maven_clean_test(project_dir, timeout)
        test_maven_output, test_timeout_occurred, test_error, execution_time = test_results

        # Update the overall timeout and error status
        timeout_occurred = timeout_occurred or test_timeout_occurred
        error = error or test_error

        # Parse test report and compute pass rate
        pass_rate, runtime_errors = parse_report_and_compute_pass_rate(TEST_REPORTS)

        # Compute coverage percentage using the extracted method
        coverage_percentage = compute_coverage_percentage(project_dir, source_file_path, timeout_occurred)

        return {
            "execution_time_sec": execution_time,
            "line_coverage_percentage": coverage_percentage["line"],
            "branch_coverage_percentage": coverage_percentage["branch"],
            "timeout_occurred": timeout_occurred,
            "test_maven_output": test_maven_output,
            "syntax_maven_output": syntax_maven_output,
            "internal_error_occurred": error,
            "syntax": syntax,
            "runtime_errors": runtime_errors,
            "test_pass_rate": pass_rate,
            "assertion_density": assertions_density,
            "assertions_mccabe_ratio": mccabe
        }
    finally:
        for copied_file in copied_files:
            os.remove(copied_file)
