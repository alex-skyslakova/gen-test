import unittest
from collections import Counter
import math

class TestEntropyFunction(unittest.TestCase):

    def test_entropy_empty_string(self):
        # Test entropy of an empty string
        self.assertEqual(entropy(""), 0.0)

    def test_entropy_single_character(self):
        # Test entropy of a string with a single character
        self.assertEqual(entropy("a"), 0.0)

    def test_entropy_uniform_distribution(self):
        # Test entropy of a string with uniform distribution of characters
        self.assertAlmostEqual(entropy("01010101"), 1.0, places=5)

    def test_entropy_non_uniform_distribution(self):
        # Test entropy of a string with non-uniform distribution of characters
        self.assertAlmostEqual(entropy("aaaaabbbbb"), 1.0, places=5)

    def test_entropy_complex_string(self):
        # Test entropy of a more complex string
        self.assertAlmostEqual(entropy("1223334444"), 1.8464393446710154, places=5)

    def test_entropy_special_characters(self):
        # Test entropy of a string with special characters
        self.assertAlmostEqual(entropy("!@#$%^&*()"), 3.0, places=5)

    def test_entropy_large_string(self):
        # Test entropy of a large string
        large_string = "a" * 1000 + "b" * 1000
        self.assertAlmostEqual(entropy(large_string), 1.0, places=5)

def entropy(s):
    p, lns = Counter(s), float(len(s))
    return -sum(count / lns * math.log(count / lns, 2) for count in p.values())

if __name__ == '__main__':
    unittest.main()
