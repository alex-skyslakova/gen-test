import unittest
import random
import shapely.geometry as geometry
from chaos_game import get_triangle, gen_point_within_poly, next_point

class TestChaosGame(unittest.TestCase):

    def test_get_triangle(self):
        triangle = get_triangle(1)
        self.assertIsInstance(triangle, geometry.Polygon)
        self.assertEqual(len(triangle.exterior.coords), 4) # 3 vertices + closing point
        self.assertEqual(triangle.exterior.coords[0], (0.0, 0.0))
        self.assertEqual(triangle.exterior.coords[1], (0.5, 0.75))
        self.assertEqual(triangle.exterior.coords[2], (1.0, 0.0))

        triangle2 = get_triangle(2)
        self.assertEqual(triangle2.exterior.coords[0], (0.0, 0.0))
        self.assertEqual(triangle2.exterior.coords[1], (1.0, 3.0))
        self.assertEqual(triangle2.exterior.coords[2], (2.0, 0.0))


    def test_gen_point_within_poly(self):
        triangle = get_triangle(1)
        for _ in range(100):  # Test multiple times due to randomness
            point = gen_point_within_poly(triangle)
            self.assertIsInstance(point, geometry.Point)
            self.assertTrue(point.within(triangle))

    def test_next_point(self):
        triangle = get_triangle(1)
        point = geometry.Point(0.2, 0.3) # Example point inside the triangle

        # Mock random.choice to test all vertices
        vertices = triangle.boundary.coords[:-1]
        for vertex in vertices:
            with unittest.mock.patch('chaos_game.random.choice', return_value=vertex):
                next_p = next_point(triangle, point)
                self.assertIsInstance(next_p, geometry.Point)

                # Check if next point is the midpoint between the current point and the chosen vertex.
                expected_x = (point.x + vertex[0]) / 2
                expected_y = (point.y + vertex[1]) / 2
                self.assertAlmostEqual(next_p.x, expected_x)
                self.assertAlmostEqual(next_p.y, expected_y)



if __name__ == '__main__':
    unittest.main()
