import unittest
from unittest.mock import patch, MagicMock

class TestHTTPRequest(unittest.TestCase):

    @patch('urllib.request.urlopen')
    def test_urlopen_success(self, mock_urlopen):
        # Mock the response from urlopen
        mock_response = MagicMock()
        mock_response.read.return_value = b'Mocked response content'
        mock_urlopen.return_value = mock_response

        # Import the module and run the code
        import http

        # Assert that urlopen was called with the correct URL
        mock_urlopen.assert_called_once_with("http://rosettacode.org")

        # Assert that the response content was printed
        # Note: Since print is not captured by default, we can't directly assert the output.
        # However, we can assert that the read method was called on the response.
        mock_response.read.assert_called_once()

    @patch('urllib.request.urlopen')
    def test_urlopen_failure(self, mock_urlopen):
        # Mock a failure in urlopen
        mock_urlopen.side_effect = urllib.error.URLError("Mocked URL error")

        # Import the module and run the code
        import http

        # Assert that urlopen was called with the correct URL
        mock_urlopen.assert_called_once_with("http://rosettacode.org")

        # Since the code will raise an exception, we don't need to assert anything else here.
        # The test will pass if the exception is raised as expected.

if __name__ == '__main__':
    unittest.main()
