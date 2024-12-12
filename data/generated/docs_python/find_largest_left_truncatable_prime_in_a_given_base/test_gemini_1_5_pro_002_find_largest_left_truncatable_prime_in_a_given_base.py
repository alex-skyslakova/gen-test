import unittest
import random
from find_largest_left_truncatable_prime_in_a_given_base import largest_left_truncatable_prime, is_probable_prime

class TestLargestLeftTruncatablePrime(unittest.TestCase):

    def test_base_3(self):
        self.assertEqual(largest_left_truncatable_prime(3), 23)

    def test_base_4(self):
        self.assertEqual(largest_left_truncatable_prime(4), 103)

    def test_base_5(self):
        self.assertEqual(largest_left_truncatable_prime(5), 43)
    
    def test_base_6(self):
        self.assertEqual(largest_left_truncatable_prime(6), 547)

    def test_base_7(self):
        self.assertEqual(largest_left_truncatable_prime(7), 1462)

    def test_base_10(self):
        self.assertEqual(largest_left_truncatable_prime(10), 35768631264646)

    def test_is_probable_prime_small_primes(self):
        primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        for p in primes:
            self.assertTrue(is_probable_prime(p, 30))

    def test_is_probable_prime_small_composites(self):
        composites = [4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20]
        for c in composites:
            self.assertFalse(is_probable_prime(c, 30))

    def test_is_probable_prime_large_prime(self):
        large_prime = 1000000007  # Example large prime
        self.assertTrue(is_probable_prime(large_prime, 30))

    def test_is_probable_prime_large_composite(self):
        large_composite = 1000000000
        self.assertFalse(is_probable_prime(large_composite, 30))
    
    def test_is_probable_prime_zero_and_one(self):
        self.assertFalse(is_probable_prime(0, 30))
        self.assertFalse(is_probable_prime(1, 30))

    def test_base_larger_than_prime_check_iterations(self):
        # Tests a larger base where primality check is important
        self.assertEqual(largest_left_truncatable_prime(12), 128543234121)


