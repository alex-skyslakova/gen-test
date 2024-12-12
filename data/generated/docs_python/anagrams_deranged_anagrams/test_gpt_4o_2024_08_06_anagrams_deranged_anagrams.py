import unittest
from anagrams_deranged_anagrams import find_anagrams, is_deranged, largest_deranged_ana

class TestAnagramsDerangedAnagrams(unittest.TestCase):

    def test_find_anagrams(self):
        words = ['listen', 'silent', 'enlist', 'google', 'gogole', 'abc', 'cab', 'bac']
        expected_anagrams = {
            ('e', 'i', 'l', 'n', 's', 't'): ['listen', 'silent', 'enlist'],
            ('e', 'g', 'g', 'l', 'o', 'o'): ['google', 'gogole'],
            ('a', 'b', 'c'): ['abc', 'cab', 'bac']
        }
        result = find_anagrams(words)
        self.assertEqual(result, expected_anagrams)

    def test_is_deranged(self):
        words = ['listen', 'silent', 'enlist']
        expected_deranged_pairs = [('listen', 'silent'), ('listen', 'enlist'), ('silent', 'enlist')]
        result = is_deranged(words)
        self.assertEqual(result, expected_deranged_pairs)

        words = ['google', 'gogole']
        expected_deranged_pairs = [('google', 'gogole')]
        result = is_deranged(words)
        self.assertEqual(result, expected_deranged_pairs)

        words = ['abc', 'cab', 'bac']
        expected_deranged_pairs = [('abc', 'cab'), ('abc', 'bac'), ('cab', 'bac')]
        result = is_deranged(words)
        self.assertEqual(result, expected_deranged_pairs)

    def test_largest_deranged_ana(self):
        anagrams = {
            ('e', 'i', 'l', 'n', 's', 't'): ['listen', 'silent', 'enlist'],
            ('e', 'g', 'g', 'l', 'o', 'o'): ['google', 'gogole'],
            ('a', 'b', 'c'): ['abc', 'cab', 'bac']
        }
        expected_largest_deranged = [('listen', 'silent'), ('listen', 'enlist'), ('silent', 'enlist')]
        result = largest_deranged_ana(anagrams)
        self.assertEqual(result, expected_largest_deranged)

if __name__ == '__main__':
    unittest.main()
