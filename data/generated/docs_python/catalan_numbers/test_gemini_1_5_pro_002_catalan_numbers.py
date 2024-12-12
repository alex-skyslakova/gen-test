import unittest
from catalan_numbers import cat_direct, catR1, catR2

class TestCatalanNumbers(unittest.TestCase):

    def test_cat_direct(self):
        expected_values = [1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440]
        for i, expected in enumerate(expected_values):
            self.assertEqual(cat_direct(i), expected)

    def test_catR1(self):
        expected_values = [1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440]
        for i, expected in enumerate(expected_values):
            self.assertEqual(catR1(i), expected)

    def test_catR2(self):
        expected_values = [1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440]
        for i, expected in enumerate(expected_values):
            self.assertEqual(catR2(i), expected)
