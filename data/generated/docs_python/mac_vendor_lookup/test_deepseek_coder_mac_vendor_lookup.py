import unittest
from unittest.mock import patch, MagicMock
import requests

class TestMacVendorLookup(unittest.TestCase):

    @patch('requests.get')
    def test_valid_mac_address(self, mock_get):
        mock_response = MagicMock()
        mock_response.text = "Apple, Inc."
        mock_get.return_value = mock_response

        addr = '88:53:2E:67:07:BE'
        vendor = requests.get('http://api.macvendors.com/' + addr).text
        self.assertEqual(vendor, "Apple, Inc.")

    @patch('requests.get')
    def test_invalid_mac_address(self, mock_get):
        mock_response = MagicMock()
        mock_response.text = "N/A"
        mock_get.return_value = mock_response

        addr = '23:45:67'
        vendor = requests.get('http://api.macvendors.com/' + addr).text
        self.assertEqual(vendor, "N/A")

    @patch('requests.get')
    def test_network_error(self, mock_get):
        mock_get.side_effect = requests.exceptions.RequestException("Network error")

        addr = '88:53:2E:67:07:BE'
        vendor = requests.get('http://api.macvendors.com/' + addr).text
        self.assertIsNone(vendor)

    @patch('requests.get')
    def test_api_throttling(self, mock_get):
        mock_response = MagicMock()
        mock_response.text = '{"errors":{"detail":"Too Many Requests","message":"Please slow down your requests or upgrade your plan at https://macvendors.com"}}'
        mock_get.return_value = mock_response

        addr = '88:53:2E:67:07:BE'
        vendor = requests.get('http://api.macvendors.com/' + addr).text
        self.assertIsNone(vendor)

if __name__ == '__main__':
    unittest.main()
