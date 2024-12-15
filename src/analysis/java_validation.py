from src.helpers import convert_to_java_filename
from src.analysis.python_validation import CompileStatus
import subprocess
import os

def validate_java_code(java_code):
    try:
        name = convert_to_java_filename("Temp.java", "", data=java_code)
        temp_file_path = os.path.join("/data/javaSetup/src/main/java/org/example/package", name)
        with open(temp_file_path, "w") as temp_file:
            temp_file.write(java_code)

        # Run javac to check for syntax errors
        result = subprocess.run(['javac', temp_file_path], capture_output=True, text=True)

        # Check the return code to determine if there was a syntax error
        if result.returncode == 0:
            # No syntax errors
            return CompileStatus.OK
        else:
            # Syntax errors occurred
            print(f"Syntax Error:\n{result.stderr}")
            return CompileStatus.SYNTAX_ERROR

    except Exception as e:
        # Catch any other unexpected exceptions
        print(f"Exception occurred: {e}")
        return CompileStatus.EXCEPTION_OCCURRED
