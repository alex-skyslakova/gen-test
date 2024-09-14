import os

from openai import OpenAI
from dotenv import load_dotenv

load_dotenv()

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

from helpers import simplify
from language import LanguageEnum

from log_conversations import log_conversation



def generate_test_gpt35(name, code, suffix):
    '''
     GPT-3.5 Turbo models can understand and generate natural language or code and have been optimized for chat
     using the Chat Completions API but work well for non-chat tasks as well.
     As of July 2024, gpt-4o-mini should be used in place of gpt-3.5-turbo, as it is cheaper, more capable,
     multimodal, and just as fast. gpt-3.5-turbo is still available for use in the API.
    '''
    return generate_test_gpt_chat(name, code, "gpt-3.5-turbo", suffix)

def generate_test_gpt4o_mini(name, code, suffix):
    '''
    GPT-4o mini (“o” for “omni”) is our most advanced model in the small models category, and our cheapest model
     yet. It is multimodal (accepting text or image inputs and outputting text), has higher intelligence than
     gpt-3.5-turbo but is just as fast. It is meant to be used for smaller tasks, including vision tasks.

    We recommend choosing gpt-4o-mini where you would have previously used gpt-3.5-turbo as this model is more capable and cheaper.
    '''
    return generate_test_gpt_chat(name, code, "gpt-4o-mini", suffix)

def generate_test_gpt4o(name, code, suffix):
    '''
    GPT-4o (“o” for “omni”) is our most advanced model. It is multimodal (accepting text or image inputs and
    outputting text), and it has the same high intelligence as GPT-4 Turbo but is much more efficient—it generates
    text 2x faster and is 50% cheaper. Additionally, GPT-4o has the best vision and performance across non-English
    languages of any of our models.
    '''
    return generate_test_gpt_chat(name, code, "gpt-4o-2024-08-06", suffix)


def generate_test_gpt_chat(name: str, code: str, model: str = "gpt-4-turbo", lang: LanguageEnum = None, temperature: float = 0.2):
    max_tokens = 4000 if "3.5" in model else 8000
    messages = [
        {"role": "system",
         "content": "You are a developer tasked with writing unit tests based on provided code."},
        {"role": "user",
         "content": "Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Code " +
                    simplify(name) + (str(lang.value) if lang else "") + ": " + code},
    ]
    completion = client.chat.completions.create(model=model,
    temperature=temperature,
    max_tokens=max_tokens,
    messages=messages)
    log_conversation(model, temperature, messages, completion.choices[0].message.content, lang.name if lang else "")

    try:
        if completion is not None:
            print(completion.choices[0].message)
            gpt_response = completion.choices[0].message
            test = gpt_response.content.split(lang.name.lower() + '\n')[1].split('```')[0] if "```{}".format(
                lang.name.lower()) in gpt_response.content else None
            return test
        return None
    except Exception as e:
        print("Failed to parse task " + simplify(name) + ", error occured: " + str(e))
        return None



def generate_test_codex(name: str, code: str, lang: LanguageEnum = None, temperature: float = 0.2):
    model = "davinci-002"
    prompt = "You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Code " + simplify(name) + ".py: " + code

    completion = client.completions.create(model=model, prompt=prompt)
    print(completion)
    log_conversation(model, temperature, prompt, completion.choices[0].text, lang.name if lang else "")

    return completion.choices[0].text
