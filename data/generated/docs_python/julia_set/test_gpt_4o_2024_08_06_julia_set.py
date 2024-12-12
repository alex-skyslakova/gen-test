import unittest
from PIL import Image
from julia_set import generate_julia_set

class TestJuliaSet(unittest.TestCase):

    def setUp(self):
        self.width = 800
        self.height = 600
        self.zoom = 1
        self.cX = -0.7
        self.cY = 0.27015
        self.moveX = 0.0
        self.moveY = 0.0
        self.maxIter = 255

    def test_image_creation(self):
        """Test if the image is created with the correct dimensions."""
        bitmap = Image.new("RGB", (self.width, self.height), "white")
        self.assertEqual(bitmap.size, (self.width, self.height))

    def test_pixel_color_range(self):
        """Test if the pixel colors are within the valid range."""
        bitmap = Image.new("RGB", (self.width, self.height), "white")
        pix = bitmap.load()

        for x in range(self.width):
            for y in range(self.height):
                zx = 1.5 * (x - self.width / 2) / (0.5 * self.zoom * self.width) + self.moveX
                zy = 1.0 * (y - self.height / 2) / (0.5 * self.zoom * self.height) + self.moveY
                i = self.maxIter
                while zx * zx + zy * zy < 4 and i > 1:
                    tmp = zx * zx - zy * zy + self.cX
                    zy, zx = 2.0 * zx * zy + self.cY, tmp
                    i -= 1
                color = (i << 21) + (i << 10) + i * 8
                pix[x, y] = color
                self.assertTrue(0 <= pix[x, y][0] <= 255)
                self.assertTrue(0 <= pix[x, y][1] <= 255)
                self.assertTrue(0 <= pix[x, y][2] <= 255)

    def test_julia_set_generation(self):
        """Test the generation of the Julia set."""
        bitmap = generate_julia_set(self.width, self.height, self.zoom, self.cX, self.cY, self.moveX, self.moveY, self.maxIter)
        self.assertIsInstance(bitmap, Image.Image)
        self.assertEqual(bitmap.size, (self.width, self.height))

if __name__ == '__main__':
    unittest.main()
