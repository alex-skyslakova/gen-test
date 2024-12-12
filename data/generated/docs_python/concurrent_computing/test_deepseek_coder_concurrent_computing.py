import asyncio
import unittest
from unittest.mock import patch, call

# Assuming the code to be tested is in a module named `concurrent_computing`
from concurrent_computing import main

class TestConcurrentComputing(unittest.IsolatedAsyncioTestCase):

    async def test_main_function(self):
        # Mock the print function to capture the output
        with patch('builtins.print') as mock_print:
            await main()

            # Assert that the print function was called with the expected strings
            mock_print.assert_has_calls([call('Enjoy'), call('Rosetta'), call('Code')], any_order=True)

            # Ensure that the print function was called exactly 3 times
            self.assertEqual(mock_print.call_count, 3)

if __name__ == '__main__':
    unittest.main()
