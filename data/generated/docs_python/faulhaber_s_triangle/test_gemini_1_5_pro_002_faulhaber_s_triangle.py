import unittest
from fractions import Fraction
from faulhaber_s_triangle import faulhaberTriangle, faulhaberSum

class TestFaulhaber(unittest.TestCase):

    def test_faulhaberTriangle_zero(self):
        self.assertEqual(faulhaberTriangle(0), [[Fraction(1, 1)]])

    def test_faulhaberTriangle_one(self):
        expected = [[Fraction(1, 1)], [Fraction(1, 2), Fraction(1, 2)]]
        self.assertEqual(faulhaberTriangle(1), expected)

    def test_faulhaberTriangle_two(self):
        expected = [[Fraction(1, 1)], [Fraction(1, 2), Fraction(1, 2)], 
                    [Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)]]
        self.assertEqual(faulhaberTriangle(2), expected)

    def test_faulhaberTriangle_three(self):
        expected = [[Fraction(1, 1)], [Fraction(1, 2), Fraction(1, 2)],
                    [Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)],
                    [Fraction(0, 1), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4)]]
        self.assertEqual(faulhaberTriangle(3), expected)


    def test_faulhaberSum_p0_n1(self):
        self.assertEqual(faulhaberSum(0, 1), 1)

    def test_faulhaberSum_p1_n2(self):
        self.assertEqual(faulhaberSum(1, 2), 3)

    def test_faulhaberSum_p2_n3(self):
        self.assertEqual(faulhaberSum(2, 3), 14)

    def test_faulhaberSum_p3_n4(self):
        self.assertEqual(faulhaberSum(3, 4), 100)

    def test_faulhaberSum_large(self):
        # Large number test, pre-calculated result
        self.assertEqual(faulhaberSum(17, 1000), 63382530011411470074835160268800) 
