import subprocess

from src.analysis.python_validation import CompileStatus


def validate_kotlin_code(kotlin_code, kotlin_file='temp.kt'):
    # Write the Kotlin code to a temporary file
    with open(kotlin_file, 'w') as f:
        f.write(kotlin_code)

    # Run the Kotlin compiler to validate the code
    result = subprocess.run(['kotlinc', kotlin_file, '-d', 'out'],
                            stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    # Check if there are compilation errors
    if result.returncode == 0:
        print("Kotlin code is valid.")
        return CompileStatus.OK
    else:
        print("Kotlin code has errors:")
        print(result.stderr.decode())
        return CompileStatus.SYNTAX_ERROR