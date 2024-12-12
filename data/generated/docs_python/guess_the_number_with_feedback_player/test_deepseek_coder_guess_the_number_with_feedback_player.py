import unittest
from unittest.mock import patch
from io import StringIO

class TestGuessTheNumberPlayer(unittest.TestCase):

    @patch('builtins.input', side_effect=['l', 'l', '=', 'h'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_correct_guess(self, mock_stdout, mock_input):
        from guess_the_number_with_feedback_player import inclusive_range, mn, mx
        mn, mx = inclusive_range
        self.assertEqual(mn, 1)
        self.assertEqual(mx, 10)

        # Simulate the game with the correct sequence of inputs
        expected_output = (
            "Think of a number between 1 and 10 and wait for me to guess it.\n"
            "On every guess of mine you should state whether the guess was\n"
            "too high, too low, or equal to your number by typing h, l, or =\n"
            "Guess  1 is:  5. The score for which is (h,l,=): "
            "Guess  2 is:  3. The score for which is (h,l,=): "
            "Guess  3 is:  2. The score for which is (h,l,=): "
            "  Ye-Haw!!\n"
            "\nThanks for keeping score.\n"
        )
        self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h', 'h'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_impossible_guess(self, mock_stdout, mock_input):
        from guess_the_number_with_feedback_player import inclusive_range, mn, mx
        mn, mx = inclusive_range
        self.assertEqual(mn, 1)
        self.assertEqual(mx, 10)

        # Simulate the game with an impossible sequence of inputs
        expected_output = (
            "Think of a number between 1 and 10 and wait for me to guess it.\n"
            "On every guess of mine you should state whether the guess was\n"
            "too high, too low, or equal to your number by typing h, l, or =\n"
            "Guess  1 is:  5. The score for which is (h,l,=): "
            "Guess  2 is:  3. The score for which is (h,l,=): "
            "Guess  3 is:  2. The score for which is (h,l,=): "
            "Guess  4 is:  1. The score for which is (h,l,=): "
            "Please check your scoring as I cannot find the value\n"
            "\nThanks for keeping score.\n"
        )
        self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch('builtins.input', side_effect=['x', 'h'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_invalid_input(self, mock_stdout, mock_input):
        from guess_the_number_with_feedback_player import inclusive_range, mn, mx
        mn, mx = inclusive_range
        self.assertEqual(mn, 1)
        self.assertEqual(mx, 10)

        # Simulate the game with an invalid input
        expected_output = (
            "Think of a number between 1 and 10 and wait for me to guess it.\n"
            "On every guess of mine you should state whether the guess was\n"
            "too high, too low, or equal to your number by typing h, l, or =\n"
            "Guess  1 is:  5. The score for which is (h,l,=): "
            "  I don't understand your input of 'x' ?\n"
            "Guess  2 is:  5. The score for which is (h,l,=): "
            "\nThanks for keeping score.\n"
        )
        self.assertEqual(mock_stdout.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()
