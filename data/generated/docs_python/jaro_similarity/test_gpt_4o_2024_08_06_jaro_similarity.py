import unittest
from jaro_similarity import jaro

class TestJaroSimilarity(unittest.TestCase):

    def test_exact_match(self):
        self.assertAlmostEqual(jaro("IDENTICAL", "IDENTICAL"), 1.0, places=10)

    def test_no_match(self):
        self.assertAlmostEqual(jaro("ABC", "XYZ"), 0.0, places=10)

    def test_partial_match(self):
        self.assertAlmostEqual(jaro("MARTHA", "MARHTA"), 0.9444444444, places=10)
        self.assertAlmostEqual(jaro("DIXON", "DICKSONX"), 0.7666666667, places=10)
        self.assertAlmostEqual(jaro("JELLYFISH", "SMELLYFISH"), 0.8962962963, places=10)

    def test_empty_strings(self):
        self.assertAlmostEqual(jaro("", ""), 1.0, places=10)
        self.assertAlmostEqual(jaro("NONEMPTY", ""), 0.0, places=10)
        self.assertAlmostEqual(jaro("", "NONEMPTY"), 0.0, places=10)

    def test_single_character(self):
        self.assertAlmostEqual(jaro("A", "A"), 1.0, places=10)
        self.assertAlmostEqual(jaro("A", "B"), 0.0, places=10)

    def test_different_lengths(self):
        self.assertAlmostEqual(jaro("ABC", "ABCD"), 0.9166666667, places=10)
        self.assertAlmostEqual(jaro("ABCD", "ABC"), 0.9166666667, places=10)

if __name__ == '__main__':
    unittest.main()
