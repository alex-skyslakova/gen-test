import unittest
from lu_decomposition import lu

class TestLUDecomposition(unittest.TestCase):

    def assertMatrixAlmostEqual(self, A, B, places=5):
        """Helper method to compare two matrices with a given precision."""
        for rowA, rowB in zip(A, B):
            for valA, valB in zip(rowA, rowB):
                self.assertAlmostEqual(valA, valB, places=places)

    def test_example_1(self):
        A = [
            [1, 3, 5],
            [2, 4, 7],
            [1, 1, 0]
        ]
        expected_L = [
            [1.00000, 0.00000, 0.00000],
            [0.50000, 1.00000, 0.00000],
            [0.50000, -1.00000, 1.00000]
        ]
        expected_U = [
            [2.00000, 4.00000, 7.00000],
            [0.00000, 1.00000, 1.50000],
            [0.00000, 0.00000, -2.00000]
        ]
        expected_P = [
            [0, 1, 0],
            [1, 0, 0],
            [0, 0, 1]
        ]

        L, U, P = lu(A)
        self.assertMatrixAlmostEqual(L, expected_L)
        self.assertMatrixAlmostEqual(U, expected_U)
        self.assertMatrixAlmostEqual(P, expected_P)

    def test_example_2(self):
        A = [
            [11, 9, 24, 2],
            [1, 5, 2, 6],
            [3, 17, 18, 1],
            [2, 5, 7, 1]
        ]
        expected_L = [
            [1.00000, 0.00000, 0.00000, 0.00000],
            [0.27273, 1.00000, 0.00000, 0.00000],
            [0.09091, 0.28750, 1.00000, 0.00000],
            [0.18182, 0.23125, 0.00360, 1.00000]
        ]
        expected_U = [
            [11.00000, 9.00000, 24.00000, 2.00000],
            [0.00000, 14.54545, 11.45455, 0.45455],
            [0.00000, 0.00000, -3.47500, 5.68750],
            [0.00000, 0.00000, 0.00000, 0.51079]
        ]
        expected_P = [
            [1, 0, 0, 0],
            [0, 0, 1, 0],
            [0, 1, 0, 0],
            [0, 0, 0, 1]
        ]

        L, U, P = lu(A)
        self.assertMatrixAlmostEqual(L, expected_L)
        self.assertMatrixAlmostEqual(U, expected_U)
        self.assertMatrixAlmostEqual(P, expected_P)

if __name__ == '__main__':
    unittest.main()
