import unittest
from unittest.mock import patch
from io import StringIO
import sys
import random
import ast
import re

# Assuming the code is in a file named 24_game.py
import 24_game

class Test24Game(unittest.TestCase):

    def setUp(self):
        # Mocking random.randint to return predictable values for testing
        random.randint = lambda a, b: 1
        self.digits = 24_game.choose4()

    @patch('sys.stdout', new_callable=StringIO)
    def test_welcome_message(self, mock_stdout):
        expected_output = 24_game.__doc__ + "\nYour four digits: 1 1 1 1\n"
        with patch('builtins.input', return_value='q'):
            try:
                # Temporarily redirect stdout to capture print statements
                captured_output = StringIO()
                sys.stdout = captured_output
                # Call the main function
                24_game.main()
                # Restore stdout
                sys.stdout = sys.__stdout__
                # Check the output
                self.assertEqual(captured_output.getvalue(), expected_output)
            finally:
                # Ensure stdout is restored even if an error occurs
                sys.stdout = sys.__stdout__

    def test_check_valid_expression(self):
        valid_expression = "(1+1)*(1+11)"
        self.assertTrue(24_game.check(valid_expression, self.digits))

    def test_check_invalid_expression(self):
        invalid_expression = "11+11"
        self.assertFalse(24_game.check(invalid_expression, self.digits))

    def test_check_invalid_characters(self):
        invalid_expression = "1+1+a"
        self.assertFalse(24_game.check(invalid_expression, self.digits))

    def test_check_wrong_digit_count(self):
        invalid_expression = "1+1+1"
        self.assertFalse(24_game.check(invalid_expression, self.digits))

    @patch('builtins.input', side_effect=['(1+1)*(1+11)', 'q'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_main_valid_expression(self, mock_input, mock_stdout):
        expected_output = 24_game.__doc__ + "\nYour four digits: 1 1 1 1\nExpression 1:  =  24\nThats right!\nThank you and goodbye\n"
        with patch('builtins.input', side_effect=['(1+1)*(1+11)', 'q']):
            try:
                captured_output = StringIO()
                sys.stdout = captured_output
                24_game.main()
                sys.stdout = sys.__stdout__
                self.assertEqual(captured_output.getvalue(), expected_output)
            finally:
                sys.stdout = sys.__stdout__

    @patch('builtins.input', side_effect=['11+11', 'q'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_main_invalid_expression(self, mock_input, mock_stdout):
        expected_output = 24_game.__doc__ + "\nYour four digits: 1 1 1 1\nExpression 1: The input '11+11' was wonky!\nExpression 2: Thank you and goodbye\n"
        with patch('builtins.input', side_effect=['11+11', 'q']):
            try:
                captured_output = StringIO()
                sys.stdout = captured_output
                24_game.main()
                sys.stdout = sys.__stdout__
                self.assertEqual(captured_output.getvalue(), expected_output)
            finally:
                sys.stdout = sys.__stdout__

    @patch('builtins.input', return_value='!')
    @patch('sys.stdout', new_callable=StringIO)
    def test_main_new_digits(self, mock_input, mock_stdout):
        expected_output = 24_game.__doc__ + "\nYour four digits: 1 1 1 1\nNew digits: 1 1 1 1\nExpression 1: Thank you and goodbye\n"
        with patch('builtins.input', return_value='!'):
            try:
                captured_output = StringIO()
                sys.stdout = captured_output
                24_game.main()
                sys.stdout = sys.__stdout__
                self.assertEqual(captured_output.getvalue(), expected_output)
            finally:
                sys.stdout = sys.__stdout__

if __name__ == '__main__':
    unittest.main()
