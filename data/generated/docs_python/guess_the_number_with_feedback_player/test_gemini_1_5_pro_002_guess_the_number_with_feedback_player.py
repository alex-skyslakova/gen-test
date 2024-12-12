import unittest
from unittest.mock import patch
import io
import sys

# Assuming the code is saved in a file named guess_the_number_with_feedback_player.py
# and mn and mx are defined globally within that file.
try:
    from guess_the_number_with_feedback_player import mn, mx
    mn = 1  # Reset for testing
    mx = 10 # Reset for testing
except ImportError:
    mn = 1
    mx = 10


class TestGuessTheNumber(unittest.TestCase):

    @patch('builtins.input', side_effect=['=', 'l', 'h', 'l', '=', 'h', 'h', 'l', 'h', '='])
    @patch('sys.stdout', new_callable=io.StringIO)
    def test_correct_guesses(self, mock_stdout, mock_input):
        global mn, mx
        mn = 1
        mx = 10
        exec(open("guess_the_number_with_feedback_player.py").read())  # Simulate running the script

        self.assertIn("Ye-Haw!!", mock_stdout.getvalue())
        self.assertIn("Thanks for keeping score.", mock_stdout.getvalue())

    @patch('builtins.input', side_effect=['a', 'b', 'c', '='])  # Invalid inputs
    @patch('sys.stdout', new_callable=io.StringIO)
    def test_invalid_input(self, mock_stdout, mock_input):
         global mn, mx
         mn = 1
         mx = 10
         exec(open("guess_the_number_with_feedback_player.py").read())

         self.assertIn("I don't understand your input", mock_stdout.getvalue())
         self.assertIn("Ye-Haw!!", mock_stdout.getvalue())  # Should still succeed after valid input
         self.assertIn("Thanks for keeping score.", mock_stdout.getvalue())


    @patch('builtins.input', side_effect=['h', 'h', 'h', 'h', 'h', 'h', 'h'])  # Incorrect scoring leading to impossible range
    @patch('sys.stdout', new_callable=io.StringIO)
    def test_inconsistent_scoring(self, mock_stdout, mock_input):
        global mn, mx
        mn = 1
        mx = 10
        exec(open("guess_the_number_with_feedback_player.py").read())

        self.assertIn("Please check your scoring", mock_stdout.getvalue()) # Should detect the impossible range
        self.assertIn("Thanks for keeping score.", mock_stdout.getvalue())


    @patch('builtins.input', side_effect=['l', 'l', 'l', 'l', 'l','l', 'l'])  # Incorrect scoring leading to impossible range
    @patch('sys.stdout', new_callable=io.StringIO)
    def test_inconsistent_scoring_low(self, mock_stdout, mock_input):
         global mn, mx
         mn = 1
         mx = 10
         exec(open("guess_the_number_with_feedback_player.py").read())
         self.assertIn("Please check your scoring", mock_stdout.getvalue()) # Should detect the impossible range
         self.assertIn("Thanks for keeping score.", mock_stdout.getvalue())



if __name__ == '__main__':
    unittest.main()
