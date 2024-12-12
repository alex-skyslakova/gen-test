import unittest
from unittest.mock import patch
from io import StringIO
import sys

class TestInputLoop(unittest.TestCase):

    @patch('builtins.input', side_effect=['25', '30', 'exit'])
    def test_input_loop(self, mock_input):
        # Redirect stdout to capture print statements
        captured_output = StringIO()
        sys.stdout = captured_output

        # Import the module containing the input loop
        import input_loop

        # Reset stdout
        sys.stdout = sys.__stdout__

        # Check the captured output
        output = captured_output.getvalue().strip().split('\n')
        expected_output = ['25', '30', 'exit']
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()
