import unittest
import random
from generate_random_chess_position import place_kings, populate_board, fen_from_board, pawn_on_promotion_square, board

class TestChessPositionGenerator(unittest.TestCase):

    def test_place_kings(self):
        brd = [[" " for x in range(8)] for y in range(8)]
        place_kings(brd)
        white_king_count = sum(row.count('K') for row in brd)
        black_king_count = sum(row.count('k') for row in brd)
        self.assertEqual(white_king_count, 1)
        self.assertEqual(black_king_count, 1)

        # Check Kings are not adjacent
        wk_pos = None
        bk_pos = None
        for r in range(8):
            for c in range(8):
                if brd[r][c] == 'K':
                    wk_pos = (r, c)
                elif brd[r][c] == 'k':
                    bk_pos = (r, c)

        self.assertTrue(abs(wk_pos[0] - bk_pos[0]) + abs(wk_pos[1] - bk_pos[1]) > 1)


    def test_populate_board(self):
        brd = [[" " for x in range(8)] for y in range(8)]
        wp = random.randint(0, 15)
        bp = random.randint(0, 15)
        populate_board(brd, wp, bp)
        total_pieces = sum(sum(1 for piece in row if piece != " ") for row in brd)
        self.assertEqual(total_pieces, wp + bp)  # kings are already placed before this func

        # Check no pawns on promotion rank
        for c in range(8):
            self.assertNotEqual(brd[0][c], 'P')
            self.assertNotEqual(brd[7][c], 'p')


    def test_fen_from_board(self):
        brd = [["r", "n", "b", "q", "k", "b", "n", "r"],
               ["p" for _ in range(8)],
               [" " for _ in range(8)],
               [" " for _ in range(8)],
               [" " for _ in range(8)],
               [" " for _ in range(8)],
               ["P" for _ in range(8)],
               ["R", "N", "B", "Q", "K", "B", "N", "R"]]
        expected_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1\n"
        self.assertEqual(fen_from_board(brd), expected_fen)

        brd = [[" " for x in range(8)] for y in range(8)]
        expected_fen = "8/8/8/8/8/8/8/8 w - - 0 1\n"
        self.assertEqual(fen_from_board(brd), expected_fen)


    def test_pawn_on_promotion_square(self):
        self.assertTrue(pawn_on_promotion_square('P', 0))
        self.assertTrue(pawn_on_promotion_square('p', 7))
        self.assertFalse(pawn_on_promotion_square('P', 7))
        self.assertFalse(pawn_on_promotion_square('p', 0))
        self.assertFalse(pawn_on_promotion_square('R', 0))
        self.assertFalse(pawn_on_promotion_square('n', 7))


if __name__ == '__main__':
    unittest.main()
