import unittest
from unittest.mock import patch
from fibonacci_word_fractal import fibonacci_word, draw_fractal

class TestFibonacciWord(unittest.TestCase):

    def test_fibonacci_word_base_cases(self):
        self.assertEqual(fibonacci_word(1), "1")
        self.assertEqual(fibonacci_word(2), "0")

    def test_fibonacci_word_recursive(self):
        self.assertEqual(fibonacci_word(3), "10")
        self.assertEqual(fibonacci_word(4), "101")
        self.assertEqual(fibonacci_word(5), "10110")

class TestDrawFractal(unittest.TestCase):

    @patch('fibonacci_word_fractal.forward')
    @patch('fibonacci_word_fractal.left')
    @patch('fibonacci_word_fractal.right')
    def test_draw_fractal_all_ones(self, mock_right, mock_left, mock_forward):
        draw_fractal("111", 10)
        self.assertEqual(mock_forward.call_count, 3)
        mock_right.assert_not_called()
        mock_left.assert_not_called()


    @patch('fibonacci_word_fractal.forward')
    @patch('fibonacci_word_fractal.left')
    @patch('fibonacci_word_fractal.right')
    def test_draw_fractal_alternating_zeros(self, mock_right, mock_left, mock_forward):
        draw_fractal("000", 10)
        self.assertEqual(mock_forward.call_count, 3)
        self.assertEqual(mock_right.call_count, 1)  # For the first and third '0'
        self.assertEqual(mock_left.call_count, 1)   # For the second '0'
        mock_right.assert_called_with(90)
        mock_left.assert_called_with(90)



    @patch('fibonacci_word_fractal.forward')
    @patch('fibonacci_word_fractal.left')
    @patch('fibonacci_word_fractal.right')
    def test_draw_fractal_mixed(self, mock_right, mock_left, mock_forward):
        draw_fractal("101001", 10)
        self.assertEqual(mock_forward.call_count, 6)
        self.assertEqual(mock_right.call_count, 2)
        self.assertEqual(mock_left.call_count, 1)




