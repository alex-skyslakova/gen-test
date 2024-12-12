import unittest
from fractions import Fraction

def fractran(n, fstring='17 / 91, 78 / 85, 19 / 51, 23 / 38, 29 / 33,'
                        '77 / 29, 95 / 23, 77 / 19, 1 / 17, 11 / 13,'
                        '13 / 11, 15 / 14, 15 / 2, 55 / 1'):
    flist = [Fraction(f) for f in fstring.replace(' ', '').split(',')]

    n = Fraction(n)
    results = []
    for _ in range(100): # Limiting the number of steps
        results.append(n.numerator)
        for f in flist:
            if (n * f).denominator == 1:
                break
        else:
            break
        n *= f
    return results


class TestFractran(unittest.TestCase):

    def test_conway_prime_program(self):
        expected_start = [2, 15, 825, 725, 1925, 2275, 425, 390, 330, 290, 770]
        self.assertEqual(fractran(2, max_steps=11), expected_start)

    def test_empty_fractions(self):
        self.assertEqual(fractran(2, fstring=""), [2])

    def test_single_fraction_integer_result(self):
        self.assertEqual(fractran(3, fstring="2/3"), [3, 2])

    def test_single_fraction_no_integer_result(self):
        self.assertEqual(fractran(2, fstring="3/4"), [2])
        
    def test_multiple_fractions_some_applicable(self):
        self.assertEqual(fractran(10, fstring="1/2, 1/5, 3/5"), [10, 5, 1, 3])

    def test_large_input(self):
        self.assertEqual(fractran(1000, fstring="1/10"), [1000, 100, 10, 1])


if __name__ == '__main__':
    unittest.main()

