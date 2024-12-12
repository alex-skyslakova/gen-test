import unittest
from PIL import Image

class TestDrawAPixel(unittest.TestCase):

    def test_window_size(self):
        img = Image.new('RGB', (320, 240))
        self.assertEqual(img.size, (320, 240), "Window size should be 320x240")

    def test_pixel_color(self):
        img = Image.new('RGB', (320, 240))
        pixels = img.load()
        pixels[100, 100] = (255, 0, 0)
        self.assertEqual(pixels[100, 100], (255, 0, 0), "Pixel at (100, 100) should be red (255, 0, 0)")

    def test_pixel_position(self):
        img = Image.new('RGB', (320, 240))
        pixels = img.load()
        pixels[100, 100] = (255, 0, 0)
        # Check that the pixel at (100, 100) is red and other pixels are not
        self.assertEqual(pixels[100, 100], (255, 0, 0), "Pixel at (100, 100) should be red (255, 0, 0)")
        self.assertNotEqual(pixels[0, 0], (255, 0, 0), "Pixel at (0, 0) should not be red (255, 0, 0)")
        self.assertNotEqual(pixels[200, 200], (255, 0, 0), "Pixel at (200, 200) should not be red (255, 0, 0)")

if __name__ == '__main__':
    unittest.main()
