import unittest
from itertools import islice
from math import sqrt

def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

def prime_generator():
    yield 2
    yield 3
    n = 5
    while True:
        if is_prime(n):
            yield n
        n += 2

class TestPrimeGenerator(unittest.TestCase):

    def test_first_twenty_primes(self):
        primes = list(islice(prime_generator(), 20))
        expected_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71]
        self.assertEqual(primes, expected_primes)

    def test_primes_between_100_and_150(self):
        primes = list(filter(lambda x: 100 <= x <= 150, prime_generator()))
        expected_primes = [101, 103, 107, 109, 113, 127, 131, 137, 139, 149]
        self.assertEqual(primes, expected_primes)

    def test_number_of_primes_between_7700_and_8000(self):
        primes = list(filter(lambda x: 7700 <= x <= 8000, prime_generator()))
        expected_count = 30
        self.assertEqual(len(primes), expected_count)

    def test_10000th_prime(self):
        primes = list(islice(prime_generator(), 10000))
        expected_10000th_prime = 104729
        self.assertEqual(primes[-1], expected_10000th_prime)

if __name__ == '__main__':
    unittest.main()
