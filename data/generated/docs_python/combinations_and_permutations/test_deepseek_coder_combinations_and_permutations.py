import unittest
from scipy.misc import factorial as fact
from scipy.misc import comb
from combinations_and_permutations import perm

class TestCombinationsAndPermutations(unittest.TestCase):

    def test_exact_permutations(self):
        # Test exact permutations for small values
        self.assertEqual(perm(1, 1, exact=True), 1)
        self.assertEqual(perm(2, 1, exact=True), 2)
        self.assertEqual(perm(3, 2, exact=True), 6)
        self.assertEqual(perm(4, 2, exact=True), 12)
        self.assertEqual(perm(5, 3, exact=True), 60)
        self.assertEqual(perm(6, 4, exact=True), 360)
        self.assertEqual(perm(7, 5, exact=True), 2520)
        self.assertEqual(perm(8, 6, exact=True), 20160)
        self.assertEqual(perm(9, 7, exact=True), 181440)
        self.assertEqual(perm(10, 8, exact=True), 1814400)
        self.assertEqual(perm(11, 9, exact=True), 19958400)
        self.assertEqual(perm(12, 10, exact=True), 239500800)

    def test_exact_combinations(self):
        # Test exact combinations for small values
        self.assertEqual(comb(10, 8, exact=True), 45)
        self.assertEqual(comb(20, 18, exact=True), 190)
        self.assertEqual(comb(30, 28, exact=True), 435)
        self.assertEqual(comb(40, 38, exact=True), 780)
        self.assertEqual(comb(50, 48, exact=True), 1225)
        self.assertEqual(comb(60, 58, exact=True), 1770)

    def test_approximate_permutations(self):
        # Test approximate permutations for large values
        self.assertAlmostEqual(perm(5, 3, exact=False), 60.0, places=5)
        self.assertAlmostEqual(perm(15, 13, exact=False), 653837184000.0, places=5)
        self.assertAlmostEqual(perm(150, 148, exact=False), 1.77266368843e+304, places=5)
        self.assertAlmostEqual(perm(1500, 1498, exact=False), float('inf'), places=5)  # Large value approximation
        self.assertAlmostEqual(perm(15000, 14998, exact=False), float('inf'), places=5)  # Large value approximation

    def test_approximate_combinations(self):
        # Test approximate combinations for large values
        self.assertAlmostEqual(comb(100, 98, exact=False), 4950.0, places=5)
        self.assertAlmostEqual(comb(200, 198, exact=False), 19900.0, places=5)
        self.assertAlmostEqual(comb(300, 298, exact=False), 44850.0, places=5)
        self.assertAlmostEqual(comb(400, 398, exact=False), 79800.0, places=5)
        self.assertAlmostEqual(comb(500, 498, exact=False), 124750.0, places=5)
        self.assertAlmostEqual(comb(600, 598, exact=False), 179700.0, places=5)
        self.assertAlmostEqual(comb(700, 698, exact=False), 244650.0, places=5)
        self.assertAlmostEqual(comb(800, 798, exact=False), 319600.0, places=5)
        self.assertAlmostEqual(comb(900, 898, exact=False), 404550.0, places=5)
        self.assertAlmostEqual(comb(1000, 998, exact=False), 499500.0, places=5)

if __name__ == '__main__':
    unittest.main()
