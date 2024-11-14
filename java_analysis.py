import os
import shutil
import time
import xml.etree.ElementTree as ET
import json
import glob

from java_assertion_ratios import assertions_density_java, assertions_mccabe_ratio_java
from python_validation import CompileStatus

JAVA_SRC_DIR = 'data/javaSetup/src/main/java/org/example/package'
JAVA_TEST_DIR = 'data/javaSetup/src/test/java/org/example/package'
JAVA_PROJECT_ROOT = 'data/javaSetup'
CHECKSTYLE_JAR_PATH = "./checkstyle-10.18.1-all.jar"
CHECKSTYLE_CONFIG = "./checkstyle-config.xml"
TEST_REPORTS = "data/javaSetup/target/surefire-reports/*.xml"

import subprocess


def run_checkstyle(java_file_path):
    """ Run Checkstyle for a Java file and return a list of errors """
    try:
        result = subprocess.run(
            ['java', '-jar', CHECKSTYLE_JAR_PATH, '-c', CHECKSTYLE_CONFIG, java_file_path],
            stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True
        )

        if result.stdout:
            print("Checkstyle stdout output:")
            print(result.stdout)

        if result.stderr:
            print("Checkstyle stderr output:")
            print(result.stderr)

        # Combine both stdout and stderr for processing, as errors might be in either
        output = result.stdout + result.stderr

        # Extract errors from the output
        errors = parse_checkstyle_errors(output)

        if errors:
            print("Found Checkstyle errors:")
            for error in errors:
                print(error)
        else:
            print("No Checkstyle issues found for this file.")

        return errors

    except Exception as e:
        print(f"Error running Checkstyle: {e}")
        return []


def parse_checkstyle_errors(output):
    """ Parse the Checkstyle output to extract errors """
    errors = []

    # Split the output into lines and look for lines that indicate errors
    for line in output.splitlines():
        if "error" in line.lower():
            errors.append(line.strip())

    return errors


def process_java_files_and_run_test_analysis(input_dir, src_dir=JAVA_SRC_DIR, test_dir=JAVA_TEST_DIR,
                                     project_dir=JAVA_PROJECT_ROOT, timeout=30):
    # Track copied files for cleanup later
    copied_files = []
    timeout_occurred = False
    error = False
    source_file_path = None
    test_file_path = None

    # Step 1: Copy .java files to the respective directories
    for file_name in os.listdir(input_dir):
        if file_name.endswith(".java"):
            source_path = os.path.join(input_dir, file_name)
            if file_name.endswith("Test.java"):
                destination_path = os.path.join(test_dir, file_name)
                test_file_path = destination_path
            else:
                destination_path = os.path.join(src_dir, file_name)
                source_file_path = destination_path
            shutil.copy(source_path, destination_path)
            copied_files.append(destination_path)

    try:
        result = subprocess.run(['mvn', 'test-compile'], cwd=project_dir, capture_output=True, text=True,
                                timeout=timeout)
        syntax_maven_output = result.stdout
        if result.returncode == 0:
            syntax = CompileStatus.OK
        else:
            syntax = CompileStatus.SYNTAX_ERROR
    except subprocess.TimeoutExpired:
        timeout_occurred = True
        syntax_maven_output = "Test execution timed out."
        syntax = CompileStatus.EXCEPTION_OCCURRED
    except Exception as e:
        print("Error occurred during compilation: ", e)
        error = True
        syntax = CompileStatus.EXCEPTION_OCCURRED
        syntax_maven_output = None

    assertions_density = assertions_density_java(test_file_path)
    try:
        mccabe = assertions_mccabe_ratio_java(source_file_path, test_file_path)
    except Exception as e:
        print("Error occurred during computation of mccabe ratio: ", e)
        mccabe = None

    if syntax != CompileStatus.OK:
        for copied_file in copied_files:
            os.remove(copied_file)
        return {
            "execution_time_sec": None,
            "coverage_percentage": None,
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

    # Step 2: Run 'mvn clean test' with a timeout and measure the time
    start_time = time.time()
    try:
        result = subprocess.run(['mvn', 'clean', 'test'], cwd=project_dir, capture_output=True, text=True,
                                timeout=timeout)
        test_maven_output = result.stdout
    except subprocess.TimeoutExpired:
        timeout_occurred = True
        test_maven_output = "Test execution timed out."
    end_time = time.time()

    # Step 3: Parse JaCoCo report for coverage percentage
    coverage_report_path = os.path.join(project_dir, 'target', 'site', 'jacoco', 'jacoco.xml')
    if os.path.exists(coverage_report_path) and not timeout_occurred:
        tree = ET.parse(coverage_report_path)
        root = tree.getroot()

        # Find the 'counter' element with type 'INSTRUCTION' and get covered and missed values
        instruction_counter = next(
            (counter for counter in root.findall('counter') if counter.get('type') == 'INSTRUCTION'), None)
        if instruction_counter is not None:
            covered = int(instruction_counter.get('covered'))
            missed = int(instruction_counter.get('missed'))
            coverage_percentage = (covered / (covered + missed)) * 100
        else:
            coverage_percentage = 0.0
    else:
        coverage_percentage = None

    pass_rate, runtime_errors = parse_report_and_compute_pass_rate()

    # Step 4: Calculate execution time
    execution_time = end_time - start_time

    parse_report_and_compute_pass_rate()
    # Step 5: Cleanup copied files
    for copied_file in copied_files:
        os.remove(copied_file)

    return {
        "execution_time_sec": execution_time,
        "coverage_percentage": coverage_percentage,
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


def validate_java_file_with_javac(file_path):
    try:
        # Run the javac command to compile the Java file
        result = subprocess.run(['javac', file_path], capture_output=True, text=True)

        # Check the return code
        if result.returncode == 0:
            # Compilation was successful, so syntax is valid
            return CompileStatus.OK
        else:
            # Compilation failed, likely due to a syntax error
            print(f"Syntax Error in file {file_path}:\n\n\n\n\n\n{result.stderr}")
            return CompileStatus.SYNTAX_ERROR

    except Exception as e:
        # Handle any other unexpected exceptions
        print(f"Exception occurred while validating file {file_path}: {e}")
        return CompileStatus.EXCEPTION_OCCURRED


def parse_report_and_compute_pass_rate():
    # Directory containing the XML report files
    print(os.getcwd())
    report_files = glob.glob(TEST_REPORTS)

    total_tests = 0
    total_failures = 0
    total_skipped = 0
    total_runtime_errors = 0

    for report_file in report_files:
        tree = ET.parse(report_file)
        root = tree.getroot()
        total_tests += int(root.attrib.get('tests', 0))
        total_failures += int(root.attrib.get('failures', 0))
        total_skipped += int(root.attrib.get('skipped', 0))
        total_runtime_errors += int(root.attrib.get('errors', 0))


    print(f"Total Tests: {total_tests}")
    print(f"Failures: {total_failures}")
    print(f"Skipped: {total_skipped}")
    print(f"Passed: {total_tests - total_failures - total_skipped}")

    total_passed = total_tests - total_failures - total_skipped
    pass_percentage = (total_passed / total_tests) * 100 if total_tests != 0 else None
    return pass_percentage, total_runtime_errors


#r = process_java_files_and_run_test_analysis(
#    "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/java/deepseek_coder/24Game")
#print(r)
# results = validate_java_file_with_javac('data/generated/java/deepseek_coder/24Game/Game24Test.java')
# print(results)
# print("Execution Time:", results["execution_time_sec"], "seconds")
# print("Coverage Percentage:", results["coverage_percentage"], "%")
# print("Maven Output:", results["maven_output"])
