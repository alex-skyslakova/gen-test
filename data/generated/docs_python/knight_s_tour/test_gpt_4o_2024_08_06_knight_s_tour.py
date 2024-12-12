import unittest
from knight_s_tour import chess2index, boardstring, knightmoves, accessibility, knights_tour

class TestKnightsTour(unittest.TestCase):

    def test_chess2index(self):
        self.assertEqual(chess2index('a1'), (0, 5))
        self.assertEqual(chess2index('b2'), (1, 4))
        self.assertEqual(chess2index('h6'), (7, 0))
        self.assertEqual(chess2index('d4'), (3, 2))

    def test_boardstring(self):
        board = {(x, y): 0 for x in range(6) for y in range(6)}
        board[(0, 0)] = 1
        board[(5, 5)] = 2
        expected_output = (
            "\n  ,  ,  ,  ,  , 2"
            "\n  ,  ,  ,  ,  ,  "
            "\n  ,  ,  ,  ,  ,  "
            "\n  ,  ,  ,  ,  ,  "
            "\n  ,  ,  ,  ,  ,  "
            "\n 1,  ,  ,  ,  ,  "
        )
        self.assertEqual(boardstring(board), expected_output)

    def test_knightmoves(self):
        board = {(x, y): 0 for x in range(6) for y in range(6)}
        board[(2, 2)] = 1
        expected_moves = {(0, 1), (0, 3), (1, 0), (1, 4), (3, 0), (3, 4), (4, 1), (4, 3)}
        self.assertEqual(knightmoves(board, (2, 2)), expected_moves)

    def test_accessibility(self):
        board = {(x, y): 0 for x in range(6) for y in range(6)}
        board[(2, 2)] = 1
        access = accessibility(board, (2, 2))
        expected_access = [
            (4, (0, 1)), (4, (0, 3)), (4, (1, 0)), (4, (1, 4)),
            (4, (3, 0)), (4, (3, 4)), (4, (4, 1)), (4, (4, 3))
        ]
        self.assertEqual(sorted(access), sorted(expected_access))

    def test_knights_tour(self):
        board = knights_tour('a1', 6)
        visited_squares = [pos for pos, move in board.items() if move > 0]
        self.assertEqual(len(visited_squares), 36)  # All squares should be visited
        self.assertEqual(len(set(visited_squares)), 36)  # All visits should be unique

if __name__ == '__main__':
    unittest.main()
