import unittest
from fractions import Fraction
from faulhaber_s_formula import nextu, nextv, sumpol, polstr

class TestFaulhaberFormula(unittest.TestCase):

    def test_nextu(self):
        # Test case for nextu function
        self.assertEqual(nextu([0, 1]), [0, 1, 1])
        self.assertEqual(nextu([0, 1, 1]), [0, 1, 3, 2])
        self.assertEqual(nextu([0, 1, 3, 2]), [0, 1, 7, 12, 6])

    def test_nextv(self):
        # Test case for nextv function
        self.assertEqual(nextv([1]), [1, 1])
        self.assertEqual(nextv([1, 1]), [1, 2, 1])
        self.assertEqual(nextv([1, 2, 1]), [1, 3, 3, 1])

    def test_sumpol(self):
        # Test case for sumpol function
        expected_results = [
            [Fraction(0), Fraction(1)],
            [Fraction(0), Fraction(1, 2), Fraction(1, 2)],
            [Fraction(0), Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)],
            [Fraction(0), Fraction(0), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4)],
            [Fraction(0), Fraction(-1, 30), Fraction(0), Fraction(1, 3), Fraction(1, 2), Fraction(1, 5)],
            [Fraction(0), Fraction(0), Fraction(-1, 12), Fraction(0), Fraction(5, 12), Fraction(1, 2), Fraction(1, 6)],
            [Fraction(0), Fraction(1, 42), Fraction(0), Fraction(-1, 6), Fraction(0), Fraction(1, 2), Fraction(1, 2), Fraction(1, 7)],
            [Fraction(0), Fraction(0), Fraction(1, 12), Fraction(0), Fraction(-7, 24), Fraction(0), Fraction(7, 12), Fraction(1, 2), Fraction(1, 8)],
            [Fraction(0), Fraction(-1, 30), Fraction(0), Fraction(2, 9), Fraction(0), Fraction(-7, 15), Fraction(0), Fraction(2, 3), Fraction(1, 2), Fraction(1, 9)],
            [Fraction(0), Fraction(0), Fraction(-3, 20), Fraction(0), Fraction(1, 2), Fraction(0), Fraction(-7, 10), Fraction(0), Fraction(3, 4), Fraction(1, 2), Fraction(1, 10)]
        ]
        for i, result in enumerate(sumpol(10)):
            self.assertEqual(result, expected_results[i])

    def test_polstr(self):
        # Test case for polstr function
        self.assertEqual(polstr([Fraction(0), Fraction(1)]), "n")
        self.assertEqual(polstr([Fraction(0), Fraction(1, 2), Fraction(1, 2)]), "1/2 n + 1/2 n^2")
        self.assertEqual(polstr([Fraction(0), Fraction(1, 6), Fraction(1, 2), Fraction(1, 3)]), "1/6 n + 1/2 n^2 + 1/3 n^3")
        self.assertEqual(polstr([Fraction(0), Fraction(0), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4)]), "1/4 n^2 + 1/2 n^3 + 1/4 n^4")
        self.assertEqual(polstr([Fraction(0), Fraction(-1, 30), Fraction(0), Fraction(1, 3), Fraction(1, 2), Fraction(1, 5)]), "-1/30 n + 1/3 n^3 + 1/2 n^4 + 1/5 n^5")

if __name__ == '__main__':
    unittest.main()
