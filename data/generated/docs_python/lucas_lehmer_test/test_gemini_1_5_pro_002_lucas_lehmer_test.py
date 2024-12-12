import unittest
from lucas_lehmer_test import is_prime, is_mersenne_prime

class TestLucasLehmer(unittest.TestCase):

    def test_is_prime_basic(self):
        self.assertTrue(is_prime(2))
        self.assertTrue(is_prime(3))
        self.assertTrue(is_prime(5))
        self.assertTrue(is_prime(7))
        self.assertTrue(is_prime(13))
        self.assertFalse(is_prime(1))
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(-1))
        self.assertFalse(is_prime(4))
        self.assertFalse(is_prime(6))
        self.assertFalse(is_prime(8))
        self.assertFalse(is_prime(9))
        self.assertFalse(is_prime(10))

    def test_is_mersenne_prime_basic(self):
        self.assertTrue(is_mersenne_prime(2))
        self.assertTrue(is_mersenne_prime(3))
        self.assertTrue(is_mersenne_prime(5))
        self.assertTrue(is_mersenne_prime(7))
        self.assertFalse(is_mersenne_prime(11))  # M11 is composite
        self.assertTrue(is_mersenne_prime(13))
        self.assertTrue(is_mersenne_prime(17))
        self.assertTrue(is_mersenne_prime(19))
        self.assertFalse(is_mersenne_prime(23))
        self.assertTrue(is_mersenne_prime(31))
        self.assertTrue(is_mersenne_prime(61))
        self.assertFalse(is_mersenne_prime(89)) # Example of a non-Mersenne prime



    def test_is_mersenne_prime_large(self):
      #Testing very large values can take considerable time in Python
        self.assertTrue(is_mersenne_prime(1279)) #This test in particular can be slow


if __name__ == '__main__':
    unittest.main()
