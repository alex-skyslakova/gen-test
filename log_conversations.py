import json
import os
from datetime import datetime

from helpers import simplify

CONVERSATION_DIR = "./conversations/"


def log_conversation(name, model, temperature, messages, output, lang, total_tokens=None, prompt_tokens=None, completion_tokens=None, dir=CONVERSATION_DIR):
    # Set max_tokens based on the model type

    # Create the conversation entry with model parameters
    conversation_entry = {
        "time": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
        "name": name,
        "model": model,
        "temperature": temperature,
        "input": messages,
        "output": output,
        "total_tokens": total_tokens,
        "prompt_tokens": prompt_tokens,
        "completion_tokens": completion_tokens
    }

    # Check if the conversations.json file exists
    filepath = os.path.join(dir, "{}_{}_conversations.json".format(lang, simplify(model)))

    if os.path.exists(filepath):
        # Open the file in read mode to load existing data
        with open(filepath, "r") as file:
            try:
                conversations = json.load(file)  # Load existing data
            except json.JSONDecodeError:
                conversations = []  # If file is empty or corrupt, start with an empty list
    else:
        # If the file doesn't exist, create an empty list
        os.makedirs(dir, exist_ok=True)
        conversations = []

    # Append the new conversation entry to the list
    conversations.append(conversation_entry)

    # Open the file in write mode (overwrite) and dump the updated list
    with open(filepath, "w") as file:
        json.dump(conversations, file, indent=4)

