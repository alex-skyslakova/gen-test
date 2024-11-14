import lizard
from pygments import lex
from pygments.lexers.jvm import KotlinLexer
from pygments.token import Token

from loc_analysis import get_sloc

kotlin_assertion_methods = [
    # JUnit assertions
    'assertEquals', 'assertNotEquals', 'assertTrue', 'assertFalse',
    'assertNull', 'assertNotNull', 'assertSame', 'assertNotSame',
    'assertArrayEquals', 'assertThrows', 'assertDoesNotThrow', 'fail',
    # kotlin.test assertions
    'assertContentEquals', 'assertFailsWith', 'assertFails',
    # Kotest assertions
    'shouldBe', 'shouldNotBe', 'shouldContain', 'shouldNotContain',
    'shouldThrow', 'shouldNotThrow', 'shouldStartWith', 'shouldEndWith',
    'shouldBeEmpty', 'shouldNotBeEmpty', 'shouldHaveSize',
    # Built-in 'assert' function
    'assert',
]


def count_assertions_in_kotlin_file(file_path):
    count = 0
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            code = f.read()
        tokens = lex(code, KotlinLexer())
        tokens_list = list(tokens)
        i = 0
        while i < len(tokens_list):
            token_type, token_value = tokens_list[i]
            if token_type == Token.Name and token_value in kotlin_assertion_methods:
                # Check if it's a function call
                # Skip whitespace and comments
                j = i + 1
                while j < len(tokens_list) and tokens_list[j][0] in (
                Token.Text, Token.Comment.Single, Token.Comment.Multiline):
                    j += 1
                if j < len(tokens_list) and tokens_list[j][1] == '(':
                    count += 1
                    i = j  # Move to the next token after '('
            i += 1
        return count
    except Exception as e:
        print(f"Error reading file {file_path}: {e}")
        return None


def assertions_mccabe_ratio_kotlin(code_file_path, test_file_path):
    assertions_count = count_assertions_in_kotlin_file(test_file_path)
    result = lizard.analyze_file(code_file_path)
    complexity = sum([f.cyclomatic_complexity for f in result.function_list])
    print("assertions: ", assertions_count)
    print("complexity: ", complexity)
    return round(assertions_count / complexity) if complexity != 0 else None


def assertions_density_kotlin(file_path):
    assertions_count = count_assertions_in_kotlin_file(file_path)
    sloc = get_sloc(file_path)
    return round(assertions_count / sloc, 2) if sloc != 0 else None


if __name__ == "__main__":
    file = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/kotlin/FibonacciWordFractal/Gpt4o20240806FibonacciWordFractalTest.kt"
    print(count_assertions_in_kotlin_file(file))
