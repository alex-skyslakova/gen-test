import unittest
from flipping_bits_game import setbits, shuffle, init, fliprow, flipcol

class TestFlippingBitsGame(unittest.TestCase):

    def setUp(self):
        self.N = 3
        self.board = [[0] * self.N for _ in range(self.N)]

    def test_setbits(self):
        setbits(self.board, count=1)
        self.assertEqual(sum(sum(row) for row in self.board), 1)

        setbits(self.board, count=2)
        self.assertEqual(sum(sum(row) for row in self.board), 3)

    def test_shuffle(self):
        initial_board = [row[:] for row in self.board]
        shuffle(self.board, count=1)
        self.assertNotEqual(self.board, initial_board)

        initial_board = [row[:] for row in self.board]
        shuffle(self.board, count=2 * self.N)
        self.assertNotEqual(self.board, initial_board)

    def test_init(self):
        target, prompt = init(self.board)
        self.assertNotEqual(self.board, target)
        self.assertIsInstance(prompt, str)

    def test_fliprow(self):
        fliprow(1)
        self.assertEqual(self.board[0], [1, 1, 1])

        fliprow(2)
        self.assertEqual(self.board[1], [1, 1, 1])

        fliprow(3)
        self.assertEqual(self.board[2], [1, 1, 1])

    def test_flipcol(self):
        flipcol(0)
        self.assertEqual([row[0] for row in self.board], [1, 1, 1])

        flipcol(1)
        self.assertEqual([row[1] for row in self.board], [1, 1, 1])

        flipcol(2)
        self.assertEqual([row[2] for row in self.board], [1, 1, 1])

if __name__ == '__main__':
    unittest.main()
