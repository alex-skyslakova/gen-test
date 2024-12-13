import lizard
from pygments import lex
from pygments.lexers.go import GoLexer
from pygments.token import Token

from src.analysis.loc_analysis import get_sloc


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


def assertions_mccabe_ratio_go(go_file, test_file):
    result = lizard.analyze_file(go_file)
    complexity_number = sum([f.cyclomatic_complexity for f in result.function_list])
    assertion_count = count_assertions_in_go_file(test_file)

    print("assertion count: ", assertion_count)
    print("Complexity number: ", complexity_number)
    mccabe_ratio = round(assertion_count / complexity_number, 2) if complexity_number != 0 else None
    return mccabe_ratio


def assertions_density_go(test_file):
    assertions_count = count_assertions_in_go_file(test_file)
    sloc = get_sloc(test_file)
    print("SLOC:", sloc)
    return round(assertions_count / sloc, 2) if sloc != 0 else None

#
# print(assertions_mccabe_ratio_go("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/knuth_s_power_tree.go",
#                             "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/gpt_4o_2024_08_06_knuth_s_power_tree_test.go")
#        )
# print(assertions_density_go("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/knuth_s_power_tree/gpt_4o_2024_08_06_knuth_s_power_tree_test.go"))