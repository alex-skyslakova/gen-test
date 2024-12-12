import unittest
from almost_prime import almostprime
from itertools import islice, count

class TestAlmostPrime(unittest.TestCase):

    def test_almostprime_k1(self):
        expected = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        actual = list(islice((n for n in count() if almostprime(n, 1)), 10))
        self.assertEqual(actual, expected)

    def test_almostprime_k2(self):
        expected = [4, 6, 9, 10, 14, 15, 21, 22, 25, 26]
        actual = list(islice((n for n in count() if almostprime(n, 2)), 10))
        self.assertEqual(actual, expected)

    def test_almostprime_k3(self):
        expected = [8, 12, 18, 20, 27, 28, 30, 42, 44, 45]
        actual = list(islice((n for n in count() if almostprime(n, 3)), 10))
        self.assertEqual(actual, expected)

    def test_almostprime_k4(self):
        expected = [16, 24, 36, 40, 54, 56, 60, 81, 84, 88]
        actual = list(islice((n for n in count() if almostprime(n, 4)), 10))
        self.assertEqual(actual, expected)

    def test_almostprime_k5(self):
        expected = [32, 48, 72, 80, 108, 112, 120, 162, 168, 176]
        actual = list(islice((n for n in count() if almostprime(n, 5)), 10))
        self.assertEqual(actual, expected)

    def test_almostprime_non_almostprime(self):
        self.assertFalse(almostprime(1))  # 1 is not prime
        self.assertFalse(almostprime(1, 2))
        self.assertFalse(almostprime(14, 3))
        self.assertFalse(almostprime(20,4))


