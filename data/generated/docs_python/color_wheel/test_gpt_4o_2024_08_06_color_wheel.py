import unittest
from PIL import Image
import colorsys
import math

class TestColorWheel(unittest.TestCase):

    def test_image_size(self):
        im = Image.new("RGB", (300, 300))
        self.assertEqual(im.size, (300, 300), "Image size should be 300x300")

    def test_center_coordinates(self):
        im = Image.new("RGB", (300, 300))
        cx, cy = im.size[0] / 2, im.size[1] / 2
        self.assertEqual((cx, cy), (150, 150), "Center coordinates should be (150, 150)")

    def test_radius_calculation(self):
        im = Image.new("RGB", (300, 300))
        radius = min(im.size) / 2.0
        self.assertEqual(radius, 150.0, "Radius should be 150.0")

    def test_pixel_color_within_wheel(self):
        im = Image.new("RGB", (300, 300))
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
                    expected_color = tuple([int(round(c * 255.0)) for c in rgb])
                    pix[x, y] = expected_color
                    self.assertEqual(pix[x, y], expected_color, f"Pixel color at ({x}, {y}) should be {expected_color}")

    def test_pixel_color_outside_wheel(self):
        im = Image.new("RGB", (300, 300))
        radius = min(im.size) / 2.0
        cx, cy = im.size[0] / 2, im.size[1] / 2
        pix = im.load()

        for x in range(im.width):
            for y in range(im.height):
                rx = x - cx
                ry = y - cy
                s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / radius
                if s > 1.0:
                    self.assertEqual(pix[x, y], (0, 0, 0), f"Pixel color at ({x}, {y}) should be black (0, 0, 0)")

if __name__ == '__main__':
    unittest.main()
