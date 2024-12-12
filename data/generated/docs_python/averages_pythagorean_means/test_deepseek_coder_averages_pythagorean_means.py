import unittest
from averages_pythagorean_means import amean, gmean, hmean

class TestPythagoreanMeans(unittest.TestCase):

    def test_amean(self):
        # Test case for arithmetic mean
        self.assertEqual(amean(range(1, 11)), 5.5)
        self.assertEqual(amean([1, 2, 3, 4, 5]), 3.0)
        self.assertEqual(amean([10, 20, 30]), 20.0)

    def test_gmean(self):
        # Test case for geometric mean
        self.assertAlmostEqual(gmean(range(1, 11)), 4.528728688116765, places=10)
        self.assertAlmostEqual(gmean([1, 2, 3, 4, 5]), 2.605171084697352, places=10)
        self.assertAlmostEqual(gmean([10, 20, 30]), 18.171205928321397, places=10)

    def test_hmean(self):
        # Test case for harmonic mean
        self.assertAlmostEqual(hmean(range(1, 11)), 3.414171521474055, places=10)
        self.assertAlmostEqual(hmean([1, 2, 3, 4, 5]), 2.18978102189781, places=10)
        self.assertAlmostEqual(hmean([10, 20, 30]), 16.363636363636363, places=10)

    def test_inequality(self):
        # Test the inequality A >= G >= H
        numbers = range(1, 11)
        a = amean(numbers)
        g = gmean(numbers)
        h = hmean(numbers)
        self.assertGreaterEqual(a, g)
        self.assertGreaterEqual(g, h)

if __name__ == '__main__':
    unittest.main()
