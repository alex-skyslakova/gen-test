import asyncio
import unittest
from unittest.mock import patch, call
from concurrent_computing import main, print_

class TestConcurrentComputing(unittest.IsolatedAsyncioTestCase):

    @patch('concurrent_computing.print')
    async def test_main(self, mock_print):
        await main()
        calls = [call('Enjoy'), call('Rosetta'), call('Code')]
        mock_print.assert_has_calls(calls, any_order=True)

    async def test_print_(self):
        with patch('concurrent_computing.print') as mock_print:
            await print_("test")
            mock_print.assert_called_once_with("test")

