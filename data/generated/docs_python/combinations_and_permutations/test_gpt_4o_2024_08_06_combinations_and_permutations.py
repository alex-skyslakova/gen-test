import unittest
from scipy.special import comb, perm

class TestCombinationsAndPermutations(unittest.TestCase):

    def test_exact_permutations(self):
        # Test exact permutations from 1 to 12
        expected_results = [
            (1, 1), (2, 1), (3, 2), (4, 6), (5, 24),
            (6, 120), (7, 720), (8, 5040), (9, 40320),
            (10, 362880), (11, 3628800), (12, 39916800)
        ]
        for N, expected in expected_results:
            k = max(N-2, 1)
            result = perm(N, k, exact=True)
            self.assertEqual(result, expected, f"Failed for {N}P{k}")

    def test_exact_combinations(self):
        # Test exact combinations from 10 to 60
        expected_results = [
            (10, 36), (20, 171), (30, 406), (40, 703),
            (50, 1225), (60, 1770)
        ]
        for N, expected in expected_results:
            k = N-2
            result = comb(N, k, exact=True)
            self.assertEqual(result, expected, f"Failed for {N}C{k}")

    def test_approximate_permutations(self):
        # Test approximate permutations from 5 to 15000
        test_cases = [5, 15, 150, 1500, 15000]
        for N in test_cases:
            k = N-2
            result = perm(N, k, exact=False)
            self.assertIsInstance(result, float, f"Result is not float for {N}P{k}")

    def test_approximate_combinations(self):
        # Test approximate combinations from 100 to 1000
        test_cases = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000]
        for N in test_cases:
            k = N-2
            result = comb(N, k, exact=False)
            self.assertIsInstance(result, float, f"Result is not float for {N}C{k}")

if __name__ == '__main__':
    unittest.main()
