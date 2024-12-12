import unittest
from itertools import count, dropwhile, repeat
from math import ceil, sqrt

# Import the functions from the provided code
def allDigitSquare(base, above):
    bools = list(repeat(True, base))
    return next(
        dropwhile(
            missingDigitsAtBase(base, bools),
            count(
                max(
                    above,
                    ceil(sqrt(int(
                        '10' + '0123456789abcdef'[2:base],
                        base
                    )))
                )
            )
        )
    )

def missingDigitsAtBase(base, bools):
    def go(x):
        xs = bools.copy()
        while x:
            xs[x % base] = False
            x //= base
        return any(xs)
    return lambda n: go(n * n)

def digit(n):
    return '0123456789abcdef'[n]

def enumFromTo(m):
    return lambda n: list(range(m, 1 + n))

def showIntAtBase(base):
    def wrap(toChr, n, rs):
        def go(nd, r):
            n, d = nd
            r_ = toChr(d) + r
            return go(divmod(n, base), r_) if 0 != n else r_
        return 'unsupported base' if 1 >= base else (
            'negative number' if 0 > n else (
                go(divmod(n, base), rs))
        )
    return lambda toChr: lambda n: lambda rs: (
        wrap(toChr, n, rs)
    )

# Unit tests
class TestPerfectSquareFunctions(unittest.TestCase):

    def test_allDigitSquare(self):
        # Test for base 2
        self.assertEqual(allDigitSquare(2, 0), 3)  # 3^2 = 9 (1001 in base 2)
        # Test for base 10
        self.assertEqual(allDigitSquare(10, 0), 32043)  # 32043^2 = 1026753849
        # Test for base 16
        self.assertEqual(allDigitSquare(16, 0), 264)  # 264^2 = 69696 (10E10 in base 16)

    def test_missingDigitsAtBase(self):
        # Test for base 2
        missing_digits_2 = missingDigitsAtBase(2, [True, True])
        self.assertFalse(missing_digits_2(3))  # 3^2 = 9 (1001 in base 2)
        # Test for base 10
        missing_digits_10 = missingDigitsAtBase(10, [True]*10)
        self.assertFalse(missing_digits_10(32043))  # 32043^2 = 1026753849
        # Test for base 16
        missing_digits_16 = missingDigitsAtBase(16, [True]*16)
        self.assertFalse(missing_digits_16(264))  # 264^2 = 69696 (10E10 in base 16)

    def test_digit(self):
        self.assertEqual(digit(0), '0')
        self.assertEqual(digit(9), '9')
        self.assertEqual(digit(10), 'a')
        self.assertEqual(digit(15), 'f')

    def test_enumFromTo(self):
        self.assertEqual(enumFromTo(2)(5), [2, 3, 4, 5])
        self.assertEqual(enumFromTo(1)(3), [1, 2, 3])

    def test_showIntAtBase(self):
        show_base_2 = showIntAtBase(2)(digit)
        self.assertEqual(show_base_2(3)(''), '11')
        show_base_10 = showIntAtBase(10)(digit)
        self.assertEqual(show_base_10(123)(''), '123')
        show_base_16 = showIntAtBase(16)(digit)
        self.assertEqual(show_base_16(255)(''), 'ff')

if __name__ == '__main__':
    unittest.main()
