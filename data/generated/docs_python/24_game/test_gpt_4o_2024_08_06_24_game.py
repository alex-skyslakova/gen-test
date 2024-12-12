import unittest
from unittest.mock import patch
import random
import re
import ast

# Assuming the functions from the provided code are imported here
# from 24_game import choose4, check

class Test24Game(unittest.TestCase):

    def test_choose4(self):
        # Test that choose4 returns a list of four digits
        digits = choose4()
        self.assertEqual(len(digits), 4)
        for digit in digits:
            self.assertIn(digit, '123456789')

    def test_check_valid_expression(self):
        # Test valid expressions
        digits = ['1', '2', '3', '4']
        self.assertTrue(check('1+2+3+18', digits))
        self.assertTrue(check('(1+3)*(2+4)', digits))
        self.assertTrue(check('4*3*2/1', digits))

    def test_check_invalid_expression(self):
        # Test invalid expressions
        digits = ['1', '2', '3', '4']
        self.assertFalse(check('12+12', digits))  # Multiple digit numbers
        self.assertFalse(check('1+2+3+5', digits))  # Using a digit not in the list
        self.assertFalse(check('1+2+3+', digits))  # Incomplete expression
        self.assertFalse(check('1+2+3+4+5', digits))  # Extra digit
        self.assertFalse(check('1++2+3+4', digits))  # Invalid syntax

    def test_check_expression_with_repeated_digits(self):
        # Test expressions with repeated digits
        digits = ['2', '2', '3', '3']
        self.assertTrue(check('2*3+2*3', digits))
        self.assertFalse(check('2*3+2*2', digits))  # Using '2' three times

    def test_check_expression_with_brackets(self):
        # Test expressions with brackets
        digits = ['1', '2', '3', '4']
        self.assertTrue(check('(1+3)*(2+4)', digits))
        self.assertFalse(check('(1+3)*(2+4', digits))  # Unmatched brackets

    @patch('builtins.input', side_effect=['1+2+3+18', 'q'])
    def test_main_quit(self, mock_input):
        with patch('sys.stdout') as mock_stdout:
            main()
            self.assertIn("Thank you and goodbye", mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['!', 'q'])
    def test_main_new_digits(self, mock_input):
        with patch('sys.stdout') as mock_stdout:
            main()
            self.assertIn("New digits:", mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['1+2+3+4', 'q'])
    def test_main_invalid_input(self, mock_input):
        with patch('sys.stdout') as mock_stdout:
            main()
            self.assertIn("The input '1+2+3+4' was wonky!", mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['(1+3)*(2+4)', 'q'])
    def test_main_correct_answer(self, mock_input):
        with patch('sys.stdout') as mock_stdout:
            main()
            self.assertIn("Thats right!", mock_stdout.getvalue())

if __name__ == '__main__':
    unittest.main()
