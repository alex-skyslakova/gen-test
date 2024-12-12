import unittest
from scipy.misc import factorial as fact
from scipy.misc import comb

def perm(N, k, exact=0):
    return comb(N, k, exact) * fact(k, exact)

class TestCombinationsAndPermutations(unittest.TestCase):

    def test_perm_exact(self):
        self.assertEqual(perm(1, 1, True), 1)
        self.assertEqual(perm(5, 3, True), 60)
        self.assertEqual(perm(12, 10, True), 239500800)

    def test_comb_exact(self):
        self.assertEqual(comb(10, 8, True), 45)
        self.assertEqual(comb(20, 18, True), 190)
        self.assertEqual(comb(60, 58, True), 1770)

    def test_perm_approx(self):
        self.assertAlmostEqual(perm(5, 3, False), 60.0)
        self.assertAlmostEqual(perm(15000, 14998, False), 224970001.0)


    def test_comb_approx(self):
        self.assertAlmostEqual(comb(100, 98, False), 4950.0)
        self.assertAlmostEqual(comb(1000, 998, False), 499500.0)


    def test_perm_invalid_input(self):
        with self.assertRaises(ValueError):  # For k > n, comb raises ValueError
            perm(5, 6, True)
        with self.assertRaises(ValueError):
            perm(5, 6, False)
        with self.assertRaises(ValueError):  # For negative inputs, comb/fact raise ValueError
           perm(-5, 2, True)
           
    def test_comb_invalid_input(self):
        with self.assertRaises(ValueError):
            comb(5, 6, True)
        with self.assertRaises(ValueError):
            comb(5, 6, False)
        with self.assertRaises(ValueError):
            comb(-5, 2, True)


if __name__ == '__main__':
    unittest.main()

