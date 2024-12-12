import unittest
from additive_primes import is_prime, digit_sum

class TestAdditivePrimes(unittest.TestCase):

    def test_is_prime(self):
        # Test known primes
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
        
        # Test known non-primes
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(1))
        self.assertFalse(is_prime(4))
        self.assertFalse(is_prime(6))
        self.assertFalse(is_prime(8))
        self.assertFalse(is_prime(9))
        self.assertFalse(is_prime(10))
        self.assertFalse(is_prime(12))
        self.assertFalse(is_prime(14))
        self.assertFalse(is_prime(15))
        self.assertFalse(is_prime(16))
        self.assertFalse(is_prime(18))
        self.assertFalse(is_prime(20))

    def test_digit_sum(self):
        # Test digit sums
        self.assertEqual(digit_sum(0), 0)
        self.assertEqual(digit_sum(1), 1)
        self.assertEqual(digit_sum(12), 3)
        self.assertEqual(digit_sum(123), 6)
        self.assertEqual(digit_sum(456), 15)
        self.assertEqual(digit_sum(789), 24)
        self.assertEqual(digit_sum(999), 27)

    def test_additive_primes(self):
        # Known additive primes less than 500
        expected_additive_primes = [
            2, 3, 5, 7, 11, 17, 23, 29, 41, 43, 47, 61, 67, 83, 89, 101, 113, 
            131, 137, 139, 167, 173, 179, 181, 191, 223, 229, 233, 239, 263, 
            269, 271, 281, 283, 293, 311, 313, 317, 337, 347, 353, 359, 367, 
            373, 379, 383, 389, 419, 421, 431, 433, 439, 461, 463, 467, 479, 
            487, 491, 499
        ]
        
        additive_primes = []
        for i in range(2, 500):
            if is_prime(i) and is_prime(digit_sum(i)):
                additive_primes.append(i)
        
        self.assertEqual(additive_primes, expected_additive_primes)
        self.assertEqual(len(additive_primes), len(expected_additive_primes))

if __name__ == '__main__':
    unittest.main()
