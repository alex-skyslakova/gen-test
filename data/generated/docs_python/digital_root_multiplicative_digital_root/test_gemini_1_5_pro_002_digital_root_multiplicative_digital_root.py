import unittest
from functools import reduce

def mdroot(n):
    'Multiplicative digital root'
    mdr = [n]
    while mdr[-1] > 9:
        mdr.append(reduce(int.__mul__, (int(dig) for dig in str(mdr[-1])), 1))
    return len(mdr) - 1, mdr[-1]

class TestMDR(unittest.TestCase):

    def test_mdroot_single_digit(self):
        self.assertEqual(mdroot(5), (0, 5))

    def test_mdroot_multiple_digits(self):
        self.assertEqual(mdroot(123321), (2, 36))
        self.assertEqual(mdroot(7739), (3, 0))
        self.assertEqual(mdroot(893), (2, 216))
        self.assertEqual(mdroot(899998), (2, 0))
        self.assertEqual(mdroot(277777788888899), (11, 0))

    def test_mdroot_zero(self):
        self.assertEqual(mdroot(0), (0, 0))

    def test_mdroot_table(self):
        table, n = {i: [] for i in range(10)}, 0
        while min(len(row) for row in table.values()) < 5:
            mpersistence, mdr = mdroot(n)
            table[mdr].append(n)
            n += 1

        expected_table = {
            0: [0, 10, 20, 25, 30],
            1: [1, 11, 111, 1111, 11111],
            2: [2, 12, 21, 26, 34],
            3: [3, 13, 31, 113, 131],
            4: [4, 14, 22, 27, 39],
            5: [5, 15, 35, 51, 53],
            6: [6, 16, 23, 28, 32],
            7: [7, 17, 71, 117, 171],
            8: [8, 18, 24, 29, 36],
            9: [9, 19, 33, 91, 119],
        }
        self.assertEqual(table, expected_table)


if __name__ == '__main__':
    unittest.main()

