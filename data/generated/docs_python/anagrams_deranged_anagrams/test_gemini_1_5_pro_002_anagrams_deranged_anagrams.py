import unittest
from unittest.mock import patch, mock_open
from collections import defaultdict
from itertools import combinations

# Code under test (importing specific functions for testing)
from anagrams_deranged_anagrams import getwords, find_anagrams, is_deranged, largest_deranged_ana


class TestAnagramsDerangedAnagrams(unittest.TestCase):

    @patch('urllib.request.urlopen')
    def test_getwords(self, mock_urlopen):
        mock_urlopen.return_value.read.return_value = b"word1\nword2\nword3\nword1\n"
        words = getwords()
        self.assertEqual(words, ['word1', 'word2', 'word3'])

    def test_find_anagrams(self):
        words = ['listen', 'silent', 'enlist', 'tinsel', 'hello', 'world']
        expected_anagrams = {
            ('e', 'i', 'l', 'n', 's', 't'): ['listen', 'silent', 'enlist', 'tinsel'],
            ('h', 'e', 'l', 'l', 'o'): ['hello'],
            ('d', 'l', 'o', 'r', 'w'): ['world']
        }
        self.assertEqual(find_anagrams(words), expected_anagrams)

    def test_is_deranged(self):
        words = ['abc', 'acb', 'bac', 'bca', 'cab', 'cba']
        expected_deranged = [('abc', 'bac'), ('abc', 'bca'), ('abc', 'cab'), ('abc', 'cba'), ('acb', 'bac'), ('acb', 'bca'), ('acb', 'cab'), ('acb', 'cba'), ('bac', 'bca'), ('bac', 'cab'), ('bac', 'cba'), ('bca', 'cab'), ('bca', 'cba'), ('cab', 'cba')]

        # Convert tuples to sets for order-insensitive comparison
        actual_deranged_sets = [set(pair) for pair in is_deranged(words)]
        expected_deranged_sets = [set(pair) for pair in expected_deranged]
        self.assertEqual(sorted(actual_deranged_sets), sorted(expected_deranged_sets))



        words_no_derangements = ['abc', 'abc', 'abd']
        self.assertEqual(is_deranged(words_no_derangements), [('abc', 'abd')])

        single_word = ['abc']
        self.assertEqual(is_deranged(single_word), [])


    def test_largest_deranged_ana(self):
        anagrams = {
            ('a', 'e', 'i', 'l', 'n', 's', 't'): ['tinsels', 'listen'],
            ('e', 'i', 'l', 'n', 's', 't'): ['listen', 'silent', 'enlist', 'tinsel'],
            ('a', 'b', 'c'): ['abc', 'bac'],
            ('a', 'b'): ['ab', 'ba']
        }
        expected_largest = [('listen', 'silent'),('listen', 'enlist'), ('listen', 'tinsel'), ('silent', 'enlist'), ('silent', 'tinsel'), ('enlist', 'tinsel')]
        actual_largest = largest_deranged_ana(anagrams)

        # Convert to sets of sets for order-insensitive comparison
        actual_largest_sets = [set(pair) for pair in actual_largest]
        expected_largest_sets = [set(pair) for pair in expected_largest]
        self.assertTrue(all(s in expected_largest_sets for s in actual_largest_sets))
        self.assertTrue(len(actual_largest) > 0 and len(actual_largest[0])==2) # Check for at least one deranged pair

        anagrams_no_derangements = {
            ('a', 'b', 'c'): ['abc', 'abc'],
            ('a', 'b'): ['ab', 'ba']
        }
        self.assertEqual(largest_deranged_ana(anagrams_no_derangements), [('ab', 'ba')])


if __name__ == '__main__':
    unittest.main()
