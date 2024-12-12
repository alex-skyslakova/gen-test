import unittest
import random
from generate_random_chess_position import place_kings, populate_board, fen_from_board, pawn_on_promotion_square, start

class TestChessPositionGenerator(unittest.TestCase):

    def test_place_kings(self):
        brd = [[" " for _ in range(8)] for _ in range(8)]
        place_kings(brd)
        white_king_found = False
        black_king_found = False
        for rank in brd:
            for square in rank:
                if square == "K":
                    white_king_found = True
                elif square == "k":
                    black_king_found = True
        self.assertTrue(white_king_found, "White king not found")
        self.assertTrue(black_king_found, "Black king not found")

        # Check that kings are not adjacent
        for i in range(8):
            for j in range(8):
                if brd[i][j] == "K":
                    for di in [-1, 0, 1]:
                        for dj in [-1, 0, 1]:
                            if 0 <= i + di < 8 and 0 <= j + dj < 8:
                                self.assertNotEqual(brd[i + di][j + dj], "k", "Kings are adjacent")

    def test_pawn_on_promotion_square(self):
        self.assertTrue(pawn_on_promotion_square("P", 0), "White pawn on 1st rank not detected")
        self.assertTrue(pawn_on_promotion_square("p", 7), "Black pawn on 8th rank not detected")
        self.assertFalse(pawn_on_promotion_square("P", 1), "White pawn on non-promotion rank detected")
        self.assertFalse(pawn_on_promotion_square("p", 6), "Black pawn on non-promotion rank detected")
        self.assertFalse(pawn_on_promotion_square("R", 0), "Non-pawn piece on promotion rank detected")

    def test_populate_board(self):
        brd = [[" " for _ in range(8)] for _ in range(8)]
        place_kings(brd)
        populate_board(brd, 5, 5)
        piece_count = sum(square != " " for rank in brd for square in rank)
        self.assertEqual(piece_count, 12, "Incorrect number of pieces placed")

    def test_fen_from_board(self):
        brd = [
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "],
            [" ", " ", " ", " ", " ", " ", " ", " "]
        ]
        place_kings(brd)
        fen = fen_from_board(brd)
        self.assertTrue("K" in fen, "White king not in FEN")
        self.assertTrue("k" in fen, "Black king not in FEN")
        self.assertTrue("w - - 0 1" in fen, "Incorrect FEN suffix")

    def test_start(self):
        random.seed(42)  # Set seed for reproducibility
        start()
        # This test is more of an integration test, ensuring the entire process runs without errors

if __name__ == '__main__':
    unittest.main()
