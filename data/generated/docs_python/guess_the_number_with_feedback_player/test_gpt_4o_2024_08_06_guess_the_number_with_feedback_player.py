import unittest
from unittest.mock import patch

class TestGuessTheNumberWithFeedbackPlayer(unittest.TestCase):

    @patch('builtins.input', side_effect=['l', 'l', 'h', '=', 'h'])
    def test_correct_guess(self, mock_input):
        with patch('builtins.print') as mock_print:
            mn, mx = 1, 10
            inclusive_range = mn, mx
            i = 0
            while True:
                i += 1
                guess = (mn + mx) // 2
                txt = mock_input("Guess %2i is: %2i. The score for which is (h,l,=): "
                                 % (i, guess)).strip().lower()[0]
                if txt not in 'hl=':
                    mock_print("  I don't understand your input of '%s' ?" % txt)
                    continue
                if txt == 'h':
                    mx = guess - 1
                if txt == 'l':
                    mn = guess + 1
                if txt == '=':
                    mock_print("  Ye-Haw!!")
                    break
                if (mn > mx) or (mn < inclusive_range[0]) or (mx > inclusive_range[1]):
                    mock_print("Please check your scoring as I cannot find the value")
                    break

            mock_print.assert_any_call("  Ye-Haw!!")

    @patch('builtins.input', side_effect=['h', 'h', 'h'])
    def test_invalid_score(self, mock_input):
        with patch('builtins.print') as mock_print:
            mn, mx = 1, 10
            inclusive_range = mn, mx
            i = 0
            while True:
                i += 1
                guess = (mn + mx) // 2
                txt = mock_input("Guess %2i is: %2i. The score for which is (h,l,=): "
                                 % (i, guess)).strip().lower()[0]
                if txt not in 'hl=':
                    mock_print("  I don't understand your input of '%s' ?" % txt)
                    continue
                if txt == 'h':
                    mx = guess - 1
                if txt == 'l':
                    mn = guess + 1
                if txt == '=':
                    mock_print("  Ye-Haw!!")
                    break
                if (mn > mx) or (mn < inclusive_range[0]) or (mx > inclusive_range[1]):
                    mock_print("Please check your scoring as I cannot find the value")
                    break

            mock_print.assert_any_call("Please check your scoring as I cannot find the value")

    @patch('builtins.input', side_effect=['x', 'l', 'h', '='])
    def test_invalid_input(self, mock_input):
        with patch('builtins.print') as mock_print:
            mn, mx = 1, 10
            inclusive_range = mn, mx
            i = 0
            while True:
                i += 1
                guess = (mn + mx) // 2
                txt = mock_input("Guess %2i is: %2i. The score for which is (h,l,=): "
                                 % (i, guess)).strip().lower()[0]
                if txt not in 'hl=':
                    mock_print("  I don't understand your input of '%s' ?" % txt)
                    continue
                if txt == 'h':
                    mx = guess - 1
                if txt == 'l':
                    mn = guess + 1
                if txt == '=':
                    mock_print("  Ye-Haw!!")
                    break
                if (mn > mx) or (mn < inclusive_range[0]) or (mx > inclusive_range[1]):
                    mock_print("Please check your scoring as I cannot find the value")
                    break

            mock_print.assert_any_call("  I don't understand your input of 'x' ?")

if __name__ == '__main__':
    unittest.main()
