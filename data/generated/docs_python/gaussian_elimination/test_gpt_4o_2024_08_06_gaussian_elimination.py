import unittest
from fractions import Fraction
from gaussian_elimination import gauss, matmul, ratmat

class TestGaussianElimination(unittest.TestCase):

    def test_determinant_and_inverse(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, inv = gauss(a, b)
        
        expected_det = -360.0
        expected_inv = [
            [-0.10277777777777776, 0.18888888888888888, -0.019444444444444438],
            [0.10555555555555554, 0.02222222222222223, -0.061111111111111116],
            [0.0638888888888889, -0.14444444444444446, 0.14722222222222223]
        ]
        
        self.assertAlmostEqual(det, expected_det)
        for row, expected_row in zip(inv, expected_inv):
            for value, expected_value in zip(row, expected_row):
                self.assertAlmostEqual(value, expected_value)

    def test_product_with_inverse(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        _, inv = gauss(a, b)
        
        product = matmul(a, inv)
        expected_product = [
            [1.0, 0.0, 0.0],
            [5.551115123125783e-17, 1.0, 0.0],
            [1.1102230246251565e-16, -2.220446049250313e-16, 1.0]
        ]
        
        for row, expected_row in zip(product, expected_product):
            for value, expected_value in zip(row, expected_row):
                self.assertAlmostEqual(value, expected_value)

    def test_determinant_and_inverse_with_fractions(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, inv = gauss(ratmat(a), ratmat(b))
        
        expected_det = Fraction(-360, 1)
        expected_inv = [
            [Fraction(-37, 360), Fraction(17, 90), Fraction(-7, 360)],
            [Fraction(19, 180), Fraction(1, 45), Fraction(-11, 180)],
            [Fraction(23, 360), Fraction(-13, 90), Fraction(53, 360)]
        ]
        
        self.assertEqual(det, expected_det)
        self.assertEqual(inv, expected_inv)

    def test_product_with_inverse_fractions(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        _, inv = gauss(ratmat(a), ratmat(b))
        
        product = matmul(a, inv)
        expected_product = [
            [Fraction(1, 1), Fraction(0, 1), Fraction(0, 1)],
            [Fraction(0, 1), Fraction(1, 1), Fraction(0, 1)],
            [Fraction(0, 1), Fraction(0, 1), Fraction(1, 1)]
        ]
        
        self.assertEqual(product, expected_product)

if __name__ == '__main__':
    unittest.main()
