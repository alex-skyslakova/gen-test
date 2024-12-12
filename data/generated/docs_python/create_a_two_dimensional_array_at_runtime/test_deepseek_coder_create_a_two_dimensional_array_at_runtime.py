import unittest
from unittest.mock import patch

class TestCreateTwoDimensionalArray(unittest.TestCase):

    @patch('builtins.raw_input', side_effect=['3', '4'])
    def test_create_array_and_set_element(self, mock_raw_input):
        # Import the function to be tested
        from create_a_two_dimensional_array_at_runtime import width, height, myarray

        # Check if the dimensions are set correctly
        self.assertEqual(width, 3)
        self.assertEqual(height, 4)

        # Check if the array is created correctly
        self.assertEqual(len(myarray), height)
        for row in myarray:
            self.assertEqual(len(row), width)

        # Check if the element is set correctly
        self.assertEqual(myarray[0][0], 3.5)

    @patch('builtins.raw_input', side_effect=['0', '0'])
    def test_create_empty_array(self, mock_raw_input):
        # Import the function to be tested
        from create_a_two_dimensional_array_at_runtime import width, height, myarray

        # Check if the dimensions are set correctly
        self.assertEqual(width, 0)
        self.assertEqual(height, 0)

        # Check if the array is created correctly
        self.assertEqual(len(myarray), height)
        for row in myarray:
            self.assertEqual(len(row), width)

        # Check if the element is set correctly (should not raise an error)
        try:
            myarray[0][0] = 3.5
        except IndexError:
            pass  # Expected behavior for empty array

    @patch('builtins.raw_input', side_effect=['-1', '4'])
    def test_negative_width(self, mock_raw_input):
        # Import the function to be tested
        from create_a_two_dimensional_array_at_runtime import width, height, myarray

        # Check if the dimensions are set correctly
        self.assertEqual(width, -1)
        self.assertEqual(height, 4)

        # Check if the array is created correctly
        self.assertEqual(len(myarray), height)
        for row in myarray:
            self.assertEqual(len(row), abs(width))  # Width should be treated as positive

        # Check if the element is set correctly (should not raise an error)
        try:
            myarray[0][0] = 3.5
        except IndexError:
            pass  # Expected behavior for negative width

    @patch('builtins.raw_input', side_effect=['3', '-1'])
    def test_negative_height(self, mock_raw_input):
        # Import the function to be tested
        from create_a_two_dimensional_array_at_runtime import width, height, myarray

        # Check if the dimensions are set correctly
        self.assertEqual(width, 3)
        self.assertEqual(height, -1)

        # Check if the array is created correctly
        self.assertEqual(len(myarray), abs(height))  # Height should be treated as positive
        for row in myarray:
            self.assertEqual(len(row), width)

        # Check if the element is set correctly (should not raise an error)
        try:
            myarray[0][0] = 3.5
        except IndexError:
            pass  # Expected behavior for negative height

if __name__ == '__main__':
    unittest.main()
