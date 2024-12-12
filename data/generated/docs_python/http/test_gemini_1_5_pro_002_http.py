import unittest
from unittest.mock import patch, mock_open
import io
import urllib.request

class TestHTTP(unittest.TestCase):

    @patch('urllib.request.urlopen')
    def test_http_success(self, mock_urlopen):
        # Mock the response from urlopen
        mock_response = mock_open(read_data=b'Test Content')
        mock_urlopen.return_value = mock_response()

        # Capture the printed output
        with patch('sys.stdout', new=io.StringIO()) as fake_out:
            # Call the code being tested (normally in a separate module)
            import http  # Simulate importing the module

            self.assertEqual(fake_out.getvalue(), 'Test Content')

    @patch('urllib.request.urlopen')
    def test_http_error(self, mock_urlopen):
        mock_urlopen.side_effect = urllib.request.URLError("Test Error")

        with self.assertRaises(urllib.request.URLError) as context:
           import http
        self.assertEqual(str(context.exception), "Test Error")


if __name__ == '__main__':
    unittest.main()
