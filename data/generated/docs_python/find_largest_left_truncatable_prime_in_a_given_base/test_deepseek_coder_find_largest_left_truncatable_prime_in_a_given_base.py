import unittest
from find_largest_left_truncatable_prime_in_a_given_base import is_probable_prime, largest_left_truncatable_prime

class TestLargestLeftTruncatablePrime(unittest.TestCase):

    def test_is_probable_prime(self):
        # Test edge cases
        self.assertFalse(is_probable_prime(0, 30))
        self.assertFalse(is_probable_prime(1, 30))
        self.assertTrue(is_probable_prime(2, 30))
        self.assertFalse(is_probable_prime(4, 30))
        
        # Test small primes
        self.assertTrue(is_probable_prime(3, 30))
        self.assertTrue(is_probable_prime(5, 30))
        self.assertTrue(is_probable_prime(7, 30))
        
        # Test small composites
        self.assertFalse(is_probable_prime(9, 30))
        self.assertFalse(is_probable_prime(15, 30))
        self.assertFalse(is_probable_prime(21, 30))
        
        # Test large primes
        self.assertTrue(is_probable_prime(104729, 30))  # 10000th prime
        self.assertTrue(is_probable_prime(15485863, 30))  # 1000000th prime
        
        # Test large composites
        self.assertFalse(is_probable_prime(104730, 30))
        self.assertFalse(is_probable_prime(15485864, 30))

    def test_largest_left_truncatable_prime(self):
        # Test base 3
        self.assertEqual(largest_left_truncatable_prime(3), 23)
        
        # Test base 10
        self.assertEqual(largest_left_truncatable_prime(10), 357686312646216567629137)
        
        # Test base 20
        self.assertEqual(largest_left_truncatable_prime(20), 1979339333)
        
        # Test base 23
        self.assertEqual(largest_left_truncatable_prime(23), 4541317)

if __name__ == '__main__':
    unittest.main()
