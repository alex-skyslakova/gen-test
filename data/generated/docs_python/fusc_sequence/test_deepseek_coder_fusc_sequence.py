import unittest
from fusc_sequence import fusc, longest_fusc
from itertools import islice

class TestFuscSequence(unittest.TestCase):

    def test_first_61_fusc_numbers(self):
        expected_first_61 = [
            0, 1, 1, 2, 1, 3, 2, 3, 1, 4, 3, 5, 2, 5, 3, 4, 1, 5, 4, 7, 3, 8, 5, 7, 2, 7, 5, 8, 3, 7, 4, 5, 1, 6, 5, 9, 4, 11, 7, 10, 3, 11, 8, 13, 5, 12, 7, 9, 2, 9, 7, 12, 5, 13, 8, 11, 3, 10, 7, 11, 4, 9, 5, 6
        ]
        self.assertEqual(list(islice(fusc(), 61)), expected_first_61)

    def test_longest_fusc_records(self):
        expected_records = [
            (0, 0),
            (3, 2),
            (11, 10),
            (37, 101),
            (117, 1001),
            (353, 10001)
        ]
        records = list(islice(longest_fusc(), 6))
        self.assertEqual(records, expected_records)

if __name__ == '__main__':
    unittest.main()
