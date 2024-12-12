import unittest
from gray_code import gray_encode, gray_decode

class TestGrayCode(unittest.TestCase):

    def test_gray_encode(self):
        self.assertEqual(gray_encode(0), 0)
        self.assertEqual(gray_encode(1), 1)
        self.assertEqual(gray_encode(2), 3)
        self.assertEqual(gray_encode(3), 2)
        self.assertEqual(gray_encode(4), 6)
        self.assertEqual(gray_encode(5), 7)
        self.assertEqual(gray_encode(6), 5)
        self.assertEqual(gray_encode(7), 4)
        self.assertEqual(gray_encode(15), 10)
        self.assertEqual(gray_encode(31), 16)


    def test_gray_decode(self):
        self.assertEqual(gray_decode(0), 0)
        self.assertEqual(gray_decode(1), 1)
        self.assertEqual(gray_decode(2), 3)
        self.assertEqual(gray_decode(3), 2)
        self.assertEqual(gray_decode(4), 7)
        self.assertEqual(gray_decode(5), 6)
        self.assertEqual(gray_decode(6), 5)
        self.assertEqual(gray_decode(7), 4)
        self.assertEqual(gray_decode(10), 15)
        self.assertEqual(gray_decode(16), 31)

    def test_encode_decode_roundtrip(self):
        for i in range(32):
            self.assertEqual(gray_decode(gray_encode(i)), i)

    def test_decode_encode_roundtrip(self):
        for i in range(32):
            self.assertEqual(gray_encode(gray_decode(i)), i)


if __name__ == '__main__':
    unittest.main()

