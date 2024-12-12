import unittest
import math

class TestAngleConversions(unittest.TestCase):

    def test_normalize2deg(self):
        self.assertEqual(normalize2deg(-2), 358)
        self.assertEqual(normalize2deg(-1), 359)
        self.assertEqual(normalize2deg(0), 0)
        self.assertEqual(normalize2deg(1), 1)
        self.assertEqual(normalize2deg(2), 2)
        self.assertEqual(normalize2deg(6.2831853), 6.2831853)
        self.assertEqual(normalize2deg(16), 16)
        self.assertEqual(normalize2deg(57.2957795), 57.2957795)
        self.assertEqual(normalize2deg(359), 359)
        self.assertEqual(normalize2deg(399), 39)
        self.assertEqual(normalize2deg(6399), 279)
        self.assertEqual(normalize2deg(1000000), 280)

    def test_normalize2grad(self):
        self.assertEqual(normalize2grad(-2), 398)
        self.assertEqual(normalize2grad(-1), 399)
        self.assertEqual(normalize2grad(0), 0)
        self.assertEqual(normalize2grad(1), 1)
        self.assertEqual(normalize2grad(2), 2)
        self.assertEqual(normalize2grad(6.2831853), 6.2831853)
        self.assertEqual(normalize2grad(16), 16)
        self.assertEqual(normalize2grad(57.2957795), 57.2957795)
        self.assertEqual(normalize2grad(359), 359)
        self.assertEqual(normalize2grad(399), 399)
        self.assertEqual(normalize2grad(6399), 399)
        self.assertEqual(normalize2grad(1000000), 0)

    def test_normalize2mil(self):
        self.assertEqual(normalize2mil(-2), 6398)
        self.assertEqual(normalize2mil(-1), 6399)
        self.assertEqual(normalize2mil(0), 0)
        self.assertEqual(normalize2mil(1), 1)
        self.assertEqual(normalize2mil(2), 2)
        self.assertEqual(normalize2mil(6.2831853), 6.2831853)
        self.assertEqual(normalize2mil(16), 16)
        self.assertEqual(normalize2mil(57.2957795), 57.2957795)
        self.assertEqual(normalize2mil(359), 359)
        self.assertEqual(normalize2mil(399), 399)
        self.assertEqual(normalize2mil(6399), 6399)
        self.assertEqual(normalize2mil(1000000), 6400)

    def test_normalize2rad(self):
        self.assertAlmostEqual(normalize2rad(-2), 4.283185307179586, places=7)
        self.assertAlmostEqual(normalize2rad(-1), 5.283185307179586, places=7)
        self.assertEqual(normalize2rad(0), 0)
        self.assertEqual(normalize2rad(1), 1)
        self.assertEqual(normalize2rad(2), 2)
        self.assertEqual(normalize2rad(6.2831853), 6.2831853)
        self.assertEqual(normalize2rad(16), 3.7168146928204138)
        self.assertEqual(normalize2rad(57.2957795), 0.28318530717958623)
        self.assertEqual(normalize2rad(359), 5.26548245743669)
        self.assertEqual(normalize2rad(399), 0.017453292519943295)
        self.assertEqual(normalize2rad(6399), 0.017453292519943295)
        self.assertEqual(normalize2rad(1000000), 5.497787143782138)

    def test_deg2grad(self):
        self.assertEqual(deg2grad(360), 400)
        self.assertEqual(deg2grad(180), 200)
        self.assertEqual(deg2grad(90), 100)

    def test_deg2mil(self):
        self.assertEqual(deg2mil(360), 6400)
        self.assertEqual(deg2mil(180), 3200)
        self.assertEqual(deg2mil(90), 1600)

    def test_deg2rad(self):
        self.assertAlmostEqual(deg2rad(360), TWO_PI, places=7)
        self.assertAlmostEqual(deg2rad(180), math.pi, places=7)
        self.assertAlmostEqual(deg2rad(90), math.pi / 2, places=7)

    def test_grad2deg(self):
        self.assertEqual(grad2deg(400), 360)
        self.assertEqual(grad2deg(200), 180)
        self.assertEqual(grad2deg(100), 90)

    def test_grad2mil(self):
        self.assertEqual(grad2mil(400), 6400)
        self.assertEqual(grad2mil(200), 3200)
        self.assertEqual(grad2mil(100), 1600)

    def test_grad2rad(self):
        self.assertAlmostEqual(grad2rad(400), TWO_PI, places=7)
        self.assertAlmostEqual(grad2rad(200), math.pi, places=7)
        self.assertAlmostEqual(grad2rad(100), math.pi / 2, places=7)

    def test_mil2deg(self):
        self.assertEqual(mil2deg(6400), 360)
        self.assertEqual(mil2deg(3200), 180)
        self.assertEqual(mil2deg(1600), 90)

    def test_mil2grad(self):
        self.assertEqual(mil2grad(6400), 400)
        self.assertEqual(mil2grad(3200), 200)
        self.assertEqual(mil2grad(1600), 100)

    def test_mil2rad(self):
        self.assertAlmostEqual(mil2rad(6400), TWO_PI, places=7)
        self.assertAlmostEqual(mil2rad(3200), math.pi, places=7)
        self.assertAlmostEqual(mil2rad(1600), math.pi / 2, places=7)

    def test_rad2deg(self):
        self.assertAlmostEqual(rad2deg(TWO_PI), 360, places=7)
        self.assertAlmostEqual(rad2deg(math.pi), 180, places=7)
        self.assertAlmostEqual(rad2deg(math.pi / 2), 90, places=7)

    def test_rad2grad(self):
        self.assertAlmostEqual(rad2grad(TWO_PI), 400, places=7)
        self.assertAlmostEqual(rad2grad(math.pi), 200, places=7)
        self.assertAlmostEqual(rad2grad(math.pi / 2), 100, places=7)

    def test_rad2mil(self):
        self.assertAlmostEqual(rad2mil(TWO_PI), 6400, places=7)
        self.assertAlmostEqual(rad2mil(math.pi), 3200, places=7)
        self.assertAlmostEqual(rad2mil(math.pi / 2), 1600, places=7)

if __name__ == '__main__':
    unittest.main()
