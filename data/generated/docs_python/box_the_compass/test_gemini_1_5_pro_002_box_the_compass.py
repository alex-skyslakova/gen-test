import unittest
from box_the_compass import degrees2compasspoint

class TestDegrees2CompassPoint(unittest.TestCase):

    def test_cardinal_points(self):
        self.assertEqual(degrees2compasspoint(0.0), "North")
        self.assertEqual(degrees2compasspoint(90.0), "East")
        self.assertEqual(degrees2compasspoint(180.0), "South")
        self.assertEqual(degrees2compasspoint(270.0), "West")
        self.assertEqual(degrees2compasspoint(360.0), "North")

    def test_intercardinal_points(self):
        self.assertEqual(degrees2compasspoint(45.0), "Northeast")
        self.assertEqual(degrees2compasspoint(135.0), "Southeast")
        self.assertEqual(degrees2compasspoint(225.0), "Southwest")
        self.assertEqual(degrees2compasspoint(315.0), "Northwest")

    def test_by_points(self):
        self.assertEqual(degrees2compasspoint(11.25), "N by e")
        self.assertEqual(degrees2compasspoint(33.75), "Ne by n")
        self.assertEqual(degrees2compasspoint(56.25), "Ne by e")

    def test_specified_values(self):
        headings = [0.0, 16.87, 16.88, 33.75, 50.62, 50.63, 67.5, 84.37, 84.38, 101.25, 118.12, 118.13, 135.0, 151.87, 151.88, 168.75, 185.62, 185.63, 202.5, 219.37, 219.38, 236.25, 253.12, 253.13, 270.0, 286.87, 286.88, 303.75, 320.62, 320.63, 337.5, 354.37, 354.38]
        expected_results = ["North", "N by e", "N by e", "Ne by n", "Ne", "Ne", "Ne by e", "E-ne", "E-ne", "E by n", "E", "E", "E by s", "Se by e", "Se by e", "Se by s", "S-se", "S-se", "S by e", "S", "S", "S by w", "Sw by s", "Sw by s", "West", "W by n", "W by n", "Nw by w", "Nw", "Nw", "Nw by n", "N-nw", "N-nw"]

        for heading, expected in zip(headings, expected_results):
            self.assertEqual(degrees2compasspoint(heading), expected)


    def test_over_360(self):
        self.assertEqual(degrees2compasspoint(360.0), "North")
        self.assertEqual(degrees2compasspoint(450.0), "East")  # 360 + 90
        self.assertEqual(degrees2compasspoint(720.0), "North") # 360 * 2
        self.assertEqual(degrees2compasspoint(-90), "West") # Equivalent to 270



if __name__ == '__main__':
    unittest.main()
