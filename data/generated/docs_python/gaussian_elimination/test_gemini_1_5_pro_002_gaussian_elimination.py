import unittest
from fractions import Fraction
from gaussian_elimination import gauss, zeromat, matmul, ratmat

class TestGauss(unittest.TestCase):

    def test_singular_matrix(self):
        a = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        b = [[1], [2], [3]]
        with self.assertRaises(ZeroDivisionError):  # Expecting division by zero during elimination
            gauss(a, b)

    def test_2x2_matrix(self):
        a = [[2, 1], [1, 2]]
        b = [[3], [4]]
        det, x = gauss(a, b)
        self.assertEqual(det, 3.0)
        self.assertListEqual(x, [[1.0], [1.0]])

    def test_3x3_matrix_integer(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, c = gauss(a, b)
        self.assertEqual(det, -360.0)
        expected_c = [[Fraction(-37, 360), Fraction(17, 90), Fraction(-7, 360)],
                      [Fraction(19, 180), Fraction(1, 45), Fraction(-11, 180)],
                      [Fraction(23, 360), Fraction(-13, 90), Fraction(53, 360)]]
        self.assertEqual(c, [[float(x) for x in row] for row in expected_c])
        result = matmul(a,c)
        # Due to float inaccuracies, direct comparison often fails. Check with a tolerance.
        expected_result = ratmat([[1,0,0], [0,1,0], [0,0,1]])
        self.assertTrue(all(abs(result[i][j]-expected_result[i][j]) < 1e-10 for i in range(len(result)) for j in range(len(result[0])) ))



    def test_3x3_matrix_fraction(self):
        a = ratmat([[2, 9, 4], [7, 5, 3], [6, 1, 8]])
        b = ratmat([[1, 0, 0], [0, 1, 0], [0, 0, 1]])
        det, c = gauss(a, b)
        self.assertEqual(det, Fraction(-360, 1))
        self.assertEqual(c, [[Fraction(-37, 360), Fraction(17, 90), Fraction(-7, 360)],
                           [Fraction(19, 180), Fraction(1, 45), Fraction(-11, 180)],
                           [Fraction(23, 360), Fraction(-13, 90), Fraction(53, 360)]])
        self.assertEqual(matmul(a, c), ratmat([[1, 0, 0], [0, 1, 0], [0, 0, 1]]))



    def test_rectangular_matrix(self):
        a = [[1, 2], [3, 4], [5, 6]]
        b = [[7], [8], [9]]
        with self.assertRaises(IndexError):  # gauss expects square matrix 'a'
            gauss(a, b)

    def test_empty_matrix(self):
        a = []
        b = []
        det, x = gauss(a, b) # should handle empty matrix without crashing
        self.assertEqual(det, 1) # Empty product should return 1
        self.assertEqual(x, [])

    def test_zero_pivoting(self):
        a = [[0, 1], [1, 0]]
        b = [[1], [2]]
        det, x = gauss(a, b)
        self.assertEqual(det, -1.0)
        self.assertEqual(x, [[2.0], [1.0]])

if __name__ == "__main__":
    unittest.main()

