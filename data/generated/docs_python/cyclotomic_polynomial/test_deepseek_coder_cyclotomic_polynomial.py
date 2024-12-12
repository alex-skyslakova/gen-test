import unittest
from cyclotomic_polynomial import primes, isprime, factors, cyclotomic, to_text, terms

class TestCyclotomicPolynomial(unittest.TestCase):

    def test_primes(self):
        prime_gen = primes()
        first_10_primes = [next(prime_gen) for _ in range(10)]
        self.assertEqual(first_10_primes, [2, 3, 5, 7, 11, 13, 17, 19, 23, 29])

    def test_isprime(self):
        self.assertTrue(isprime(2))
        self.assertTrue(isprime(3))
        self.assertFalse(isprime(4))
        self.assertTrue(isprime(5))
        self.assertFalse(isprime(6))
        self.assertTrue(isprime(7))
        self.assertFalse(isprime(8))
        self.assertFalse(isprime(9))
        self.assertFalse(isprime(10))
        self.assertTrue(isprime(11))

    def test_factors(self):
        self.assertEqual(list(factors(2)), [(2, 1, 1)])
        self.assertEqual(list(factors(3)), [(3, 1, 1)])
        self.assertEqual(list(factors(4)), [(2, 2, 1)])
        self.assertEqual(list(factors(5)), [(5, 1, 1)])
        self.assertEqual(list(factors(6)), [(2, 1, 3), (3, 1, 1)])
        self.assertEqual(list(factors(7)), [(7, 1, 1)])
        self.assertEqual(list(factors(8)), [(2, 3, 1)])
        self.assertEqual(list(factors(9)), [(3, 2, 1)])
        self.assertEqual(list(factors(10)), [(2, 1, 5), (5, 1, 1)])

    def test_cyclotomic(self):
        self.assertEqual(cyclotomic(0), ([], []))
        self.assertEqual(cyclotomic(1), ([1], []))
        self.assertEqual(cyclotomic(2), ([2], []))
        self.assertEqual(cyclotomic(3), ([3], []))
        self.assertEqual(cyclotomic(4), ([4], []))
        self.assertEqual(cyclotomic(5), ([5], []))
        self.assertEqual(cyclotomic(6), ([6], []))
        self.assertEqual(cyclotomic(7), ([7], []))
        self.assertEqual(cyclotomic(8), ([8], []))
        self.assertEqual(cyclotomic(9), ([9], []))
        self.assertEqual(cyclotomic(10), ([10], []))

    def test_to_text(self):
        self.assertEqual(to_text([(1, 0)]), '1')
        self.assertEqual(to_text([(1, 1)]), 'x')
        self.assertEqual(to_text([(1, 2)]), 'x²')
        self.assertEqual(to_text([(1, 3)]), 'x³')
        self.assertEqual(to_text([(1, 4)]), 'x⁴')
        self.assertEqual(to_text([(1, 5)]), 'x⁵')
        self.assertEqual(to_text([(1, 6)]), 'x⁶')
        self.assertEqual(to_text([(1, 7)]), 'x⁷')
        self.assertEqual(to_text([(1, 8)]), 'x⁸')
        self.assertEqual(to_text([(1, 9)]), 'x⁹')
        self.assertEqual(to_text([(1, 10)]), 'x¹⁰')

    def test_terms(self):
        self.assertEqual(list(terms(([1], []))), [(1, 0)])
        self.assertEqual(list(terms(([2], []))), [(1, 1)])
        self.assertEqual(list(terms(([3], []))), [(1, 2)])
        self.assertEqual(list(terms(([4], []))), [(1, 3)])
        self.assertEqual(list(terms(([5], []))), [(1, 4)])
        self.assertEqual(list(terms(([6], []))), [(1, 5)])
        self.assertEqual(list(terms(([7], []))), [(1, 6)])
        self.assertEqual(list(terms(([8], []))), [(1, 7)])
        self.assertEqual(list(terms(([9], []))), [(1, 8)])
        self.assertEqual(list(terms(([10], []))), [(1, 9)])

if __name__ == '__main__':
    unittest.main()
