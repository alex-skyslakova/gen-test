import concurrent.futures
import re

from language import LanguageEnum

GENERATED_DIR= "data/generated/kotlin/"
STATS_DIR="./data/generated/stats/"

def run_with_timeout(func, timeout):
    with concurrent.futures.ThreadPoolExecutor() as executor:
        future = executor.submit(func)
        try:
            return future.result(timeout=timeout)
        except concurrent.futures.TimeoutError:
            print("Method took too long to complete and was terminated.")
            return None


def clean_string(input_string):
    # Remove non-breaking spaces and any other non-ASCII characters
    cleaned_string = ''.join(c for c in input_string if ord(c) < 128)
    return cleaned_string


def save_generated_test(name: str, model: str, test: str, lang):
    # Specify the directory name
    directory = GENERATED_DIR + convert_to_filename(name, model, lang, directory=True)

    if not os.path.exists(directory):
        os.makedirs(directory)

    filename = os.path.join(directory, convert_to_filename(name, model, lang, test=True))

    with open(filename, "w", encoding='utf-8') as file:
        file.write(test)
    return filename


def convert_to_filename(input_str, model, language, directory=False, test=False):
    if language == LanguageEnum.Python:
        return convert_to_python_filename(input_str, model, directory, test)
    elif language == LanguageEnum.Go:
        return convert_to_go_filename(input_str, model, directory, test)
    elif language == LanguageEnum.Java:
        return convert_to_java_filename(input_str, model, directory, test)
    elif language == LanguageEnum.Kotlin:
        return convert_to_kotlin_filename(input_str, model, directory, test)
    else:
        print("unrecognized language")
        return

def convert_to_python_filename(input_str, model, directory=False, test=False):
    # Remove invalid characters (anything that's not a letter, number, or space)
    if directory or not test:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    else:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', model + " " + input_str)
    snake_case_str = '_'.join(cleaned_str.lower().split())
    if test:
        snake_case_str = 'test_' + snake_case_str
    if directory:
        return snake_case_str
    return snake_case_str + '.py'


def convert_to_kotlin_filename(input_str, model, directory=False, test=False):
    # Remove invalid characters (anything that's not a letter or number)
    if directory or not test:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    else:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', model + " " + input_str)
    pascal_case_str = ''.join(word.capitalize() for word in cleaned_str.split())
    if test:
        pascal_case_str += 'Test'
    if directory:
        return pascal_case_str
    return pascal_case_str + '.kt'


def convert_to_java_filename(input_str, model, directory=False,  test=False):
    # Remove invalid characters (anything that's not a letter or number)
    if directory or not test:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    else:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', model + " " + input_str)
    # Convert to PascalCase
    pascal_case_str = ''.join(word.capitalize() for word in cleaned_str.split())
    # Add 'Test' if the test parameter is True
    if test:
        pascal_case_str += 'Test'
    if directory:
        return pascal_case_str
    # Add the .java extension
    return pascal_case_str + '.java'


def convert_to_go_filename(input_str, model, directory=False, test=False):
    # Remove invalid characters (anything that's not a letter, number, or space)
    if directory or not test:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    else:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', model + " " + input_str)
    snake_case_str = '_'.join(cleaned_str.lower().split())
    if test:
        snake_case_str += '_test'
    if directory:
        return snake_case_str
    return snake_case_str + '.go'



import os

def read_generated_test(name: str, type: str, suffix):
    # Specify the directory name
    directory = GENERATED_DIR + simplify(name)

    # Generate the filename using the same logic
    filename = os.path.join(directory, name_to_testfile(name, type, suffix))

    # Check if the file exists
    if os.path.exists(filename):
        # If the file exists, read its content
        with open(filename, "r", encoding='utf-8') as file:
            return file.read(), filename
    else:
        # Return None if the file does not exist
        return None, None


def save_content(name: str, content: str, lang):
    # Specify the directory name
    directory = os.path.join(GENERATED_DIR, convert_to_filename(name, "", lang, directory=True))

    # Create the directory in the current working directory
    if not os.path.exists(directory):
        os.makedirs(directory)

    with open(os.path.join(directory, convert_to_filename(name, "", lang)), "w", encoding='utf-8') as file:
        file.write(clean_string(content))


def name_to_testfile(name: str, type: str, suffix):
    return "test_" + type + "_" + simplify(name) + suffix

def simplify(name: str):
    return name.lower().replace(" ", "_").replace("/", "_").replace("-", "_")

def is_not_blank(s):
    return bool(s and not s.isspace())

