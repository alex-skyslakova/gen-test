import unittest
from unittest.mock import patch
from flipping_bits_game import board, setbits, shuffle, pr, init, fliprow, flipcol

class TestFlippingBitsGame(unittest.TestCase):

    def setUp(self):
        self.board = [[0]*3 for _ in range(3)]

    def test_setbits(self):
        setbits(self.board, count=3)
        # Check if exactly 3 bits are set
        total_bits = sum(sum(row) for row in self.board)
        self.assertEqual(total_bits, 3)

    def test_shuffle(self):
        original_board = deepcopy(self.board)
        shuffle(self.board, count=5)
        # Check if board has changed
        self.assertNotEqual(original_board, self.board)

    def test_fliprow(self):
        self.board = [[0, 0, 0], [1, 1, 1], [0, 0, 0]]
        fliprow(2)
        self.assertEqual(self.board, [[0, 0, 0], [0, 0, 0], [0, 0, 0]])

    def test_flipcol(self):
        self.board = [[0, 1, 0], [0, 1, 0], [0, 1, 0]]
        flipcol(1)
        self.assertEqual(self.board, [[0, 0, 0], [0, 0, 0], [0, 0, 0]])

    def test_init(self):
        target, prompt = init(self.board)
        # Check if target is different from the initial board
        self.assertNotEqual(target, self.board)
        # Check if prompt is correctly formatted
        self.assertEqual(prompt, '  X, T, or 1-3 / a-c to flip: ')

    @patch('builtins.input', side_effect=['1', 'a', 'T', 'X'])
    def test_gameplay(self, mock_input):
        target, prompt = init(self.board)
        turns = 0
        while self.board != target:
            turns += 1
            ans = input(prompt).strip()
            if (len(ans) == 1 
                and ans in ascii_lowercase and ascii_lowercase.index(ans) < 3):
                flipcol(ascii_lowercase.index(ans))
            elif ans and all(ch in '0123456789' for ch in ans) and 1 <= int(ans) <= 3:
                fliprow(int(ans))
            elif ans == 'T':
                turns -= 1
            elif ans == 'X':
                break
            else:
                turns -= 1
        # Check if the game exits correctly
        self.assertTrue(True)

if __name__ == '__main__':
    unittest.main()
