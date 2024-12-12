import unittest
from unittest.mock import patch
import math

# Assuming the code is in a module named `draw_a_sphere`
import draw_a_sphere

class TestDrawSphere(unittest.TestCase):

    def test_normalize(self):
        # Test case for the normalize function
        result = draw_a_sphere.normalize((3, 4, 0))
        self.assertAlmostEqual(result[0], 0.6, places=5)
        self.assertAlmostEqual(result[1], 0.8, places=5)
        self.assertAlmostEqual(result[2], 0.0, places=5)

    def test_dot(self):
        # Test case for the dot function
        result = draw_a_sphere.dot((1, 0, 0), (0, 1, 0))
        self.assertEqual(result, 0)

        result = draw_a_sphere.dot((1, 0, 0), (1, 0, 0))
        self.assertEqual(result, 1)

        result = draw_a_sphere.dot((1, 0, 0), (-1, 0, 0))
        self.assertEqual(result, -1)

    @patch('builtins.print')
    def test_draw_sphere(self, mock_print):
        # Test case for the draw_sphere function
        light = draw_a_sphere.normalize((30, 30, -50))
        draw_a_sphere.draw_sphere(20, 4, 0.1, light)
        draw_a_sphere.draw_sphere(10, 2, 0.4, light)

        # Ensure that print was called with non-empty strings
        for call in mock_print.call_args_list:
            self.assertTrue(len(call[0][0]) > 0)

if __name__ == '__main__':
    unittest.main()
