import unittest
from PIL import Image
from julia_set import generate_julia_set

class TestJuliaSet(unittest.TestCase):

    def test_image_creation(self):
        w, h, zoom = 800, 600, 1
        bitmap = generate_julia_set(w, h, zoom)
        
        # Check if the image is created with the correct dimensions and background color
        self.assertEqual(bitmap.size, (w, h))
        self.assertEqual(bitmap.mode, "RGB")
        self.assertEqual(bitmap.getpixel((0, 0)), (255, 255, 255))  # White background

    def test_pixel_calculation(self):
        w, h, zoom = 100, 100, 1
        bitmap = generate_julia_set(w, h, zoom)
        pix = bitmap.load()
        
        # Test a few specific pixels to ensure they are calculated correctly
        # For simplicity, we test the center pixel and a few others
        center_pixel = pix[w//2, h//2]
        self.assertNotEqual(center_pixel, (255, 255, 255))  # Center pixel should not be white
        
        # Test a pixel near the edge (e.g., top-left corner)
        edge_pixel = pix[0, 0]
        self.assertNotEqual(edge_pixel, (255, 255, 255))  # Edge pixel should not be white
        
        # Test another pixel near the edge (e.g., bottom-right corner)
        edge_pixel_2 = pix[w-1, h-1]
        self.assertNotEqual(edge_pixel_2, (255, 255, 255))  # Edge pixel should not be white

if __name__ == "__main__":
    unittest.main()
