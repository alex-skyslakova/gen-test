import unittest
from langton_s_ant import Dir, Color, invert_color, next_direction, next_position

class TestLangtonsAnt(unittest.TestCase):

    def test_invert_color(self):
        grid = [[Color.WHITE] * 3 for _ in range(3)]
        invert_color(grid, 1, 1)
        self.assertEqual(grid[1][1], Color.BLACK)
        invert_color(grid, 1, 1)
        self.assertEqual(grid[1][1], Color.WHITE)

    def test_next_direction(self):
        grid = [[Color.WHITE] * 3 for _ in range(3)]
        self.assertEqual(next_direction(grid, 1, 1, Dir.UP), Dir.RIGHT)
        grid[1][1] = Color.BLACK
        self.assertEqual(next_direction(grid, 1, 1, Dir.UP), Dir.LEFT)
        self.assertEqual(next_direction(grid, 1, 1, Dir.RIGHT), Dir.DOWN)
        self.assertEqual(next_direction(grid, 1, 1, Dir.DOWN), Dir.LEFT)
        self.assertEqual(next_direction(grid, 1, 1, Dir.LEFT), Dir.UP)


    def test_next_position(self):
        x, y = 1, 1
        self.assertEqual(next_position(x, y, Dir.UP), (1, 0))
        self.assertEqual(next_position(x, y, Dir.RIGHT), (0, 1))
        self.assertEqual(next_position(x, y, Dir.DOWN), (1, 2))
        self.assertEqual(next_position(x, y, Dir.LEFT), (2, 1))

