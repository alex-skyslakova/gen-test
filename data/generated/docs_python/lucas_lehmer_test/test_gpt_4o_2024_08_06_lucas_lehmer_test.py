import unittest
from lucas_lehmer_test import is_prime, is_mersenne_prime

class TestLucasLehmer(unittest.TestCase):

    def test_is_prime(self):
        # Test known primes
        self.assertTrue(is_prime(2))
        self.assertTrue(is_prime(3))
        self.assertTrue(is_prime(5))
        self.assertTrue(is_prime(7))
        self.assertTrue(is_prime(11))
        
        # Test known non-primes
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(1))
        self.assertFalse(is_prime(4))
        self.assertFalse(is_prime(6))
        self.assertFalse(is_prime(9))
        self.assertFalse(is_prime(15))
        
        # Test negative numbers
        self.assertFalse(is_prime(-1))
        self.assertFalse(is_prime(-3))
        self.assertFalse(is_prime(-5))

    def test_is_mersenne_prime(self):
        # Test known Mersenne primes
        self.assertTrue(is_mersenne_prime(2))  # Mersenne prime for p=2 is 3
        self.assertTrue(is_mersenne_prime(3))  # Mersenne prime for p=3 is 7
        self.assertTrue(is_mersenne_prime(5))  # Mersenne prime for p=5 is 31
        self.assertTrue(is_mersenne_prime(7))  # Mersenne prime for p=7 is 127
        
        # Test known non-Mersenne primes
        self.assertFalse(is_mersenne_prime(11))  # Mersenne number for p=11 is not prime
        self.assertFalse(is_mersenne_prime(13))  # Mersenne number for p=13 is not prime
        
        # Test non-prime p values
        self.assertFalse(is_mersenne_prime(4))
        self.assertFalse(is_mersenne_prime(6))
        self.assertFalse(is_mersenne_prime(8))

if __name__ == '__main__':
    unittest.main()
