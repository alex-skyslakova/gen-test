import unittest
from count_in_factors import pfactor

class TestPrimeFactorization(unittest.TestCase):

    def test_unity(self):
        self.assertEqual(pfactor(1), [1], "Unity should be represented as itself.")

    def test_prime_numbers(self):
        self.assertEqual(pfactor(2), [2], "2 is prime and should be represented as itself.")
        self.assertEqual(pfactor(3), [3], "3 is prime and should be represented as itself.")
        self.assertEqual(pfactor(5), [5], "5 is prime and should be represented as itself.")
        self.assertEqual(pfactor(7), [7], "7 is prime and should be represented as itself.")
        self.assertEqual(pfactor(11), [11], "11 is prime and should be represented as itself.")

    def test_non_prime_numbers(self):
        self.assertEqual(pfactor(4), [2, 2], "4 should be represented as 2x2.")
        self.assertEqual(pfactor(6), [2, 3], "6 should be represented as 2x3.")
        self.assertEqual(pfactor(8), [2, 2, 2], "8 should be represented as 2x2x2.")
        self.assertEqual(pfactor(9), [3, 3], "9 should be represented as 3x3.")
        self.assertEqual(pfactor(10), [2, 5], "10 should be represented as 2x5.")
        self.assertEqual(pfactor(12), [2, 2, 3], "12 should be represented as 2x2x3.")
        self.assertEqual(pfactor(2144), [2, 2, 2, 2, 2, 67], "2144 should be represented as 2x2x2x2x2x67.")

    def test_large_number(self):
        self.assertEqual(pfactor(5000), [2, 2, 2, 5, 5, 5, 5], "5000 should be represented as 2x2x2x5x5x5x5.")

    def test_cache_info(self):
        # This test checks if the cache is being used correctly
        pfactor(10)
        pfactor(20)
        cache_info = pfactor.cache_info()
        self.assertGreater(cache_info.hits, 0, "Cache should have hits after repeated calls.")
        self.assertGreater(cache_info.misses, 0, "Cache should have misses for new calculations.")
        self.assertLessEqual(cache_info.currsize, cache_info.maxsize, "Cache size should not exceed maxsize.")

if __name__ == '__main__':
    unittest.main()
