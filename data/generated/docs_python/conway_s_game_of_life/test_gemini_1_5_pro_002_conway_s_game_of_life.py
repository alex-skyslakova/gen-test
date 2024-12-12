import unittest
from collections import defaultdict

class TestGameOfLife(unittest.TestCase):

    def run_game(self, initial_state, generations):
        celltable = defaultdict(int, {
            (1, 2): 1,
            (1, 3): 1,
            (0, 3): 1,
        })
        universe = defaultdict(int, initial_state)
        cellcount = (3, 3) # Fixed for these tests


        for _ in range(generations):
            nextgeneration = defaultdict(int)
            for row in range(cellcount[1]):
                for col in range(cellcount[0]):
                    nextgeneration[(row, col)] = celltable[
                        (universe[(row, col)],
                         -universe[(row, col)] + sum(universe[(r, c)]
                                                    for r in range(row - 1, row + 2)
                                                    for c in range(col - 1, col + 2)))
                    ]
            universe = nextgeneration
        return universe

    def test_blinker(self):
        initial_state = {(1, 0): 1, (1, 1): 1, (1, 2): 1}
        gen1 = self.run_game(initial_state, 1)
        expected_gen1 = {(0, 1): 1, (1, 1): 1, (2, 1): 1}
        self.assertEqual(gen1, expected_gen1)

        gen2 = self.run_game(initial_state, 2)  # Back to original blinker after 2
        self.assertEqual(gen2, initial_state)
        
        gen3 = self.run_game(initial_state, 3)
        self.assertEqual(gen3, expected_gen1)


    def test_lonely_cell(self):
        initial_state = {(1,1): 1}
        gen1 = self.run_game(initial_state, 1)
        expected_gen1 = defaultdict(int) # All dead because lonely
        self.assertEqual(gen1, expected_gen1)

    def test_overcrowded_cell(self):
        initial_state = {(0,0):1, (0,1):1, (0,2):1, (1,0):1, (1,1):1, (1,2):1, (2,0):1, (2,1):1} # leaves 2,2 free
        gen1 = self.run_game(initial_state, 1)
        expected_gen1 = {(0,1):1, (1,0):1, (1,2):1, (2,1):1} # all surrounded cells die due to overcrowding
        self.assertEqual(gen1, expected_gen1)

    def test_birth(self):
        initial_state = {(0, 0): 1, (0, 1): 1, (1, 0): 1}  # Cell at 1,1 should be born
        gen1 = self.run_game(initial_state, 1)
        self.assertEqual(gen1[(0,0)], 1)
        self.assertEqual(gen1[(0, 1)], 1)
        self.assertEqual(gen1[(1, 0)], 1)
        self.assertEqual(gen1[(1, 1)], 1)
        


if __name__ == '__main__':
    unittest.main()
