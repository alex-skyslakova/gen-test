import os

from openai import OpenAI
from dotenv import load_dotenv

from src.helpers import simplify, convert_to_filename, extract_code_blocks
from src.log_conversations import log_conversation

load_dotenv()


client = OpenAI(api_key=os.getenv("DEEPSEEK_API_KEY"), base_url="https://api.deepseek.com/beta")


def generate_test_deepseek_coder(name, code, lang, docs=""):
    filename = convert_to_filename(name, 'deepseek-coder', lang, data=code)
    model = "deepseek-coder"
    docs = (" Documentation: " + docs) if docs is not None and docs != "" else ""
    messages = [
        {"role": "system",
         "content": "You are a developer tasked with writing unit tests based on provided code."},
        {"role": "user",
         "content": "Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response." + docs + " Code " + (
                    filename if filename else "") + ": " + code},
    ]
    completion = client.chat.completions.create(
        model=model,
        messages=messages
    )

    log_conversation(
        filename,
        model,
        None,
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
        deepseek_response = completion.choices[0].message
        return extract_code_blocks(deepseek_response.content, lang.name.lower())
    except Exception as e:
        print("Failed to parse task " + simplify(name) + ", error occurred: " + str(e))
        return None


