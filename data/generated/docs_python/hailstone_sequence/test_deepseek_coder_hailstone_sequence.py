import unittest
from hailstone_sequence import hailstone

class TestHailstoneSequence(unittest.TestCase):

    def test_hailstone_sequence_for_27(self):
        h = hailstone(27)
        self.assertEqual(len(h), 112)
        self.assertEqual(h[:4], [27, 82, 41, 124])
        self.assertEqual(h[-4:], [8, 4, 2, 1])

    def test_hailstone_sequence_for_1(self):
        h = hailstone(1)
        self.assertEqual(h, [1])

    def test_hailstone_sequence_for_even_number(self):
        h = hailstone(4)
        self.assertEqual(h, [4, 2, 1])

    def test_hailstone_sequence_for_odd_number(self):
        h = hailstone(3)
        self.assertEqual(h, [3, 10, 5, 16, 8, 4, 2, 1])

    def test_hailstone_sequence_for_large_number(self):
        h = hailstone(99999)
        self.assertTrue(len(h) > 1)
        self.assertEqual(h[-1], 1)

    def test_hailstone_sequence_for_longest_under_100000(self):
        max_length, number = max((len(hailstone(i)), i) for i in range(1, 100000))
        self.assertEqual(max_length, 351)
        self.assertEqual(number, 77031)

if __name__ == '__main__':
    unittest.main()
