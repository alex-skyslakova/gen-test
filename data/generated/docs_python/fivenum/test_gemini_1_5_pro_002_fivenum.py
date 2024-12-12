import unittest
import fivenum
import sys
from io import StringIO

class TestFivenum(unittest.TestCase):

    def test_empty_array(self):
        original_stderr = sys.stderr
        sys.stderr = StringIO()
        with self.assertRaises(SystemExit):
            fivenum.fivenum([])
        self.assertEqual(sys.stderr.getvalue().strip(), "you entered an empty array.")
        sys.stderr = original_stderr


    def test_single_element_array(self):
        self.assertEqual(fivenum.fivenum([5]), [5.0, 5.0, 5.0, 5.0, 5.0])

    def test_example_array(self):
        x = [0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594, 0.73438555, -0.03035726, 1.46675970,
             -0.74621349, -0.72588772, 0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469, 0.66206163,
             1.04312009, -0.10305385, 0.75775634, 0.32566578]
        expected_result = [-1.95059594, -0.868050605, 0.375365725, 0.960039895, 1.73131507]
        self.assertEqual(fivenum.fivenum(x), expected_result)


    def test_even_length_array(self):
        self.assertEqual(fivenum.fivenum([1, 2, 3, 4]), [1.0, 1.5, 2.5, 3.5, 4.0])


    def test_odd_length_array(self):
        self.assertEqual(fivenum.fivenum([1, 2, 3, 4, 5]), [1.0, 1.5, 3.0, 4.5, 5.0])

    def test_negative_numbers(self):
        self.assertEqual(fivenum.fivenum([-5, -4, -3, -2, -1]), [-5.0, -4.5, -3.0, -2.5, -1.0])


    def test_duplicate_numbers(self):
        self.assertEqual(fivenum.fivenum([1, 1, 2, 2, 3]), [1.0, 1.0, 2.0, 2.0, 3.0])


