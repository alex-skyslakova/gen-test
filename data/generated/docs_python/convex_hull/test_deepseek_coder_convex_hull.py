import unittest
from shapely.geometry import MultiPoint

class TestConvexHull(unittest.TestCase):

    def test_convex_hull_example(self):
        # Example points from the prompt
        pts = MultiPoint([(16,3), (12,17), (0,6), (-4,-6), (16,6), (16,-7), (16,-3), (17,-4), (5,19), (19,-8), (3,16), (12,13), (3,-4), (17,5), (-3,15), (-3,-9), (0,11), (-9,-3), (-4,-2), (12,10)])
        expected_hull = MultiPoint([(-9,-3), (-3,-9), (19,-8), (17,5), (12,17), (5,19), (-3,15)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

    def test_convex_hull_single_point(self):
        # Single point should return the point itself
        pts = MultiPoint([(1, 1)])
        expected_hull = MultiPoint([(1, 1)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

    def test_convex_hull_collinear_points(self):
        # Collinear points should return the line segment
        pts = MultiPoint([(0, 0), (1, 1), (2, 2)])
        expected_hull = MultiPoint([(0, 0), (2, 2)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

    def test_convex_hull_triangle(self):
        # Points forming a triangle should return the triangle
        pts = MultiPoint([(0, 0), (1, 0), (0, 1)])
        expected_hull = MultiPoint([(0, 0), (1, 0), (0, 1)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

    def test_convex_hull_square(self):
        # Points forming a square should return the square
        pts = MultiPoint([(0, 0), (0, 1), (1, 1), (1, 0)])
        expected_hull = MultiPoint([(0, 0), (0, 1), (1, 1), (1, 0)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

    def test_convex_hull_random_points(self):
        # Random points should return the correct convex hull
        pts = MultiPoint([(1, 2), (4, 5), (3, 1), (6, 3), (2, 4)])
        expected_hull = MultiPoint([(1, 2), (3, 1), (6, 3), (4, 5)]).convex_hull
        self.assertEqual(pts.convex_hull, expected_hull)

if __name__ == '__main__':
    unittest.main()
