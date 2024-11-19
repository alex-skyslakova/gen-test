import os
import re

import pandas as pd
from openai import OpenAI
from dotenv import load_dotenv

load_dotenv()

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

from helpers import simplify, convert_to_filename
from language import LanguageEnum

from log_conversations import log_conversation


def generate_test_gpt35(name, code, suffix, docs):
    '''
     GPT-3.5 Turbo models can understand and generate natural language or code and have been optimized for chat
     using the Chat Completions API but work well for non-chat tasks as well.
     As of July 2024, gpt-4o-mini should be used in place of gpt-3.5-turbo, as it is cheaper, more capable,
     multimodal, and just as fast. gpt-3.5-turbo is still available for use in the API.
    '''
    return generate_test_gpt_chat(name, code, "gpt-3.5-turbo", suffix, docs=docs)


def generate_test_gpt4o_mini(name, code, suffix, docs):
    '''
    GPT-4o mini (“o” for “omni”) is our most advanced model in the small models category, and our cheapest model
     yet. It is multimodal (accepting text or image inputs and outputting text), has higher intelligence than
     gpt-3.5-turbo but is just as fast. It is meant to be used for smaller tasks, including vision tasks.

    We recommend choosing gpt-4o-mini where you would have previously used gpt-3.5-turbo as this model is more capable and cheaper.
    '''
    return generate_test_gpt_chat(name, code, "gpt-4o-mini", suffix, docs=docs)


def generate_test_gpt4o(name, code, suffix, docs):
    '''
    GPT-4o (“o” for “omni”) is our most advanced model. It is multimodal (accepting text or image inputs and
    outputting text), and it has the same high intelligence as GPT-4 Turbo but is much more efficient—it generates
    text 2x faster and is 50% cheaper. Additionally, GPT-4o has the best vision and performance across non-English
    languages of any of our models.
    '''
    return generate_test_gpt_chat(name, code, "gpt-4o-2024-08-06", suffix, docs=docs)


def generate_test_gpt_chat(name: str, code: str, model: str, lang: LanguageEnum = None, temperature: float = 0.2,
                           docs=""):
    filename = convert_to_filename(name, model, lang, data=code)
    print("generating for {}".format(filename))
    docs = (" Documentation: " + docs) if docs is not None and docs != "" else ""
    messages = [
        {"role": "system",
         "content": "You are a developer tasked with writing unit tests based on provided code."},
        {"role": "user",
         "content": "Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response." + docs + " Code " + (
             filename if filename else "") + ": " + code},
    ]
    completion = client.chat.completions.create(model=model,
                                                temperature=temperature,
                                                messages=messages)
    log_conversation(
        filename,
        model,
        temperature,
        messages,
        completion.choices[0].message.content,
        lang.name if lang else "",
        completion.usage.total_tokens,
        completion.usage.prompt_tokens,
        completion.usage.completion_tokens
    )
    try:
        if completion is not None:
            print(completion.choices[0].message)
            gpt_response = completion.choices[0].message
            return extract_code_blocks(gpt_response.content, lang.name.lower())
        return None
    except Exception as e:
        print("Failed to parse task " + simplify(name) + ", error occured: " + str(e))
        return None


def generate_test_codex(name: str, code: str, lang: LanguageEnum = None, temperature: float = 0.2):
    model = "davinci-002"

    prompt = "You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Code " + simplify(
        name) + ".py: " + code

    completion = client.completions.create(model=model, prompt=prompt)

    print(completion)
    log_conversation(
        model,
        temperature,
        prompt,
        completion.choices[0].text,
        lang.name if lang else "",
        completion.usage.total_tokens,
        completion.usage.prompt_tokens,
        completion.usage.completion_tokens
    )

    return completion.choices[0].text


import json


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

    # Find all matches in the input text
    matches = pattern.findall(text)

    if not matches:
        if "None" in text:
            return "none"
        else:
            return "error"
    # Concatenate all code blocks
    return "\n".join(matches)


def compare_csv_rows(file1: str, file2: str, column: str):
    """
    Compare a specific column row-by-row in two CSV files and print mismatched values.

    Parameters:
        file1 (str): Path to the first CSV file.
        file2 (str): Path to the second CSV file.
        column (str): Name of the column to compare.
    """
    try:
        # Load the CSV files
        df1 = pd.read_csv(file1)
        df2 = pd.read_csv(file2)

        # Check if the column exists in both files
        if column not in df1.columns or column not in df2.columns:
            print(f"Column '{column}' not found in one of the files.")
            return

        # Ensure both files have the same number of rows
        if len(df1) != len(df2):
            print(f"Files have different number of rows: {len(df1)} vs {len(df2)}")
            return

        # Compare the column values row by row
        mismatches = []
        for i, (value1, value2) in enumerate(zip(df1[column], df2[column])):
            if value1 != value2:
                mismatches.append((i, value1, value2))

        # Print mismatched rows
        if mismatches:
            print(f"Mismatched rows in column '{column}':")
            for index, val1, val2 in mismatches:
                print(f"Row {index + 1}: File1 = {val1}, File2 = {val2}")
        else:
            print(f"No mismatches found in column '{column}'.")

    except Exception as e:
        print(f"An error occurred: {e}")


# Example usage
if __name__ == "__main__":
    pass
    # file1 = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/stats/test2_filtered_Kotlin_stats_gpt_4o_2024_08_06.csv"
    # file2 = "/Users/alex/PycharmProjects/chatgptApi/llm-test-gen/data/generated/stats/test_filtered_Kotlin_stats_gpt_4o_2024_08_06.csv"
    # column_name = "generated_code"  # Replace with the column name you want to compare
    # compare_csv_rows(file1, file2, column_name)
    # print(extract_code_blocks(""""```kotlin\nimport java.math.BigInteger\nimport org.junit.jupiter.api.Test\nimport org.junit.jupiter.api.Assertions.*\n\nclass LeftFactorialTest {\n\n    @Test\n    fun testLeftFactorialZero() {\n        assertEquals(BigInteger.ZERO, leftFactorial(0))\n    }\n\n    @Test\n    fun testLeftFactorialOne() {\n        assertEquals(BigInteger.ONE, leftFactorial(1))\n    }\n\n    @Test\n    fun testLeftFactorialTwo() {\n        assertEquals(BigInteger.valueOf(2), leftFactorial(2))\n    }\n\n    @Test\n    fun testLeftFactorialThree() {\n        assertEquals(BigInteger.valueOf(4), leftFactorial(3))\n    }\n\n    @Test\n    fun testLeftFactorialFour() {\n        assertEquals(BigInteger.valueOf(10), leftFactorial(4))\n    }\n\n    @Test\n    fun testLeftFactorialLargeNumber() {\n        assertEquals(BigInteger.valueOf(335221220561898622604896037674114679704778975726705888432785845175724750186175913115270470087711217379481627190698686013396737482202598473923724155971578819129728345540000000000000000000000000), leftFactorial(100))\n    }\n\n\n    @Test\n    fun testLeftFactorialNegativeNumber(){\n        assertThrows<IllegalArgumentException> {leftFactorial(-1)}\n    }\n\n    // Add more test cases for different input values as needed,  especially focusing on edge cases and larger numbers to ensure the BigInteger handling is correct.  Consider using parameterized tests for efficiency if many cases are needed.\n\n}\n```\n",
    #     """, "kotlin"))
