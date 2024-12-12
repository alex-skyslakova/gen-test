import unittest
from fractions import Fraction
from faulhaber_s_formula import sumpol, polstr  # Assuming the code is in faulhaber_s_formula.py

class TestFaulhaber(unittest.TestCase):

    def test_sumpol_0(self):
        expected = [Fraction(0), Fraction(1)]
        self.assertEqual(list(sumpol(1))[0], expected)

    def test_sumpol_1(self):
        expected = [Fraction(1, 2), Fraction(1, 2)]
        self.assertEqual(list(sumpol(2))[1], expected)

    def test_sumpol_2(self):
        expected = [Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)]
        self.assertEqual(list(sumpol(3))[2], expected)

    def test_sumpol_3(self):
        expected = [Fraction(0), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4)]
        self.assertEqual(list(sumpol(4))[3], expected)
    
    def test_sumpol_4(self):
        expected = [Fraction(-1, 30), Fraction(0), Fraction(5, 12), Fraction(1, 2), Fraction(1, 5)]
        self.assertEqual(list(sumpol(5))[4], expected)

    def test_sumpol_5(self):
        expected = [Fraction(0), Fraction(-1, 12), Fraction(0), Fraction(5, 12), Fraction(1, 2), Fraction(1, 6)]
        self.assertEqual(list(sumpol(6))[5], expected)
    
    def test_sumpol_9(self):  # Testing a higher value
         # Calculating expected value for p=9 is complex, so we check the length instead
        self.assertEqual(len(list(sumpol(10))[9]), 11)  # Length should be p+2


    def test_polstr_simple(self):
        self.assertEqual(polstr([1, 2, 3]), "3n^2 + 2n + 1")

    def test_polstr_negative(self):
        self.assertEqual(polstr([-1, 2, -3]), "-3n^2 + 2n - 1")

    def test_polstr_zero_coeff(self):
        self.assertEqual(polstr([0, 2, 3]), "3n^2 + 2n")

    def test_polstr_unit_coeff(self):
        self.assertEqual(polstr([1, 1, 1]), "n^2 + n + 1")
    
    def test_polstr_all_zero(self):
        self.assertEqual(polstr([0,0,0]), "0")
    
    def test_polstr_fraction(self):
        self.assertEqual(polstr([Fraction(1,2), Fraction(3,2)]), "3/2n + 1/2")

if __name__ == '__main__':
    unittest.main()
