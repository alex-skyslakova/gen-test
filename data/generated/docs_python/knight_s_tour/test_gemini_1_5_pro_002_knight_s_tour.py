import unittest
from knight_s_tour import knights_tour, chess2index, boardstring

class TestKnightsTour(unittest.TestCase):

    def test_knights_tour_small_board(self):
        boardsize = 5
        start = "c3"
        board = knights_tour(start, boardsize)
        self.assertEqual(len(board), boardsize * boardsize)
        for i in range(1, boardsize * boardsize + 1):
            self.assertIn(i, board.values())

    def test_knights_tour_medium_board(self):
        boardsize = 6
        start = "a1"
        board = knights_tour(start, boardsize)
        self.assertEqual(len(board), boardsize * boardsize)
        for i in range(1, boardsize * boardsize + 1):
            self.assertIn(i, board.values())
    
    def test_knights_tour_different_start(self):
        boardsize = 5
        start = "e5"  # Different starting position
        board = knights_tour(start, boardsize)
        self.assertEqual(len(board), boardsize * boardsize)
        for i in range(1, boardsize * boardsize + 1):
            self.assertIn(i, board.values())

    def test_chess2index(self):
        self.assertEqual(chess2index("a1", 8), (0, 7))
        self.assertEqual(chess2index("h8", 8), (7, 0))
        self.assertEqual(chess2index("c3", 5), (2, 2))

    def test_boardstring_empty(self):
        board = {(x, y): 0 for x in range(5) for y in range(5)}
        string = boardstring(board, 5)
        self.assertTrue(all(line.strip().replace(",", "").isdigit() == False for line in string.splitlines()[1:])) # Check if all squares are empty(represented by spaces)

    def test_boardstring_filled(self):
        boardsize = 5
        board = {(x, y): x + y * boardsize + 1 for x in range(boardsize) for y in range(boardsize)}
        string = boardstring(board, boardsize)
        for i in range(1, boardsize * boardsize + 1):
            self.assertIn(str(i).zfill(2), string)  # Check all numbers are present



