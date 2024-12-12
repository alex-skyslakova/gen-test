import unittest
from jaro_similarity import jaro

class TestJaro(unittest.TestCase):

    def test_empty_strings(self):
        self.assertEqual(jaro("", ""), 1)

    def test_equal_strings(self):
        self.assertEqual(jaro("hello", "hello"), 1)

    def test_different_strings(self):
        self.assertAlmostEqual(jaro("MARTHA", "MARHTA"), 0.9444444444, places=10)
        self.assertAlmostEqual(jaro("DIXON", "DICKSONX"), 0.7666666667, places=10)
        self.assertAlmostEqual(jaro("JELLYFISH", "SMELLYFISH"), 0.8962962963, places=10)

    def test_one_empty_string(self):
        self.assertEqual(jaro("hello", ""), 0)
        self.assertEqual(jaro("", "hello"), 0)
    
    def test_no_matching_characters(self):
        self.assertEqual(jaro("abc", "xyz"), 0)

    def test_transpositions(self):
        self.assertAlmostEqual(jaro("abc", "acb"), 0.9444444444, places=10)


if __name__ == '__main__':
    unittest.main()
