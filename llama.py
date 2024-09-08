from transformers import AutoModelForCausalLM, AutoTokenizer

checkpoint = "Salesforce/codegen-350M-mono"
model = AutoModelForCausalLM.from_pretrained(checkpoint)
tokenizer = AutoTokenizer.from_pretrained(checkpoint)

text = "def hello_world():"

completion = model.generate(**tokenizer(text, return_tensors="pt"))

print(tokenizer.decode(completion[0]))
