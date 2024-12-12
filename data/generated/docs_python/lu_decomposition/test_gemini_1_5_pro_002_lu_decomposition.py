import unittest
from lu_decomposition import lu, matrixMul

class TestLUDecomposition(unittest.TestCase):

    def test_lu_decomposition_example1(self):
        a = [[1, 3, 5], [2, 4, 7], [1, 1, 0]]
        l_expected = [[1.0, 0.0, 0.0], [0.5, 1.0, 0.0], [0.5, -1.0, 1.0]]
        u_expected = [[2.0, 4.0, 7.0], [0.0, 1.0, 1.5], [0.0, 0.0, -2.0]]
        p_expected = [[0.0, 1.0, 0.0], [1.0, 0.0, 0.0], [0.0, 0.0, 1.0]]

        l, u, p = lu(a)

        self.assertEqual(l, l_expected)
        self.assertEqual(u, u_expected)
        self.assertEqual(p, p_expected)


    def test_lu_decomposition_example2(self):
        b = [[11, 9, 24, 2], [1, 5, 2, 6], [3, 17, 18, 1], [2, 5, 7, 1]]
        l_expected = [[1.0, 0.0, 0.0, 0.0],
                       [0.2727272727272727, 1.0, 0.0, 0.0],
                       [0.09090909090909091, 0.2875, 1.0, 0.0],
                       [0.18181818181818182, 0.23125, 0.0036036036036036037, 1.0]]
        u_expected = [[11.0, 9.0, 24.0, 2.0],
                       [0.0, 14.545454545454545, 11.454545454545455, 0.45454545454545453],
                       [0.0, 0.0, -3.475, 5.6875],
                       [0.0, 0.0, 0.0, 0.5107913669064749]]
        p_expected = [[1.0, 0.0, 0.0, 0.0],
                       [0.0, 0.0, 1.0, 0.0],
                       [0.0, 1.0, 0.0, 0.0],
                       [0.0, 0.0, 0.0, 1.0]]


        l, u, p = lu(b)

        for row_index in range(len(l)):
            for col_index in range(len(l[0])):
                self.assertAlmostEqual(l[row_index][col_index], l_expected[row_index][col_index], places=7)
                self.assertAlmostEqual(u[row_index][col_index], u_expected[row_index][col_index], places=7)
                self.assertAlmostEqual(p[row_index][col_index], p_expected[row_index][col_index], places=7)

    def test_matrix_mul(self): # Added test for the helper function matrixMul
      A = [[1,2], [3,4]]
      B = [[5,6], [7,8]]
      expected_result = [[19, 22], [43, 50]]
      self.assertEqual(matrixMul(A,B), expected_result)
      
if __name__ == "__main__":
    unittest.main()
