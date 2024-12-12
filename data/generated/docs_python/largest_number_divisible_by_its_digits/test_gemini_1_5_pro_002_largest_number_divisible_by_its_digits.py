import unittest
from itertools import (chain, permutations)
from functools import (reduce)
from math import (gcd)

# Tested functions
def intFromDigits(xs):
    return reduce(lambda a, x: a * 10 + x, xs, 0)

def concatMap(f):
    def go(xs):
        return chain.from_iterable(map(f, xs))
    return go

def delete(xs):
    def go(x):
        ys = xs.copy()
        ys.remove(x)
        return ys
    return go

def lcm(x, y):
    return 0 if (0 == x or 0 == y) else abs(
        y * (x // gcd(x, y))
    )



class TestLargestNumber(unittest.TestCase):

    def test_intFromDigits(self):
        self.assertEqual(intFromDigits([1, 2, 3]), 123)
        self.assertEqual(intFromDigits([9, 8, 7, 6]), 9876)
        self.assertEqual(intFromDigits([]), 0)

    def test_concatMap(self):
        self.assertEqual(list(concatMap(lambda x: [x * 2])([1, 2, 3])), [2, 4, 6])
        self.assertEqual(list(concatMap(lambda x: [])([1, 2, 3])), [])
        self.assertEqual(list(concatMap(lambda x: [[x, x]])([1, 2])), [[1, 1], [2, 2]])


    def test_delete(self):
        self.assertEqual(delete([1, 2, 3, 2])(2), [1, 3, 2])
        self.assertEqual(delete([1, 2, 3])(4), [1, 2, 3])  # No change if element not present
        self.assertEqual(delete([])(1), [])


    def test_lcm(self):
        self.assertEqual(lcm(12, 18), 36)
        self.assertEqual(lcm(5, 7), 35)
        self.assertEqual(lcm(0, 5), 0)
        self.assertEqual(lcm(4, 0), 0)
        self.assertEqual(lcm(4, 4), 4)

    # Integration test (covering the main logic without printing)
    def test_main_logic(self):
        digits = [1, 2, 3, 4, 6, 7, 8, 9]
        lcmDigits = reduce(lcm, digits)
        sevenDigits = ((delete)(digits)(x) for x in [1, 4, 7])
        result = max(
            (
                intFromDigits(x) for x
                in concatMap(permutations)(sevenDigits)
            ),
            key=lambda n: n if 0 == n % lcmDigits else 0
        )
        self.assertEqual(result, 9876512) # Expected result based on problem description


if __name__ == '__main__':
    unittest.main()
