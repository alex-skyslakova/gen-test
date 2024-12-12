import unittest
from entropy_narcissist import entropy

class TestEntropyFunction(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual(entropy(''), 0.0, "Entropy of an empty string should be 0.")

    def test_single_character_string(self):
        self.assertEqual(entropy('aaaa'), 0.0, "Entropy of a string with a single repeated character should be 0.")

    def test_two_character_string(self):
        self.assertAlmostEqual(entropy('abab'), 1.0, places=5, msg="Entropy of 'abab' should be 1.")

    def test_varied_characters(self):
        self.assertAlmostEqual(entropy('abcabcabc'), 1.58496, places=5, msg="Entropy of 'abcabcabc' should be approximately 1.58496.")

    def test_different_frequencies(self):
        self.assertAlmostEqual(entropy('aab'), 0.918295, places=5, msg="Entropy of 'aab' should be approximately 0.918295.")

    def test_long_string(self):
        long_string = 'a' * 1000 + 'b' * 1000
        self.assertAlmostEqual(entropy(long_string), 1.0, places=5, msg="Entropy of a long string with equal 'a' and 'b' should be 1.")

if __name__ == '__main__':
    unittest.main()
