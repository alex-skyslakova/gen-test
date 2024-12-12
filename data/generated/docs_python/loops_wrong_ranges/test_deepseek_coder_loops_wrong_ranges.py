import unittest
from itertools import islice

class TestLoopsWrongRanges(unittest.TestCase):

    def test_normal(self):
        start, stop, increment = -2, 2, 1
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [-2, -1, 0, 1])

    def test_zero_increment(self):
        start, stop, increment = -2, 2, 0
        with self.assertRaises(ValueError):
            values = list(islice(range(start, stop, increment), 999))

    def test_increments_away_from_stop_value(self):
        start, stop, increment = -2, 2, -1
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [-2, -3, -4, -5])

    def test_first_increment_beyond_stop_value(self):
        start, stop, increment = -2, 2, 10
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [-2])

    def test_start_more_than_stop_positive_increment(self):
        start, stop, increment = 2, -2, 1
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [])

    def test_start_equal_stop_positive_increment(self):
        start, stop, increment = 2, 2, 1
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [])

    def test_start_equal_stop_negative_increment(self):
        start, stop, increment = 2, 2, -1
        values = list(islice(range(start, stop, increment), 999))
        self.assertEqual(values, [])

    def test_start_equal_stop_zero_increment(self):
        start, stop, increment = 2, 2, 0
        with self.assertRaises(ValueError):
            values = list(islice(range(start, stop, increment), 999))

    def test_start_equal_stop_equal_zero_zero_increment(self):
        start, stop, increment = 0, 0, 0
        with self.assertRaises(ValueError):
            values = list(islice(range(start, stop, increment), 999))

if __name__ == '__main__':
    unittest.main()
