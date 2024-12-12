import unittest
from random import randrange
from collections import Counter

def s_of_n_creator(n):
    sample, i = [], 0
    def s_of_n(item):
        nonlocal i
        i += 1
        if i <= n:
            # Keep first n items
            sample.append(item)
        elif randrange(i) < n:
            # Keep item
            sample[randrange(n)] = item
        return sample
    return s_of_n

class TestKnuthSAlgorithmS(unittest.TestCase):

    def test_initial_sample_size(self):
        s_of_n = s_of_n_creator(3)
        for item in range(3):
            sample = s_of_n(item)
        self.assertEqual(len(sample), 3)

    def test_sample_size_after_n_items(self):
        s_of_n = s_of_n_creator(3)
        for item in range(10):
            sample = s_of_n(item)
        self.assertEqual(len(sample), 3)

    def test_sample_contains_items(self):
        s_of_n = s_of_n_creator(3)
        items = list(range(10))
        for item in items:
            sample = s_of_n(item)
        self.assertTrue(all(item in sample for item in items[:3]))

    def test_frequency_distribution(self):
        n = 3
        trials = 100000
        items = list(range(10))
        bin = Counter()

        for _ in range(trials):
            s_of_n = s_of_n_creator(n)
            for item in items:
                sample = s_of_n(item)
            for s in sample:
                bin[s] += 1

        expected_frequency = trials * n / len(items)
        tolerance = expected_frequency * 0.1  # 10% tolerance

        for item in items:
            self.assertAlmostEqual(bin[item], expected_frequency, delta=tolerance)

if __name__ == '__main__':
    unittest.main()
