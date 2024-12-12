import unittest
from PIL import Image
import colorsys
import math

class TestColorWheel(unittest.TestCase):

    def setUp(self):
        self.im = Image.new("RGB", (300, 300))
        self.radius = min(self.im.size) / 2.0
        self.cx, self.cy = self.im.size[0] / 2, self.im.size[1] / 2
        self.pix = self.im.load()

    def test_center_pixel(self):
        # Test the center pixel of the color wheel
        x, y = self.cx, self.cy
        rx = x - self.cx
        ry = y - self.cy
        s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / self.radius
        h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
        rgb = colorsys.hsv_to_rgb(h, s, 1.0)
        expected_color = tuple([int(round(c * 255.0)) for c in rgb])
        
        self.assertEqual(self.pix[x, y], expected_color)

    def test_edge_pixel(self):
        # Test a pixel on the edge of the color wheel
        x, y = self.cx + self.radius, self.cy
        rx = x - self.cx
        ry = y - self.cy
        s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / self.radius
        h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
        rgb = colorsys.hsv_to_rgb(h, s, 1.0)
        expected_color = tuple([int(round(c * 255.0)) for c in rgb])
        
        self.assertEqual(self.pix[x, y], expected_color)

    def test_outside_pixel(self):
        # Test a pixel outside the color wheel (should be black)
        x, y = self.cx + self.radius + 1, self.cy
        rx = x - self.cx
        ry = y - self.cy
        s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / self.radius
        
        if s > 1.0:
            expected_color = (0, 0, 0)
        else:
            h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
            rgb = colorsys.hsv_to_rgb(h, s, 1.0)
            expected_color = tuple([int(round(c * 255.0)) for c in rgb])
        
        self.assertEqual(self.pix[x, y], expected_color)

    def test_inside_pixel(self):
        # Test a pixel inside the color wheel
        x, y = self.cx + self.radius / 2, self.cy
        rx = x - self.cx
        ry = y - self.cy
        s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / self.radius
        h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
        rgb = colorsys.hsv_to_rgb(h, s, 1.0)
        expected_color = tuple([int(round(c * 255.0)) for c in rgb])
        
        self.assertEqual(self.pix[x, y], expected_color)

    def test_all_pixels(self):
        # Test all pixels to ensure they are correctly colored
        for x in range(self.im.width):
            for y in range(self.im.height):
                rx = x - self.cx
                ry = y - self.cy
                s = (rx ** 2.0 + ry ** 2.0) ** 0.5 / self.radius
                if s <= 1.0:
                    h = ((math.atan2(ry, rx) / math.pi) + 1.0) / 2.0
                    rgb = colorsys.hsv_to_rgb(h, s, 1.0)
                    expected_color = tuple([int(round(c * 255.0)) for c in rgb])
                    self.assertEqual(self.pix[x, y], expected_color)
                else:
                    self.assertEqual(self.pix[x, y], (0, 0, 0))

if __name__ == "__main__":
    unittest.main()
