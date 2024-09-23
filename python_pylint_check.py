import json
import subprocess

def run_pylint_and_collect_errors(filepath: str):
    result = subprocess.run(
        ['pylint', filepath, '-f', 'json'],  # Run pylint with JSON output format
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )

    pylint_output = json.loads(result.stdout)

    return pylint_output


def pylint_check(file_to_check: str):
    errors = run_pylint_and_collect_errors(file_to_check)

    print(len(errors))
    print([e["message"] + " = " + e["type"] for e in errors])

    return len(errors), errors


def get_warnings_errors_fatals_count(findings):
    if findings is None:
        return None, None, None
    warnings, errors, fatals = 0, 0, 0
    for f in findings:
        if f["type"] == "error":
            errors += 1
        if f["type"] == "warning":
            warnings += 1
        if f["type"] == "fatal":
            fatals += 1
    return warnings, errors, fatals
