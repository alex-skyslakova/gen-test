import unittest
from find_the_intersection_of_two_lines import line_intersect

class TestLineIntersect(unittest.TestCase):

    def test_intersection_exists(self):
        # Test case where lines intersect
        Ax1, Ay1, Ax2, Ay2 = 4, 0, 6, 10
        Bx1, By1, Bx2, By2 = 0, 3, 10, 7
        result = line_intersect(Ax1, Ay1, Ax2, Ay2, Bx1, By1, Bx2, By2)
        self.assertIsNotNone(result)
        self.assertAlmostEqual(result[0], 5.2, places=1)
        self.assertAlmostEqual(result[1], 5.0, places=1)

    def test_no_intersection_parallel(self):
        # Test case where lines are parallel and do not intersect
        Ax1, Ay1, Ax2, Ay2 = 0, 0, 10, 10
        Bx1, By1, Bx2, By2 = 0, 1, 10, 11
        result = line_intersect(Ax1, Ay1, Ax2, Ay2, Bx1, By1, Bx2, By2)
        self.assertIsNone(result)

    def test_no_intersection_collinear(self):
        # Test case where lines are collinear and do not intersect within segments
        Ax1, Ay1, Ax2, Ay2 = 0, 0, 10, 10
        Bx1, By1, Bx2, By2 = 20, 20, 30, 30
        result = line_intersect(Ax1, Ay1, Ax2, Ay2, Bx1, By1, Bx2, By2)
        self.assertIsNone(result)

    def test_intersection_at_endpoint(self):
        # Test case where lines intersect at an endpoint
        Ax1, Ay1, Ax2, Ay2 = 0, 0, 5, 5
        Bx1, By1, Bx2, By2 = 5, 5, 10, 0
        result = line_intersect(Ax1, Ay1, Ax2, Ay2, Bx1, By1, Bx2, By2)
        self.assertIsNotNone(result)
        self.assertEqual(result, (5, 5))

if __name__ == '__main__':
    unittest.main()
