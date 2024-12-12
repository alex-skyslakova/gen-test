from PIL import Image
import colorsys
import math
import unittest
from unittest.mock import patch
import io

class TestColorWheel(unittest.TestCase):

    @patch('PIL.Image.Image.show')
    def test_color_wheel_generation(self, mock_show):
        # Mock Image.show to avoid opening the image during testing
        
        # Create a smaller image for faster testing
        width, height = 100, 100
        im = Image.new("RGB", (width, height))
        radius = min(im.size) / 2.0
        cx, cy = im.size[0] / 2, im.size[1] / 2
        pix = im.load()

        for x in range(im.width):
            for y in range(im.height):
                rx = x - cx
                ry = y - cy
                s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / radius
                if s <= 1.0:
                    h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
                    rgb = colorsys.hsv_to_rgb(h, s, 1.0)
                    pix[x, y] = tuple([int(round(c * 255.0)) for c in rgb])

        # Assertions - Check a few specific pixels for expected color values.
        # These values are determined based on the color wheel logic.
        center_pixel = pix[int(cx), int(cy)]
        self.assertEqual(center_pixel, (255, 0, 0)) # Red at the center

        # Pixel at the edge, right side (hue should be close to 0)
        edge_pixel_right = pix[int(cx + radius -1), int(cy)]
        self.assertAlmostEqual(colorsys.rgb_to_hsv(tuple(c/255 for c in edge_pixel_right))[0], 0, delta=0.02) # Reddish


        # Pixel at the edge, top (hue should be close to 0.25 i.e. greenish)
        edge_pixel_top = pix[int(cx), int(cy- radius + 1)]
        self.assertAlmostEqual(colorsys.rgb_to_hsv(tuple(c/255 for c in edge_pixel_top))[0], 0.25, delta=0.02) # Greenish


        # Pixel outside the circle - should be black by default because it's not modified.
        outside_pixel = pix[0,0]
        self.assertEqual(outside_pixel, (0, 0, 0))

        # Verify that im.show() was called.
        mock_show.assert_called_once_with()




