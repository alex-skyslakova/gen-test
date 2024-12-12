import unittest
from unittest.mock import patch, MagicMock
from greyscale_bars_display import ty_pruhy, begin_graphics, set_colour, box, keys_pressed, Colour

class TestGreyscaleBarsDisplay(unittest.TestCase):

    @patch('greyscale_bars_display.begin_graphics')
    def test_begin_graphics_called_with_correct_parameters(self, mock_begin_graphics):
        begin_graphics(width=640, height=480, title="Gray stripes", background=Colour.black)
        mock_begin_graphics.assert_called_once_with(width=640, height=480, title="Gray stripes", background=Colour.black)

    @patch('greyscale_bars_display.set_colour')
    @patch('greyscale_bars_display.box')
    def test_ty_pruhy_creates_correct_bars(self, mock_box, mock_set_colour):
        # Test for the first quarter (8 bars, black to white)
        ty_pruhy([4, 8, True])
        self.assertEqual(mock_set_colour.call_count, 8)
        self.assertEqual(mock_box.call_count, 8)

        # Test for the second quarter (16 bars, white to black)
        mock_set_colour.reset_mock()
        mock_box.reset_mock()
        ty_pruhy([3, 16, False])
        self.assertEqual(mock_set_colour.call_count, 16)
        self.assertEqual(mock_box.call_count, 16)

        # Test for the third quarter (32 bars, black to white)
        mock_set_colour.reset_mock()
        mock_box.reset_mock()
        ty_pruhy([2, 32, True])
        self.assertEqual(mock_set_colour.call_count, 32)
        self.assertEqual(mock_box.call_count, 32)

        # Test for the fourth quarter (64 bars, white to black)
        mock_set_colour.reset_mock()
        mock_box.reset_mock()
        ty_pruhy([1, 64, False])
        self.assertEqual(mock_set_colour.call_count, 64)
        self.assertEqual(mock_box.call_count, 64)

    @patch('greyscale_bars_display.keys_pressed', return_value=[' '])
    def test_exit_on_spacebar_press(self, mock_keys_pressed):
        self.assertEqual(keys_pressed(), [' '])

if __name__ == '__main__':
    unittest.main()
