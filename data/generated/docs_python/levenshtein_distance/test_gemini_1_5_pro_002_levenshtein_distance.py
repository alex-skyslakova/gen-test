import unittest
from levenshtein_distance import levenshteinDistance

class TestLevenshteinDistance(unittest.TestCase):

    def test_kitten_sitting(self):
        self.assertEqual(levenshteinDistance("kitten", "sitting"), 3)

    def test_rosettacode_raisethysword(self):
        self.assertEqual(levenshteinDistance("rosettacode", "raisethysword"), 8)

    def test_empty_strings(self):
        self.assertEqual(levenshteinDistance("", ""), 0)

    def test_empty_string_non_empty(self):
        self.assertEqual(levenshteinDistance("", "hello"), 5)
        self.assertEqual(levenshteinDistance("world", ""), 5)

    def test_same_strings(self):
        self.assertEqual(levenshteinDistance("hello", "hello"), 0)

    def test_reversed_strings(self):
        str1 = "abcdefg"
        str2 = "gfedcba"
        self.assertEqual(levenshteinDistance(str1, str2), levenshteinDistance(str1[::-1], str2[::-1]))
        
    def test_one_char_diff(self):
        self.assertEqual(levenshteinDistance("abc", "abd"), 1)

    def test_all_different_chars(self):
        self.assertEqual(levenshteinDistance("abc", "def"), 3)

    def test_substring(self):
        self.assertEqual(levenshteinDistance("apple", "pineapple"), 4)


if __name__ == '__main__':
    unittest.main()
