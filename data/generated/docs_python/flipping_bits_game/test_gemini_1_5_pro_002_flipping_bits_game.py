import unittest
from unittest.mock import patch
from io import StringIO
from flipping_bits_game import (N, board, setbits, shuffle, pr, init, fliprow, 
                               flipcol, deepcopy)
from string import ascii_lowercase

class TestFlippingBitsGame(unittest.TestCase):

    def test_setbits(self):
        board_test = [[0] * N for _ in range(N)]
        setbits(board_test, 2)
        ones_count = sum(sum(row) for row in board_test)
        self.assertLessEqual(ones_count,2)
        self.assertGreaterEqual(ones_count, 0)


    def test_shuffle(self):
        board_test = [[0] * N for _ in range(N)]
        original_board = deepcopy(board_test)
        shuffle(board_test, 2)
        self.assertNotEqual(board_test, original_board)

    def test_pr(self):
        with patch('sys.stdout', new = StringIO()) as fake_out:
            pr(board, "Test comment")
            output = fake_out.getvalue().strip()
            self.assertTrue("Test comment" in output)
            self.assertTrue("  1 2 3" in output)

    def test_init(self):
        target, prompt = init(board)
        self.assertNotEqual(board, target)
        self.assertTrue(prompt)

    def test_fliprow(self):
        board_test = [[0, 1, 0], [1, 0, 1], [0,0,0]]
        fliprow(2)  # Flip row 1 (index 1)
        self.assertEqual(board_test[1], [0, 1, 0])


    def test_flipcol(self):
        board_test = [[0, 1, 0], [1, 0, 1], [0,0,0]]
        flipcol(1)
        self.assertEqual([row[1] for row in board_test], [0,1,1])


    @patch('builtins.input', side_effect=['1', 'a', 'T', 'X'])
    @patch('sys.stdout', new_callable=StringIO)
    def test_game_flow(self, mock_stdout, mock_input):
         # Execute the main game loop. Because it contains user input we need to run it to fully test
        from flipping_bits_game import __main__ 

        output = mock_stdout.getvalue()

        self.assertIn("Target configuration is:", output)
        self.assertIn("1:", output) # Check if turns are displayed
        self.assertIn("I don't understand", output) # Invalid input 'dfg' handling
        self.assertIn("Well done!", output) # Game completion message



if __name__ == '__main__':
    unittest.main()

