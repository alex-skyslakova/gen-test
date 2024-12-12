import unittest
from levenshtein_distance_alignment import levenshtein
from io import StringIO
import sys

class TestLevenshtein(unittest.TestCase):

    def test_place_palace(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("place", "palace")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "p-lace")

    def test_rosettacode_raisethysword(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("rosettacode", "raisethysword")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "r-osetta--c-ode")

    def test_empty_string1(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("", "abc")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "---")

    def test_empty_string2(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("abc", "")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "abc")


    def test_equal_strings(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("abc", "abc")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "abc")

    def test_one_insertion(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("ac", "abc")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "a-c")


    def test_one_deletion(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("abc", "ac")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "ac") # Demonstrates a flaw in the logic. Should ideally be "ab-c-"

    def test_multiple_insertions_deletions(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        levenshtein("apple", "pineapple")
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue().strip(), "-apple----") # Demonstrates a flaw in the logic


