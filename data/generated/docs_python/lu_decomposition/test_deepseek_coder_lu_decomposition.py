import unittest
from lu_decomposition import lu, matrixMul

class TestLUDecomposition(unittest.TestCase):

    def test_example_1(self):
        A = [[1, 3, 5], [2, 4, 7], [1, 1, 0]]
        L_expected = [[1.0, 0.0, 0.0], [0.5, 1.0, 0.0], [0.5, -1.0, 1.0]]
        U_expected = [[2.0, 4.0, 7.0], [0.0, 1.0, 1.5], [0.0, 0.0, -2.0]]
        P_expected = [[0, 1, 0], [1, 0, 0], [0, 0, 1]]

        L, U, P = lu(A)

        self.assertEqual(L, L_expected)
        self.assertEqual(U, U_expected)
        self.assertEqual(P, P_expected)

    def test_example_2(self):
        A = [[11, 9, 24, 2], [1, 5, 2, 6], [3, 17, 18, 1], [2, 5, 7, 1]]
        L_expected = [[1.0, 0.0, 0.0, 0.0], [0.27273, 1.0, 0.0, 0.0], [0.09091, 0.2875, 1.0, 0.0], [0.18182, 0.23125, 0.0036, 1.0]]
        U_expected = [[11.0, 9.0, 24.0, 2.0], [0.0, 14.54545, 11.45455, 0.45455], [0.0, 0.0, -3.475, 5.6875], [0.0, 0.0, 0.0, 0.51079]]
        P_expected = [[1, 0, 0, 0], [0, 0, 1, 0], [0, 1, 0, 0], [0, 0, 0, 1]]

        L, U, P = lu(A)

        self.assertEqual(L, L_expected)
        self.assertEqual(U, U_expected)
        self.assertEqual(P, P_expected)

    def test_matrix_multiplication(self):
        A = [[1, 2], [3, 4]]
        B = [[5, 6], [7, 8]]
        result_expected = [[19, 22], [43, 50]]

        result = matrixMul(A, B)

        self.assertEqual(result, result_expected)

if __name__ == '__main__':
    unittest.main()
