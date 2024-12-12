import unittest
from catalan_numbers import cat_direct, catR1, catR2

class TestCatalanNumbers(unittest.TestCase):
    
    def setUp(self):
        # Precomputed first 15 Catalan numbers
        self.expected_catalan_numbers = [
            1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 
            16796, 58786, 208012, 742900, 2674440
        ]

    def test_cat_direct(self):
        for n, expected in enumerate(self.expected_catalan_numbers):
            with self.subTest(n=n):
                self.assertEqual(cat_direct(n), expected)

    def test_catR1(self):
        for n, expected in enumerate(self.expected_catalan_numbers):
            with self.subTest(n=n):
                self.assertEqual(catR1(n), expected)

    def test_catR2(self):
        for n, expected in enumerate(self.expected_catalan_numbers):
            with self.subTest(n=n):
                self.assertEqual(catR2(n), expected)

if __name__ == '__main__':
    unittest.main()
