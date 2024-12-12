import unittest
from fractions import Fraction
from math import ceil

class Fr(Fraction):
    def __repr__(self):
        return '%s/%s' % (self.numerator, self.denominator)

def ef(fr):
    ans = []
    if fr >= 1:
        if fr.denominator == 1:
            return [[int(fr)], Fr(0, 1)]
        intfr = int(fr)
        ans, fr = [[intfr]], fr - intfr
    x, y = fr.numerator, fr.denominator
    while x != 1:
        ans.append(Fr(1, ceil(1/fr)))
        fr = Fr(-y % x, y * ceil(1/fr))
        x, y = fr.numerator, fr.denominator
    ans.append(fr)
    return ans

class TestEgyptianFractions(unittest.TestCase):

    def test_proper_fraction(self):
        fr = Fr(43, 48)
        result = ef(fr)
        self.assertEqual(result, [Fr(1, 2), Fr(1, 3), Fr(1, 16)])

    def test_small_proper_fraction(self):
        fr = Fr(5, 121)
        result = ef(fr)
        self.assertEqual(result, [Fr(1, 25), Fr(1, 757), Fr(1, 763309), Fr(1, 873960180913), Fr(1, 1527612795642093418846225)])

    def test_improper_fraction(self):
        fr = Fr(2014, 59)
        result = ef(fr)
        self.assertEqual(result, [[34], Fr(1, 8), Fr(1, 95), Fr(1, 14947), Fr(1, 670223480)])

    def test_whole_number(self):
        fr = Fr(5, 1)
        result = ef(fr)
        self.assertEqual(result, [[5], Fr(0, 1)])

    def test_unit_fraction(self):
        fr = Fr(1, 7)
        result = ef(fr)
        self.assertEqual(result, [Fr(1, 7)])

    def test_large_denominator(self):
        fr = Fr(1, 123456789)
        result = ef(fr)
        self.assertEqual(result, [Fr(1, 123456789)])

    def test_large_number_of_terms(self):
        fr = Fr(99, 100)
        result = ef(fr)
        self.assertEqual(result, [Fr(1, 2), Fr(1, 3), Fr(1, 7), Fr(1, 43), Fr(1, 1807), Fr(1, 3263443), Fr(1, 10650056950807), Fr(1, 113423713055421844361000443)])

if __name__ == '__main__':
    unittest.main()
