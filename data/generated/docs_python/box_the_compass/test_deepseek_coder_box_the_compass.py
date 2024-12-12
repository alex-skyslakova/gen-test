import unittest
from box_the_compass import degrees2compasspoint

class TestDegrees2CompassPoint(unittest.TestCase):

    def test_north_headings(self):
        self.assertEqual(degrees2compasspoint(0.0), "North")
        self.assertEqual(degrees2compasspoint(16.87), "North by east")
        self.assertEqual(degrees2compasspoint(16.88), "North by east")
        self.assertEqual(degrees2compasspoint(33.75), "North-northeast")
        self.assertEqual(degrees2compasspoint(50.62), "Northeast by north")
        self.assertEqual(degrees2compasspoint(50.63), "Northeast by north")
        self.assertEqual(degrees2compasspoint(67.5), "Northeast")
        self.assertEqual(degrees2compasspoint(84.37), "Northeast by east")
        self.assertEqual(degrees2compasspoint(84.38), "Northeast by east")

    def test_east_headings(self):
        self.assertEqual(degrees2compasspoint(101.25), "East-northeast")
        self.assertEqual(degrees2compasspoint(118.12), "East by north")
        self.assertEqual(degrees2compasspoint(118.13), "East by north")
        self.assertEqual(degrees2compasspoint(135.0), "East")
        self.assertEqual(degrees2compasspoint(151.87), "East by south")
        self.assertEqual(degrees2compasspoint(151.88), "East by south")
        self.assertEqual(degrees2compasspoint(168.75), "East-southeast")
        self.assertEqual(degrees2compasspoint(185.62), "Southeast by east")
        self.assertEqual(degrees2compasspoint(185.63), "Southeast by east")

    def test_south_headings(self):
        self.assertEqual(degrees2compasspoint(202.5), "Southeast")
        self.assertEqual(degrees2compasspoint(219.37), "Southeast by south")
        self.assertEqual(degrees2compasspoint(219.38), "Southeast by south")
        self.assertEqual(degrees2compasspoint(236.25), "South-southeast")
        self.assertEqual(degrees2compasspoint(253.12), "South by east")
        self.assertEqual(degrees2compasspoint(253.13), "South by east")
        self.assertEqual(degrees2compasspoint(270.0), "South")
        self.assertEqual(degrees2compasspoint(286.87), "South by west")
        self.assertEqual(degrees2compasspoint(286.88), "South by west")

    def test_west_headings(self):
        self.assertEqual(degrees2compasspoint(303.75), "South-southwest")
        self.assertEqual(degrees2compasspoint(320.62), "Southwest by south")
        self.assertEqual(degrees2compasspoint(320.63), "Southwest by south")
        self.assertEqual(degrees2compasspoint(337.5), "Southwest")
        self.assertEqual(degrees2compasspoint(354.37), "Southwest by west")
        self.assertEqual(degrees2compasspoint(354.38), "Southwest by west")
        self.assertEqual(degrees2compasspoint(360.0), "North")  # Wrap around

    def test_edge_cases(self):
        self.assertEqual(degrees2compasspoint(-16.87), "North by east")  # Negative degrees
        self.assertEqual(degrees2compasspoint(720.0), "North")  # More than 360 degrees
        self.assertEqual(degrees2compasspoint(360.0), "North")  # Exactly 360 degrees

if __name__ == '__main__':
    unittest.main()
