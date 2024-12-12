import unittest
from sympy.geometry import Point, Triangle
from find_if_a_point_is_within_a_triangle import iswithin

class TestPointInTriangle(unittest.TestCase):

    def test_point_inside_triangle(self):
        # Point inside the triangle
        point = Point(1, 1)
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
        a, b, c = triangle.vertices
        self.assertTrue(iswithin(point, a, b, c))

    def test_point_on_vertex(self):
        # Point on one of the triangle's vertices
        point = Point(0, 0)
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
        a, b, c = triangle.vertices
        self.assertTrue(iswithin(point, a, b, c))

    def test_point_on_edge(self):
        # Point on one of the triangle's edges
        point = Point(1, 0)
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
        a, b, c = triangle.vertices
        self.assertTrue(iswithin(point, a, b, c))

    def test_point_outside_triangle(self):
        # Point outside the triangle
        point = Point(3, 3)
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
        a, b, c = triangle.vertices
        self.assertFalse(iswithin(point, a, b, c))

    def test_point_on_extended_line(self):
        # Point on the line extended from one of the triangle's edges
        point = Point(3, 0)
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
        a, b, c = triangle.vertices
        self.assertFalse(iswithin(point, a, b, c))

if __name__ == '__main__':
    unittest.main()
