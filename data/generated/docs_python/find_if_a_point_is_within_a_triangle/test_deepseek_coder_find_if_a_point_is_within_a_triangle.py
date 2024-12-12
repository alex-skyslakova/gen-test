import unittest
from sympy.geometry import Point, Triangle
from find_if_a_point_is_within_a_triangle import iswithin

class TestIsWithinTriangle(unittest.TestCase):

    def setUp(self):
        # Define a triangle ABC
        self.A = Point(1.5, 2.4)
        self.B = Point(5.1, -3.1)
        self.C = Point(-3.8, 0.5)
        self.triangle = Triangle(self.A, self.B, self.C)

    def test_point_inside_triangle(self):
        # Point inside the triangle
        P = Point(1, 1)
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_outside_triangle(self):
        # Point outside the triangle
        P = Point(10, 10)
        self.assertFalse(iswithin(P, self.A, self.B, self.C))

    def test_point_on_triangle_edge(self):
        # Point on the edge of the triangle
        P = Point(1.5, 2.4)  # Point A
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_on_triangle_vertex(self):
        # Point on the vertex of the triangle
        P = Point(5.1, -3.1)  # Point B
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_on_triangle_edge_shared_vertex(self):
        # Point on the edge of the triangle, sharing a vertex
        P = Point(3.3, -0.35)  # Midpoint of AB
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_on_triangle_edge_not_shared_vertex(self):
        # Point on the edge of the triangle, not sharing a vertex
        P = Point(-1.15, 1.45)  # Midpoint of AC
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_on_triangle_edge_opposite_vertex(self):
        # Point on the edge of the triangle, opposite a vertex
        P = Point(0.65, -1.3)  # Midpoint of BC
        self.assertTrue(iswithin(P, self.A, self.B, self.C))

    def test_point_exactly_outside_triangle_edge(self):
        # Point exactly outside the triangle edge
        P = Point(1.5, 2.5)  # Slightly outside the edge of AB
        self.assertFalse(iswithin(P, self.A, self.B, self.C))

    def test_point_exactly_outside_triangle_vertex(self):
        # Point exactly outside the triangle vertex
        P = Point(1.5, 2.41)  # Slightly outside the vertex A
        self.assertFalse(iswithin(P, self.A, self.B, self.C))

if __name__ == "__main__":
    unittest.main()
