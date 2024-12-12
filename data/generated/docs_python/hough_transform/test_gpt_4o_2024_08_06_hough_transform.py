import unittest
from PIL import Image
from hough_transform import hough

class TestHoughTransform(unittest.TestCase):

    def setUp(self):
        # Create a simple black and white image for testing
        self.test_image = Image.new("L", (100, 100), 255)
        pixels = self.test_image.load()
        for i in range(100):
            pixels[i, i] = 0  # Draw a diagonal line

    def test_hough_transform_output_type(self):
        # Test if the output is an instance of PIL Image
        result = hough(self.test_image)
        self.assertIsInstance(result, Image.Image)

    def test_hough_transform_output_size(self):
        # Test if the output image has the expected size
        ntx, mry = 460, 360
        result = hough(self.test_image, ntx, mry)
        self.assertEqual(result.size, (ntx, mry))

    def test_hough_transform_line_detection(self):
        # Test if the Hough transform detects the line correctly
        result = hough(self.test_image)
        pixels = result.load()
        # Check if there is a significant change in pixel values indicating a line
        line_detected = any(pixels[x, y] < 255 for x in range(result.size[0]) for y in range(result.size[1]))
        self.assertTrue(line_detected)

    def test_hough_transform_no_line(self):
        # Test with an image with no lines
        blank_image = Image.new("L", (100, 100), 255)
        result = hough(blank_image)
        pixels = result.load()
        # Check if all pixel values are 255 indicating no lines
        no_line_detected = all(pixels[x, y] == 255 for x in range(result.size[0]) for y in range(result.size[1]))
        self.assertTrue(no_line_detected)

if __name__ == '__main__':
    unittest.main()
