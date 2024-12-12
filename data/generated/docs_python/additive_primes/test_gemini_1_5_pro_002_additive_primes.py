import unittest
from additive_primes import is_prime, digit_sum

class TestAdditivePrimes(unittest.TestCase):

    def test_is_prime(self):
        self.assertTrue(is_prime(2))
        self.assertTrue(is_prime(3))
        self.assertTrue(is_prime(5))
        self.assertTrue(is_prime(7))
        self.assertTrue(is_prime(11))
        self.assertTrue(is_prime(13))
        self.assertTrue(is_prime(17))
        self.assertTrue(is_prime(19))
        self.assertTrue(is_prime(23))
        self.assertTrue(is_prime(29))
        self.assertTrue(is_prime(31))
        self.assertTrue(is_prime(997))

        self.assertFalse(is_prime(1))
        self.assertFalse(is_prime(4))
        self.assertFalse(is_prime(6))
        self.assertFalse(is_prime(8))
        self.assertFalse(is_prime(9))
        self.assertFalse(is_prime(10))
        self.assertFalse(is_prime(12))
        self.assertFalse(is_prime(15))
        self.assertFalse(is_prime(21))
        self.assertFalse(is_prime(25))
        self.assertFalse(is_prime(27))
        self.assertFalse(is_prime(999))


    def test_digit_sum(self):
        self.assertEqual(digit_sum(0), 0)
        self.assertEqual(digit_sum(1), 1)
        self.assertEqual(digit_sum(12), 3)
        self.assertEqual(digit_sum(123), 6)
        self.assertEqual(digit_sum(1234), 10)
        self.assertEqual(digit_sum(99), 18)
        self.assertEqual(digit_sum(997), 25)

