import unittest
from averages_pythagorean_means import amean, gmean, hmean

class TestPythagoreanMeans(unittest.TestCase):
    
    def setUp(self):
        self.numbers = list(range(1, 11))  # 1 through 10

    def test_amean(self):
        expected_amean = sum(self.numbers) / len(self.numbers)
        self.assertAlmostEqual(amean(self.numbers), expected_amean, places=7)

    def test_gmean(self):
        product = 1
        for num in self.numbers:
            product *= num
        expected_gmean = product**(1 / len(self.numbers))
        self.assertAlmostEqual(gmean(self.numbers), expected_gmean, places=7)

    def test_hmean(self):
        expected_hmean = len(self.numbers) / sum(1 / n for n in self.numbers)
        self.assertAlmostEqual(hmean(self.numbers), expected_hmean, places=7)

    def test_inequality(self):
        a = amean(self.numbers)
        g = gmean(self.numbers)
        h = hmean(self.numbers)
        self.assertTrue(a >= g >= h)

if __name__ == '__main__':
    unittest.main()
