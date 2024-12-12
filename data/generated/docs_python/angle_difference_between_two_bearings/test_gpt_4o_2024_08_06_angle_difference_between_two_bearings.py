import unittest
from angle_difference_between_two_bearings import getDifference

class TestAngleDifference(unittest.TestCase):

    def test_standard_range(self):
        # Test cases with input bearings in the range -180 to +180
        self.assertAlmostEqual(getDifference(20.0, 45.0), 25.0)
        self.assertAlmostEqual(getDifference(-45.0, 45.0), 90.0)
        self.assertAlmostEqual(getDifference(-85.0, 90.0), 175.0)
        self.assertAlmostEqual(getDifference(-95.0, 90.0), 185.0 - 360.0)  # -175.0
        self.assertAlmostEqual(getDifference(-45.0, 125.0), 170.0)
        self.assertAlmostEqual(getDifference(-45.0, 145.0), 190.0 - 360.0)  # -170.0
        self.assertAlmostEqual(getDifference(29.4803, -88.6381), -118.1184)
        self.assertAlmostEqual(getDifference(-78.3251, -159.036), -80.7109)

    def test_wider_range(self):
        # Test cases with input bearings outside the range -180 to +180
        self.assertAlmostEqual(getDifference(-70099.74233810938, 29840.67437876723), 179.9327168766108)
        self.assertAlmostEqual(getDifference(-165313.6666297357, 33693.9894517456), 179.6760814813453)
        self.assertAlmostEqual(getDifference(1174.8380510598456, -154146.66490124757), -179.5029523077228)
        self.assertAlmostEqual(getDifference(60175.77306795546, 42213.07192354373), -179.2988555880833)

if __name__ == '__main__':
    unittest.main()
