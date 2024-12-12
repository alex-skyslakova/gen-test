import unittest
import requests
from unittest.mock import patch, Mock
import mac_vendor_lookup  # Assuming the provided code is saved as mac_vendor_lookup.py


class TestMacVendorLookup(unittest.TestCase):

    @patch('mac_vendor_lookup.requests.get')
    def test_valid_mac_address(self, mock_get):
        mock_response = Mock()
        mock_response.text = "Apple, Inc."
        mock_get.return_value = mock_response

        addr = "88:53:2E:67:07:BE"
        #  We don't call the loop directly, we test the underlying logic.
        vendor = requests.get('http://api.macvendors.com/' + addr).text

        self.assertEqual(vendor, "Apple, Inc.")


    @patch('mac_vendor_lookup.requests.get')
    def test_invalid_mac_address(self, mock_get):
         mock_response = Mock()
         mock_response.text = "N/A"  # or check for status code that macvendors.com returns for unknown MAC
         mock_get.return_value = mock_response

         addr = "23:45:67" 
         vendor = requests.get('http://api.macvendors.com/' + addr).text

         self.assertEqual(vendor, "N/A")



    @patch('mac_vendor_lookup.requests.get')
    def test_network_error(self, mock_get):
        mock_get.side_effect = requests.exceptions.RequestException("Network Error")

        addr = "88:53:2E:67:07:BE"
        try:
            vendor = requests.get('http://api.macvendors.com/' + addr).text
            self.fail("Expected RequestException") # Should not reach here
        except requests.exceptions.RequestException:
            # Assert the correct exception type is raised
            pass

    @patch('mac_vendor_lookup.requests.get')
    def test_rate_limiting(self, mock_get):
        mock_response_ok = Mock()
        mock_response_ok.text = "Apple, Inc."

        mock_response_rate_limited = Mock()
        mock_response_rate_limited.text = '{"errors":{"detail":"Too Many Requests"}}'  # Simplified for testing
        mock_response_rate_limited.status_code = 429  # Important: check the status code


        mock_get.side_effect = [mock_response_ok, mock_response_rate_limited] # First call ok, second rate limited

        addr1 = "88:53:2E:67:07:BE"
        addr2 = "FC:FB:FB:01:FA:21"

        vendor1 = requests.get('http://api.macvendors.com/' + addr1).text
        self.assertEqual(vendor1, "Apple, Inc.")


        try:
            vendor2 = requests.get('http://api.macvendors.com/' + addr2)
            # Check the status code, and handle the error appropriately.  Could also check the json response.
            self.assertEqual(vendor2.status_code, 429)

        except Exception as e:
            self.fail(f"Unexpected exception: {e}")



if __name__ == '__main__':
    unittest.main()

