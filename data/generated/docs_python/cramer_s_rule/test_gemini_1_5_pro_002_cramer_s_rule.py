import unittest
from cramer_s_rule import det

class TestCramersRule(unittest.TestCase):

    def test_determinant(self):
        self.assertEqual(det([[1]], 1), 1)
        self.assertEqual(det([[1, 2], [3, 4]], 2), -2)
        self.assertEqual(det([[1, 2, 3], [4, 5, 6], [7, 8, 9]], 3), 0)
        self.assertEqual(det([[1, 2, 3], [4, 5, 6], [7, 8, 10]], 3), -3)

    def test_cramers_rule_unique_solution(self):
        # Example system with a unique solution
        h = [[2, -1, 5, 1], [3, 2, 2, -6], [1, 3, 3, -1], [5, -2, -3, 3]]
        t = [-3, -32, -47, 49]

        w = len(t)
        d = det(h, w)
        
        # Check for zero determinant to avoid division by zero.
        # Although the original code did this, good practice in tests
        # is to check it explicitly, rather than rely on exception handling.
        self.assertNotEqual(d, 0)
        
        r = [det([r[0:i] + [s] + r[i+1:] for r, s in zip(h, t)], w) / d for i in range(w)]
        self.assertAlmostEqual(r[0], 2.0)
        self.assertAlmostEqual(r[1], -5.0)
        self.assertAlmostEqual(r[2], -6.0)
        self.assertAlmostEqual(r[3], 3.0)

    def test_cramers_rule_no_solution(self):
         # Example system with no solution (dependent rows)
        h = [[1, 1, 1], [2, 2, 2], [1, 2, 3]]
        t = [1, 2, 3]  # Note:  dependent rows but independent augmented column.
        w = len(t)
        d = det(h,w)
        self.assertEqual(d, 0)  # Expecting a zero determinant


    def test_cramers_rule_infinite_solutions(self):
        # Example with infinite solutions (dependent rows, augmented column consistent)
        h = [[1, 1, 1], [2, 2, 2], [1, 2, 3]]
        t = [1, 2, 4]  #  dependent rows and a dependent augmented column
        w = len(t)
        d = det(h,w)
        self.assertEqual(d, 0) # Expecting a zero determinant


if __name__ == '__main__':
    unittest.main()

