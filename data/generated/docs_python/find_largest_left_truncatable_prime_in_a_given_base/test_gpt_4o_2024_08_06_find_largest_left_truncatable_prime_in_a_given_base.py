import unittest
from find_largest_left_truncatable_prime_in_a_given_base import is_probable_prime, largest_left_truncatable_prime

class TestTruncatablePrimes(unittest.TestCase):

    def test_is_probable_prime(self):
        # Test known primes
        self.assertTrue(is_probable_prime(2, 30))
        self.assertTrue(is_probable_prime(3, 30))
        self.assertTrue(is_probable_prime(5, 30))
        self.assertTrue(is_probable_prime(7, 30))
        self.assertTrue(is_probable_prime(11, 30))
        self.assertTrue(is_probable_prime(13, 30))
        
        # Test known non-primes
        self.assertFalse(is_probable_prime(0, 30))
        self.assertFalse(is_probable_prime(1, 30))
        self.assertFalse(is_probable_prime(4, 30))
        self.assertFalse(is_probable_prime(6, 30))
        self.assertFalse(is_probable_prime(8, 30))
        self.assertFalse(is_probable_prime(9, 30))
        self.assertFalse(is_probable_prime(10, 30))

    def test_largest_left_truncatable_prime_base_3(self):
        # Base 3 example from the documentation
        self.assertEqual(largest_left_truncatable_prime(3), 2)

    def test_largest_left_truncatable_prime_base_10(self):
        # Known result for base 10
        self.assertEqual(largest_left_truncatable_prime(10), 739397)

    def test_largest_left_truncatable_prime_various_bases(self):
        # Test for various bases
        self.assertEqual(largest_left_truncatable_prime(4), 3)
        self.assertEqual(largest_left_truncatable_prime(5), 13)
        self.assertEqual(largest_left_truncatable_prime(6), 23)
        self.assertEqual(largest_left_truncatable_prime(7), 53)
        self.assertEqual(largest_left_truncatable_prime(8), 73)
        self.assertEqual(largest_left_truncatable_prime(9), 313)
        self.assertEqual(largest_left_truncatable_prime(11), 2333)
        self.assertEqual(largest_left_truncatable_prime(12), 3137)
        self.assertEqual(largest_left_truncatable_prime(13), 23333)
        self.assertEqual(largest_left_truncatable_prime(14), 31397)
        self.assertEqual(largest_left_truncatable_prime(15), 233993)
        self.assertEqual(largest_left_truncatable_prime(16), 313997)
        self.assertEqual(largest_left_truncatable_prime(17), 2339933)
        self.assertEqual(largest_left_truncatable_prime(18), 3139973)
        self.assertEqual(largest_left_truncatable_prime(19), 23399339)
        self.assertEqual(largest_left_truncatable_prime(20), 31399733)
        self.assertEqual(largest_left_truncatable_prime(21), 233993399)
        self.assertEqual(largest_left_truncatable_prime(22), 313997333)
        self.assertEqual(largest_left_truncatable_prime(23), 2339933993)

if __name__ == '__main__':
    unittest.main()
