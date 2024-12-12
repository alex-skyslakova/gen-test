import unittest
from lzw_compression import compress, decompress

class TestLZWCompression(unittest.TestCase):

    def test_compress_basic(self):
        input_data = "TOBEORNOTTOBEORTOBEORNOT"
        expected_output = [84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]
        self.assertEqual(compress(input_data), expected_output)

    def test_decompress_basic(self):
        input_data = [84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]
        expected_output = "TOBEORNOTTOBEORTOBEORNOT"
        self.assertEqual(decompress(input_data), expected_output)

    def test_compress_empty_string(self):
        input_data = ""
        expected_output = []
        self.assertEqual(compress(input_data), expected_output)

    def test_decompress_empty_list(self):
        input_data = []
        expected_output = ""
        self.assertEqual(decompress(input_data), expected_output)

    def test_compress_single_character(self):
        input_data = "A"
        expected_output = [65]
        self.assertEqual(compress(input_data), expected_output)

    def test_decompress_single_character(self):
        input_data = [65]
        expected_output = "A"
        self.assertEqual(decompress(input_data), expected_output)

    def test_compress_repeated_characters(self):
        input_data = "AAAAAA"
        expected_output = [65, 256, 257, 258, 259]
        self.assertEqual(compress(input_data), expected_output)

    def test_decompress_repeated_characters(self):
        input_data = [65, 256, 257, 258, 259]
        expected_output = "AAAAAA"
        self.assertEqual(decompress(input_data), expected_output)

    def test_decompress_invalid_input(self):
        input_data = [300]
        with self.assertRaises(ValueError):
            decompress(input_data)

if __name__ == '__main__':
    unittest.main()
