import unittest
from egyptian_division import egyptian_divmod

class TestEgyptianDivision(unittest.TestCase):

    def test_zero_divisor(self):
        with self.assertRaises(AssertionError):
            egyptian_divmod(10, 0)

    def test_small_numbers(self):
        for i in range(13):
            for j in range(1, 13):
                self.assertEqual(egyptian_divmod(i, j), divmod(i, j))

    def test_large_numbers(self):
        self.assertEqual(egyptian_divmod(580, 34), (17, 2))

    def test_zero_dividend(self):
        self.assertEqual(egyptian_divmod(0, 5), (0, 0))

    def test_dividend_less_than_divisor(self):
        self.assertEqual(egyptian_divmod(5, 10), (0, 5))

    def test_equal_dividend_and_divisor(self):
        self.assertEqual(egyptian_divmod(10, 10), (1, 0))

    def test_large_dividend_small_divisor(self):
        self.assertEqual(egyptian_divmod(1000, 2), (500, 0))

    def test_small_dividend_large_divisor(self):
        self.assertEqual(egyptian_divmod(2, 1000), (0, 2))

