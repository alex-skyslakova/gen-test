import unittest
from math import isclose
from cholesky_decomposition import cholesky

class TestCholeskyDecomposition(unittest.TestCase):

    def test_example_1(self):
        A = [[25, 15, -5],
             [15, 18,  0],
             [-5,  0, 11]]
        expected_L = [[5.0, 0.0, 0.0],
                      [3.0, 3.0, 0.0],
                      [-1.0, 1.0, 3.0]]
        L = cholesky(A)
        self.assert_matrix_equal(L, expected_L)

    def test_example_2(self):
        A = [[18, 22,  54,  42],
             [22, 70,  86,  62],
             [54, 86, 174, 134],
             [42, 62, 134, 106]]
        expected_L = [[4.24264, 0.00000, 0.00000, 0.00000],
                      [5.18545, 6.56591, 0.00000, 0.00000],
                      [12.72792, 3.04604, 1.64974, 0.00000],
                      [9.89949, 1.62455, 1.84971, 1.39262]]
        L = cholesky(A)
        self.assert_matrix_equal(L, expected_L)

    def assert_matrix_equal(self, L, expected_L):
        for i in range(len(L)):
            for j in range(len(L[i])):
                self.assertTrue(isclose(L[i][j], expected_L[i][j], rel_tol=1e-5),
                                f"Mismatch at ({i}, {j}): expected {expected_L[i][j]}, got {L[i][j]}")

if __name__ == "__main__":
    unittest.main()
