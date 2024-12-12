import unittest
from io import StringIO
import sys
from levenshtein_distance_alignment import levenshtein

class TestLevenshteinAlignment(unittest.TestCase):

    def setUp(self):
        # Redirect stdout to capture print statements
        self.held, sys.stdout = sys.stdout, StringIO()

    def tearDown(self):
        # Restore stdout
        sys.stdout = self.held

    def test_alignment_place_palace(self):
        levenshtein("place", "palace")
        output = sys.stdout.getvalue().strip()
        expected_output = "P-LACE"
        self.assertEqual(output, expected_output)

    def test_alignment_rosettacode_raisethysword(self):
        levenshtein("rosettacode", "raisethysword")
        output = sys.stdout.getvalue().strip()
        expected_output = "R--OSETTACODE"
        self.assertEqual(output, expected_output)

    def test_alignment_empty_strings(self):
        levenshtein("", "")
        output = sys.stdout.getvalue().strip()
        expected_output = ""
        self.assertEqual(output, expected_output)

    def test_alignment_one_empty_string(self):
        levenshtein("abc", "")
        output = sys.stdout.getvalue().strip()
        expected_output = "---"
        self.assertEqual(output, expected_output)

        sys.stdout = StringIO()  # Reset stdout capture
        levenshtein("", "abc")
        output = sys.stdout.getvalue().strip()
        expected_output = "abc"
        self.assertEqual(output, expected_output)

    def test_alignment_identical_strings(self):
        levenshtein("identical", "identical")
        output = sys.stdout.getvalue().strip()
        expected_output = "identical"
        self.assertEqual(output, expected_output)

    def test_alignment_completely_different_strings(self):
        levenshtein("abc", "xyz")
        output = sys.stdout.getvalue().strip()
        expected_output = "---"
        self.assertEqual(output, expected_output)

    def test_alignment_partial_overlap(self):
        levenshtein("abcde", "abxyz")
        output = sys.stdout.getvalue().strip()
        expected_output = "ab---"
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()
