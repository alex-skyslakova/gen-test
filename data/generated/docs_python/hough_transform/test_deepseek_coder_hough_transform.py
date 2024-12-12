import unittest
from PIL import Image
from hough_transform import hough

class TestHoughTransform(unittest.TestCase):

    def test_hough_transform_dimensions(self):
        # Test with a small image
        im = Image.new("L", (100, 100), 255)
        him = hough(im, ntx=200, mry=150)
        self.assertEqual(him.size, (200, 150))

        # Test with a larger image
        im = Image.new("L", (500, 500), 255)
        him = hough(im, ntx=460, mry=360)
        self.assertEqual(him.size, (460, 360))

    def test_hough_transform_with_single_point(self):
        # Test with a single black point in the image
        im = Image.new("L", (100, 100), 255)
        im.putpixel((50, 50), 0)
        him = hough(im, ntx=200, mry=150)
        # Check if the output image has any non-255 values
        phim = him.load()
        has_non_255 = any(phim[x, y] != 255 for x in range(200) for y in range(150))
        self.assertTrue(has_non_255)

    def test_hough_transform_with_line(self):
        # Test with a horizontal line in the image
        im = Image.new("L", (100, 100), 255)
        for x in range(100):
            im.putpixel((x, 50), 0)
        him = hough(im, ntx=200, mry=150)
        # Check if the output image has a significant change in values
        phim = him.load()
        max_value = max(phim[x, y] for x in range(200) for y in range(150))
        min_value = min(phim[x, y] for x in range(200) for y in range(150))
        self.assertLess(min_value, 255)
        self.assertGreater(max_value, 0)

    def test_hough_transform_with_empty_image(self):
        # Test with an empty (all white) image
        im = Image.new("L", (100, 100), 255)
        him = hough(im, ntx=200, mry=150)
        # Check if the output image is all 255
        phim = him.load()
        all_255 = all(phim[x, y] == 255 for x in range(200) for y in range(150))
        self.assertTrue(all_255)

    def test_hough_transform_with_vertical_line(self):
        # Test with a vertical line in the image
        im = Image.new("L", (100, 100), 255)
        for y in range(100):
            im.putpixel((50, y), 0)
        him = hough(im, ntx=200, mry=150)
        # Check if the output image has a significant change in values
        phim = him.load()
        max_value = max(phim[x, y] for x in range(200) for y in range(150))
        min_value = min(phim[x, y] for x in range(200) for y in range(150))
        self.assertLess(min_value, 255)
        self.assertGreater(max_value, 0)

if __name__ == "__main__":
    unittest.main()
