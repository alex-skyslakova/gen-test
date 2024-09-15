import javalang

def validate_java_code(java_code):
    try:
        javalang.parse.parse(java_code)
        return True
    except javalang.parser.JavaSyntaxError as e:
        return False
    except Exception:
        return False

