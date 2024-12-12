import unittest
from PIL import Image
from hough_transform import hough
import numpy as np
from unittest.mock import patch
import math

class TestHough(unittest.TestCase):

    def setUp(self):
        # Create a simple test image (white background, black diagonal line)
        self.simple_image = Image.new("L", (10, 10), 255)
        pixels = self.simple_image.load()
        for i in range(10):
            pixels[i, i] = 0

        # Create a blank image
        self.blank_image = Image.new("L", (10, 10), 255)


    def test_hough_simple_image(self):
        him = hough(self.simple_image, ntx=10, mry=10)
        # Check for expected darker spot corresponding to the line
        self.assertNotEqual(him.getpixel((0, 5)), 255)  # Example check, adjust based on expected output

    def test_hough_blank_image(self):
        him = hough(self.blank_image, ntx=10, mry=10)
        # Check if the resulting image is all white (no lines detected)
        self.assertEqual(him.getextrema(), (255, 255))

    def test_hough_different_dimensions(self):
      
        him = hough(self.simple_image, ntx=20, mry=30)  # Different dimensions
        self.assertEqual(him.size, (20, 30))

    def test_hough_all_black_image(self):
        black_image = Image.new("L", (10, 10), 0)
        him = hough(black_image, ntx = 10, mry=10)
        # Define an assertion based on the expected behavior for an all-black image
        # Example: Expect the accumulator to have lower values across a wider range
        # due to multiple potential line interpretations.
        self.assertNotEqual(him.getextrema(), (255,255))

    @patch('hough_transform.hypot')
    def test_rmax_calculation(self, mock_hypot):
        mock_hypot.return_value = 10.0 # Example mock value
        him = hough(self.simple_image, ntx=10, mry=10)
        mock_hypot.assert_called_once_with(10, 10)

    @patch('hough_transform.cos')
    @patch('hough_transform.sin')
    def test_polar_to_rectangular_conversion(self, mock_sin, mock_cos):
      jx = 5
      iy = 5
      th = math.pi/4 # Example value

      mock_cos.return_value = 0.7071
      mock_sin.return_value = 0.7071

      hough(self.simple_image, ntx=10, mry=10)

      mock_cos.assert_called_with(th)  # Adjust expected calls and arguments
      mock_sin.assert_called_with(th) # Adjust expected calls and arguments



