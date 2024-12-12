import unittest
import math
from unittest.mock import patch
from io import StringIO
from draw_a_sphere import normalize, dot, draw_sphere, shades

class TestSphereFunctions(unittest.TestCase):

    def test_normalize(self):
        self.assertEqual(normalize((0,0,0)), (0,0,0)) # Handle zero vector
        self.assertAlmostEqual(normalize((1,0,0)), (1,0,0))
        self.assertAlmostEqual(normalize((0,1,0)), (0,1,0))
        self.assertAlmostEqual(normalize((0,0,1)), (0,0,1))
        self.assertAlmostEqual(normalize((1,1,1)), (1/math.sqrt(3), 1/math.sqrt(3), 1/math.sqrt(3)))
        self.assertAlmostEqual(normalize((2,3,4)), (2/math.sqrt(29), 3/math.sqrt(29), 4/math.sqrt(29)))

    def test_dot(self):
        self.assertEqual(dot((1,0,0), (1,0,0)), 0) # Light opposite to normal, should be zero
        self.assertEqual(dot((1,0,0), (-1,0,0)), 1)
        self.assertEqual(dot((1,1,1), (-1,-1,-1)), 3)
        self.assertEqual(dot((1,1,1), (1,-1,-1)), 0) # Partially opposite

    @patch('sys.stdout', new_callable=StringIO)
    def test_draw_sphere_small(self, mock_stdout):
        draw_sphere(1, 2, 0.1, normalize((1,1,1)))
        output = mock_stdout.getvalue()
        self.assertIn("*", output) # Expect at least some visible sphere part


    @patch('sys.stdout', new_callable=StringIO)
    def test_draw_sphere_larger(self, mock_stdout):
         draw_sphere(3, 4, 0.2, normalize((-1,1,1))) 
         output = mock_stdout.getvalue()
         self.assertIn(" ", output) # Expect spaces for the empty parts
         self.assertTrue(len(output) > 20) # Expect a larger output due to larger radius


    @patch('sys.stdout', new_callable=StringIO)
    def test_draw_sphere_edge_cases(self, mock_stdout):
        draw_sphere(0, 2, 0.1, normalize((1,1,1))) # Zero radius
        output_zero = mock_stdout.getvalue().strip() # remove newlines and extra spaces
        self.assertEqual(len(output_zero), 1) # Should be a single point, technically


        draw_sphere(2, 100, 0.1, normalize((1, 1, 1)))  # High k value
        draw_sphere(2, 0, 0.9, normalize((1, 1, 1)))  # Zero k value
        draw_sphere(2, 2, 1, normalize((1, 1, 1))) # Ambient at maximum 



    @patch('sys.stdout', new_callable=StringIO)
    def test_draw_sphere_different_light(self, mock_stdout):
        draw_sphere(5, 2, 0.3, normalize((0, 0, -1))) # light directly above
        output_above = mock_stdout.getvalue()

        draw_sphere(5, 2, 0.3, normalize((0, 0, 1))) # light directly below
        output_below = mock_stdout.getvalue()

        self.assertNotEqual(output_above, output_below) # Lighting from different directions should produce different results




