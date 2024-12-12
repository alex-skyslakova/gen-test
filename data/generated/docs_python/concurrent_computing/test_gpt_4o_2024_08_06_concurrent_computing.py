import asyncio
import unittest
from unittest.mock import patch
from io import StringIO
from concurrent_computing import main

class TestConcurrentComputing(unittest.TestCase):

    @patch('sys.stdout', new_callable=StringIO)
    def test_output(self, mock_stdout):
        # Run the main coroutine
        asyncio.run(main())

        # Capture the output and split by lines
        output = mock_stdout.getvalue().strip().split('\n')

        # Expected strings
        expected_strings = {'Enjoy', 'Rosetta', 'Code'}

        # Check if the output contains exactly the expected strings
        self.assertEqual(set(output), expected_strings)

        # Check if the output has exactly three lines
        self.assertEqual(len(output), 3)

if __name__ == '__main__':
    unittest.main()
