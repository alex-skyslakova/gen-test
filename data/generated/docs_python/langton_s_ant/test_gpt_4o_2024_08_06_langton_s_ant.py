import unittest
from langton_s_ant import Dir, Color, invert_color, next_direction, next_position, ant

class TestLangtonsAnt(unittest.TestCase):

    def setUp(self):
        self.width = 100
        self.height = 100
        self.grid = [[Color.WHITE] * self.width for _ in range(self.height)]
        self.x = self.width // 2
        self.y = self.height // 2
        self.direction = Dir.UP

    def test_invert_color_white_to_black(self):
        invert_color(self.grid, self.x, self.y)
        self.assertEqual(self.grid[self.y][self.x], Color.BLACK)

    def test_invert_color_black_to_white(self):
        self.grid[self.y][self.x] = Color.BLACK
        invert_color(self.grid, self.x, self.y)
        self.assertEqual(self.grid[self.y][self.x], Color.WHITE)

    def test_next_direction_white_cell(self):
        self.assertEqual(next_direction(self.grid, self.x, self.y, self.direction), Dir.RIGHT)

    def test_next_direction_black_cell(self):
        self.grid[self.y][self.x] = Color.BLACK
        self.assertEqual(next_direction(self.grid, self.x, self.y, self.direction), Dir.LEFT)

    def test_next_position_up(self):
        new_x, new_y = next_position(self.x, self.y, Dir.UP)
        self.assertEqual((new_x, new_y), (self.x, self.y - 1))

    def test_next_position_right(self):
        new_x, new_y = next_position(self.x, self.y, Dir.RIGHT)
        self.assertEqual((new_x, new_y), (self.x - 1, self.y))

    def test_next_position_down(self):
        new_x, new_y = next_position(self.x, self.y, Dir.DOWN)
        self.assertEqual((new_x, new_y), (self.x, self.y + 1))

    def test_next_position_left(self):
        new_x, new_y = next_position(self.x, self.y, Dir.LEFT)
        self.assertEqual((new_x, new_y), (self.x + 1, self.y))

    def test_ant_movement(self):
        # This test will check if the ant moves correctly within the grid
        # and terminates when it moves out of bounds.
        ant(width=10, height=10, max_nb_steps=100)
        # Since the function prints the grid and doesn't return a value,
        # we can't assert on the final state of the grid without modifying the original code.
        # This test will ensure no exceptions are raised during execution.

if __name__ == '__main__':
    unittest.main()
