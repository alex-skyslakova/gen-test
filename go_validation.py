import subprocess

from python_validation import CompileStatus


def validate_go_code_with_build(go_code, go_file='temp.go'):
    try:
        with open(go_file, 'w') as f:
            f.write(go_code)

        # Run 'go build' to validate syntax (this does not produce a binary)
        result = subprocess.run(['go', 'build', go_file],
                                stdout=subprocess.PIPE, stderr=subprocess.PIPE)

        if result.returncode == 0:
            print("Go code is valid.")
            return CompileStatus.OK
        else:
            print("Go code has syntax errors:")
            print(result.stderr.decode())
            return CompileStatus.SYNTAX_ERROR
    except Exception as e:
        print("Exception occurred during validation: ", e)
        return CompileStatus.EXCEPTION_OCCURRED
