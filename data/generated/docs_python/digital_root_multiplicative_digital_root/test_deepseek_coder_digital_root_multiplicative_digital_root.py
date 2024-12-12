import unittest
from functools import reduce

def mdroot(n):
    'Multiplicative digital root'
    mdr = [n]
    while mdr[-1] > 9:
        mdr.append(reduce(int.__mul__, (int(dig) for dig in str(mdr[-1])), 1))
    return len(mdr) - 1, mdr[-1]

class TestMdroot(unittest.TestCase):

    def test_mdroot_single_digit(self):
        self.assertEqual(mdroot(5), (0, 5))
        self.assertEqual(mdroot(9), (0, 9))

    def test_mdroot_two_digits(self):
        self.assertEqual(mdroot(10), (1, 0))
        self.assertEqual(mdroot(25), (1, 0))

    def test_mdroot_multiple_digits(self):
        self.assertEqual(mdroot(123321), (3, 8))
        self.assertEqual(mdroot(7739), (3, 8))
        self.assertEqual(mdroot(893), (3, 2))
        self.assertEqual(mdroot(899998), (2, 0))

    def test_mdroot_large_number(self):
        self.assertEqual(mdroot(123456789), (2, 0))
        self.assertEqual(mdroot(999999999999), (3, 0))

    def test_mdroot_zero(self):
        self.assertEqual(mdroot(0), (0, 0))

    def test_mdroot_one(self):
        self.assertEqual(mdroot(1), (0, 1))

    def test_mdroot_all_same_digits(self):
        self.assertEqual(mdroot(222222), (1, 8))
        self.assertEqual(mdroot(333333), (1, 0))

    def test_mdroot_alternating_digits(self):
        self.assertEqual(mdroot(121212), (2, 8))
        self.assertEqual(mdroot(343434), (2, 0))

if __name__ == '__main__':
    unittest.main()
