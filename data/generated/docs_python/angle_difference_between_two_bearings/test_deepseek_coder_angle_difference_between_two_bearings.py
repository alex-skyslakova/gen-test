import unittest
from angle_difference_between_two_bearings import getDifference

class TestAngleDifference(unittest.TestCase):

    def test_basic_cases(self):
        self.assertEqual(getDifference(20.0, 45.0), 25.0)
        self.assertEqual(getDifference(-45.0, 45.0), 90.0)
        self.assertEqual(getDifference(-85.0, 90.0), 175.0)
        self.assertEqual(getDifference(-95.0, 90.0), -175.0)
        self.assertEqual(getDifference(-45.0, 125.0), 170.0)
        self.assertEqual(getDifference(-45.0, 145.0), 190.0)
        self.assertEqual(getDifference(29.4803, -88.6381), -118.1184)
        self.assertEqual(getDifference(-78.3251, -159.036), -80.7109)

    def test_wider_range_cases(self):
        self.assertEqual(getDifference(-70099.74233810938, 29840.67437876723), -139.5833881781651)
        self.assertEqual(getDifference(-165313.6666297357, 33693.9894517456), -72.34391851868713)
        self.assertEqual(getDifference(1174.8380510598456, -154146.66490124757), 230.91389178596395)
        self.assertEqual(getDifference(60175.77306795546, 42213.07192354373), -177.69231474812078)

if __name__ == '__main__':
    unittest.main()
