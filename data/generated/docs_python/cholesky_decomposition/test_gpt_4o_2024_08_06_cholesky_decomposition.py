import unittest
from cholesky_decomposition import cholesky

class TestCholeskyDecomposition(unittest.TestCase):
    
    def test_example_1(self):
        A = [
            [25, 15, -5],
            [15, 18, 0],
            [-5, 0, 11]
        ]
        expected_L = [
            [5, 0, 0],
            [3, 3, 0],
            [-1, 1, 3]
        ]
        result_L = cholesky(A)
        for i in range(len(expected_L)):
            for j in range(len(expected_L[i])):
                self.assertAlmostEqual(result_L[i][j], expected_L[i][j], places=5)

    def test_example_2(self):
        A = [
            [18, 22, 54, 42],
            [22, 70, 86, 62],
            [54, 86, 174, 134],
            [42, 62, 134, 106]
        ]
        expected_L = [
            [4.24264, 0.00000, 0.00000, 0.00000],
            [5.18545, 6.56591, 0.00000, 0.00000],
            [12.72792, 3.04604, 1.64974, 0.00000],
            [9.89949, 1.62455, 1.84971, 1.39262]
        ]
        result_L = cholesky(A)
        for i in range(len(expected_L)):
            for j in range(len(expected_L[i])):
                self.assertAlmostEqual(result_L[i][j], expected_L[i][j], places=5)

if __name__ == '__main__':
    unittest.main()
