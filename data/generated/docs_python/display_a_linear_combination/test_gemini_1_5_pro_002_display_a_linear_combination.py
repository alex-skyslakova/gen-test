import unittest
from display_a_linear_combination import linear

class TestLinear(unittest.TestCase):

    def test_all_nonzero(self):
        self.assertEqual(linear([1, 2, 3]), "e(1) + 2*e(2) + 3*e(3)")

    def test_leading_zero(self):
        self.assertEqual(linear([0, 1, 2, 3]), "e(2) + 2*e(3) + 3*e(4)")

    def test_middle_zero(self):
        self.assertEqual(linear([1, 0, 3, 4]), "e(1) + 3*e(3) + 4*e(4)")

    def test_trailing_zero(self):
        self.assertEqual(linear([1, 2, 0]), "e(1) + 2*e(2)")

    def test_all_zeros(self):
        self.assertEqual(linear([0, 0, 0]), "0")

    def test_single_zero(self):
        self.assertEqual(linear([0]), "0")

    def test_all_ones(self):
        self.assertEqual(linear([1, 1, 1]), "e(1) + e(2) + e(3)")

    def test_all_negative_ones(self):
        self.assertEqual(linear([-1, -1, -1]), "-e(1) - e(2) - e(3)")

    def test_mixed_negative(self):
        self.assertEqual(linear([-1, -2, 0, -3]), "-e(1) - 2*e(2) - 3*e(4)")
    
    def test_single_negative_one(self):
        self.assertEqual(linear([-1]), "-e(1)")

