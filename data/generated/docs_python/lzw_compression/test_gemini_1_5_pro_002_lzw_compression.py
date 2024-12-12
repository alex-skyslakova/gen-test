import unittest
from lzw_compression import compress, decompress

class TestLZW(unittest.TestCase):

    def test_compress_empty_string(self):
        self.assertEqual(compress(""), [])

    def test_compress_single_char(self):
        self.assertEqual(compress("A"), [65])

    def test_compress_repeated_chars(self):
        self.assertEqual(compress("AAA"), [65, 256])

    def test_compress_simple_string(self):
        self.assertEqual(compress("ABABABA"), [65, 66, 256, 258])

    def test_compress_example_string(self):
        self.assertEqual(compress('TOBEORNOTTOBEORTOBEORNOT'), [84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 262, 259])


    def test_decompress_empty_list(self):
        self.assertEqual(decompress([]), "")

    def test_decompress_single_char(self):
        self.assertEqual(decompress([65]), "A")

    def test_decompress_repeated_chars(self):
        self.assertEqual(decompress([65, 256]), "AAA")

    def test_decompress_simple_string(self):
        self.assertEqual(decompress([65, 66, 256, 258]), "ABABABA")

    def test_decompress_example_string(self):
        self.assertEqual(decompress([84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 262, 259]), 'TOBEORNOTTOBEORTOBEORNOT')

    def test_decompress_invalid_input(self):
        with self.assertRaises(ValueError):
            decompress([1000]) # Invalid code


    def test_roundtrip(self):
        test_strings = ["", "A", "AAA", "ABABABA", "TOBEORNOTTOBEORTOBEORNOT", "This is a test string."]
        for s in test_strings:
            self.assertEqual(decompress(compress(s)), s)
