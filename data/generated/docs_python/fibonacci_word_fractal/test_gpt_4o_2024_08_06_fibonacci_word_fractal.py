import unittest
from fibonacci_word_fractal import fibonacci_word, draw_fractal
from turtle import TurtleScreen, RawTurtle, ScrolledCanvas

class TestFibonacciWordFractal(unittest.TestCase):

    def test_fibonacci_word_base_cases(self):
        self.assertEqual(fibonacci_word(1), "1")
        self.assertEqual(fibonacci_word(2), "0")

    def test_fibonacci_word_recursive_cases(self):
        self.assertEqual(fibonacci_word(3), "10")
        self.assertEqual(fibonacci_word(4), "010")
        self.assertEqual(fibonacci_word(5), "10010")
        self.assertEqual(fibonacci_word(6), "01010010")

    def test_fibonacci_word_large_n(self):
        result = fibonacci_word(10)
        expected = "01001010010010100101001001010010"
        self.assertEqual(result, expected)

    def test_draw_fractal(self):
        # Setup a mock turtle screen and turtle
        canvas = ScrolledCanvas()
        screen = TurtleScreen(canvas)
        turtle = RawTurtle(screen)

        # Test drawing with a simple word
        word = "010"
        step = 10
        draw_fractal(word, step)

        # Check the turtle's final position and heading
        # This is a simple check, in a real scenario, we would need to verify the drawing visually
        self.assertAlmostEqual(turtle.xcor(), 0)
        self.assertAlmostEqual(turtle.ycor(), 10)
        self.assertAlmostEqual(turtle.heading(), 0)

if __name__ == '__main__':
    unittest.main()
