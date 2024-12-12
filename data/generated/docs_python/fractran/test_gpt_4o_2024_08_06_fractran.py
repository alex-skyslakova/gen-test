import unittest
from fractions import Fraction
from fractran import fractran

class TestFractran(unittest.TestCase):

    def test_initial_sequence(self):
        # Test the initial sequence with n=2
        expected_sequence = [2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290, 770]
        result_sequence = list(f for f, _ in zip(fractran(2), range(len(expected_sequence))))
        self.assertEqual(result_sequence, expected_sequence)

    def test_custom_fractions(self):
        # Test with a custom fraction list
        custom_fractions = '3 / 2, 5 / 3, 7 / 5'
        expected_sequence = [2, 3, 5, 7]
        result_sequence = list(f for f, _ in zip(fractran(2, custom_fractions), range(len(expected_sequence))))
        self.assertEqual(result_sequence, expected_sequence)

    def test_no_fraction_applies(self):
        # Test when no fraction applies, should return the initial number
        custom_fractions = '3 / 4, 5 / 6'
        expected_sequence = [2]
        result_sequence = list(f for f, _ in zip(fractran(2, custom_fractions), range(len(expected_sequence))))
        self.assertEqual(result_sequence, expected_sequence)

    def test_large_number_of_steps(self):
        # Test with a large number of steps
        m = 20
        result_sequence = list(f for f, _ in zip(fractran(2), range(m)))
        self.assertEqual(len(result_sequence), m)

    def test_fraction_parsing(self):
        # Test if fractions are parsed correctly
        fstring = '3 / 2, 5 / 3, 7 / 5'
        flist = [Fraction(f) for f in fstring.replace(' ', '').split(',')]
        expected_fractions = [Fraction(3, 2), Fraction(5, 3), Fraction(7, 5)]
        self.assertEqual(flist, expected_fractions)

if __name__ == '__main__':
    unittest.main()
