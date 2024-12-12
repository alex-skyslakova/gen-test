import unittest
from unittest.mock import patch
from turtle import TurtleScreen, RawTurtle

# Assuming the code is in a file named 'color_pinstripe_display.py'
from color_pinstripe_display import colors, left_edge, right_edge, quarter_height, half_height  # Import necessary variables

class TestColorPinstripeDisplay(unittest.TestCase):

    @patch('color_pinstripe_display.getscreen')
    @patch('color_pinstripe_display.input', return_value='') # Mock input to avoid blocking
    def test_pinstripe_drawing(self, mock_input, mock_getscreen):
        mock_screen = TurtleScreen()
        mock_screen.setup(width=400, height=300)  # Set up a fixed size screen for testing
        mock_screen.colormode(255) # Ensure colormode is set
        mock_getscreen.return_value = mock_screen
        mock_turtle = RawTurtle(mock_screen)
        
        # Call the drawing logic (indirectly through module import side effects).
        # Importing the module runs the drawing code.
        from color_pinstripe_display import * # Re-import to trigger the drawing code with mock in place
        

        # Assertions: check pensize, color, and positions at specific points.

        # Example: check the first pinstripe of the first quarter
        mock_turtle.penup()
        mock_turtle.setpos(left_edge, -quarter_height)
        mock_turtle.pendown()
        self.assertEqual(mock_turtle.pensize(), 1)
        self.assertEqual(mock_turtle.pencolor(), colors[0]) # Black
        self.assertEqual(mock_turtle.pos(), (left_edge, -quarter_height))
        mock_turtle.penup()
        mock_turtle.setpos(left_edge, 0)
        self.assertEqual(mock_turtle.pos(), (left_edge, 0))


        # Example: Check a pinstripe in the second quarter
        mock_turtle.penup()
        mock_turtle.setpos(left_edge + 2, -2 * quarter_height)
        mock_turtle.pendown()
        self.assertEqual(mock_turtle.pensize(), 2)
        self.assertEqual(mock_turtle.pencolor(), colors[0]) # Color cycle repeats, starting with black
        self.assertEqual(mock_turtle.pos(), (left_edge + 2, -2* quarter_height))

        # Example: Check a pinstripe in the third quarter
        mock_turtle.penup()
        mock_turtle.setpos(left_edge+6, -3*quarter_height)
        self.assertEqual(mock_turtle.pensize(), 3)


        # Example: Check the last pinstripe color of the fourth quarter (wrapping around color list)
        last_x = right_edge - (right_edge % 4)  # Adjust for the pinstripe width
        mock_turtle.penup()
        mock_turtle.setpos(last_x, -4 * quarter_height)  
        mock_turtle.pendown()
        expected_last_color_index = (last_x // 4) % len(colors)
        self.assertEqual(mock_turtle.pencolor(), colors[expected_last_color_index])



        mock_screen.mainloop() # Keep the window open to visually inspect (optional) – comment out for automated testing



if __name__ == '__main__':
    unittest.main()
