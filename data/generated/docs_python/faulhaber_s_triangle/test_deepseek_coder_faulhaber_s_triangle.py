import unittest
from fractions import Fraction
from faulhaber_s_triangle import faulhaberTriangle, faulhaberSum

class TestFaulhaberTriangle(unittest.TestCase):

    def test_faulhaber_triangle_first_10_rows(self):
        expected_rows = [
            [Fraction(1, 1)],
            [Fraction(1, 2), Fraction(1, 2)],
            [Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)],
            [Fraction(0, 1), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4)],
            [Fraction(-1, 30), Fraction(0, 1), Fraction(1, 3), Fraction(1, 2), Fraction(1, 5)],
            [Fraction(0, 1), Fraction(1, 12), Fraction(0, 1), Fraction(5, 12), Fraction(1, 2), Fraction(1, 6)],
            [Fraction(1, 42), Fraction(0, 1), Fraction(-1, 6), Fraction(0, 1), Fraction(1, 2), Fraction(1, 2), Fraction(1, 7)],
            [Fraction(0, 1), Fraction(-1, 12), Fraction(0, 1), Fraction(7, 24), Fraction(0, 1), Fraction(23, 24), Fraction(1, 2), Fraction(1, 8)],
            [Fraction(-1, 30), Fraction(0, 1), Fraction(1, 10), Fraction(0, 1), Fraction(-3, 20), Fraction(0, 1), Fraction(11, 10), Fraction(1, 2), Fraction(1, 9)],
            [Fraction(0, 1), Fraction(5, 66), Fraction(0, 1), Fraction(-7, 18), Fraction(0, 1), Fraction(25, 36), Fraction(0, 1), Fraction(9, 10), Fraction(1, 2), Fraction(1, 10)]
        ]
        actual_rows = faulhaberTriangle(9)
        self.assertEqual(actual_rows, expected_rows)

    def test_faulhaber_sum_17th_row(self):
        expected_sum = 10509700181562122750
        actual_sum = faulhaberSum(17, 1000)
        self.assertEqual(actual_sum, expected_sum)

if __name__ == '__main__':
    unittest.main()
