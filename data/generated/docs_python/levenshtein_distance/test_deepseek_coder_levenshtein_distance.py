import unittest

class TestLevenshteinDistance(unittest.TestCase):

    def test_same_string(self):
        self.assertEqual(levenshteinDistance("kitten", "kitten"), 0)

    def test_one_substitution(self):
        self.assertEqual(levenshteinDistance("kitten", "sitten"), 1)

    def test_one_insertion(self):
        self.assertEqual(levenshteinDistance("kitten", "kittens"), 1)

    def test_one_deletion(self):
        self.assertEqual(levenshteinDistance("kitten", "kittn"), 1)

    def test_multiple_operations(self):
        self.assertEqual(levenshteinDistance("kitten", "sitting"), 3)

    def test_different_lengths(self):
        self.assertEqual(levenshteinDistance("rosettacode", "raisethysword"), 8)

    def test_empty_strings(self):
        self.assertEqual(levenshteinDistance("", ""), 0)
        self.assertEqual(levenshteinDistance("kitten", ""), 6)
        self.assertEqual(levenshteinDistance("", "sitting"), 7)

    def test_case_sensitivity(self):
        self.assertEqual(levenshteinDistance("Kitten", "kitten"), 1)

    def test_reversed_strings(self):
        self.assertEqual(levenshteinDistance("kitten", "gnittik"), 6)
        self.assertEqual(levenshteinDistance("rosettacode", "drowhsyetsar"), 8)

if __name__ == '__main__':
    unittest.main()
