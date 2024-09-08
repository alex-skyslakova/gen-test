import javalang

def validate_java_code(java_code):
    try:
        javalang.parse.parse(java_code)
        return "Syntax is valid"
    except javalang.parser.JavaSyntaxError as e:
        return f"Syntax Error: {str(e)}"

