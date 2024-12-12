import unittest
from unittest.mock import patch
from io import StringIO
import sys
import random
import ast
import re
from itertools import permutations, product, chain
from fractions import Fraction as F

# Assuming the code is in a file named 24_game_solve.py
import 24_game_solve as game

class Test24Game(unittest.TestCase):

    def setUp(self):
        # Mock random.randint to return predictable values
        random.seed(1)

    def test_choose4(self):
        self.assertEqual(game.choose4(), ['2', '9', '1', '9'])

    def test_ask4(self):
        with patch('builtins.input', return_value='1234'):
            self.assertEqual(game.ask4(), ['1', '2', '3', '4'])

    def test_welcome(self):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.welcome(['1', '2', '3', '4'])
            self.assertIn("Your four digits: 1 2 3 4", mock_stdout.getvalue())

    def test_check_valid_expression(self):
        self.assertTrue(game.check('2+3*4', ['2', '3', '4']))

    def test_check_invalid_expression(self):
        self.assertFalse(game.check('2+3*10', ['2', '3', '4']))

    def test_check_invalid_characters(self):
        self.assertFalse(game.check('2+3*a', ['2', '3', '4']))

    def test_check_multiple_digit_numbers(self):
        self.assertFalse(game.check('12+3', ['1', '2', '3']))

    def test_solve_with_solution(self):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            solution = game.solve(['3', '2', '4', '6'])
            self.assertIn("Solution found:", mock_stdout.getvalue())
            self.assertNotEqual(solution, '!')

    def test_solve_without_solution(self):
        with patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            solution = game.solve(['1', '1', '1', '1'])
            self.assertIn("No solution found for:", mock_stdout.getvalue())
            self.assertEqual(solution, '!')

    def test_main_quit(self):
        with patch('builtins.input', side_effect=['q']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn("Thank you and goodbye", mock_stdout.getvalue())

    def test_main_new_digits(self):
        with patch('builtins.input', side_effect=['!']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn("New digits:", mock_stdout.getvalue())

    def test_main_custom_digits(self):
        with patch('builtins.input', side_effect=['!!', '1234']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn("New digits: 1 2 3 4", mock_stdout.getvalue())

    def test_main_compute_solution(self):
        with patch('builtins.input', side_effect=['?', 'q']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn("Solution found:", mock_stdout.getvalue())

    def test_main_valid_expression(self):
        with patch('builtins.input', side_effect=['2+3*4', 'q']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn(" =  24", mock_stdout.getvalue())
            self.assertIn("Thats right!", mock_stdout.getvalue())

    def test_main_invalid_expression(self):
        with patch('builtins.input', side_effect=['2+3*a', 'q']), patch('sys.stdout', new_callable=StringIO) as mock_stdout:
            game.main()
            self.assertIn("The input '2+3*a' was wonky!", mock_stdout.getvalue())

if __name__ == '__main__':
    unittest.main()
