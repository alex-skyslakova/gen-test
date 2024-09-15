import os

from openai import OpenAI
from dotenv import load_dotenv

from helpers import simplify
from log_conversations import log_conversation

load_dotenv()


client = OpenAI(api_key=os.getenv("DEEPSEEK_API_KEY"), base_url="https://api.deepseek.com/beta")


def generate_test_deepseek_coder(name, code, lang):
    model = "deepseek-coder"
    max_tokens = 8000
    messages = [
        {"role": "system",
         "content": "You are a developer tasked with writing unit tests based on provided code."},
        {"role": "user",
         "content": "Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Code " +
                    simplify(name) + (str(lang.value) if lang else "") + ": " + code},
    ]
    completion = client.chat.completions.create(
        model=model,
        max_tokens=max_tokens,
        messages=messages,
        stream=False  # todo ???
    )

    log_conversation(model, None, messages, completion.choices[0].message.content, lang.name if lang else "")

    try:
        if completion is not None:
            print(completion.choices[0].message)
        deepseek_response = completion.choices[0].message
        test = deepseek_response.content.split(lang.name.lower() + '\n')[1].split('```')[0] if "```{}".format(
            lang.name.lower()) in deepseek_response.content else None
        return test
    except Exception as e:
        print("Failed to parse task " + simplify(name) + ", error occured: " + str(e))
        return None


