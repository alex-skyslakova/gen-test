import unittest
from sympy.geometry import Point, Triangle
from find_if_a_point_is_within_a_triangle import iswithin

class TestIsWithin(unittest.TestCase):

    def test_inside(self):
        triangle = Triangle(Point(0, 0), Point(1, 0), Point(0, 1))
        self.assertTrue(iswithin(Point(0.1, 0.1), *triangle.vertices))

    def test_outside(self):
        triangle = Triangle(Point(0, 0), Point(1, 0), Point(0, 1))
        self.assertFalse(iswithin(Point(1, 1), *triangle.vertices))

    def test_on_edge(self):
        triangle = Triangle(Point(0, 0), Point(1, 0), Point(0, 1))
        self.assertTrue(iswithin(Point(0.5, 0), *triangle.vertices))

    def test_on_vertex(self):
        triangle = Triangle(Point(0, 0), Point(1, 0), Point(0, 1))
        self.assertTrue(iswithin(Point(0, 0), *triangle.vertices))

    def test_obtuse_triangle(self):
        triangle = Triangle(Point(0, 0), Point(2, 0), Point(1, 1))
        self.assertTrue(iswithin(Point(1, 0.5), *triangle.vertices))
        self.assertFalse(iswithin(Point(1, 1.5), *triangle.vertices))


    def test_provided_example(self):
        tri = Triangle(Point(1.5, 2.4), Point(5.1, -3.1), Point(-3.8, 0.5))
        self.assertFalse(iswithin(Point(0,0), *tri.vertices))


    def test_degenerate_triangle(self):
        #Degenerate triangles should return True if point lies on the line segment
        triangle = Triangle(Point(0,0), Point(1,0), Point(2,0))
        self.assertTrue(iswithin(Point(1,0), *triangle.vertices))
        self.assertFalse(iswithin(Point(1,1), *triangle.vertices))



