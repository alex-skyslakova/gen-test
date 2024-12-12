import unittest
from unittest.mock import patch, MagicMock
from PIL import Image

# Assuming the code is in a file named julia_set.py
import julia_set  # Replace with the actual filename if different

class TestJuliaSet(unittest.TestCase):

    @patch('julia_set.Image.new')
    @patch('julia_set.Image.Image.load')
    @patch('julia_set.Image.Image.show')
    def test_julia_set_generation(self, mock_show, mock_load, mock_new):
        # Mock the Image methods to avoid actual image creation and display
        mock_image = MagicMock()
        mock_pix = MagicMock()
        mock_new.return_value = mock_image
        mock_load.return_value = mock_pix

        # Call the main part of the code (which is normally under if __name__ == "__main__":)
        julia_set.w = 200  # Reduce size for faster testing
        julia_set.h = 150
        julia_set.zoom = 1
        julia_set.cX = -0.7
        julia_set.cY = 0.27015
        julia_set.moveX = 0.0
        julia_set.moveY = 0.0
        julia_set.maxIter = 255

        # Simulate running the code in the __main__ block. 
        # Since the code is not inside a function, we can't directly call it.
        # Workaround is to copy the logic into a test function or refactor the original code
        with patch('julia_set.__name__', '__main__'):  # Simulate __main__
            # Re-import the module.  This is a bit hacky. 
            # The better solution would be to not have the code directly under __main__
            import importlib; importlib.reload(julia_set)


        # Assertions
        mock_new.assert_called_once_with("RGB", (200, 150), "white")  # Check image creation
        mock_load.assert_called_once()  # Check pixel loading
        mock_show.assert_called_once()  # Check image display

        # Check some pixel values (adjust coordinates and expected values as needed)
        # You can calculate expected pixel colors using the same formula to compare.
        # Example: test the corners. Use a smaller test size. 
        self.assertEqual(mock_pix[0][0], 1052688)
        self.assertEqual(mock_pix[199][0], 0)
        self.assertEqual(mock_pix[0][149], 1052688)
        self.assertEqual(mock_pix[199][149], 0)
