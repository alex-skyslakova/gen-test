import unittest
from unittest.mock import patch
from io import StringIO
import random

class Test24GameSolve(unittest.TestCase):

    def test_choose4(self):
        # Test that choose4 returns a list of four digits between 1 and 9
        digits = choose4()
        self.assertEqual(len(digits), 4)
        for digit in digits:
            self.assertIn(digit, '123456789')

    def test_ask4_valid_input(self):
        # Test ask4 with valid input
        with patch('builtins.input', return_value='1234'):
            digits = ask4()
            self.assertEqual(digits, ['1', '2', '3', '4'])

    def test_ask4_invalid_input(self):
        # Test ask4 with invalid input followed by valid input
        with patch('builtins.input', side_effect=['abcd', '123']):
            digits = ask4()
            self.assertEqual(digits, ['1', '2', '3'])

    def test_check_valid_expression(self):
        # Test check with a valid expression
        self.assertTrue(check('2 + 3 * 6 + 4', ['2', '3', '6', '4']))

    def test_check_invalid_expression(self):
        # Test check with an invalid expression
        self.assertFalse(check('12 + 12', ['1', '2', '2', '1']))

    def test_solve_with_solution(self):
        # Test solve with a set of digits that has a solution
        with patch('sys.stdout', new=StringIO()) as fake_out:
            result = solve(['3', '2', '4', '6'])
            self.assertIn("Solution found:", fake_out.getvalue())
            self.assertEqual(result, '2 + 3 * 6 + 4')

    def test_solve_no_solution(self):
        # Test solve with a set of digits that has no solution
        with patch('sys.stdout', new=StringIO()) as fake_out:
            result = solve(['1', '1', '1', '1'])
            self.assertIn("No solution found for:", fake_out.getvalue())
            self.assertEqual(result, '!')

    def test_main_quit(self):
        # Test main function with quit command
        with patch('builtins.input', side_effect=['q']), patch('sys.stdout', new=StringIO()) as fake_out:
            main()
            self.assertIn("Thank you and goodbye", fake_out.getvalue())

    def test_main_generate_new_digits(self):
        # Test main function with generate new digits command
        with patch('builtins.input', side_effect=['!', 'q']), patch('sys.stdout', new=StringIO()) as fake_out:
            main()
            self.assertIn("New digits:", fake_out.getvalue())

    def test_main_user_input_digits(self):
        # Test main function with user input digits command
        with patch('builtins.input', side_effect=['!!', '1234', 'q']), patch('sys.stdout', new=StringIO()) as fake_out:
            main()
            self.assertIn("New digits: 1 2 3 4", fake_out.getvalue())

    def test_main_solve_command(self):
        # Test main function with solve command
        with patch('builtins.input', side_effect=['?', 'q']), patch('sys.stdout', new=StringIO()) as fake_out:
            main()
            self.assertIn("Solution found:", fake_out.getvalue())

if __name__ == '__main__':
    unittest.main()
