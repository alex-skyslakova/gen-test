import unittest
from unittest.mock import patch
import sys

class TestFindLimitOfRecursion(unittest.TestCase):

    @patch('sys.getrecursionlimit')
    def test_getrecursionlimit_default(self, mock_getrecursionlimit):
        # Mock the default recursion limit
        mock_getrecursionlimit.return_value = 1000
        
        # Capture the output of the print statement
        with patch('builtins.print') as mock_print:
            import find_limit_of_recursion
            mock_print.assert_called_once_with(1000)

    @patch('sys.getrecursionlimit')
    def test_getrecursionlimit_custom(self, mock_getrecursionlimit):
        # Mock a custom recursion limit
        mock_getrecursionlimit.return_value = 500
        
        # Capture the output of the print statement
        with patch('builtins.print') as mock_print:
            import find_limit_of_recursion
            mock_print.assert_called_once_with(500)

    @patch('sys.getrecursionlimit')
    def test_getrecursionlimit_edge_case(self, mock_getrecursionlimit):
        # Mock an edge case recursion limit
        mock_getrecursionlimit.return_value = 1
        
        # Capture the output of the print statement
        with patch('builtins.print') as mock_print:
            import find_limit_of_recursion
            mock_print.assert_called_once_with(1)

if __name__ == '__main__':
    unittest.main()
