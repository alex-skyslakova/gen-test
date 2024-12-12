import unittest
from PIL import Image
from barnsley_fern import BarnsleyFern

class TestBarnsleyFern(unittest.TestCase):

    def test_init(self):
        fern = BarnsleyFern(500, 500)
        self.assertEqual(fern.img_width, 500)
        self.assertEqual(fern.img_height, 500)
        self.assertEqual(fern.paint_color, (0, 150, 0))
        self.assertEqual(fern.x, 0)
        self.assertEqual(fern.y, 0)
        self.assertEqual(fern.age, 0)
        self.assertIsInstance(fern.fern, Image.Image)

    def test_scale(self):
        fern = BarnsleyFern(500, 500)
        self.assertEqual(fern.scale(0, 0), (45.25483400566575, 499.0))
        self.assertEqual(fern.scale(-2.182, 9.9983), (0.0, 0.0))
        self.assertEqual(fern.scale(2.6558, 0), (499.0, 499.0))

    def test_transform(self):
        fern = BarnsleyFern(500, 500)
        # Test all 4 transformations by mocking random.uniform
        with unittest.mock.patch('barnsley_fern.random.uniform') as mock_random:
            mock_random.return_value = 0.5  # f1
            self.assertEqual(fern.transform(1, 1), (0, 0.16))

            mock_random.return_value = 50  # f2
            self.assertEqual(fern.transform(1, 1), (0.89, 2.41))

            mock_random.return_value = 90  # f3
            self.assertEqual(fern.transform(1, 1), (-0.06, 2.05))

            mock_random.return_value = 95 # f4
            self.assertEqual(fern.transform(1, 1), (0.13, 0.94))


    def test_iterate(self):
        fern = BarnsleyFern(500, 500)
        initial_age = fern.age
        fern.iterate(1000)
        self.assertEqual(fern.age, initial_age + 1000)
        # Check if pixels have been modified (indirectly testing transform and scale)
        #  It's difficult to test precise pixel values because of the randomness involved
        self.assertNotEqual(fern.fern.getpixel((0,0)), fern.fern.getpixel((250,250)) ) 



