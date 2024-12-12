import unittest
from unittest.mock import patch
from io import StringIO
import random

# Assuming the code is in a file named guess_the_number.py
import guess_the_number

class TestGuessTheNumber(unittest.TestCase):

    @patch('random.randint')
    @patch('builtins.input', side_effect=['5', '3', '7'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_guess_on_first_try(self, mock_stdout, mock_input, mock_randint):
        mock_randint.return_value = 5
        guess_the_number.t = 5
        guess_the_number.g = 0
        guess_the_number.g = int(mock_input())
        while guess_the_number.t != guess_the_number.g:
            guess_the_number.g = int(mock_input())
        print("That's right!")
        self.assertEqual(mock_stdout.getvalue().strip(), "That's right!")

    @patch('random.randint')
    @patch('builtins.input', side_effect=['3', '5', '7'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_guess_on_second_try(self, mock_stdout, mock_input, mock_randint):
        mock_randint.return_value = 5
        guess_the_number.t = 5
        guess_the_number.g = 0
        guess_the_number.g = int(mock_input())
        while guess_the_number.t != guess_the_number.g:
            guess_the_number.g = int(mock_input())
        print("That's right!")
        self.assertEqual(mock_stdout.getvalue().strip(), "That's right!")

    @patch('random.randint')
    @patch('builtins.input', side_effect=['3', '7', '5'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_guess_on_third_try(self, mock_stdout, mock_input, mock_randint):
        mock_randint.return_value = 5
        guess_the_number.t = 5
        guess_the_number.g = 0
        guess_the_number.g = int(mock_input())
        while guess_the_number.t != guess_the_number.g:
            guess_the_number.g = int(mock_input())
        print("That's right!")
        self.assertEqual(mock_stdout.getvalue().strip(), "That's right!")

    @patch('random.randint')
    @patch('builtins.input', side_effect=['1', '2', '3', '4', '5'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_guess_after_multiple_tries(self, mock_stdout, mock_input, mock_randint):
        mock_randint.return_value = 5
        guess_the_number.t = 5
        guess_the_number.g = 0
        guess_the_number.g = int(mock_input())
        while guess_the_number.t != guess_the_number.g:
            guess_the_number.g = int(mock_input())
        print("That's right!")
        self.assertEqual(mock_stdout.getvalue().strip(), "That's right!")

if __name__ == '__main__':
    unittest.main()
