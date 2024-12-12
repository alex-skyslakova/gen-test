import json
import os
from datetime import datetime

from config import Config
from helpers import simplify


def log_conversation(name, model, temperature, messages, output, lang, total_tokens=None, prompt_tokens=None,
                     completion_tokens=None, dir=Config.get_conversations_dir()):
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

    filepath = os.path.join(dir, "{}_{}_conversations.json".format(lang, simplify(model)))

    if os.path.exists(filepath):
        with open(filepath, "r") as file:
            try:
                conversations = json.load(file)
            except json.JSONDecodeError:
                conversations = []
    else:
        os.makedirs(dir, exist_ok=True)
        conversations = []

    # Append the new conversation entry to the list
    conversations.append(conversation_entry)

    # Open the file in write mode (overwrite) and dump the updated list
    with open(filepath, "w") as file:
        json.dump(conversations, file, indent=4)
