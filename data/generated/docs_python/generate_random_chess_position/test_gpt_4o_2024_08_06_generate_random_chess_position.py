import unittest
from generate_random_chess_position import place_kings, populate_board, fen_from_board, pawn_on_promotion_square

class TestGenerateRandomChessPosition(unittest.TestCase):

    def setUp(self):
        self.board = [[" " for _ in range(8)] for _ in range(8)]

    def test_place_kings(self):
        place_kings(self.board)
        white_king_position = None
        black_king_position = None

        for rank in range(8):
            for file in range(8):
                if self.board[rank][file] == "K":
                    white_king_position = (rank, file)
                elif self.board[rank][file] == "k":
                    black_king_position = (rank, file)

        self.assertIsNotNone(white_king_position, "White king not placed on the board")
        self.assertIsNotNone(black_king_position, "Black king not placed on the board")

        # Ensure kings are not adjacent
        self.assertTrue(
            abs(white_king_position[0] - black_king_position[0]) > 1 or
            abs(white_king_position[1] - black_king_position[1]) > 1,
            "Kings are placed on adjacent squares"
        )

    def test_populate_board(self):
        place_kings(self.board)
        populate_board(self.board, 15, 15)  # Max pieces for both sides

        piece_count = sum(row.count(piece) for row in self.board for piece in "RNBQPrnbqp")
        self.assertLessEqual(piece_count, 32, "More than 32 pieces on the board")

        for rank in range(8):
            self.assertNotIn("P", self.board[0], "White pawn on promotion square")
            self.assertNotIn("p", self.board[7], "Black pawn on promotion square")

    def test_fen_from_board(self):
        place_kings(self.board)
        populate_board(self.board, 15, 15)
        fen = fen_from_board(self.board)

        # Check FEN format
        self.assertTrue(fen.endswith(" w - - 0 1\n"), "FEN does not end with ' w - - 0 1'")

        # Check that FEN has 8 ranks
        ranks = fen.split("/")
        self.assertEqual(len(ranks), 8, "FEN does not have 8 ranks")

    def test_pawn_on_promotion_square(self):
        self.assertTrue(pawn_on_promotion_square("P", 0), "White pawn on promotion square not detected")
        self.assertTrue(pawn_on_promotion_square("p", 7), "Black pawn on promotion square not detected")
        self.assertFalse(pawn_on_promotion_square("P", 1), "False positive for white pawn on non-promotion square")
        self.assertFalse(pawn_on_promotion_square("p", 6), "False positive for black pawn on non-promotion square")

if __name__ == '__main__':
    unittest.main()
