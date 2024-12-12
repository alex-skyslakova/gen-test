import unittest
import random
import shapely.geometry as geometry
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
from chaos_game import get_triangle, gen_point_within_poly, next_point, get_data, update_line

class TestChaosGame(unittest.TestCase):

    def test_get_triangle(self):
        triangle = get_triangle(1)
        self.assertIsInstance(triangle, geometry.Polygon)
        self.assertEqual(len(triangle.exterior.coords), 4)  # A triangle should have 4 points (including the repeated first point)
        self.assertAlmostEqual(triangle.area, 0.5 * 0.75, places=2)  # Area of the triangle

    def test_gen_point_within_poly(self):
        triangle = get_triangle(1)
        point = gen_point_within_poly(triangle)
        self.assertIsInstance(point, geometry.Point)
        self.assertTrue(point.within(triangle))

    def test_next_point(self):
        triangle = get_triangle(1)
        initial_point = gen_point_within_poly(triangle)
        next_pt = next_point(triangle, initial_point)
        self.assertIsInstance(next_pt, geometry.Point)
        # Ensure the next point is within the bounds of the triangle
        self.assertTrue(next_pt.x >= 0 and next_pt.x <= 1)
        self.assertTrue(next_pt.y >= 0 and next_pt.y <= 0.75)

    def test_get_data(self):
        data = get_data(100)
        self.assertEqual(len(data), 100)
        for point in data:
            self.assertIsInstance(point, tuple)
            self.assertEqual(len(point), 2)
            x, y = point
            self.assertTrue(isinstance(x, float) and isinstance(y, float))

    def test_update_line(self):
        fig = plt.figure()
        line, = plt.plot([], [], ".")
        data = [(0, 0), (0.5, 0.5), (1, 0)]
        update_line(2, data, line)
        self.assertEqual(line.get_data(), ([0, 0.5], [0, 0.5]))

if __name__ == "__main__":
    unittest.main()
