import concurrent.futures
import json
import re

from src.config import Config
from src.language import LanguageEnum


def run_with_timeout(func, timeout):
    with concurrent.futures.ThreadPoolExecutor() as executor:
        future = executor.submit(func)
        try:
            return future.result(timeout=timeout)
        except concurrent.futures.TimeoutError:
            print("Method took too long to complete and was terminated.")
            return None


def clean_string(input_string):
    cleaned_string = ''.join(c for c in input_string if ord(c) < 128)
    return cleaned_string


def convert_to_filename(input_str, model, language, directory=False, test=False, data=None):
    if language == LanguageEnum.Python:
        return convert_to_python_filename(input_str, model, directory, test)
    elif language == LanguageEnum.Go:
        return convert_to_go_filename(input_str, model, directory, test)
    elif language == LanguageEnum.Java:
        return convert_to_java_filename(input_str, model, directory, test, data)
    elif language == LanguageEnum.Kotlin:
        return convert_to_kotlin_filename(input_str, model, directory, test)
    else:
        print("unrecognized language")
        return


def extract_llm_model(path):
    llm_model_match = re.search(r"stats_(\w+)", os.path.basename(path))
    return llm_model_match.group(1) if llm_model_match else "Unknown"


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


def convert_to_kotlin_filename(input_str, model, directory=False, test=False, data=None):
    # Remove invalid characters (anything that's not a letter or number)
    if directory or not test:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    else:
        cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', model + " " + input_str)
    pascal_case_str = ''.join(word.capitalize() for word in cleaned_str.split())
    if test:
        pascal_case_str += 'Test'
    if directory:
        p = os.path.join(model, pascal_case_str)
        print("Dir name: ", p)
        return p
    return pascal_case_str + '.kt'


def convert_to_java_filename(input_str, model, directory=False, test=False, data=None):
    # Remove invalid characters (anything that's not a letter or number)

    if data is not None and not directory:
        class_pattern = r'\bpublic\s+class\s+(\w+)\b'
        match = re.search(class_pattern, data)

        # Check if we found a class declaration
        if match:
            class_name = match.group(1)

            # Return the file name based on the is_test_file flag
            if test and "Test" not in class_name:
                return f"{class_name}Test.java"
            return f"{class_name}.java"
        else:
            print("No class name found in the provided code string.")

    cleaned_str = re.sub(r'[^a-zA-Z0-9\s]', ' ', input_str)
    # Convert to PascalCase
    pascal_case_str = ''.join(word.capitalize() for word in cleaned_str.split())
    # Add 'Test' if the test parameter is True
    if test:
        pascal_case_str += 'Test'
    if directory:
        p = os.path.join(model, pascal_case_str)
        print("Dir name: ", p)
        return p
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

def select_output_dir(language: LanguageEnum):
    if language == LanguageEnum.Python:
        return Config.get_python_output_dir()
    elif language == LanguageEnum.Go:
        return Config.get_go_output_dir()
    elif language == LanguageEnum.Java:
        return Config.get_java_output_dir()
    elif language == LanguageEnum.Kotlin:
        return Config.get_kotlin_output_dir()
    else:
        print("unrecognized language")
        return


def save_generated_test(name: str, model: str, test: str, lang):
    # Specify the directory name
    directory = os.path.join(select_output_dir(lang), convert_to_filename(name, model, lang, directory=True, data=test))

    if not os.path.exists(directory):
        os.makedirs(directory)

    filename = os.path.join(directory, convert_to_filename(name, model, lang, test=True, data=test))

    with open(filename, "w", encoding='utf-8') as file:
        file.write(test.replace('\u00A0', ' '))
    return filename


def save_content(name: str, model, content: str, lang):
    directory = os.path.join(select_output_dir(lang), convert_to_filename(name, model, lang, directory=True, data=content))

    # Create the directory in the current working directory
    if not os.path.exists(directory):
        os.makedirs(directory)

    with open(os.path.join(directory, convert_to_filename(name, model, lang, data=content)), "w",
              encoding='utf-8') as file:
        file.write(clean_string(content))


def name_to_testfile(name: str, type: str, suffix):
    return "test_" + type + "_" + simplify(name) + suffix


def simplify(name: str):
    return name.lower().replace(" ", "_").replace("/", "_").replace("-", "_")


def is_not_blank(s):
    return bool(s and not s.isspace())


# Pattern to match the public class name in a .java file
class_pattern = r'\bpublic\s+class\s+(\w+)\b'


def get_public_class_name(file_path):
    with open(file_path, 'r') as file:
        content = file.read()
        match = re.search(class_pattern, content)
        if match:
            g = match.group(1)
            print(g)
            return g  # Return the class name if found

    return None


def find_output(json_file, input_argument):
    try:
        with open(json_file, 'r') as file:
            data = json.load(file)

            for item in data:
                input_content = item.get("input", [])

                if input_content == input_argument:
                    return item.get("output", "Output not found")

        return "No matching input found"
    except FileNotFoundError:
        return "JSON file not found"
    except json.JSONDecodeError:
        return "Error decoding JSON file"


def extract_code_blocks(text: str, language: str):
    # Regex pattern to find code blocks enclosed in triple backticks with a language specifier
    pattern = re.compile(rf"```{language}\s*(.*?)```", re.DOTALL)
    matches = pattern.findall(text)

    if not matches:
        if "None" in text:
            return "none"
        else:
            return "error"
    return "\n".join(matches)