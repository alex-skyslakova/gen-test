import unittest
from fractions import Fraction
from egyptian_fractions import Fr, ef

class TestEgyptianFractions(unittest.TestCase):

    def test_proper_fraction_43_48(self):
        fr = Fr(43, 48)
        result = ef(fr)
        expected = [Fr(1, 2), Fr(1, 3), Fr(1, 16)]
        self.assertEqual(result, expected)

    def test_proper_fraction_5_121(self):
        fr = Fr(5, 121)
        result = ef(fr)
        expected = [Fr(1, 25), Fr(1, 757), Fr(1, 763309)]
        self.assertEqual(result, expected)

    def test_improper_fraction_2014_59(self):
        fr = Fr(2014, 59)
        result = ef(fr)
        expected = [[34], Fr(1, 2), Fr(1, 3), Fr(1, 11), Fr(1, 646)]
        self.assertEqual(result, expected)

    def test_largest_number_of_terms(self):
        lenmax = (0, None)
        for fr in set(Fr(a, b) for a in range(1, 100) for b in range(1, 100)):
            e = ef(fr)
            elen = len(e)
            if elen > lenmax[0]:
                lenmax = (elen, fr, e)
        self.assertEqual(lenmax[1], Fr(63, 64))  # Example expected fraction
        self.assertEqual(lenmax[0], 6)  # Example expected number of terms

    def test_largest_denominator(self):
        denommax = (0, None)
        for fr in set(Fr(a, b) for a in range(1, 100) for b in range(1, 100)):
            e = ef(fr)
            edenom = e[-1].denominator
            if edenom > denommax[0]:
                denommax = (edenom, fr, e)
        self.assertEqual(denommax[1], Fr(1, 99))  # Example expected fraction
        self.assertEqual(denommax[0], 9901)  # Example expected largest denominator

if __name__ == '__main__':
    unittest.main()
