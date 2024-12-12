import unittest
from unittest.mock import patch
import io
import sys
import re
from code_24_game import choose4, welcome, check

class Test24Game(unittest.TestCase):

    def test_choose4(self):
        for _ in range(100):  # Test multiple times for randomness
            digits = choose4()
            self.assertEqual(len(digits), 4)
            for digit in digits:
                self.assertTrue('1' <= digit <= '9')

    @patch('sys.stdout', new_callable=io.StringIO)
    def test_welcome(self, mock_stdout):
        digits = ['1', '2', '3', '4']
        welcome(digits)
        output = mock_stdout.getvalue()
        self.assertIn("Your four digits: 1 2 3 4", output)
        # Check for the presence of the docstring in the output.
        self.assertIn("The 24 Game", output)  
        self.assertIn("so an answer of 12+12 when given 1, 2, 2, and 1 would not be allowed.", output)


    def test_check_valid(self):
        digits = ['1', '2', '3', '4']
        self.assertTrue(check('1+2+3+4', digits))
        self.assertTrue(check('(1+2)*(3+4)', digits))
        self.assertTrue(check('1*2*3*4', digits))
        self.assertTrue(check('1/2+3+4', digits))

    def test_check_invalid_characters(self):
        digits = ['1', '2', '3', '4']
        self.assertFalse(check('1+2+3+a', digits))
        self.assertFalse(check('1+2+3+$', digits))

    def test_check_invalid_digit_count(self):
        digits = ['1', '2', '3', '4']
        self.assertFalse(check('1+2+3', digits))
        self.assertFalse(check('1+2+3+4+5', digits))
        self.assertFalse(check('1+2+3+3', digits))  # Duplicate 3, but only one in digits

    def test_check_invalid_double_digits(self):
        digits = ['1', '2', '3', '4']
        self.assertFalse(check('12+3+4', digits))

    def test_check_invalid_syntax(self):
        digits = ['1', '2', '3', '4']
        self.assertFalse(check('1+*2+3+4', digits))
        self.assertFalse(check('(1+2+3+4', digits))


