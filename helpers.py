import concurrent.futures
import os
import re

from language import LanguageEnum

GENERATED_DIR = "./data/generated/python/"
STATS_DIR = "./data/generated/stats/"


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
    directory = GENERATED_DIR + convert_to_filename(name, model, lang, directory=True, data=test)

    if not os.path.exists(directory):
        os.makedirs(directory)

    filename = os.path.join(directory, convert_to_filename(name, model, lang, test=True, data=test))

    with open(filename, "w", encoding='utf-8') as file:
        file.write(test.replace('\u00A0', ' '))
    return filename


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
        return pascal_case_str
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


def save_content(name: str, model, content: str, lang):
    # Specify the directory name
    directory = os.path.join(GENERATED_DIR, convert_to_filename(name, model, lang, directory=True, data=content))

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
    """Reads a Java file and returns the name of the public class, if found."""
    with open(file_path, 'r') as file:
        content = file.read()
        match = re.search(class_pattern, content)
        if match:
            g = match.group(1)
            print(g)
            return g  # Return the class name if found

    return None


def rename_java_files(base_dir):
    """Renames .java files in each subdirectory (excluding *Test.java files) to the public class name."""
    for subdir, _, files in os.walk(base_dir):
        for file_name in files:
            # Process only .java files that do not contain 'Test' in their name
            if file_name.endswith('.java') and 'Test' not in file_name:
                file_path = os.path.join(subdir, file_name)

                # Get the name of the public class from the file
                public_class_name = get_public_class_name(file_path)

                if public_class_name:
                    # Create the new file path with the public class name
                    new_file_path = os.path.join(subdir, f"{public_class_name}.java")

                    # Rename the file if the new name is different
                    if new_file_path != file_path:
                        os.rename(file_path, new_file_path)
                        print(f"Renamed '{file_path}' to '{new_file_path}'")
                else:
                    print(f"No public class found in '{file_path}', skipping.")
