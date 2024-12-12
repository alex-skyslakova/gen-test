import unittest
from long_primes import sieve, findPeriod

class TestLongPrimes(unittest.TestCase):

    def test_sieve(self):
        # Test the sieve function to ensure it returns correct prime numbers
        self.assertEqual(sieve(10), [3, 5, 7])
        self.assertEqual(sieve(20), [3, 5, 7, 11, 13, 17, 19])
        self.assertEqual(sieve(30), [3, 5, 7, 11, 13, 17, 19, 23, 29])

    def test_findPeriod(self):
        # Test the findPeriod function to ensure it calculates the correct period
        self.assertEqual(findPeriod(7), 6)  # 1/7 = 0.142857 (period 6)
        self.assertEqual(findPeriod(3), 1)  # 1/3 = 0.3 (period 1)
        self.assertEqual(findPeriod(11), 2) # 1/11 = 0.09 (period 2)

    def test_long_primes(self):
        # Test the overall functionality to ensure it identifies long primes correctly
        primes = sieve(64000)
        longPrimes = [prime for prime in primes if findPeriod(prime) == prime - 1]

        # Check the number of long primes up to various limits
        self.assertEqual(len([p for p in longPrimes if p <= 500]), 6)
        self.assertEqual(len([p for p in longPrimes if p <= 1000]), 16)
        self.assertEqual(len([p for p in longPrimes if p <= 2000]), 37)
        self.assertEqual(len([p for p in longPrimes if p <= 4000]), 60)
        self.assertEqual(len([p for p in longPrimes if p <= 8000]), 109)
        self.assertEqual(len([p for p in longPrimes if p <= 16000]), 186)
        self.assertEqual(len([p for p in longPrimes if p <= 32000]), 324)
        self.assertEqual(len([p for p in longPrimes if p <= 64000]), 564)

if __name__ == '__main__':
    unittest.main()
