import unittest
from unittest.mock import patch, Mock
from livewires import Colour, begin_graphics, box, keys_pressed, set_colour

# Mock necessary livewires functions to avoid graphical output during testing
mock_begin_graphics = Mock()
mock_box = Mock()
mock_set_colour = Mock()
mock_keys_pressed = Mock(side_effect=[[], [' ']])  # Simulate spacebar press

@patch('livewires.begin_graphics', mock_begin_graphics)
@patch('livewires.box', mock_box)
@patch('livewires.set_colour', mock_set_colour)
@patch('livewires.keys_pressed', mock_keys_pressed)
class TestGreyScaleBars(unittest.TestCase):

    def test_ty_pruhy(self):
        horiz = 640
        vert = 480
        pruh = vert / 4
        dpp = 255.0

        def ty_pruhy(each):
            hiy = each[0] * pruh
            loy = hiy - pruh
            krok = horiz / each[1]
            piecol = 255.0 / (each[1] - 1)
            for x in range(0, each[1]):
                barva = Colour(piecol * x / dpp, piecol * x / dpp, piecol * x / dpp)
                mock_set_colour.assert_called_with(barva) #check color setting
                mock_set_colour.reset_mock() #clear the calls log for a clean test in the next loop
                if each[2]:
                    mock_box.assert_called_with(x * krok, hiy, x * krok + krok, loy, filled=1)
                else:
                    mock_box.assert_called_with(horiz - x * krok, hiy, horiz - ((x + 1) * krok), loy, filled=1)
                mock_box.reset_mock() #clear the calls log

        test_cases = [[4, 8, True], [3, 16, False], [2, 32, True], [1, 64, False]]
        for case in test_cases:
            ty_pruhy(case)
            # Check if the correct number of boxes were drawn
            self.assertEqual(mock_box.call_count, case[1])

    def test_main_loop(self):
        horiz = 640
        vert = 480
        pruh = vert / 4
        dpp = 255.0

        def ty_pruhy(each): #no action here as it's tested separately, but must be here 
            pass

        source = [[4, 8, True], [3, 16, False], [2, 32, True], [1, 64, False]]
        for each in source:
            ty_pruhy(each)

        # Check that begin_graphics was called with the correct parameters
        mock_begin_graphics.assert_called_once_with(width=horiz, height=vert, title="Gray stripes", background=Colour.black)

if __name__ == '__main__':
    unittest.main()

