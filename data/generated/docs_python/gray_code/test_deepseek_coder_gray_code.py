import unittest
from gray_code import gray_encode, gray_decode

class TestGrayCode(unittest.TestCase):

    def test_gray_encode(self):
        # Test cases for encoding
        self.assertEqual(gray_encode(0), 0)
        self.assertEqual(gray_encode(1), 1)
        self.assertEqual(gray_encode(2), 3)
        self.assertEqual(gray_encode(3), 2)
        self.assertEqual(gray_encode(4), 6)
        self.assertEqual(gray_encode(5), 7)
        self.assertEqual(gray_encode(6), 5)
        self.assertEqual(gray_encode(7), 4)
        self.assertEqual(gray_encode(8), 12)
        self.assertEqual(gray_encode(9), 13)
        self.assertEqual(gray_encode(10), 15)
        self.assertEqual(gray_encode(11), 14)
        self.assertEqual(gray_encode(12), 10)
        self.assertEqual(gray_encode(13), 11)
        self.assertEqual(gray_encode(14), 9)
        self.assertEqual(gray_encode(15), 8)
        self.assertEqual(gray_encode(16), 24)
        self.assertEqual(gray_encode(17), 25)
        self.assertEqual(gray_encode(18), 27)
        self.assertEqual(gray_encode(19), 26)
        self.assertEqual(gray_encode(20), 30)
        self.assertEqual(gray_encode(21), 31)
        self.assertEqual(gray_encode(22), 29)
        self.assertEqual(gray_encode(23), 28)
        self.assertEqual(gray_encode(24), 20)
        self.assertEqual(gray_encode(25), 21)
        self.assertEqual(gray_encode(26), 23)
        self.assertEqual(gray_encode(27), 22)
        self.assertEqual(gray_encode(28), 18)
        self.assertEqual(gray_encode(29), 19)
        self.assertEqual(gray_encode(30), 17)
        self.assertEqual(gray_encode(31), 16)

    def test_gray_decode(self):
        # Test cases for decoding
        self.assertEqual(gray_decode(0), 0)
        self.assertEqual(gray_decode(1), 1)
        self.assertEqual(gray_decode(3), 2)
        self.assertEqual(gray_decode(2), 3)
        self.assertEqual(gray_decode(6), 4)
        self.assertEqual(gray_decode(7), 5)
        self.assertEqual(gray_decode(5), 6)
        self.assertEqual(gray_decode(4), 7)
        self.assertEqual(gray_decode(12), 8)
        self.assertEqual(gray_decode(13), 9)
        self.assertEqual(gray_decode(15), 10)
        self.assertEqual(gray_decode(14), 11)
        self.assertEqual(gray_decode(10), 12)
        self.assertEqual(gray_decode(11), 13)
        self.assertEqual(gray_decode(9), 14)
        self.assertEqual(gray_decode(8), 15)
        self.assertEqual(gray_decode(24), 16)
        self.assertEqual(gray_decode(25), 17)
        self.assertEqual(gray_decode(27), 18)
        self.assertEqual(gray_decode(26), 19)
        self.assertEqual(gray_decode(30), 20)
        self.assertEqual(gray_decode(31), 21)
        self.assertEqual(gray_decode(29), 22)
        self.assertEqual(gray_decode(28), 23)
        self.assertEqual(gray_decode(20), 24)
        self.assertEqual(gray_decode(21), 25)
        self.assertEqual(gray_decode(23), 26)
        self.assertEqual(gray_decode(22), 27)
        self.assertEqual(gray_decode(18), 28)
        self.assertEqual(gray_decode(19), 29)
        self.assertEqual(gray_decode(17), 30)
        self.assertEqual(gray_decode(16), 31)

    def test_gray_encode_decode_round_trip(self):
        # Test round-trip encoding and decoding for all 5-bit numbers
        for i in range(32):
            encoded = gray_encode(i)
            decoded = gray_decode(encoded)
            self.assertEqual(decoded, i)

if __name__ == '__main__':
    unittest.main()
