import unittest
from unittest.mock import patch
from barnsley_fern import BarnsleyFern

class TestBarnsleyFern(unittest.TestCase):

    def setUp(self):
        self.fern = BarnsleyFern(500, 500)

    def test_initialization(self):
        self.assertEqual(self.fern.img_width, 500)
        self.assertEqual(self.fern.img_height, 500)
        self.assertEqual(self.fern.paint_color, (0, 150, 0))
        self.assertEqual(self.fern.x, 0)
        self.assertEqual(self.fern.y, 0)
        self.assertEqual(self.fern.age, 0)
        self.assertEqual(self.fern.pix[self.fern.scale(0, 0)], (0, 150, 0))

    def test_scale(self):
        h, k = self.fern.scale(0, 0)
        self.assertEqual(h, 225)
        self.assertEqual(k, 499)

        h, k = self.fern.scale(2.182, 9.9983)
        self.assertEqual(h, 499)
        self.assertEqual(k, 0)

    def test_transform(self):
        with patch('random.uniform', return_value=0.5):
            x, y = self.fern.transform(0, 0)
            self.assertAlmostEqual(x, 0.0)
            self.assertAlmostEqual(y, 1.6)

        with patch('random.uniform', return_value=85.5):
            x, y = self.fern.transform(1, 1)
            self.assertAlmostEqual(x, 0.89)
            self.assertAlmostEqual(y, 2.41)

        with patch('random.uniform', return_value=86.5):
            x, y = self.fern.transform(1, 1)
            self.assertAlmostEqual(x, -0.06)
            self.assertAlmostEqual(y, 1.85)

        with patch('random.uniform', return_value=99.5):
            x, y = self.fern.transform(1, 1)
            self.assertAlmostEqual(x, 0.13)
            self.assertAlmostEqual(y, 0.94)

    def test_iterate(self):
        with patch('random.uniform', return_value=0.5):
            self.fern.iterate(1)
            self.assertEqual(self.fern.age, 1)
            self.assertAlmostEqual(self.fern.x, 0.0)
            self.assertAlmostEqual(self.fern.y, 1.6)
            self.assertEqual(self.fern.pix[self.fern.scale(0.0, 1.6)], (0, 150, 0))

        with patch('random.uniform', return_value=85.5):
            self.fern.iterate(1)
            self.assertEqual(self.fern.age, 2)
            self.assertAlmostEqual(self.fern.x, 0.0)
            self.assertAlmostEqual(self.fern.y, 2.86)
            self.assertEqual(self.fern.pix[self.fern.scale(0.0, 2.86)], (0, 150, 0))

if __name__ == '__main__':
    unittest.main()
