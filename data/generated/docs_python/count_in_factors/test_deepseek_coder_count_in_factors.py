import unittest
from count_in_factors import pfactor, primes

class TestPrimeFactorization(unittest.TestCase):

    def test_prime_factorization_of_1(self):
        self.assertEqual(pfactor(1), [1])

    def test_prime_factorization_of_prime_numbers(self):
        prime_numbers = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        for prime in prime_numbers:
            self.assertEqual(pfactor(prime), [prime])

    def test_prime_factorization_of_composite_numbers(self):
        self.assertEqual(pfactor(6), [2, 3])
        self.assertEqual(pfactor(2144), [2, 2, 2, 2, 2, 67])
        self.assertEqual(pfactor(12), [2, 2, 3])
        self.assertEqual(pfactor(18), [2, 3, 3])
        self.assertEqual(pfactor(30), [2, 3, 5])

    def test_prime_factorization_of_large_numbers(self):
        self.assertEqual(pfactor(5000), [2, 2, 2, 5, 5, 5])
        self.assertEqual(pfactor(4999), [4999])  # 4999 is a prime number

    def test_prime_list_extension(self):
        # Ensure that the primes list is extended correctly
        initial_length = len(primes)
        pfactor(29)  # 29 is a prime number and should be added to the list
        self.assertGreater(len(primes), initial_length)

    def test_cache_usage(self):
        # Ensure that the cache is being used effectively
        pfactor(12)
        pfactor(18)
        cache_info = pfactor.cache_info()
        self.assertGreater(cache_info.hits, 0)
        self.assertGreater(cache_info.misses, 0)

if __name__ == '__main__':
    unittest.main()
