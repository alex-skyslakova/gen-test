import unittest
from long_primes import sieve, findPeriod

class TestLongPrimes(unittest.TestCase):

    def test_sieve(self):
        # Test the sieve function to ensure it correctly identifies prime numbers up to a limit.
        self.assertEqual(sieve(10), [3, 5, 7])
        self.assertEqual(sieve(20), [3, 5, 7, 11, 13, 17, 19])
        self.assertEqual(sieve(30), [3, 5, 7, 11, 13, 17, 19, 23, 29])

    def test_findPeriod(self):
        # Test the findPeriod function to ensure it correctly identifies the period of the reciprocal of a prime number.
        self.assertEqual(findPeriod(7), 6)
        self.assertEqual(findPeriod(11), 2)
        self.assertEqual(findPeriod(13), 6)
        self.assertEqual(findPeriod(17), 16)
        self.assertEqual(findPeriod(19), 18)

    def test_long_primes_up_to_500(self):
        # Test the long primes up to 500.
        primes = sieve(500)
        long_primes = [prime for prime in primes if findPeriod(prime) == prime - 1]
        expected_long_primes = [7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 257, 263, 269, 313, 337, 367, 379, 383, 389, 419, 433, 461, 487, 491, 499]
        self.assertEqual(long_primes, expected_long_primes)

    def test_long_primes_count(self):
        # Test the count of long primes up to various limits.
        primes = sieve(64000)
        long_primes = [prime for prime in primes if findPeriod(prime) == prime - 1]
        numbers = [500, 1000, 2000, 4000, 8000, 16000, 32000, 64000]
        counts = []
        count = 0
        index = 0
        for long_prime in long_primes:
            if long_prime > numbers[index]:
                counts.append(count)
                index += 1
            count += 1
        counts.append(count)

        expected_counts = [35, 40, 46, 50, 55, 60, 65, 75]
        self.assertEqual(counts, expected_counts)

if __name__ == '__main__':
    unittest.main()
