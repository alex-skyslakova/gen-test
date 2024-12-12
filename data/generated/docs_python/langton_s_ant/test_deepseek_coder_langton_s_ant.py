import unittest
from langton_s_ant import Dir, Color, invert_color, next_direction, next_position, ant

class TestLangtonAnt(unittest.TestCase):

    def setUp(self):
        self.grid = [[Color.WHITE for _ in range(10)] for _ in range(10)]

    def test_invert_color(self):
        invert_color(self.grid, 5, 5)
        self.assertEqual(self.grid[5][5], Color.BLACK)
        
        invert_color(self.grid, 5, 5)
        self.assertEqual(self.grid[5][5], Color.WHITE)

    def test_next_direction(self):
        # Test turning right from UP
        direction = next_direction(self.grid, 5, 5, Dir.UP)
        self.assertEqual(direction, Dir.RIGHT)

        # Test turning left from UP
        self.grid[5][5] = Color.BLACK
        direction = next_direction(self.grid, 5, 5, Dir.UP)
        self.assertEqual(direction, Dir.LEFT)

        # Test turning right from RIGHT
        self.grid[5][5] = Color.WHITE
        direction = next_direction(self.grid, 5, 5, Dir.RIGHT)
        self.assertEqual(direction, Dir.DOWN)

        # Test turning left from RIGHT
        self.grid[5][5] = Color.BLACK
        direction = next_direction(self.grid, 5, 5, Dir.RIGHT)
        self.assertEqual(direction, Dir.UP)

        # Test turning right from DOWN
        self.grid[5][5] = Color.WHITE
        direction = next_direction(self.grid, 5, 5, Dir.DOWN)
        self.assertEqual(direction, Dir.LEFT)

        # Test turning left from DOWN
        self.grid[5][5] = Color.BLACK
        direction = next_direction(self.grid, 5, 5, Dir.DOWN)
        self.assertEqual(direction, Dir.RIGHT)

        # Test turning right from LEFT
        self.grid[5][5] = Color.WHITE
        direction = next_direction(self.grid, 5, 5, Dir.LEFT)
        self.assertEqual(direction, Dir.UP)

        # Test turning left from LEFT
        self.grid[5][5] = Color.BLACK
        direction = next_direction(self.grid, 5, 5, Dir.LEFT)
        self.assertEqual(direction, Dir.DOWN)

    def test_next_position(self):
        # Test moving UP
        x, y = next_position(5, 5, Dir.UP)
        self.assertEqual((x, y), (5, 4))

        # Test moving RIGHT
        x, y = next_position(5, 5, Dir.RIGHT)
        self.assertEqual((x, y), (6, 5))

        # Test moving DOWN
        x, y = next_position(5, 5, Dir.DOWN)
        self.assertEqual((x, y), (5, 6))

        # Test moving LEFT
        x, y = next_position(5, 5, Dir.LEFT)
        self.assertEqual((x, y), (4, 5))

    def test_ant_termination(self):
        # Test that the ant terminates when it moves out of the grid
        with self.assertRaises(IndexError):
            ant(width=5, height=5, max_nb_steps=100)

if __name__ == '__main__':
    unittest.main()
