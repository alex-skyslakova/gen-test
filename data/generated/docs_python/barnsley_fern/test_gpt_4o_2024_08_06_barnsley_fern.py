import unittest
from barnsley_fern import BarnsleyFern

class TestBarnsleyFern(unittest.TestCase):

    def setUp(self):
        self.fern = BarnsleyFern(500, 500)

    def test_initial_position(self):
        # Test initial position of the fern
        self.assertEqual((self.fern.x, self.fern.y), (0, 0))

    def test_scale_function(self):
        # Test the scale function with known values
        h, k = self.fern.scale(0, 0)
        self.assertAlmostEqual(h, 226.182, places=3)
        self.assertAlmostEqual(k, 499.0, places=1)

    def test_transform_function_f1(self):
        # Mock random.uniform to test f1 transformation
        random.uniform = lambda a, b: 0.5
        x, y = self.fern.transform(0, 0)
        self.assertEqual((x, y), (0, 0))

    def test_transform_function_f2(self):
        # Mock random.uniform to test f2 transformation
        random.uniform = lambda a, b: 50
        x, y = self.fern.transform(0, 0)
        self.assertEqual((x, y), (0, 1.6))

    def test_transform_function_f3(self):
        # Mock random.uniform to test f3 transformation
        random.uniform = lambda a, b: 90
        x, y = self.fern.transform(0, 0)
        self.assertEqual((x, y), (0, 1.6))

    def test_transform_function_f4(self):
        # Mock random.uniform to test f4 transformation
        random.uniform = lambda a, b: 95
        x, y = self.fern.transform(0, 0)
        self.assertEqual((x, y), (0, 0.44))

    def test_iterate_function(self):
        # Test iterate function to ensure it updates the age
        initial_age = self.fern.age
        self.fern.iterate(100)
        self.assertEqual(self.fern.age, initial_age + 100)

    def test_image_creation(self):
        # Test if the image is created with the correct dimensions
        self.assertEqual(self.fern.fern.size, (500, 500))

if __name__ == '__main__':
    unittest.main()
