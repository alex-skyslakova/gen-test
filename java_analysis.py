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

