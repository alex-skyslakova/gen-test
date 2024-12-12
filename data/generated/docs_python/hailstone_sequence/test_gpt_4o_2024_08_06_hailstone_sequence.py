import unittest
from hailstone_sequence import hailstone

class TestHailstoneSequence(unittest.TestCase):

    def test_hailstone_sequence_27(self):
        # Test the hailstone sequence for the number 27
        sequence = hailstone(27)
        self.assertEqual(len(sequence), 112)
        self.assertEqual(sequence[:4], [27, 82, 41, 124])
        self.assertEqual(sequence[-4:], [8, 4, 2, 1])

    def test_hailstone_sequence_1(self):
        # Test the hailstone sequence for the number 1
        sequence = hailstone(1)
        self.assertEqual(sequence, [1])

    def test_hailstone_sequence_even(self):
        # Test the hailstone sequence for an even number
        sequence = hailstone(16)
        self.assertEqual(sequence, [16, 8, 4, 2, 1])

    def test_hailstone_sequence_odd(self):
        # Test the hailstone sequence for an odd number
        sequence = hailstone(7)
        self.assertEqual(sequence, [7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1])

    def test_longest_hailstone_sequence_under_100000(self):
        # Test to find the number less than 100,000 with the longest hailstone sequence
        max_length = 0
        number_with_max_length = 0
        for i in range(1, 100000):
            length = len(hailstone(i))
            if length > max_length:
                max_length = length
                number_with_max_length = i
        self.assertEqual(number_with_max_length, 77031)  # Known result
        self.assertEqual(max_length, 351)  # Known result

if __name__ == '__main__':
    unittest.main()
