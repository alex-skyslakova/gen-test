import unittest
from fractions import Fraction
from faulhaber_s_formula import nextu, nextv, sumpol, polstr

class TestFaulhabersFormula(unittest.TestCase):

    def test_nextu(self):
        self.assertEqual(nextu([0, 1]), [0, 1, 1])
        self.assertEqual(nextu([0, 1, 1]), [0, 2, 2, 1])
        self.assertEqual(nextu([0, 2, 2, 1]), [0, 6, 5, 3, 1])

    def test_nextv(self):
        self.assertEqual(nextv([1, 1]), [0, 1, 1])
        self.assertEqual(nextv([0, 1, 1]), [0, 0, 1, 1])
        self.assertEqual(nextv([0, 0, 1, 1]), [0, 0, 0, 1, 1])

    def test_sumpol(self):
        expected_results = [
            [Fraction(0), Fraction(1)],  # p = 0
            [Fraction(0), Fraction(1, 2), Fraction(1, 2)],  # p = 1
            [Fraction(0), Fraction(1, 3), Fraction(1, 2), Fraction(1, 6)],  # p = 2
            [Fraction(0), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4), Fraction(0)],  # p = 3
            [Fraction(0), Fraction(1, 5), Fraction(1, 2), Fraction(1, 3), Fraction(0), Fraction(0)],  # p = 4
            [Fraction(0), Fraction(1, 6), Fraction(1, 2), Fraction(5, 12), Fraction(0), Fraction(0), Fraction(0)],  # p = 5
            [Fraction(0), Fraction(1, 7), Fraction(1, 2), Fraction(1, 2), Fraction(0), Fraction(0), Fraction(0), Fraction(0)],  # p = 6
            [Fraction(0), Fraction(1, 8), Fraction(1, 2), Fraction(7, 12), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0)],  # p = 7
            [Fraction(0), Fraction(1, 9), Fraction(1, 2), Fraction(2, 3), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0)],  # p = 8
            [Fraction(0), Fraction(1, 10), Fraction(1, 2), Fraction(3, 4), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0), Fraction(0)],  # p = 9
        ]

        for i, expected in enumerate(expected_results):
            with self.subTest(i=i):
                result = next(sumpol(i + 1))
                self.assertEqual(result, expected)

    def test_polstr(self):
        self.assertEqual(polstr([Fraction(0), Fraction(1)]), "1")
        self.assertEqual(polstr([Fraction(0), Fraction(1, 2), Fraction(1, 2)]), "1/2 n + 1/2")
        self.assertEqual(polstr([Fraction(0), Fraction(1, 3), Fraction(1, 2), Fraction(1, 6)]), "1/3 n^2 + 1/2 n + 1/6")
        self.assertEqual(polstr([Fraction(0), Fraction(1, 4), Fraction(1, 2), Fraction(1, 4), Fraction(0)]), "1/4 n^3 + 1/2 n^2 + 1/4 n")

if __name__ == '__main__':
    unittest.main()
