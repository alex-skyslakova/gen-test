import unittest
import base64

class TestBase64Decode(unittest.TestCase):

    def test_valid_base64_decode(self):
        # Valid Base64 encoded string
        encoded_data = 'VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g='
        expected_output = 'To err is human, but to really foul things up you need a computer.\n    -- Paul R. Ehrlich'
        decoded_data = base64.b64decode(encoded_data).decode('utf-8')
        self.assertEqual(decoded_data, expected_output)

    def test_invalid_base64_decode(self):
        # Invalid Base64 encoded string (missing padding)
        invalid_encoded_data = 'VG8gZXJyIGlzIGh1bWFuLCBidXQgdG8gcmVhbGx5IGZvdWwgdGhpbmdzIHVwIHlvdSBuZWVkIGEgY29tcHV0ZXIuCiAgICAtLSBQYXVsIFIuIEVocmxpY2g'
        with self.assertRaises(Exception):
            base64.b64decode(invalid_encoded_data).decode('utf-8')

    def test_empty_base64_decode(self):
        # Empty Base64 encoded string
        empty_encoded_data = ''
        with self.assertRaises(Exception):
            base64.b64decode(empty_encoded_data).decode('utf-8')

    def test_non_ascii_base64_decode(self):
        # Base64 encoded string with non-ASCII characters
        encoded_data = '4pyTIMOgIGxhIG1vZGU='
        expected_output = '🐍'
        decoded_data = base64.b64decode(encoded_data).decode('utf-8')
        self.assertEqual(decoded_data, expected_output)

if __name__ == '__main__':
    unittest.main()
