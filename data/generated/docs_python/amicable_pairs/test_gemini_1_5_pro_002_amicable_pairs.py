import unittest
from amicable_pairs import amicable, proper_divs

class TestAmicable(unittest.TestCase):

    def test_amicable_small_range(self):
        expected = [(220, 284), (1184, 1210), (2620, 2924), (5020, 5564), (6232, 6368), (10744, 10856), (12285, 14595), (17296, 18416)]
        actual = list(amicable())
        self.assertEqual(actual, expected)
    
    def test_amicable_no_pairs(self):
        # Test a range where no amicable pairs exist.
        self.assertEqual(list(amicable(rangemax=200)), [])

    def test_proper_divs_basic(self):
        self.assertEqual(sorted(proper_divs(220)), [1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110])
        self.assertEqual(sorted(proper_divs(284)), [1, 2, 4, 71, 142])
        self.assertEqual(sorted(proper_divs(1)), []) # Test for 1, which has no proper divisors

    def test_proper_divs_prime(self):
        self.assertEqual(sorted(proper_divs(17)), [1])

    def test_proper_divs_large_number(self):
        self.assertEqual(sum(proper_divs(10000)), 14211)  # Example from the documentation


