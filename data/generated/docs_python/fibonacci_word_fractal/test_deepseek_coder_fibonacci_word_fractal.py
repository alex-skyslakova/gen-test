import unittest
from fibonacci_word_fractal import fibonacci_word, draw_fractal

class TestFibonacciWordFractal(unittest.TestCase):

    def test_fibonacci_word(self):
        # Test the first few Fibonacci words
        self.assertEqual(fibonacci_word(1), "1")
        self.assertEqual(fibonacci_word(2), "0")
        self.assertEqual(fibonacci_word(3), "01")
        self.assertEqual(fibonacci_word(4), "010")
        self.assertEqual(fibonacci_word(5), "01001")
        self.assertEqual(fibonacci_word(6), "01001010")

    def test_fibonacci_word_invalid_input(self):
        # Test invalid input for fibonacci_word
        with self.assertRaises(AssertionError):
            fibonacci_word(0)
        with self.assertRaises(AssertionError):
            fibonacci_word(-1)

    def test_draw_fractal_logic(self):
        # Mock the turtle functions to test the logic of draw_fractal
        class MockTurtle:
            def __init__(self):
                self.actions = []

            def forward(self, step):
                self.actions.append(f"forward({step})")

            def left(self, angle):
                self.actions.append(f"left({angle})")

            def right(self, angle):
                self.actions.append(f"right({angle})")

        mock_turtle = MockTurtle()
        draw_fractal_with_mock = draw_fractal.__wrapped__

        # Test with a simple Fibonacci word
        draw_fractal_with_mock("01", 10, mock_turtle)
        self.assertEqual(mock_turtle.actions, [
            "forward(10)",
            "left(90)",
            "forward(10)",
            "right(90)"
        ])

        # Test with a longer Fibonacci word
        mock_turtle.actions.clear()
        draw_fractal_with_mock("01001", 5, mock_turtle)
        self.assertEqual(mock_turtle.actions, [
            "forward(5)",
            "left(90)",
            "forward(5)",
            "right(90)",
            "forward(5)",
            "left(90)",
            "forward(5)",
            "left(90)",
            "forward(5)",
            "right(90)"
        ])

if __name__ == '__main__':
    unittest.main()
