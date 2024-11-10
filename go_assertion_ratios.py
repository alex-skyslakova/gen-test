import os
import re
import subprocess

from pygments import lex
from pygments.lexers.go import GoLexer
from pygments.token import Token

from loc_analysis import get_sloc


def find_gocyclo():
    """
    Checks common locations for the gocyclo binary.
    Returns the path to gocyclo if found, otherwise None.
    """
    # Common locations where gocyclo might be installed
    possible_paths = [
        os.path.expanduser("~/go/bin/gocyclo"),  # Default Go binary path
        "/usr/local/bin/gocyclo",
        "/usr/bin/gocyclo"
    ]

    for path in possible_paths:
        if os.path.exists(path):
            return path

    return None


def install_gocyclo():
    """
    Installs gocyclo using Go if it's not already installed.
    Assumes that Go is installed and available in the system PATH.
    """
    # Check if gocyclo is already installed in common locations
    gocyclo_path = find_gocyclo()
    if gocyclo_path:
        print("gocyclo is already installed at:", gocyclo_path)
        return gocyclo_path

    print("gocyclo not found, installing...")

    try:
        # Install gocyclo using go install
        subprocess.run(
            ["go", "install", "github.com/fzipp/gocyclo/cmd/gocyclo@latest"],
            check=True
        )

        # Check again if gocyclo is now available in common locations
        gocyclo_path = find_gocyclo()
        if gocyclo_path:
            print("gocyclo installed successfully.")
            return gocyclo_path
        else:
            raise FileNotFoundError("gocyclo installation failed, binary not found.")

    except subprocess.CalledProcessError as e:
        print("Failed to install gocyclo:", e)
        return None


# List of common assertion methods in Go
go_assertion_methods = [
    # Standard library testing functions
    'Error', 'Errorf', 'Fatal', 'Fatalf', 'Fail', 'FailNow',
    # Third-party libraries like testify
    'Equal', 'NotEqual', 'Nil', 'NotNil', 'Contains',
    # Gomega and other libraries
    'Expect', 'Should', 'ShouldNot', 'BeTrue', 'BeFalse', 'MatchError',
    'HaveOccurred', 'Succeed',
]


def count_assertions_in_go_file(file_path):
    count = 0
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            code = f.read()
        tokens = lex(code, GoLexer())
        tokens_list = list(tokens)
        i = 0
        while i < len(tokens_list):
            token_type, token_value = tokens_list[i]
            if token_type == Token.Name.Other and token_value in go_assertion_methods:
                # Check if the method is being called
                # Skip whitespace and comments
                j = i + 1
                while j < len(tokens_list) and tokens_list[j][0] in (Token.Text, Token.Comment.Single, Token.Comment.Multiline):
                    j += 1
                if j < len(tokens_list) and tokens_list[j][1] == '(':
                    count += 1
                    i = j  # Move to the next token after '('
            i += 1
        return count
    except Exception as e:
        print(f"Error reading file {file_path}: {e}")
        return None

if __name__ == "__main__":
    file_path = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/levenshtein_distance/gemini_1_5_flash_002_levenshtein_distance_test.go"
    print(f"Number of assertions found: {count_assertions_in_go_file(file_path)}")


def compute_cyclomatic_complexity(go_file, threshold=0):
    """
    Computes the total cyclomatic complexity of Go functions in the specified file
    using gocyclo. Returns a tuple with the success status (True/False) and the
    computed complexity sum.

    Parameters:
    - gocyclo_path (str): Full path to the gocyclo executable.
    - directory (str): The path to the directory containing the Go file.
    - go_file_name (str): The name of the Go file to analyze.
    - threshold (int): Complexity threshold for displaying results. Only functions
      with complexity above this will be shown.

    Returns:
    - (bool, int): A tuple with a success status (True if successful) and the total
      cyclomatic complexity.
    """
    gocyclo_path = install_gocyclo()
    if not gocyclo_path:
        print("Failed to install gocyclo, exiting.")
        exit(1)

    # Run the gocyclo command within the specified directory
    result = subprocess.run(
        [gocyclo_path, f"-over={threshold}", go_file],
        cwd=os.path.dirname(go_file),
        capture_output=True,
        text=True
    )

    # Check if gocyclo executed successfully
    if result.returncode != 0:
        print("Error running gocyclo:", result.stderr)
        return False, 0

    # Initialize total complexity
    total_complexity = 0

    # Parse each line of the output to extract the complexity value
    for line in result.stdout.strip().splitlines():
        # Extract the complexity value from each line (assuming it’s the first number)
        match = re.match(r"(\d+)", line)
        if match:
            total_complexity += int(match.group(1))

    return True, total_complexity


def assertions_mccabe_ratio(go_file, test_file):
    """
    Computes the Assertions-McCabe ratio for a given Go test file.

    Parameters:
    - gocyclo_path (str): Full path to the gocyclo executable.
    - directory (str): Path to the directory containing the Go file.
    - go_file_name (str): Name of the Go test file to analyze.

    Returns:
    - float: The Assertions-McCabe ratio, or None if complexity is zero.
    """
    # Compute the cyclomatic complexity
    success, complexity_number = compute_cyclomatic_complexity(go_file)

    if not success or complexity_number == 0:
        print("ERROR: Unable to compute complexity or complexity is zero.")
        return None

    # Count assertions in the test file
    assertion_count = count_assertions_in_go_file(test_file)

    # Calculate the Assertions-McCabe ratio
    print("assertion count: ", assertion_count)
    print("Complexity number: ", complexity_number)
    mccabe_ratio = round(assertion_count / complexity_number, 2) if complexity_number != 0 else None
    return mccabe_ratio

def assertions_density(test_file):
    assertions_count = count_assertions_in_go_file(test_file)
    sloc = get_sloc(test_file)
    print("SLOC:", sloc)
    return round(assertions_count / sloc, 2) if sloc != 0 else None



# print(compute_mccabe_ratio(gocyclo_path, "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/knuth_s_power_tree.go",
#                            "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/gpt_4o_2024_08_06_knuth_s_power_tree_test.go")
#       )
print(assertions_density("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/gpt_4o_2024_08_06_knuth_s_power_tree_test.go"))