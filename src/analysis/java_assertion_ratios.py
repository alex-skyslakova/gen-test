import javalang
import lizard
from src.analysis.loc_analysis import get_sloc


def is_assertion_method(method_name):
    # List of common assertion methods in testing frameworks
    assertion_methods = [
        'assertEquals', 'assertNotEquals', 'assertTrue', 'assertFalse',
        'assertNull', 'assertNotNull', 'assertSame', 'assertNotSame',
        'assertArrayEquals', 'assertThrows', 'assertDoesNotThrow', 'fail',
        'assertThat', 'verify', 'check'
    ]
    return method_name.startswith('assert') or method_name in assertion_methods

def count_assertions_in_file(file_path):
    count = 0
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            code = f.read()
        tree = javalang.parse.parse(code)
    except Exception as e:
        print(f"Failed to parse {file_path}: {e}")
        return 0

    # Walk through the AST nodes
    for path, node in tree:
        # Count built-in 'assert' statements
        if isinstance(node, javalang.parser.tree.AssertStatement):
            count += 1

        # Count method calls that are assertions
        elif isinstance(node, javalang.parser.tree.MethodInvocation):
            method_name = node.member
            if is_assertion_method(method_name):
                count += 1

    return count


def assertions_density_java(file_path):
    assertions_count = count_assertions_in_file(file_path)
    sloc = get_sloc(file_path)
    return round(assertions_count / sloc, 2) if (sloc != 0 and sloc is not None) else None


def assertions_mccabe_ratio_java(code_file_path, test_file_path):
    assertions_count = count_assertions_in_file(test_file_path)
    result = lizard.analyze_file(code_file_path)
    complexity = sum([f.cyclomatic_complexity for f in result.function_list])
    print("assertions: ", assertions_count)
    print("complexity: ", complexity)
    return round(assertions_count / complexity) if (complexity != 0 and complexity is not None) else None

if __name__ == "__main__":
    print(assertions_density_java("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/java/gemini_1_5_flash_002/AbbreviationsSimple/AbbreviationsTest.java"))
    print(assertions_mccabe_ratio_java("/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/golang/abbreviations_simple/abbreviations_simple.go", "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/java/gemini_1_5_flash_002/AbbreviationsSimple/AbbreviationsTest.java"))
