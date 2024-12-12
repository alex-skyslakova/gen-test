import unittest
from cramer_s_rule import det

class TestCramersRule(unittest.TestCase):

    def test_determinant_2x2(self):
        matrix = [
            [4, 3],
            [3, 2]
        ]
        self.assertEqual(det(matrix, 2), -1)

    def test_determinant_3x3(self):
        matrix = [
            [1, 2, 3],
            [0, 1, 4],
            [5, 6, 0]
        ]
        self.assertEqual(det(matrix, 3), 1)

    def test_determinant_4x4(self):
        matrix = [
            [2, -1, 5, 1],
            [3, 2, 2, -6],
            [1, 3, 3, -1],
            [5, -2, -3, 3]
        ]
        self.assertEqual(det(matrix, 4), -418)

    def test_solve_system(self):
        h = [
            [2, -1, 5, 1],
            [3, 2, 2, -6],
            [1, 3, 3, -1],
            [5, -2, -3, 3]
        ]
        t = [-3, -32, -47, 49]
        w = len(t)
        d = det(h, w)
        if d == 0:
            result = []
        else:
            result = [det([r[0:i] + [s] + r[i+1:] for r, s in zip(h, t)], w) / d for i in range(w)]
        
        expected_result = [1, -2, -3, 4]  # Replace with the correct expected result
        self.assertEqual(result, expected_result)

    def test_singular_matrix(self):
        matrix = [
            [1, 2, 3],
            [2, 4, 6],
            [3, 6, 9]
        ]
        self.assertEqual(det(matrix, 3), 0)

if __name__ == '__main__':
    unittest.main()
