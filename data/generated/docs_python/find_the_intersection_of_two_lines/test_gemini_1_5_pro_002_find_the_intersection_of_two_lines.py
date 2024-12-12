import unittest
from find_the_intersection_of_two_lines import line_intersect

class TestLineIntersect(unittest.TestCase):

    def test_intersection(self):
        self.assertEqual(line_intersect(4, 0, 6, 10, 0, 3, 10, 7), (5.0, 5.0))

    def test_no_intersection(self):
        self.assertIsNone(line_intersect(4, 0, 6, 4, 0, 3, 10, 7))

    def test_parallel_lines(self):
        self.assertIsNone(line_intersect(0, 0, 1, 1, 0, 1, 1, 2))

    def test_coincident_lines(self):
        self.assertIsNone(line_intersect(0, 0, 1, 1, 2, 2, 3, 3))

    def test_vertical_line(self):
        self.assertEqual(line_intersect(1, 0, 1, 10, 0, 0, 2, 2), (1.0, 1.0))

    def test_horizontal_line(self):
        self.assertEqual(line_intersect(0, 1, 10, 1, 0, 0, 2, 2), (1.0, 1.0))
    
    def test_both_vertical_lines(self):
        self.assertIsNone(line_intersect(1, 0, 1, 2, 2, 0, 2, 2))


if __name__ == '__main__':
    unittest.main()
