import unittest
from egyptian_division import egyptian_divmod

class TestEgyptianDivmod(unittest.TestCase):

    def test_basic_cases(self):
        # Test cases that should match Python's built-in divmod
        for i in range(13):
            for j in range(1, 13):
                with self.subTest(i=i, j=j):
                    self.assertEqual(egyptian_divmod(i, j), divmod(i, j))

    def test_large_numbers(self):
        # Test with larger numbers
        self.assertEqual(egyptian_divmod(580, 34), (17, 2))
        self.assertEqual(egyptian_divmod(1000, 3), divmod(1000, 3))
        self.assertEqual(egyptian_divmod(123456, 789), divmod(123456, 789))

    def test_division_by_one(self):
        # Any number divided by 1 should return the number itself and remainder 0
        for i in range(100):
            with self.subTest(i=i):
                self.assertEqual(egyptian_divmod(i, 1), (i, 0))

    def test_dividend_zero(self):
        # 0 divided by any number should return 0 quotient and 0 remainder
        for j in range(1, 100):
            with self.subTest(j=j):
                self.assertEqual(egyptian_divmod(0, j), (0, 0))

    def test_divisor_larger_than_dividend(self):
        # If divisor is larger than dividend, quotient should be 0 and remainder should be dividend
        self.assertEqual(egyptian_divmod(5, 10), (0, 5))
        self.assertEqual(egyptian_divmod(3, 7), (0, 3))

    def test_divisor_equals_dividend(self):
        # If divisor equals dividend, quotient should be 1 and remainder should be 0
        for i in range(1, 100):
            with self.subTest(i=i):
                self.assertEqual(egyptian_divmod(i, i), (1, 0))

    def test_edge_cases(self):
        # Edge cases
        self.assertEqual(egyptian_divmod(1, 2), (0, 1))
        self.assertEqual(egyptian_divmod(2, 2), (1, 0))
        self.assertEqual(egyptian_divmod(2, 3), (0, 2))

if __name__ == '__main__':
    unittest.main()
