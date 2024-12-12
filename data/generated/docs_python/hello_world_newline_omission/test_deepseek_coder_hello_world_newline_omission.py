import unittest
from io import StringIO
import sys
from unittest.mock import patch

class TestHelloWorldNewlineOmission(unittest.TestCase):

    @patch('sys.stdout', new_callable=StringIO)
    def test_output_without_newline(self, mock_stdout):
        # Import the function to be tested
        import hello_world_newline_omission
        
        # Capture the output
        output = mock_stdout.getvalue()
        
        # Assert that the output is exactly "Goodbye, World!" without a newline
        self.assertEqual(output, "Goodbye, World!")

if __name__ == '__main__':
    unittest.main()
