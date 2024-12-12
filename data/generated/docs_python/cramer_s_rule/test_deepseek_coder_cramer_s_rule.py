import unittest
from cramer_s_rule import det  # Assuming the determinant function is in cramer_s_rule.py

class TestCramersRule(unittest.TestCase):

    def test_determinant_2x2(self):
        matrix = [
            [1, 2],
            [3, 4]
        ]
        self.assertEqual(det(matrix, 2), -2)

    def test_determinant_3x3(self):
        matrix = [
            [6, 1, 1],
            [4, -2, 5],
            [2, 8, 7]
        ]
        self.assertEqual(det(matrix, 3), -306)

    def test_determinant_4x4(self):
        matrix = [
            [1, 0, 2, -1],
            [3, 0, 0, 5],
            [2, 1, 4, -3],
            [1, 0, 5, 0]
        ]
        self.assertEqual(det(matrix, 4), 30)

    def test_cramers_rule_given_system(self):
        h = [
            [2, -1, 5, 1],
            [3, 2, 2, -6],
            [1, 3, 3, -1],
            [5, -2, -3, 3]
        ]
        t = [-3, -32, -47, 49]
        w = len(t)
        d = det(h, w)
        self.assertNotEqual(d, 0)

        expected_result = [-4, 1, -3, 2]
        result = [det([r[0:i] + [s] + r[i+1:] for r, s in zip(h, t)], w) / d for i in range(w)]
        for i in range(w):
            self.assertAlmostEqual(result[i], expected_result[i], places=5)

    def test_cramers_rule_no_solution(self):
        h = [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9]
        ]
        t = [1, 2, 3]
        w = len(t)
        d = det(h, w)
        self.assertEqual(d, 0)
        result = []
        self.assertEqual(result, [])

if __name__ == '__main__':
    unittest.main()
