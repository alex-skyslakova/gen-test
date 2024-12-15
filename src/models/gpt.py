from openai import OpenAI
from src.config import Config
from src.helpers import simplify, convert_to_filename, extract_code_blocks
from src.language import LanguageEnum

from src.log_conversations import log_conversation

client = OpenAI(api_key=Config.get_openai_api_key())


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
            gpt_response = completion.choices[0].message
            return extract_code_blocks(gpt_response.content, lang.name.lower())
        return None
    except Exception as e:
        print("Failed to parse task " + simplify(name) + ", error occured: " + str(e))
        return None
