import unittest
from itertools import islice

def prime_generator():
    """Generator function for prime numbers."""
    D = {}
    q = 2  # Starting with the first prime number

    while True:
        if q not in D:
            # q is a new prime number
            yield q
            # Mark the first multiple of q that is not already marked
            D[q * q] = [q]
        else:
            # q is not a prime, it is a composite number
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1

class TestPrimeGenerator(unittest.TestCase):
    def test_first_twenty_primes(self):
        gen = prime_generator()
        first_twenty_primes = list(islice(gen, 20))
        expected_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71]
        self.assertEqual(first_twenty_primes, expected_primes)

    def test_primes_between_100_and_150(self):
        gen = prime_generator()
        primes_between_100_and_150 = [p for p in gen if p >= 100 and p <= 150]
        expected_primes = [101, 103, 107, 109, 113, 127, 131, 137, 139, 149]
        self.assertEqual(primes_between_100_and_150, expected_primes)

    def test_number_of_primes_between_7700_and_8000(self):
        gen = prime_generator()
        primes_between_7700_and_8000 = [p for p in gen if p >= 7700 and p <= 8000]
        self.assertEqual(len(primes_between_7700_and_8000), 28)

    def test_10000th_prime(self):
        gen = prime_generator()
        ten_thousandth_prime = next(islice(gen, 9999, None))
        self.assertEqual(ten_thousandth_prime, 104729)

if __name__ == '__main__':
    unittest.main()
