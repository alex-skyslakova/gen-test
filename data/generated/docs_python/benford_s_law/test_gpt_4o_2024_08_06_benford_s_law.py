import unittest
from collections import Counter
from math import log10
from itertools import islice, count
from random import randint

# Assuming the functions from the provided code are imported here:
# fib, power_of_threes, heads, show_dist, rand1000

class TestBenfordsLaw(unittest.TestCase):

    def setUp(self):
        # Expected distribution according to Benford's Law
        self.expected = [log10(1 + 1/d) for d in range(1, 10)]

    def calculate_distribution(self, sequence, num_samples):
        # Calculate the distribution of first digits in the sequence
        first_digits = list(islice(heads(sequence), num_samples))
        counter = Counter(first_digits)
        total = sum(counter.values())
        distribution = [counter[d] / total for d in range(1, 10)]
        return distribution

    def test_fibonacci_distribution(self):
        # Test the distribution of the first 1000 Fibonacci numbers
        actual_distribution = self.calculate_distribution(fib(), 1000)
        for actual, expected in zip(actual_distribution, self.expected):
            self.assertAlmostEqual(actual, expected, delta=0.05)

    def test_power_of_threes_distribution(self):
        # Test the distribution of the first 1000 powers of three
        actual_distribution = self.calculate_distribution(power_of_threes(), 1000)
        for actual, expected in zip(actual_distribution, self.expected):
            self.assertAlmostEqual(actual, expected, delta=0.05)

    def test_random_distribution(self):
        # Test the distribution of 10000 random numbers
        actual_distribution = self.calculate_distribution(rand1000(), 10000)
        # Random distribution should not follow Benford's Law closely
        # So we expect a larger deviation
        deviations = [abs(actual - expected) for actual, expected in zip(actual_distribution, self.expected)]
        self.assertTrue(any(deviation > 0.1 for deviation in deviations))

if __name__ == '__main__':
    unittest.main()
