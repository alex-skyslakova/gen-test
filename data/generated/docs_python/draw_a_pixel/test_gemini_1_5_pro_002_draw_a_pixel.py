from PIL import Image
import unittest
from unittest.mock import patch
import io

class TestDrawPixel(unittest.TestCase):

    @patch('PIL.Image.Image.show')
    def test_pixel_color_and_position(self, mock_show):
        # Mock show to avoid opening a window during testing
        img = Image.new('RGB', (320, 240))
        pixels = img.load()
        pixels[100, 100] = (255, 0, 0)

        # Check the pixel color at the specified position
        self.assertEqual(pixels[100, 100], (255, 0, 0))

        # Check image dimensions
        self.assertEqual(img.size, (320, 240))

        # Optionally, check if show() was called (though not strictly necessary for this simple example)
        mock_show.assert_called_once()


    @patch('PIL.Image.Image.show')
    def test_image_output(self, mock_show):  # More robust test using in-memory buffer
        img = Image.new('RGB', (320, 240))
        pixels = img.load()
        pixels[100, 100] = (255, 0, 0)


        # Save the image to an in-memory buffer to check the actual pixel data
        buffer = io.BytesIO()
        img.save(buffer, format="PNG")
        buffer.seek(0)

        # Re-open the image from the buffer and check pixel
        loaded_img = Image.open(buffer)
        loaded_pixels = loaded_img.load()
        self.assertEqual(loaded_pixels[100, 100], (255, 0, 0))
        


if __name__ == '__main__':
    unittest.main()

