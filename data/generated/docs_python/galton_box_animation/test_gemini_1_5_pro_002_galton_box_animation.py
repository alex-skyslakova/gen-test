import unittest
from unittest.mock import patch
import io
import sys
from galton_box_animation import Ball, Board

class TestBall(unittest.TestCase):

    def test_ball_init(self):
        ball = Ball()
        self.assertEqual(ball.x, 0)
        self.assertEqual(ball.y, 0)

    def test_ball_update(self):
        ball = Ball()
        with patch('galton_box_animation.random.randint', return_value=1):  # Mock random.randint
            ball.update()
            self.assertEqual(ball.x, 1)
            self.assertEqual(ball.y, 1)

        with patch('galton_box_animation.random.randint', return_value=0):  # Mock random.randint
            ball.update()
            self.assertEqual(ball.x, 1)
            self.assertEqual(ball.y, 2)


    def test_ball_fall(self):
        ball = Ball()
        ball.fall()
        self.assertEqual(ball.y, 1)



class TestBoard(unittest.TestCase):

    def setUp(self):
        self.board = Board(width=5, well_depth=3, N=10)

    def test_board_init(self):
        self.assertEqual(self.board.width, 5)
        self.assertEqual(self.board.well_depth, 3)
        self.assertEqual(self.board.N, 10)
        self.assertEqual(len(self.board.fallen), 6)
        self.assertEqual(self.board.balls, [])


    def test_board_update(self):
        self.board.add_ball()
        with patch('galton_box_animation.random.randint', return_value=0):
            self.board.update()
            self.assertEqual(self.board.balls[0].x, 0)
            self.assertEqual(self.board.balls[0].y, 1)

        for _ in range(self.board.width -1 ):  # let it get to last row
            self.board.update()


        for _ in range(self.board.well_depth):  # let it fall
            self.board.update()

        self.assertTrue(self.board.fallen[self.board.balls[0].x] > 0)

    def test_balls_on_board(self):
        self.board.add_ball()
        self.assertEqual(self.board.balls_on_board(), 1)
        self.board.update()
        self.assertEqual(self.board.balls_on_board(), 1)



    def test_add_ball(self):
        for _ in range(self.board.N):
            self.board.add_ball()
        self.assertEqual(len(self.board.balls), self.board.N)
        self.board.add_ball() # Adding one more, exceeding N
        self.assertEqual(len(self.board.balls), self.board.N)

    @patch('galton_box_animation.sys.stdout', new_callable=io.StringIO)
    def test_print_functions(self):  # Only checks if it runs without err
        self.board.print_board()
        self.board.add_ball()
        self.board.print_ball(self.board.balls[0])
        self.board.print_all()


