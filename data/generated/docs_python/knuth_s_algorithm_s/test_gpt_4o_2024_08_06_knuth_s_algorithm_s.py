import unittest
from collections import Counter
from random import seed

# Assuming the function s_of_n_creator is imported from knuth_s_algorithm_s.py

class TestKnuthsAlgorithmS(unittest.TestCase):
    
    def test_single_run_sample(self):
        # Test a single run of the algorithm
        s_of_n = s_of_n_creator(3)
        items = range(10)
        sample = None
        for item in items:
            sample = s_of_n(item)
        self.assertEqual(len(sample), 3)
        self.assertTrue(all(isinstance(x, int) and 0 <= x <= 9 for x in sample))

    def test_sample_distribution(self):
        # Test the distribution of the samples over many runs
        seed(0)  # Set seed for reproducibility
        bin = [0] * 10
        items = range(10)
        trials = 100000
        for trial in range(trials):
            s_of_n = s_of_n_creator(3)
            for item in items:
                sample = s_of_n(item)
            for s in sample:
                bin[s] += 1
        
        # Check that each item has been sampled approximately equally
        expected_frequency = trials * 3 / 10
        tolerance = expected_frequency * 0.05  # 5% tolerance
        for count in bin:
            self.assertTrue(abs(count - expected_frequency) < tolerance)

    def test_sample_uniqueness(self):
        # Test that the sample contains unique items
        s_of_n = s_of_n_creator(3)
        items = range(10)
        sample = None
        for item in items:
            sample = s_of_n(item)
        self.assertEqual(len(sample), len(set(sample)))

    def test_sample_size(self):
        # Test that the sample size does not exceed n
        n = 3
        s_of_n = s_of_n_creator(n)
        items = range(10)
        for item in items:
            sample = s_of_n(item)
            self.assertLessEqual(len(sample), n)

if __name__ == '__main__':
    unittest.main()
