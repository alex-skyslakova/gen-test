import unittest
from fractions import Fraction

class TestFractran(unittest.TestCase):

    def test_fractran_initial_sequence(self):
        # Test the initial sequence of numbers generated by the FRACTRAN program
        n = 2
        expected_sequence = [2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290, 770]
        generator = fractran(n)
        for expected in expected_sequence:
            self.assertEqual(next(generator), expected)

    def test_fractran_powers_of_two(self):
        # Test that the sequence contains the correct powers of 2
        n = 2
        powers_of_two = [4, 8, 32, 128, 2048, 8192, 131072, 524288]
        generator = fractran(n)
        for _ in range(1000):  # Limit the number of steps to avoid infinite loop
            current = next(generator)
            if current in powers_of_two:
                powers_of_two.remove(current)
        self.assertEqual(powers_of_two, [])

    def test_fractran_with_custom_fractions(self):
        # Test with a custom list of fractions
        n = 2
        custom_fstring = '2/3, 3/4, 4/5'
        generator = fractran(n, custom_fstring)
        expected_sequence = [2, 3, 4, 5]
        for expected in expected_sequence:
            self.assertEqual(next(generator), expected)

    def test_fractran_with_no_valid_fractions(self):
        # Test when no fraction can be applied (should halt immediately)
        n = 3
        custom_fstring = '2/3, 3/4, 4/5'
        generator = fractran(n, custom_fstring)
        self.assertEqual(next(generator), 3)
        with self.assertRaises(StopIteration):
            next(generator)

    def test_fractran_with_large_initial_value(self):
        # Test with a large initial value
        n = 1000
        generator = fractran(n)
        self.assertEqual(next(generator), 1000)

    def test_fractran_with_zero_initial_value(self):
        # Test with an initial value of zero (should halt immediately)
        n = 0
        generator = fractran(n)
        with self.assertRaises(StopIteration):
            next(generator)

    def test_fractran_with_negative_initial_value(self):
        # Test with a negative initial value (should halt immediately)
        n = -1
        generator = fractran(n)
        with self.assertRaises(StopIteration):
            next(generator)

if __name__ == '__main__':
    unittest.main()
