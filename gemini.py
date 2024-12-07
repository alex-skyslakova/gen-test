import os
import time

import google.generativeai as genai

from gpt import extract_code_blocks
from helpers import simplify, convert_to_filename

from dotenv import load_dotenv
from openai import OpenAI
from log_conversations import log_conversation

load_dotenv()


GEMINI_API_KEY=os.getenv("GEMINI_API_KEY")
client = OpenAI(api_key=GEMINI_API_KEY,
                base_url="https://generativelanguage.googleapis.com/v1beta/")


def generate_test_gemini_1_5_pro(name, code, lang, docs=""):

    try:
        genai.configure(api_key=GEMINI_API_KEY)
        filename = convert_to_filename(name, 'gemini-1.5-pro-002', lang, data=code)

        model = genai.GenerativeModel('gemini-1.5-pro-002')
        print("generating for " + simplify(name))
        docs = (" Documentation: " + docs) if docs is not None and docs != "" else ""
        message = "You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response." + docs + " Code " + (filename if filename else "") + ": " + code
        response = model.generate_content(message)
        test = extract_code_blocks(response.text, lang.name.lower())

        log_conversation(
            filename,
            'gemini-1.5-pro-002',
            None,
            message,
            response.text,
            lang.name if lang else "",
            response.usage_metadata.total_token_count,
            response.usage_metadata.prompt_token_count,
            response.usage_metadata.candidates_token_count,
        )
        return test
    except Exception as e:
        print("Failed to generate test: ", e)


def generate_test_gemini_1_5_flash(name, code, lang, docs=""):
    try:
        filename = convert_to_filename(name, 'gemini-1.5-flash-002', lang, data=code)
        genai.configure(api_key=GEMINI_API_KEY)

        model = genai.GenerativeModel('gemini-1.5-flash-002')
        print("generating for " + filename)
        docs = (" Documentation: " + docs) if docs is not None and docs != "" else ""
        message = "You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response." + docs + " Code " + (filename if filename else "") + ": " + code
        response = model.generate_content(message)
        test = extract_code_blocks(response.text, lang.name.lower())
        log_conversation(
            filename,
            'gemini-1.5-flash-002',
            None,
            message,
            response.text,
            lang.name if lang else "",
            response.usage_metadata.total_token_count,
            response.usage_metadata.prompt_token_count,
            response.usage_metadata.candidates_token_count,
        )
        return test
    except Exception as e:
        print("Failed to generate test: ", e)
