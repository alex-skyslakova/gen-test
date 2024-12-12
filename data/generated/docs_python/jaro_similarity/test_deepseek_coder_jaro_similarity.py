import unittest
from jaro_similarity import jaro

class TestJaroSimilarity(unittest.TestCase):

    def test_exact_match(self):
        self.assertEqual(jaro("MARTHA", "MARTHA"), 1.0)
        self.assertEqual(jaro("DIXON", "DIXON"), 1.0)
        self.assertEqual(jaro("JELLYFISH", "JELLYFISH"), 1.0)

    def test_no_match(self):
        self.assertEqual(jaro("ABC", "DEF"), 0.0)
        self.assertEqual(jaro("XYZ", "123"), 0.0)

    def test_partial_match(self):
        self.assertAlmostEqual(jaro("MARTHA", "MARHTA"), 0.9444444444, places=10)
        self.assertAlmostEqual(jaro("DIXON", "DICKSONX"), 0.7666666667, places=10)
        self.assertAlmostEqual(jaro("JELLYFISH", "SMELLYFISH"), 0.8962962963, places=10)

    def test_empty_strings(self):
        self.assertEqual(jaro("", ""), 1.0)
        self.assertEqual(jaro("ABC", ""), 0.0)
        self.assertEqual(jaro("", "DEF"), 0.0)

    def test_different_lengths(self):
        self.assertAlmostEqual(jaro("ABCD", "ABC"), 0.8888888889, places=10)
        self.assertAlmostEqual(jaro("ABC", "ABCD"), 0.8888888889, places=10)

    def test_transpositions(self):
        self.assertAlmostEqual(jaro("CRATE", "TRACE"), 0.7333333333, places=10)
        self.assertAlmostEqual(jaro("DWAYNE", "DUANE"), 0.8222222222, places=10)

    def test_case_sensitivity(self):
        self.assertAlmostEqual(jaro("MARTHA", "martha"), 0.0, places=10)
        self.assertAlmostEqual(jaro("DIXON", "dixon"), 0.0, places=10)

if __name__ == '__main__':
    unittest.main()
