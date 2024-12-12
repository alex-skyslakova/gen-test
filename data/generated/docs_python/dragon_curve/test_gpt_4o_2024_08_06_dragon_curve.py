import unittest
from turtle import TurtleScreen, RawTurtle, ScrolledCanvas
from dragon_curve import dragon

class TestDragonCurve(unittest.TestCase):

    def setUp(self):
        # Set up a turtle screen for testing
        self.canvas = ScrolledCanvas()
        self.screen = TurtleScreen(self.canvas)
        self.turtle = RawTurtle(self.screen)

    def test_dragon_curve_zero_steps(self):
        # Test with 0 steps, should not move the turtle
        initial_position = self.turtle.position()
        dragon(0, 100)
        self.assertEqual(self.turtle.position(), initial_position)

    def test_dragon_curve_one_step(self):
        # Test with 1 step, should move the turtle
        initial_position = self.turtle.position()
        dragon(1, 100)
        self.assertNotEqual(self.turtle.position(), initial_position)

    def test_dragon_curve_two_steps(self):
        # Test with 2 steps, should move the turtle
        initial_position = self.turtle.position()
        dragon(2, 100)
        self.assertNotEqual(self.turtle.position(), initial_position)

    def test_dragon_curve_negative_steps(self):
        # Test with negative steps, should not move the turtle
        initial_position = self.turtle.position()
        dragon(-1, 100)
        self.assertEqual(self.turtle.position(), initial_position)

    def test_dragon_curve_large_steps(self):
        # Test with a large number of steps
        initial_position = self.turtle.position()
        dragon(10, 100)
        self.assertNotEqual(self.turtle.position(), initial_position)

    def tearDown(self):
        # Clean up the turtle screen after each test
        self.screen.bye()

if __name__ == '__main__':
    unittest.main()
