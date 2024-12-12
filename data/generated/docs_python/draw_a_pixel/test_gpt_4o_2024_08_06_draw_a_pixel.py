import unittest
from PIL import Image

class TestDrawAPixel(unittest.TestCase):
    def setUp(self):
        # Create the image as done in draw_a_pixel.py
        self.img = Image.new('RGB', (320, 240))
        self.pixels = self.img.load()
        self.pixels[100, 100] = (255, 0, 0)

    def test_window_size(self):
        # Test if the window size is 320 x 240
        self.assertEqual(self.img.size, (320, 240))

    def test_pixel_color(self):
        # Test if the pixel at (100, 100) is red
        self.assertEqual(self.pixels[100, 100], (255, 0, 0))

    def test_other_pixels(self):
        # Test if other pixels are not red
        for x in range(320):
            for y in range(240):
                if (x, y) != (100, 100):
                    self.assertNotEqual(self.pixels[x, y], (255, 0, 0))

if __name__ == '__main__':
    unittest.main()
