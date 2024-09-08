import google.generativeai as genai

from testing.helpers import simplify

GEMINI_API_KEY=""


def generate_test_gemini_1_5_pro(name, code):
    try:
        genai.configure(api_key=GEMINI_API_KEY)

        model = genai.GenerativeModel('gemini-1.5-pro-001')
        print("generating for " + simplify(name))
        response = model.generate_content("You are a developer tasked with writing unit tests based on provided code. Provide complete, ready-to-use test code covering all use cases. If tests can't be written, respond with 'None'. Do not include tested code to the response. Code " + simplify(name) + ".py: " + code)
        print(response.text)
        test = response.text.split('python\n')[1].split('```')[0] if "```python" in response.text else None
        print(test)
        return test
    except Exception as e:
        print("Failed to generate test: ", e)
