import unittest
from angles_geometric_normalization_and_conversion import (
    normalize2deg, normalize2grad, normalize2mil, normalize2rad,
    deg2grad, deg2mil, deg2rad,
    grad2deg, grad2mil, grad2rad,
    mil2deg, mil2grad, mil2rad,
    rad2deg, rad2grad, rad2mil
)

class TestAngleConversions(unittest.TestCase):

    def test_normalize2deg(self):
        self.assertEqual(normalize2deg(-2), 358)
        self.assertEqual(normalize2deg(-1), 359)
        self.assertEqual(normalize2deg(0), 0)
        self.assertEqual(normalize2deg(1), 1)
        self.assertEqual(normalize2deg(361), 1)
        self.assertEqual(normalize2deg(359), 359)
        self.assertEqual(normalize2deg(399), 39)
        self.assertEqual(normalize2deg(6399), 359)
        self.assertEqual(normalize2deg(1000000), 280)

    def test_normalize2grad(self):
        self.assertEqual(normalize2grad(-2), 398)
        self.assertEqual(normalize2grad(-1), 399)
        self.assertEqual(normalize2grad(0), 0)
        self.assertEqual(normalize2grad(1), 1)
        self.assertEqual(normalize2grad(401), 1)
        self.assertEqual(normalize2grad(399), 399)
        self.assertEqual(normalize2grad(439), 39)
        self.assertEqual(normalize2grad(6399), 399)
        self.assertEqual(normalize2grad(1000000), 280)

    def test_normalize2mil(self):
        self.assertEqual(normalize2mil(-2), 6398)
        self.assertEqual(normalize2mil(-1), 6399)
        self.assertEqual(normalize2mil(0), 0)
        self.assertEqual(normalize2mil(1), 1)
        self.assertEqual(normalize2mil(6401), 1)
        self.assertEqual(normalize2mil(6399), 6399)
        self.assertEqual(normalize2mil(6799), 399)
        self.assertEqual(normalize2mil(1000000), 280)

    def test_normalize2rad(self):
        self.assertAlmostEqual(normalize2rad(-0.1), 6.1831853, places=7)
        self.assertAlmostEqual(normalize2rad(0), 0, places=7)
        self.assertAlmostEqual(normalize2rad(1), 1, places=7)
        self.assertAlmostEqual(normalize2rad(6.2831853), 0, places=7)
        self.assertAlmostEqual(normalize2rad(6.3831853), 0.1, places=7)
        self.assertAlmostEqual(normalize2rad(1000000), 2.841592653589793, places=7)

    def test_deg2grad(self):
        self.assertAlmostEqual(deg2grad(-2), -2.2222222, places=7)
        self.assertAlmostEqual(deg2grad(0), 0, places=7)
        self.assertAlmostEqual(deg2grad(1), 1.1111111, places=7)
        self.assertAlmostEqual(deg2grad(360), 400, places=7)

    def test_deg2mil(self):
        self.assertAlmostEqual(deg2mil(-2), -35.5555555, places=7)
        self.assertAlmostEqual(deg2mil(0), 0, places=7)
        self.assertAlmostEqual(deg2mil(1), 17.7777777, places=7)
        self.assertAlmostEqual(deg2mil(360), 6400, places=7)

    def test_deg2rad(self):
        self.assertAlmostEqual(deg2rad(-2), -0.0349066, places=7)
        self.assertAlmostEqual(deg2rad(0), 0, places=7)
        self.assertAlmostEqual(deg2rad(1), 0.0174533, places=7)
        self.assertAlmostEqual(deg2rad(360), 6.2831853, places=7)

    def test_grad2deg(self):
        self.assertAlmostEqual(grad2deg(-2), -1.8, places=7)
        self.assertAlmostEqual(grad2deg(0), 0, places=7)
        self.assertAlmostEqual(grad2deg(1), 0.9, places=7)
        self.assertAlmostEqual(grad2deg(400), 360, places=7)

    def test_grad2mil(self):
        self.assertAlmostEqual(grad2mil(-2), -32, places=7)
        self.assertAlmostEqual(grad2mil(0), 0, places=7)
        self.assertAlmostEqual(grad2mil(1), 16, places=7)
        self.assertAlmostEqual(grad2mil(400), 6400, places=7)

    def test_grad2rad(self):
        self.assertAlmostEqual(grad2rad(-2), -0.0314159, places=7)
        self.assertAlmostEqual(grad2rad(0), 0, places=7)
        self.assertAlmostEqual(grad2rad(1), 0.0157080, places=7)
        self.assertAlmostEqual(grad2rad(400), 6.2831853, places=7)

    def test_mil2deg(self):
        self.assertAlmostEqual(mil2deg(-2), -0.1125, places=7)
        self.assertAlmostEqual(mil2deg(0), 0, places=7)
        self.assertAlmostEqual(mil2deg(1), 0.05625, places=7)
        self.assertAlmostEqual(mil2deg(6400), 360, places=7)

    def test_mil2grad(self):
        self.assertAlmostEqual(mil2grad(-2), -0.125, places=7)
        self.assertAlmostEqual(mil2grad(0), 0, places=7)
        self.assertAlmostEqual(mil2grad(1), 0.0625, places=7)
        self.assertAlmostEqual(mil2grad(6400), 400, places=7)

    def test_mil2rad(self):
        self.assertAlmostEqual(mil2rad(-2), -0.0009817, places=7)
        self.assertAlmostEqual(mil2rad(0), 0, places=7)
        self.assertAlmostEqual(mil2rad(1), 0.0004909, places=7)
        self.assertAlmostEqual(mil2rad(6400), 6.2831853, places=7)

    def test_rad2deg(self):
        self.assertAlmostEqual(rad2deg(-0.1), -5.7295779, places=7)
        self.assertAlmostEqual(rad2deg(0), 0, places=7)
        self.assertAlmostEqual(rad2deg(1), 57.2957795, places=7)
        self.assertAlmostEqual(rad2deg(6.2831853), 360, places=7)

    def test_rad2grad(self):
        self.assertAlmostEqual(rad2grad(-0.1), -6.3661977, places=7)
        self.assertAlmostEqual(rad2grad(0), 0, places=7)
        self.assertAlmostEqual(rad2grad(1), 63.6619772, places=7)
        self.assertAlmostEqual(rad2grad(6.2831853), 400, places=7)

    def test_rad2mil(self):
        self.assertAlmostEqual(rad2mil(-0.1), -101.8591635, places=7)
        self.assertAlmostEqual(rad2mil(0), 0, places=7)
        self.assertAlmostEqual(rad2mil(1), 1018.5916357, places=7)
        self.assertAlmostEqual(rad2mil(6.2831853), 6400, places=7)

if __name__ == '__main__':
    unittest.main()
