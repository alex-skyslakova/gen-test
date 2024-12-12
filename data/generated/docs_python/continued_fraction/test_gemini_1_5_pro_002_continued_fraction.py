import unittest
from fractions import Fraction
import itertools

try:
    zip = itertools.izip
except:
    pass

# The Continued Fraction (unchanged from the original code)
def CF(a, b, t):
    terms = list(itertools.islice(zip(a, b), t))
    z = Fraction(1, 1)
    for a, b in reversed(terms):
        z = a + b / z
    return z

# Approximates a fraction to a string (unchanged from original code)
def pRes(x, d):
    q, x = divmod(x, 1)
    res = str(q)
    res += "."
    for i in range(d):
        x *= 10
        q, x = divmod(x, 1)
        res += str(q)
    return res


# Generators for sqrt2, Napier's Constant, and Pi (unchanged from original code)
def sqrt2_a():
    yield 1
    for x in itertools.repeat(2):
        yield x


def sqrt2_b():
    for x in itertools.repeat(1):
        yield x


def Napier_a():
    yield 2
    for x in itertools.count(1):
        yield x


def Napier_b():
    yield 1
    for x in itertools.count(1):
        yield x


def Pi_a():
    yield 3
    for x in itertools.repeat(6):
        yield x


def Pi_b():
    for x in itertools.count(1, 2):
        yield x * x


class TestContinuedFraction(unittest.TestCase):

    def test_sqrt2(self):
        self.assertEqual(pRes(CF(sqrt2_a(), sqrt2_b(), 950), 10), "1.4142135623")

    def test_Napier(self):
        self.assertEqual(pRes(CF(Napier_a(), Napier_b(), 950), 10), "2.7182818284")

    def test_Pi(self):
        self.assertEqual(pRes(CF(Pi_a(), Pi_b(), 950), 10), "3.1415926532")

    def test_CF_zero_terms(self):
        self.assertEqual(CF(sqrt2_a(), sqrt2_b(), 0), Fraction(1, 1))


    def test_CF_one_term(self):
        self.assertEqual(CF(sqrt2_a(), sqrt2_b(), 1), Fraction(1, 1))


    def test_CF_two_terms(self):  # Added test for two terms
        self.assertEqual(CF(sqrt2_a(), sqrt2_b(), 2), Fraction(3, 1))




if __name__ == '__main__':
    unittest.main()

