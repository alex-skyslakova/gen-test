import unittest
from unittest.mock import patch, MagicMock
import urllib.request
import re

# Assuming the function `simple_stats` and `plausibility_check` are in a module named `i_before_e_except_after_c`
from i_before_e_except_after_c import simple_stats, plausibility_check, print_result

class TestIBeforeEExceptAfterC(unittest.TestCase):

    @patch('urllib.request.urlopen')
    def test_simple_stats(self, mock_urlopen):
        # Mock the response from the URL
        mock_response = MagicMock()
        mock_response.read.return_value = b'ie\ncei\ncie\nnot_c_ie\nnot_c_ei'
        mock_urlopen.return_value = mock_response

        cei, cie, not_c_ie, not_c_ei = simple_stats()

        self.assertEqual(cei, 1)
        self.assertEqual(cie, 1)
        self.assertEqual(not_c_ie, 2)
        self.assertEqual(not_c_ei, 1)

    def test_plausibility_check(self):
        # Test plausible cases
        self.assertTrue(plausibility_check('Test Plausible Case 1', 10, 1))
        self.assertTrue(plausibility_check('Test Plausible Case 2', 20, 2))

        # Test implausible cases
        self.assertFalse(plausibility_check('Test Implausible Case 1', 2, 2))
        self.assertFalse(plausibility_check('Test Implausible Case 2', 1, 10))

    @patch('i_before_e_except_after_c.plausibility_check')
    def test_print_result(self, mock_plausibility_check):
        # Mock the return values of plausibility_check
        mock_plausibility_check.side_effect = [True, True]

        # Capture the output
        with patch('builtins.print') as mock_print:
            print_result(10, 1, 20, 2)

            # Check if the correct messages are printed
            mock_print.assert_any_call('\nOVERALL IT IS PLAUSIBLE!')
            mock_print.assert_any_call('(To be plausible, one count must exceed another by 2 times)')

        # Reset the side effect
        mock_plausibility_check.side_effect = [False, False]

        # Capture the output
        with patch('builtins.print') as mock_print:
            print_result(1, 10, 2, 20)

            # Check if the correct messages are printed
            mock_print.assert_any_call('\nOVERALL IT IS IMPLAUSIBLE!')
            mock_print.assert_any_call('(To be plausible, one count must exceed another by 2 times)')

if __name__ == '__main__':
    unittest.main()
