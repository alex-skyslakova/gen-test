import unittest
from gaussian_elimination import gauss, matmul, ratmat

class TestGaussianElimination(unittest.TestCase):

    def test_identity_matrix(self):
        a = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, x = gauss(a, b)
        self.assertEqual(det, 1)
        self.assertEqual(x, b)

    def test_3x3_matrix_inverse(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, x = gauss(a, b)
        self.assertEqual(det, -360)
        expected_inverse = [
            [-0.10277777777777776, 0.18888888888888888, -0.019444444444444438],
            [0.10555555555555554, 0.02222222222222223, -0.061111111111111116],
            [0.0638888888888889, -0.14444444444444446, 0.14722222222222223]
        ]
        for i in range(3):
            for j in range(3):
                self.assertAlmostEqual(x[i][j], expected_inverse[i][j], places=10)

    def test_2x2_matrix_inverse(self):
        a = [[4, 7], [2, 6]]
        b = [[1, 0], [0, 1]]
        det, x = gauss(a, b)
        self.assertEqual(det, 10)
        expected_inverse = [
            [0.6, -0.7],
            [-0.2, 0.4]
        ]
        for i in range(2):
            for j in range(2):
                self.assertAlmostEqual(x[i][j], expected_inverse[i][j], places=10)

    def test_matrix_multiplication_check(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, x = gauss(a, b)
        product = matmul(a, x)
        expected_product = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        for i in range(3):
            for j in range(3):
                self.assertAlmostEqual(product[i][j], expected_product[i][j], places=10)

    def test_fraction_matrix_inverse(self):
        a = [[2, 9, 4], [7, 5, 3], [6, 1, 8]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, x = gauss(ratmat(a), ratmat(b))
        self.assertEqual(det, -360)
        expected_inverse = [
            [-37/360, 17/90, -7/360],
            [19/180, 1/45, -11/180],
            [23/360, -13/90, 53/360]
        ]
        for i in range(3):
            for j in range(3):
                self.assertEqual(x[i][j], expected_inverse[i][j])

    def test_singular_matrix(self):
        a = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        b = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
        det, x = gauss(a, b)
        self.assertEqual(det, 0)
        # Since the matrix is singular, the inverse should not exist
        # We can't check the exact values of x, but we can check that it's not the identity matrix
        self.assertNotEqual(x, b)

if __name__ == '__main__':
    unittest.main()
