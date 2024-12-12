import unittest
import io
import sys

class TestGoodbyeWorldOutput(unittest.TestCase):
    def test_goodbye_world_no_newline(self):
        # Redirect stdout to capture the output
        captured_output = io.StringIO()
        sys.stdout = captured_output

        # Import the module to execute the code
        import hello_world_newline_omission

        # Reset redirect.
        sys.stdout = sys.__stdout__

        # Check the output
        self.assertEqual(captured_output.getvalue(), "Goodbye, World!")

if __name__ == '__main__':
    unittest.main()
