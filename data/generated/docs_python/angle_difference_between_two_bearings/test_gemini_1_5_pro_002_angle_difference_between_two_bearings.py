import unittest
from angle_difference_between_two_bearings import getDifference

class TestGetDifference(unittest.TestCase):

    def test_within_range(self):
        self.assertAlmostEqual(getDifference(20.0, 45.0), 25.0)
        self.assertAlmostEqual(getDifference(-45.0, 45.0), 90.0)
        self.assertAlmostEqual(getDifference(-85.0, 90.0), 175.0)
        self.assertAlmostEqual(getDifference(-95.0, 90.0), -175.0)
        self.assertAlmostEqual(getDifference(-45.0, 125.0), 170.0)
        self.assertAlmostEqual(getDifference(-45.0, 145.0), -170.0)
        self.assertAlmostEqual(getDifference(29.4803, -88.6381), -118.1184)
        self.assertAlmostEqual(getDifference(-78.3251, -159.036), -80.7109)


    def test_wider_range(self):
        self.assertAlmostEqual(getDifference(-70099.74233810938, 29840.67437876723), -140.06796)
        self.assertAlmostEqual(getDifference(-165313.6666297357, 33693.9894517456), 23.32329)
        self.assertAlmostEqual(getDifference(1174.8380510598456, -154146.66490124757), 118.17275)
        self.assertAlmostEqual(getDifference(60175.77306795546, 42213.07192354373), -179.62714)

if __name__ == '__main__':
    unittest.main()
