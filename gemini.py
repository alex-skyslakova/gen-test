import os
import time

import google.generativeai as genai

from helpers import simplify

from dotenv import load_dotenv

load_dotenv()


GEMINI_API_KEY=os.getenv("GEMINI_API_KEY")


def generate_test_gemini_1_5_pro(name, code, lang):

    try:
        genai.configure(api_key=GEMINI_API_KEY)

        model = genai.GenerativeModel('gemini-1.5-pro-001')
        print("generating for " + simplify(name))
        response = model.generate_content("You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response. Code " + simplify(name) + (str(lang.value) if lang else "") + ": " + code)
        test = response.text.split(lang.name.lower() + '\n')[1].split('```')[0] if f"```{lang.name.lower()}" in response.text else None
        return test
    except Exception as e:
        print("Failed to generate test: ", e)


def generate_test_gemini_1_5_flash(name, code, lang):
    time.sleep(5)
    try:
        genai.configure(api_key=GEMINI_API_KEY)

        model = genai.GenerativeModel('gemini-1.5-flash')
        print("generating for " + simplify(name))
        response = model.generate_content("You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response. Code " + simplify(name) + (str(lang.value) if lang else "") + ": " + code)
        test = response.text.split(lang.name.lower() + '\n')[1].split('```')[0] if f"```{lang.name.lower()}" in response.text else None
        return test
    except Exception as e:
        print("Failed to generate test: ", e)

