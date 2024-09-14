import json
import os
from datetime import datetime

from helpers import simplify

CONVERSATION_DIR = "./conversations/"

def log_conversation(model, temperature, messages, output, lang, dir=CONVERSATION_DIR):
    # Set max_tokens based on the model type
    max_tokens = 4000 if "3.5" in model else 8000

    # Create the conversation entry with model parameters
    conversation_entry = {
        "time": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
        "model": model,
        "temperature": temperature,
        "max_tokens": max_tokens,
        "input": messages,
        "output": output
    }

    # Check if the conversations.json file exists
    filepath = os.path.join(dir, "{}_{}_conversations.json".format(lang, simplify(model)))
    if os.path.exists(filepath):
        # Open the file in read mode to load existing data
        with open(filepath, "r") as file:
            try:
                conversations = json.load(file)  # Load existing data
            except json.JSONDecodeError:
                conversations = []  # If file is empty or corrupt, start with empty list
    else:
        # If the file doesn't exist, create an empty list
        os.makedirs(dir, exist_ok=True)
        conversations = []

    # Append the new conversation entry to the list
    conversations.append(conversation_entry)

    # Open the file in write mode and dump the updated list
    with open(filepath, "a") as file:
        json.dump(conversations, file, indent=4)

