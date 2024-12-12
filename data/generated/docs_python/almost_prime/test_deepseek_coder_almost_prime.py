import unittest
from almost_prime import almostprime

class TestAlmostPrime(unittest.TestCase):

    def test_1_almost_primes(self):
        # 1-almost-primes are prime numbers
        primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        for prime in primes:
            self.assertTrue(almostprime(prime, 1), f"{prime} should be a 1-almost-prime")

    def test_2_almost_primes(self):
        # 2-almost-primes are semiprimes
        semiprimes = [4, 6, 9, 10, 14, 15, 21, 22, 25, 26]
        for semiprime in semiprimes:
            self.assertTrue(almostprime(semiprime, 2), f"{semiprime} should be a 2-almost-prime")

    def test_3_almost_primes(self):
        # 3-almost-primes are numbers with exactly 3 prime factors
        almost_primes_3 = [8, 12, 18, 20, 27, 28, 30, 42, 44, 45]
        for num in almost_primes_3:
            self.assertTrue(almostprime(num, 3), f"{num} should be a 3-almost-prime")

    def test_4_almost_primes(self):
        # 4-almost-primes are numbers with exactly 4 prime factors
        almost_primes_4 = [16, 24, 36, 40, 54, 56, 60, 81, 84, 88]
        for num in almost_primes_4:
            self.assertTrue(almostprime(num, 4), f"{num} should be a 4-almost-prime")

    def test_5_almost_primes(self):
        # 5-almost-primes are numbers with exactly 5 prime factors
        almost_primes_5 = [32, 48, 72, 80, 108, 112, 120, 162, 168, 176]
        for num in almost_primes_5:
            self.assertTrue(almostprime(num, 5), f"{num} should be a 5-almost-prime")

    def test_non_almost_primes(self):
        # Numbers that are not k-almost primes for any k
        non_almost_primes = [1, 150, 200, 300, 400, 500, 600, 700, 800, 900]
        for num in non_almost_primes:
            for k in range(1, 6):
                self.assertFalse(almostprime(num, k), f"{num} should not be a {k}-almost-prime")

if __name__ == '__main__':
    unittest.main()
