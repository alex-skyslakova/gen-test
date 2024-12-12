import unittest
from unittest.mock import patch
import shapely.geometry as geometry
import chaos_game

class TestChaosGame(unittest.TestCase):

    def test_get_triangle(self):
        # Test if the triangle is created correctly
        triangle = chaos_game.get_triangle(1)
        expected_coords = [(0.0, 0.0), (0.5, 0.75), (1.0, 0.0)]
        self.assertEqual(list(triangle.exterior.coords)[:-1], expected_coords)

    def test_gen_point_within_poly(self):
        # Test if the generated point is within the triangle
        triangle = chaos_game.get_triangle(1)
        point = chaos_game.gen_point_within_poly(triangle)
        self.assertTrue(point.within(triangle))

    @patch('random.choice')
    def test_next_point(self, mock_random_choice):
        # Test if the next point is correctly calculated
        triangle = chaos_game.get_triangle(1)
        start_point = geometry.Point(0.5, 0.5)
        mock_random_choice.return_value = (0.0, 0.0)  # Mocking to always choose the first vertex
        next_pt = chaos_game.next_point(triangle, start_point)
        expected_point = geometry.Point(0.25, 0.25)  # Midpoint between (0.5, 0.5) and (0.0, 0.0)
        self.assertAlmostEqual(next_pt.x, expected_point.x)
        self.assertAlmostEqual(next_pt.y, expected_point.y)

    def test_get_data(self):
        # Test if data is generated correctly
        data = chaos_game.get_data(5)
        self.assertEqual(len(data), 5)
        # Check if all points are within the triangle
        triangle = chaos_game.get_triangle(1)
        for point in data:
            self.assertTrue(geometry.Point(point).within(triangle))

    @patch('chaos_game.get_data')
    def test_update_line(self, mock_get_data):
        # Test if the line is updated correctly
        mock_get_data.return_value = [(0.1, 0.1), (0.2, 0.2), (0.3, 0.3)]
        line = unittest.mock.Mock()
        chaos_game.update_line(2, mock_get_data.return_value, line)
        line.set_data.assert_called_once_with(((0.1, 0.2), (0.1, 0.2)))

if __name__ == '__main__':
    unittest.main()
