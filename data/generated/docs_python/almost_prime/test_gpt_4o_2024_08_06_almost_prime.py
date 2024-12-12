import unittest
from almost_prime import almostprime

class TestAlmostPrime(unittest.TestCase):

    def test_1_almost_prime(self):
        # 1-almost-primes are prime numbers
        expected_1_almost_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        result = list(islice((n for n in count(2) if almostprime(n, 1)), 10))
        self.assertEqual(result, expected_1_almost_primes)

    def test_2_almost_prime(self):
        # 2-almost-primes are semiprimes
        expected_2_almost_primes = [4, 6, 9, 10, 14, 15, 21, 22, 25, 26]
        result = list(islice((n for n in count(2) if almostprime(n, 2)), 10))
        self.assertEqual(result, expected_2_almost_primes)

    def test_3_almost_prime(self):
        # 3-almost-primes
        expected_3_almost_primes = [8, 12, 18, 20, 27, 28, 30, 32, 44, 45]
        result = list(islice((n for n in count(2) if almostprime(n, 3)), 10))
        self.assertEqual(result, expected_3_almost_primes)

    def test_4_almost_prime(self):
        # 4-almost-primes
        expected_4_almost_primes = [16, 24, 36, 40, 54, 56, 60, 64, 72, 80]
        result = list(islice((n for n in count(2) if almostprime(n, 4)), 10))
        self.assertEqual(result, expected_4_almost_primes)

    def test_5_almost_prime(self):
        # 5-almost-primes
        expected_5_almost_primes = [32, 48, 72, 80, 108, 112, 120, 128, 144, 160]
        result = list(islice((n for n in count(2) if almostprime(n, 5)), 10))
        self.assertEqual(result, expected_5_almost_primes)

if __name__ == '__main__':
    unittest.main()
