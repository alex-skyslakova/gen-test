import unittest
from unittest.mock import patch
import random
import io
import sys

class TestGuessTheNumber(unittest.TestCase):
    
    @patch('random.randint', return_value=5)
    @patch('builtins.input', side_effect=['3', '7', '5'])
    def test_correct_guess_after_multiple_attempts(self, mock_input, mock_randint):
        captured_output = io.StringIO()
        sys.stdout = captured_output
        import guess_the_number
        sys.stdout = sys.__stdout__
        self.assertIn("That's right!", captured_output.getvalue())

    @patch('random.randint', return_value=8)
    @patch('builtins.input', side_effect=['8'])
    def test_correct_guess_first_attempt(self, mock_input, mock_randint):
        captured_output = io.StringIO()
        sys.stdout = captured_output
        import guess_the_number
        sys.stdout = sys.__stdout__
        self.assertIn("That's right!", captured_output.getvalue())

    @patch('random.randint', return_value=2)
    @patch('builtins.input', side_effect=['1', '3', '4', '2'])
    def test_correct_guess_after_several_attempts(self, mock_input, mock_randint):
        captured_output = io.StringIO()
        sys.stdout = captured_output
        import guess_the_number
        sys.stdout = sys.__stdout__
        self.assertIn("That's right!", captured_output.getvalue())

    @patch('random.randint', return_value=10)
    @patch('builtins.input', side_effect=['9', '10'])
    def test_correct_guess_after_one_wrong_attempt(self, mock_input, mock_randint):
        captured_output = io.StringIO()
        sys.stdout = captured_output
        import guess_the_number
        sys.stdout = sys.__stdout__
        self.assertIn("That's right!", captured_output.getvalue())

if __name__ == '__main__':
    unittest.main()
