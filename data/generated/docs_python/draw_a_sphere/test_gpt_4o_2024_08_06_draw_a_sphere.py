import unittest
from draw_a_sphere import normalize, dot, draw_sphere

class TestDrawSphere(unittest.TestCase):

    def test_normalize(self):
        # Test normalization of a vector
        self.assertEqual(normalize((3, 0, 0)), (1.0, 0.0, 0.0))
        self.assertEqual(normalize((0, 4, 0)), (0.0, 1.0, 0.0))
        self.assertEqual(normalize((0, 0, 5)), (0.0, 0.0, 1.0))
        self.assertAlmostEqual(normalize((1, 1, 1))[0], 1/math.sqrt(3))
        self.assertAlmostEqual(normalize((1, 1, 1))[1], 1/math.sqrt(3))
        self.assertAlmostEqual(normalize((1, 1, 1))[2], 1/math.sqrt(3))

    def test_dot(self):
        # Test dot product calculation
        self.assertEqual(dot((1, 0, 0), (1, 0, 0)), 0)
        self.assertEqual(dot((0, 1, 0), (0, 1, 0)), 0)
        self.assertEqual(dot((0, 0, 1), (0, 0, 1)), 0)
        self.assertEqual(dot((1, 0, 0), (-1, 0, 0)), 1)
        self.assertEqual(dot((1, 1, 1), (-1, -1, -1)), 3)

    def test_draw_sphere(self):
        # Test drawing a sphere with different parameters
        # Since draw_sphere prints to console, we will capture the output
        import io
        import sys

        # Capture the output of draw_sphere
        captured_output = io.StringIO()
        sys.stdout = captured_output
        draw_sphere(1, 1, 0.1, normalize((1, 1, -1)))
        sys.stdout = sys.__stdout__

        # Check if the output is not empty
        self.assertTrue(len(captured_output.getvalue()) > 0)

        # Check if the output contains expected characters
        output = captured_output.getvalue()
        self.assertIn('.', output)
        self.assertIn(':', output)
        self.assertIn('!', output)
        self.assertIn('*', output)
        self.assertIn('o', output)
        self.assertIn('e', output)
        self.assertIn('&', output)
        self.assertIn('#', output)
        self.assertIn('%', output)
        self.assertIn('@', output)

if __name__ == '__main__':
    unittest.main()
