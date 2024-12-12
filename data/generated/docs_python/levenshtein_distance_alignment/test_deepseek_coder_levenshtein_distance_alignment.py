import unittest
from levenshtein_distance_alignment import levenshtein

class TestLevenshteinAlignment(unittest.TestCase):

    def test_simple_alignment(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("place", "palace")
        self.assertEqual(cm.output, ["INFO:root:P-LACE"])

    def test_complex_alignment(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("rosettacode", "raisethysword")
        self.assertEqual(cm.output, ["INFO:root:RO-SETT-ACODE"])

    def test_identical_strings(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("hello", "hello")
        self.assertEqual(cm.output, ["INFO:root:HELLO"])

    def test_empty_string(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("", "test")
        self.assertEqual(cm.output, ["INFO:root:----TEST"])

    def test_both_empty_strings(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("", "")
        self.assertEqual(cm.output, ["INFO:root:"])

    def test_single_character_strings(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("a", "b")
        self.assertEqual(cm.output, ["INFO:root:A-B"])

    def test_insertion_only(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("abc", "abxbc")
        self.assertEqual(cm.output, ["INFO:root:AB-XB-C"])

    def test_deletion_only(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("abc", "ac")
        self.assertEqual(cm.output, ["INFO:root:A-BC"])

    def test_substitution_only(self):
        with self.assertLogs(level='INFO') as cm:
            levenshtein("abc", "axc")
        self.assertEqual(cm.output, ["INFO:root:A-XC"])

if __name__ == '__main__':
    unittest.main()
