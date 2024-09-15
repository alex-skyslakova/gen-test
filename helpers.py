import concurrent.futures
import os


GENERATED_DIR="./data/generated/go/"
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

def save_generated_test(name: str, type: str, test: str, suffix):
    # Specify the directory name
    directory = GENERATED_DIR + simplify(name)

    # Create the directory in the current working directory
    if not os.path.exists(directory):
        os.makedirs(directory)

    filename = os.path.join(directory, name_to_testfile(name, type, suffix))
    with open(filename, "w", encoding='utf-8') as file:
        file.write(test)
    return filename

def save_content(name: str, content: str, suffix):
    # Specify the directory name
    directory = os.path.join(GENERATED_DIR, simplify(name))

    # Create the directory in the current working directory
    if not os.path.exists(directory):
        os.makedirs(directory)

    with open(os.path.join(directory, simplify(name) + suffix), "w", encoding='utf-8') as file:
        file.write(clean_string(content))


def name_to_testfile(name: str, type: str, suffix):
    return "test_" + type + "_" + simplify(name) + suffix

def simplify(name: str):
    return name.lower().replace(" ", "_").replace("/", "_").replace("-", "_")

def is_not_blank(s):
    return bool(s and not s.isspace())