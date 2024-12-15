import os
import shutil
import subprocess
import tempfile

from src.analysis.python_validation import CompileStatus
from src.config import Config


def validate_go_code_with_build(go_code):
    try:
        """Create a temporary folder, save the code from string into temp dir, and run validation."""
        # Base directory for temporary files
        base_temp_dir = Config.get_go_input_dir()
        os.makedirs(base_temp_dir, exist_ok=True)
        temp_dir = tempfile.mkdtemp(dir=base_temp_dir)

        # Save the current working directory
        original_cwd = os.getcwd()

        try:
            # Change to temporary directory
            os.chdir(temp_dir)

            # Write the code to a file in the temp directory
            go_file_name = 'temp.go'
            with open(go_file_name, 'x') as f:
                f.write(go_code)

            # Run validation on the temp directory
            return validate_go_directory(".")
        except Exception as e:
            print("Exception occurred during validation:", e, e.__traceback__)
        finally:
            # Change back to the original working directory
            os.chdir(original_cwd)
            # Clean up the temporary directory
            shutil.rmtree(temp_dir)
    except Exception as e:
        print("Exception occurred during validation:", e)
        return CompileStatus.EXCEPTION_OCCURRED


def validate_go_directory(temp_dir):
    try:
        # Run 'go build' in the temp directory to validate syntax
        result_build = subprocess.run(
            ['go', 'build'],
            cwd=temp_dir,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE
        )

        if result_build.returncode == 0:
            print("Go code is syntactically valid.")

            # Run 'go vet' in the temp directory to check for issues
            result_vet = subprocess.run(
                ['go', 'vet'],
                cwd=temp_dir,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE
            )

            if result_vet.returncode == 0:
                print("Go code passed 'go vet' checks.")
                return CompileStatus.OK
            else:
                print("Go code has 'go vet' warnings/errors:")
                print(result_vet.stderr.decode())
                return CompileStatus.SYNTAX_ERROR
        else:
            print("Go code has syntax errors:")
            print(result_build.stderr.decode())
            return CompileStatus.SYNTAX_ERROR
    except Exception as e:
        print("Exception occurred during validation:", e)
        return CompileStatus.EXCEPTION_OCCURRED
