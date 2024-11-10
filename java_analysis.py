import os
import shutil
import time
import xml.etree.ElementTree as ET
from python_validation import CompileStatus

JAVA_SRC_DIR = 'data/javaSetup/src/main/java/org/example/package'
JAVA_TEST_DIR = 'data/javaSetup/src/test/java/org/example/package'
JAVA_PROJECT_ROOT = 'data/javaSetup'
CHECKSTYLE_JAR_PATH = "./checkstyle-10.18.1-all.jar"
CHECKSTYLE_CONFIG = "./checkstyle-config.xml"

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


def process_java_files_and_run_tests(input_dir, src_dir=JAVA_SRC_DIR, test_dir=JAVA_TEST_DIR,
                                     project_dir=JAVA_PROJECT_ROOT, timeout=30):
    # Track copied files for cleanup later
    copied_files = []
    timeout_occurred = False

    # Step 1: Copy .java files to the respective directories
    for file_name in os.listdir(input_dir):
        if file_name.endswith(".java"):
            source_path = os.path.join(input_dir, file_name)
            if file_name.endswith("Test.java"):
                destination_path = os.path.join(test_dir, file_name)
            else:
                destination_path = os.path.join(src_dir, file_name)
            shutil.copy(source_path, destination_path)
            copied_files.append(destination_path)

    # Step 2: Run 'mvn clean test' with a timeout and measure the time
    start_time = time.time()
    try:
        result = subprocess.run(['mvn', 'clean', 'test'], cwd=project_dir, capture_output=True, text=True,
                                timeout=timeout)
        maven_output = result.stdout
    except subprocess.TimeoutExpired:
        timeout_occurred = True
        maven_output = "Test execution timed out."
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
        coverage_percentage = 0.0

    # Step 4: Calculate execution time
    execution_time = end_time - start_time

    # Step 5: Cleanup copied files
    for copied_file in copied_files:
        os.remove(copied_file)

    return {
        "execution_time_sec": execution_time,
        "coverage_percentage": coverage_percentage,
        "timeout_occurred": timeout_occurred,
        "maven_output": maven_output
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


def run_analysis(filepath):
    dir = os.path.dirname(filepath)
    warnings = run_checkstyle(filepath)

    validation = validate_java_file_with_javac(filepath)
    if validation != CompileStatus.OK:
        return {
            "execution_time_sec": None,
            "coverage_percentage": None,
            "timeout_occurred": None,
            "maven_output": None,
            "syntax": validation
        }

# results = process_java_files_and_run_tests('data/generated/java/deepseek_coder/24Game')
# print(results)
# print("Execution Time:", results["execution_time_sec"], "seconds")
# print("Coverage Percentage:", results["coverage_percentage"], "%")
# print("Maven Output:", results["maven_output"])


