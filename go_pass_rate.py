import subprocess
import re


def run_go_tests(directory):
    try:
        # Run `go test -v` for the specified directory
        result = subprocess.run(
            ["go", "test", "-v", directory],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )

        # Output from the test execution
        output = result.stdout
        print(output)  # Print output for debugging

        # Regular expressions to count passed and failed tests
        passed_tests = len(re.findall(r'--- PASS:', output))
        failed_tests = len(re.findall(r'--- FAIL:', output))

        # Return the result summary
        return {
            "passed": passed_tests,
            "failed": failed_tests
        }

    except Exception as e:
        print(f"Error running Go tests: {e}")
        return None


if __name__ == "__main__":
    # Specify the directory containing Go tests
    directory = "./path/to/directory"  # Update this with your desired directory

    test_results = run_go_tests(directory)

    if test_results:
        print(f"Passed tests: {test_results['passed']}")
        print(f"Failed tests: {test_results['failed']}")
    else:
        print("Failed to execute Go tests.")
