import unittest
from unittest.mock import patch
from galton_box_animation import Ball, Board

class TestBall(unittest.TestCase):
    def test_initial_position(self):
        ball = Ball()
        self.assertEqual(ball.x, 0)
        self.assertEqual(ball.y, 0)

    def test_update_position(self):
        ball = Ball()
        with patch('random.randint', return_value=1):
            ball.update()
            self.assertEqual(ball.x, 1)
            self.assertEqual(ball.y, 1)

        with patch('random.randint', return_value=0):
            ball.update()
            self.assertEqual(ball.x, 1)
            self.assertEqual(ball.y, 2)

    def test_fall(self):
        ball = Ball()
        ball.fall()
        self.assertEqual(ball.y, 1)

class TestBoard(unittest.TestCase):
    def setUp(self):
        self.board = Board(width=5, well_depth=5, N=10)

    def test_initial_fallen(self):
        self.assertEqual(self.board.fallen, [0] * 6)

    def test_add_ball(self):
        self.board.add_ball()
        self.assertEqual(len(self.board.balls), 1)

    def test_balls_on_board(self):
        self.board.add_ball()
        self.assertEqual(self.board.balls_on_board(), 1)

        # Simulate a ball falling into a bin
        self.board.balls[0].y = 10
        self.board.update()
        self.assertEqual(self.board.balls_on_board(), 0)

    def test_update(self):
        self.board.add_ball()
        ball = self.board.balls[0]

        # Simulate ball movement
        ball.y = 4
        ball.x = 2
        self.board.update()
        self.assertEqual(ball.y, 5)
        self.assertEqual(ball.x, 2)  # Assuming random.randint returns 0

    def test_print_board(self):
        # Test that print_board does not raise any exceptions
        try:
            self.board.print_board()
        except Exception as e:
            self.fail(f"print_board raised an exception {e}")

    def test_print_ball(self):
        ball = Ball()
        ball.x = 2
        ball.y = 3
        # Test that print_ball does not raise any exceptions
        try:
            self.board.print_ball(ball)
        except Exception as e:
            self.fail(f"print_ball raised an exception {e}")

    def test_print_all(self):
        self.board.add_ball()
        # Test that print_all does not raise any exceptions
        try:
            self.board.print_all()
        except Exception as e:
            self.fail(f"print_all raised an exception {e}")

if __name__ == '__main__':
    unittest.main()
