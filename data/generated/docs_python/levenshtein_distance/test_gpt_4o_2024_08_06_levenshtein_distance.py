import unittest
from levenshtein_distance import levenshteinDistance

class TestLevenshteinDistance(unittest.TestCase):

    def test_kitten_sitting(self):
        self.assertEqual(levenshteinDistance("kitten", "sitting"), 3)

    def test_rosettacode_raisethysword(self):
        self.assertEqual(levenshteinDistance("rosettacode", "raisethysword"), 8)

    def test_empty_strings(self):
        self.assertEqual(levenshteinDistance("", ""), 0)

    def test_one_empty_string(self):
        self.assertEqual(levenshteinDistance("abc", ""), 3)
        self.assertEqual(levenshteinDistance("", "abc"), 3)

    def test_same_strings(self):
        self.assertEqual(levenshteinDistance("same", "same"), 0)

    def test_single_character_difference(self):
        self.assertEqual(levenshteinDistance("a", "b"), 1)

    def test_reversed_strings(self):
        self.assertEqual(levenshteinDistance("abc", "cba"), 2)

    def test_case_sensitivity(self):
        self.assertEqual(levenshteinDistance("abc", "ABC"), 3)

    def test_long_strings(self):
        str1 = "a" * 1000
        str2 = "a" * 999 + "b"
        self.assertEqual(levenshteinDistance(str1, str2), 1)

if __name__ == '__main__':
    unittest.main()
