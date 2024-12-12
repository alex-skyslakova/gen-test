import unittest
from angles_geometric_normalization_and_conversion import *

class TestAngleConversions(unittest.TestCase):

    def test_normalize2deg(self):
        self.assertEqual(normalize2deg(-2), 358)
        self.assertEqual(normalize2deg(-1), 359)
        self.assertEqual(normalize2deg(0), 0)
        self.assertEqual(normalize2deg(1), 1)
        self.assertEqual(normalize2deg(2), 2)
        self.assertEqual(normalize2deg(359), 359)
        self.assertEqual(normalize2deg(360), 0)
        self.assertEqual(normalize2deg(720), 0)
        self.assertEqual(normalize2deg(-720), 0)


    def test_normalize2grad(self):
        self.assertEqual(normalize2grad(-2), 398)
        self.assertEqual(normalize2grad(0), 0)
        self.assertEqual(normalize2grad(1), 1)
        self.assertEqual(normalize2grad(399), 399)
        self.assertEqual(normalize2grad(400), 0)
        self.assertEqual(normalize2grad(800), 0)
        self.assertEqual(normalize2grad(-800), 0)

    def test_normalize2mil(self):
        self.assertEqual(normalize2mil(-2), 6398)
        self.assertEqual(normalize2mil(0), 0)
        self.assertEqual(normalize2mil(1), 1)
        self.assertEqual(normalize2mil(6399), 6399)
        self.assertEqual(normalize2mil(6400), 0)
        self.assertEqual(normalize2mil(12800), 0)
        self.assertEqual(normalize2mil(-12800), 0)


    def test_normalize2rad(self):
        self.assertAlmostEqual(normalize2rad(-0.1), TWO_PI - 0.1, places=7)
        self.assertEqual(normalize2rad(0), 0)
        self.assertAlmostEqual(normalize2rad(0.1), 0.1, places=7)
        self.assertAlmostEqual(normalize2rad(TWO_PI), 0, places=7)
        self.assertAlmostEqual(normalize2rad(2 * TWO_PI), 0, places=7)
        self.assertAlmostEqual(normalize2rad(-2 * TWO_PI), 0, places=7)

    def test_deg2grad(self):
        self.assertEqual(deg2grad(90), 100)

    def test_deg2mil(self):
        self.assertEqual(deg2mil(90), 1600)

    def test_deg2rad(self):
        self.assertAlmostEqual(deg2rad(180), PI, places=7)

    def test_grad2deg(self):
        self.assertEqual(grad2deg(100), 90)

    def test_grad2mil(self):
        self.assertEqual(grad2mil(100), 1600)

    def test_grad2rad(self):
        self.assertAlmostEqual(grad2rad(200), PI, places=7)

    def test_mil2deg(self):
        self.assertEqual(mil2deg(1600), 90)

    def test_mil2grad(self):
        self.assertEqual(mil2grad(1600), 100)

    def test_mil2rad(self):
        self.assertAlmostEqual(mil2rad(3200), PI, places=7)


    def test_rad2deg(self):
        self.assertAlmostEqual(rad2deg(PI), 180, places=7)

    def test_rad2grad(self):
        self.assertAlmostEqual(rad2grad(PI), 200, places=7)


    def test_rad2mil(self):
        self.assertAlmostEqual(rad2mil(PI), 3200, places=7)





if __name__ == '__main__':
    unittest.main()
