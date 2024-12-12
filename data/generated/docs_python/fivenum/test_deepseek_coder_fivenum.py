import unittest
import math
import sys
from fivenum import fivenum  # Assuming the code is in a file named fivenum.py

class TestFiveNum(unittest.TestCase):

    def test_empty_array(self):
        with self.assertRaises(SystemExit):
            fivenum([])

    def test_single_element_array(self):
        self.assertEqual(fivenum([5]), [5, 5, 5, 5, 5])

    def test_two_element_array(self):
        self.assertEqual(fivenum([1, 2]), [1, 1.25, 1.5, 1.75, 2])

    def test_odd_length_array(self):
        self.assertEqual(fivenum([1, 2, 3, 4, 5]), [1, 2, 3, 4, 5])

    def test_even_length_array(self):
        self.assertEqual(fivenum([1, 2, 3, 4]), [1, 1.5, 2.5, 3.5, 4])

    def test_negative_numbers(self):
        self.assertEqual(fivenum([-5, -3, -1, 1, 3, 5]), [-5, -3, -0.5, 3, 5])

    def test_mixed_numbers(self):
        self.assertEqual(fivenum([-1, 0, 1, 2, 3, 4, 5]), [-1, 0.5, 2, 3.5, 5])

    def test_large_numbers(self):
        self.assertEqual(fivenum([100, 200, 300, 400, 500]), [100, 200, 300, 400, 500])

    def test_decimal_numbers(self):
        self.assertEqual(fivenum([0.1, 0.2, 0.3, 0.4, 0.5]), [0.1, 0.2, 0.3, 0.4, 0.5])

    def test_provided_example(self):
        x = [0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594, 0.73438555, -0.03035726, 1.46675970,
             -0.74621349, -0.72588772, 0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469, 0.66206163,
             1.04312009, -0.10305385, 0.75775634, 0.32566578]
        expected = [-1.95059594, -0.676741205, 0.23324706, 0.746070945, 1.73131507]
        self.assertEqual(fivenum(x), expected)

if __name__ == '__main__':
    unittest.main()
