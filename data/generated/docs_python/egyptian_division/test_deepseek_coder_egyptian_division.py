import unittest
from egyptian_division import egyptian_divmod

class TestEgyptianDivision(unittest.TestCase):

    def test_division_by_zero(self):
        with self.assertRaises(AssertionError):
            egyptian_divmod(10, 0)

    def test_basic_divisions(self):
        test_cases = [
            (10, 2, (5, 0)),
            (10, 3, (3, 1)),
            (20, 4, (5, 0)),
            (20, 3, (6, 2)),
            (100, 10, (10, 0)),
            (100, 3, (33, 1)),
            (100, 7, (14, 2)),
            (100, 11, (9, 1)),
            (100, 13, (7, 9)),
            (100, 17, (5, 15)),
            (100, 19, (5, 5)),
            (100, 23, (4, 8)),
            (100, 29, (3, 13)),
            (100, 31, (3, 7)),
            (100, 37, (2, 26)),
            (100, 41, (2, 18)),
            (100, 43, (2, 14)),
            (100, 47, (2, 6)),
            (100, 53, (1, 47)),
            (100, 59, (1, 41)),
            (100, 61, (1, 39)),
            (100, 67, (1, 33)),
            (100, 71, (1, 29)),
            (100, 73, (1, 27)),
            (100, 79, (1, 21)),
            (100, 83, (1, 17)),
            (100, 89, (1, 11)),
            (100, 97, (1, 3)),
        ]
        for dividend, divisor, expected in test_cases:
            with self.subTest(dividend=dividend, divisor=divisor):
                self.assertEqual(egyptian_divmod(dividend, divisor), expected)

    def test_large_numbers(self):
        test_cases = [
            (123456789, 987654321, (0, 123456789)),
            (987654321, 123456789, (8, 6211))
        ]
        for dividend, divisor, expected in test_cases:
            with self.subTest(dividend=dividend, divisor=divisor):
                self.assertEqual(egyptian_divmod(dividend, divisor), expected)

    def test_edge_cases(self):
        test_cases = [
            (0, 1, (0, 0)),
            (1, 1, (1, 0)),
            (1, 2, (0, 1)),
            (2, 1, (2, 0)),
            (100, 100, (1, 0)),
            (100, 101, (0, 100)),
            (101, 100, (1, 1)),
        ]
        for dividend, divisor, expected in test_cases:
            with self.subTest(dividend=dividend, divisor=divisor):
                self.assertEqual(egyptian_divmod(dividend, divisor), expected)

if __name__ == '__main__':
    unittest.main()
