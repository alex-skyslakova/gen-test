import unittest
from hailstone_sequence import hailstone

class TestHailstone(unittest.TestCase):

    def test_hailstone_1(self):
        self.assertEqual(hailstone(1), [1])

    def test_hailstone_2(self):
        self.assertEqual(hailstone(2), [2, 1])

    def test_hailstone_4(self):
        self.assertEqual(hailstone(4), [4, 2, 1])

    def test_hailstone_8(self):
        self.assertEqual(hailstone(8), [8, 4, 2, 1])

    def test_hailstone_3(self):
        self.assertEqual(hailstone(3), [3, 10, 5, 16, 8, 4, 2, 1])

    def test_hailstone_5(self):
        self.assertEqual(hailstone(5), [5, 16, 8, 4, 2, 1])

    def test_hailstone_7(self):
        self.assertEqual(hailstone(7), [7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1])

    def test_hailstone_27(self):
        h = hailstone(27)
        self.assertEqual(len(h), 112)
        self.assertEqual(h[:4], [27, 82, 41, 124])
        self.assertEqual(h[-4:], [8, 4, 2, 1])

    def test_max_length_under_10(self):
        max_len, num = max((len(hailstone(i)), i) for i in range(1, 10))
        self.assertEqual((max_len,num),(20,9))
        
    def test_max_length_under_100(self):
        max_len, num = max((len(hailstone(i)), i) for i in range(1, 100))
        self.assertEqual((max_len, num), (119, 97))




