import unittest
from io import StringIO
import sys
from unittest.mock import patch

class TestHelloWorldStandardError(unittest.TestCase):

    @patch('sys.stderr', new_callable=StringIO)
    def test_print_to_standard_error(self, mock_stderr):
        # Import the function to be tested
        import hello_world_standard_error

        # Check if the expected message was printed to standard error
        self.assertEqual(mock_stderr.getvalue().strip(), "Goodbye, World!")

if __name__ == '__main__':
    unittest.main()
