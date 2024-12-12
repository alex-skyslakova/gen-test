import unittest
from math import pi, cos, sin

def calculate_spiral_point(i, a=1, b=5):
    t = i / 20 * pi
    x = (a + b * t) * cos(t)
    y = (a + b * t) * sin(t)
    return x, y

class TestArchimedeanSpiral(unittest.TestCase):
    def test_spiral_point_calculation(self):
        # Test a few points to ensure the calculations are correct
        points_to_test = [
            (0, (1.0, 0.0)),
            (10, (1.0 + 5 * (10 / 20 * pi) * cos(10 / 20 * pi), (1.0 + 5 * (10 / 20 * pi)) * sin(10 / 20 * pi))),
            (50, (1.0 + 5 * (50 / 20 * pi) * cos(50 / 20 * pi), (1.0 + 5 * (50 / 20 * pi)) * sin(50 / 20 * pi))),
            (100, (1.0 + 5 * (100 / 20 * pi) * cos(100 / 20 * pi), (1.0 + 5 * (100 / 20 * pi)) * sin(100 / 20 * pi))),
            (199, (1.0 + 5 * (199 / 20 * pi) * cos(199 / 20 * pi), (1.0 + 5 * (199 / 20 * pi)) * sin(199 / 20 * pi))),
        ]

        for i, expected in points_to_test:
            with self.subTest(i=i):
                calculated = calculate_spiral_point(i)
                self.assertAlmostEqual(calculated[0], expected[0], places=5)
                self.assertAlmostEqual(calculated[1], expected[1], places=5)

if __name__ == '__main__':
    unittest.main()
