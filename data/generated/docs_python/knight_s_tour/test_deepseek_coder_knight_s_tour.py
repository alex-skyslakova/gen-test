import unittest
from knight_s_tour import chess2index, boardstring, knightmoves, accessibility, knights_tour

class TestKnightTour(unittest.TestCase):

    def test_chess2index(self):
        self.assertEqual(chess2index('a1'), (0, 5))
        self.assertEqual(chess2index('h8'), (7, 0))
        self.assertEqual(chess2index('d4'), (3, 2))
        self.assertEqual(chess2index('e5'), (4, 1))
        self.assertEqual(chess2index('c3'), (2, 3))

    def test_boardstring(self):
        board = {(x, y): x + y for x in range(6) for y in range(6)}
        expected_output = (
            "\n 0, 1, 2, 3, 4, 5"
            "\n 1, 2, 3, 4, 5, 6"
            "\n 2, 3, 4, 5, 6, 7"
            "\n 3, 4, 5, 6, 7, 8"
            "\n 4, 5, 6, 7, 8, 9"
            "\n 5, 6, 7, 8, 9,10"
        )
        self.assertEqual(boardstring(board), expected_output)

    def test_knightmoves(self):
        board = {(x, y): 0 for x in range(6) for y in range(6)}
        self.assertEqual(knightmoves(board, (0, 0)), {(1, 2}, {2, 1}))
        self.assertEqual(knightmoves(board, (3, 3)), {
            (1, 2), (2, 1), (1, 4), (4, 1), (5, 2), (2, 5), (4, 5), (5, 4)
        })
        self.assertEqual(knightmoves(board, (5, 5)), {(3, 4}, {4, 3}))

    def test_accessibility(self):
        board = {(x, y): 0 for x in range(6) for y in range(6)}
        self.assertEqual(accessibility(board, (0, 0)), [
            (2, (1, 2)), (2, (2, 1))
        ])
        self.assertEqual(accessibility(board, (3, 3)), [
            (6, (1, 2)), (6, (2, 1)), (6, (1, 4)), (6, (4, 1)),
            (6, (5, 2)), (6, (2, 5)), (6, (4, 5)), (6, (5, 4))
        ])

    def test_knights_tour(self):
        board = knights_tour('a1', boardsize=6)
        self.assertEqual(len(board), 36)
        self.assertEqual(set(board.values()), set(range(1, 37)))

if __name__ == '__main__':
    unittest.main()
