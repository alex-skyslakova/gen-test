from enum import Enum


class CompileStatus(Enum):
    OK = 1
    SYNTAX_ERROR = 2
    EXCEPTION_OCCURRED = 3


def check_syntax(filepath) -> CompileStatus:
    try:
        with open(filepath, 'r') as file:
            source_code = file.read()
        compile(source_code, filepath, 'exec')
        print(f"{filepath} is syntactically correct.")
        return CompileStatus.OK
    except SyntaxError as e:
        print(f"Syntax error in {filepath}: {e}")
        return CompileStatus.SYNTAX_ERROR
    except Exception as e:
        print(f"An error occurred: {e}")
        return CompileStatus.EXCEPTION_OCCURRED
