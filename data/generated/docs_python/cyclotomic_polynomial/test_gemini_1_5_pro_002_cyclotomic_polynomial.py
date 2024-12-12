import unittest
from itertools import count, chain
from collections import deque

# Tested code omitted for brevity. Include the code from the prompt here.

class TestCyclotomic(unittest.TestCase):

    def test_cyclotomic_small_n(self):
        self.assertEqual(list(terms(cyclotomic(0))), [(1, 0)])  # x^0 = 1
        self.assertEqual(list(terms(cyclotomic(1))), [(1, 1)]) # x -1 -> x
        self.assertEqual(list(terms(cyclotomic(2))), [(1, 1), (1, 0)])  # x + 1
        self.assertEqual(list(terms(cyclotomic(3))), [(1, 2), (1, 1), (1, 0)])  # x^2+x+1
        self.assertEqual(list(terms(cyclotomic(4))), [(1, 2), (1, 0)]) # x^2 + 1
        self.assertEqual(list(terms(cyclotomic(5))), [(1, 4), (1, 3), (1, 2), (1, 1), (1, 0)]) # x^4+x^3+x^2+x+1
        self.assertEqual(list(terms(cyclotomic(6))), [(1, 2), (-1, 1), (1, 0)])
        self.assertEqual(list(terms(cyclotomic(7))), [(1, 6), (1, 5), (1, 4), (1, 3), (1, 2), (1, 1), (1, 0)])
        self.assertEqual(list(terms(cyclotomic(8))), [(1, 4), (1, 0)])


    def test_to_text(self):
        self.assertEqual(to_text(terms(cyclotomic(1))), "x")
        self.assertEqual(to_text(terms(cyclotomic(2))), "x + 1")
        self.assertEqual(to_text(terms(cyclotomic(3))), "x² + x + 1")
        self.assertEqual(to_text(terms(cyclotomic(4))), "x² + 1")
        self.assertEqual(to_text(terms(cyclotomic(6))), "x² - x + 1")
        self.assertEqual(to_text([(2,3),(-3,1),(1,0)]), "2x³ - 3x + 1")
        self.assertEqual(to_text([(-1,2),(1,1)]), " - x² + x")


    def test_primes(self):
        p_gen = primes()
        self.assertEqual(next(p_gen), 2)
        self.assertEqual(next(p_gen), 3)
        self.assertEqual(next(p_gen), 5)
        self.assertEqual(next(p_gen), 7)
        self.assertEqual(next(p_gen), 11)

    def test_isprime(self):
        self.assertTrue(isprime(2))
        self.assertTrue(isprime(3))
        self.assertTrue(isprime(5))
        self.assertTrue(isprime(7))
        self.assertFalse(isprime(4))
        self.assertFalse(isprime(6))
        self.assertFalse(isprime(8))

    def test_factors(self):
        self.assertEqual(list(factors(1)), [])
        self.assertEqual(list(factors(2)), [(2, 1, 1)])
        self.assertEqual(list(factors(4)), [(2, 2, 1)])
        self.assertEqual(list(factors(6)), [(2, 1, 3)])
        self.assertEqual(list(factors(12)), [(2, 2, 3)])
        self.assertEqual(list(factors(100)), [(2,2,25)])

    

if __name__ == '__main__':
    unittest.main()

