import unittest
from unittest.mock import patch
from turtle import Screen, Turtle

class TestColorPinstripeDisplay(unittest.TestCase):

    @patch('turtle.getscreen')
    @patch('turtle.setposition')
    @patch('turtle.pencolor')
    @patch('turtle.pensize')
    @patch('turtle.penup')
    @patch('turtle.pendown')
    def test_pinstripe_display(self, mock_pendown, mock_penup, mock_pensize, mock_pencolor, mock_setposition, mock_getscreen):
        # Mock screen dimensions
        mock_screen = Screen()
        mock_screen.window_width = lambda: 800
        mock_screen.window_height = lambda: 600
        mock_getscreen.return_value = mock_screen

        # Expected calls
        expected_colors = ["black", "red", "green", "blue", "magenta", "cyan", "yellow", "white"]
        expected_pensizes = [1, 2, 3, 4]
        expected_positions = []

        left_edge = -mock_screen.window_width() // 2
        right_edge = mock_screen.window_width() // 2
        quarter_height = mock_screen.window_height() // 4
        half_height = quarter_height * 2

        for quarter in range(4):
            pensize = quarter + 1
            min_y = half_height - ((quarter + 1) * quarter_height)
            max_y = half_height - (quarter * quarter_height)

            for x in range(left_edge, right_edge, pensize):
                expected_positions.append((x, min_y))
                expected_positions.append((x, max_y))

        # Run the code
        import color_pinstripe_display

        # Check pensize calls
        pensize_calls = [call[0][0] for call in mock_pensize.call_args_list]
        self.assertEqual(pensize_calls, expected_pensizes * (right_edge - left_edge) // sum(expected_pensizes))

        # Check pencolor calls
        pencolor_calls = [call[0][0] for call in mock_pencolor.call_args_list]
        self.assertEqual(pencolor_calls, expected_colors * ((right_edge - left_edge) // len(expected_colors)))

        # Check setposition calls
        setposition_calls = [call[0] for call in mock_setposition.call_args_list]
        self.assertEqual(setposition_calls, expected_positions)

        # Check penup and pendown calls
        self.assertEqual(mock_penup.call_count, len(expected_positions) // 2)
        self.assertEqual(mock_pendown.call_count, len(expected_positions) // 2)

if __name__ == '__main__':
    unittest.main()
