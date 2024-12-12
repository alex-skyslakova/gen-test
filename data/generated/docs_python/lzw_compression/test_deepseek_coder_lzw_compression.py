import unittest
from lzw_compression import compress, decompress

class TestLZWCompression(unittest.TestCase):

    def test_compress_empty_string(self):
        self.assertEqual(compress(""), [])

    def test_compress_single_character(self):
        self.assertEqual(compress("A"), [65])

    def test_compress_repeated_characters(self):
        self.assertEqual(compress("AAAA"), [65, 128, 128])

    def test_compress_simple_string(self):
        self.assertEqual(compress("TOBEORNOTTOBEORTOBEORNOT"), [84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263])

    def test_decompress_empty_list(self):
        self.assertEqual(decompress([]), "")

    def test_decompress_single_character(self):
        self.assertEqual(decompress([65]), "A")

    def test_decompress_repeated_characters(self):
        self.assertEqual(decompress([65, 128, 128]), "AAAA")

    def test_decompress_simple_string(self):
        compressed = [84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]
        self.assertEqual(decompress(compressed), "TOBEORNOTTOBEORTOBEORNOT")

    def test_round_trip_compression_decompression(self):
        original = "TOBEORNOTTOBEORTOBEORNOT"
        compressed = compress(original)
        decompressed = decompress(compressed)
        self.assertEqual(decompressed, original)

    def test_decompress_bad_compressed_k(self):
        with self.assertRaises(ValueError):
            decompress([256])

if __name__ == '__main__':
    unittest.main()
