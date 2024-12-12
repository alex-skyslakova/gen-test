import unittest
from find_the_intersection_of_two_lines import line_intersect

class TestLineIntersection(unittest.TestCase):

    def test_intersection_exists(self):
        # Test case where intersection exists
        result = line_intersect(4, 0, 6, 10, 0, 3, 10, 7)
        self.assertEqual(result, (5.0, 5.0))

    def test_no_intersection(self):
        # Test case where lines are parallel and do not intersect
        result = line_intersect(4, 0, 6, 4, 0, 3, 10, 7)
        self.assertIsNone(result)

    def test_intersection_at_endpoint(self):
        # Test case where intersection is at the endpoint of one line
        result = line_intersect(4, 0, 6, 10, 6, 10, 10, 7)
        self.assertEqual(result, (6.0, 10.0))

    def test_intersection_outside_segment(self):
        # Test case where intersection is outside the segment of one line
        result = line_intersect(4, 0, 6, 10, 0, 3, 2, 4)
        self.assertIsNone(result)

    def test_same_line(self):
        # Test case where both lines are the same
        result = line_intersect(4, 0, 6, 10, 4, 0, 6, 10)
        self.assertIsNone(result)

    def test_vertical_line(self):
        # Test case where one line is vertical
        result = line_intersect(4, 0, 4, 10, 0, 3, 10, 7)
        self.assertEqual(result, (4.0, 5.0))

    def test_horizontal_line(self):
        # Test case where one line is horizontal
        result = line_intersect(4, 5, 6, 5, 0, 3, 10, 7)
        self.assertEqual(result, (5.0, 5.0))

    def test_both_lines_vertical(self):
        # Test case where both lines are vertical
        result = line_intersect(4, 0, 4, 10, 5, 0, 5, 10)
        self.assertIsNone(result)

    def test_both_lines_horizontal(self):
        # Test case where both lines are horizontal
        result = line_intersect(4, 5, 6, 5, 0, 5, 10, 5)
        self.assertIsNone(result)

if __name__ == '__main__':
    unittest.main()
