import unittest
from itertools import repeat
from math import ceil, sqrt

# Assuming the functions are imported from the module
# from first_perfect_square_in_base_n_with_n_unique_digits import allDigitSquare, missingDigitsAtBase, digit, enumFromTo, showIntAtBase

class TestPerfectSquare(unittest.TestCase):

    def test_allDigitSquare(self):
        # Test for base 2
        self.assertEqual(allDigitSquare(2, 0), 3)
        # Test for base 10
        self.assertEqual(allDigitSquare(10, 0), 32043)
        # Test for base 16
        self.assertEqual(allDigitSquare(16, 0), 523)

    def test_missingDigitsAtBase(self):
        # Test for base 2
        bools = list(repeat(True, 2))
        self.assertTrue(missingDigitsAtBase(2, bools)(1))
        self.assertFalse(missingDigitsAtBase(2, bools)(3))
        
        # Test for base 10
        bools = list(repeat(True, 10))
        self.assertTrue(missingDigitsAtBase(10, bools)(123456789))
        self.assertFalse(missingDigitsAtBase(10, bools)(1026753849))

    def test_digit(self):
        self.assertEqual(digit(0), '0')
        self.assertEqual(digit(9), '9')
        self.assertEqual(digit(10), 'a')
        self.assertEqual(digit(15), 'f')

    def test_enumFromTo(self):
        self.assertEqual(enumFromTo(2)(5), [2, 3, 4, 5])
        self.assertEqual(enumFromTo(0)(0), [0])
        self.assertEqual(enumFromTo(5)(2), [])

    def test_showIntAtBase(self):
        # Test for base 10
        self.assertEqual(showIntAtBase(10)(digit)(32043)(''), '32043')
        # Test for base 16
        self.assertEqual(showIntAtBase(16)(digit)(523)(''), '20b')
        # Test for base 2
        self.assertEqual(showIntAtBase(2)(digit)(3)(''), '11')

if __name__ == '__main__':
    unittest.main()
