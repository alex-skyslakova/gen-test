import unittest
from long_primes import sieve, findPeriod

class TestLongPrimes(unittest.TestCase):

    def test_sieve(self):
        self.assertEqual(sieve(10), [3, 5, 7])
        self.assertEqual(sieve(20), [3, 5, 7, 11, 13, 17, 19])
        self.assertEqual(sieve(2), [])
        self.assertEqual(sieve(3), [3])

    def test_findPeriod(self):
        self.assertEqual(findPeriod(7), 6)
        self.assertEqual(findPeriod(3), 1)
        self.assertEqual(findPeriod(11), 2)
        self.assertEqual(findPeriod(13), 6)
        self.assertEqual(findPeriod(17), 16)
        self.assertEqual(findPeriod(19), 18)
        self.assertEqual(findPeriod(2), 1)
        self.assertEqual(findPeriod(37), 3)



