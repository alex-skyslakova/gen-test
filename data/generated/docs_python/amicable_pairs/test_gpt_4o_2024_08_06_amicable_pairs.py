import unittest
from amicable_pairs import amicable
from proper_divisors import proper_divs

class TestAmicablePairs(unittest.TestCase):

    def test_amicable_pairs_below_20000(self):
        expected_pairs = [
            (220, 284),
            (1184, 1210),
            (2620, 2924),
            (5020, 5564),
            (6232, 6368),
            (10744, 10856),
            (12285, 14595),
            (17296, 18416)
        ]
        result_pairs = list(amicable(20000))
        self.assertEqual(result_pairs, expected_pairs)

    def test_proper_divisors(self):
        # Test proper divisors for a known number
        self.assertEqual(sorted(proper_divs(220)), [1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110])
        self.assertEqual(sorted(proper_divs(284)), [1, 2, 4, 71, 142])

    def test_no_amicable_pairs_below_100(self):
        result_pairs = list(amicable(100))
        self.assertEqual(result_pairs, [])

    def test_amicable_pair_properties(self):
        # Test that the sum of proper divisors of each number in a pair equals the other number
        for num, divsum in amicable(20000):
            self.assertEqual(sum(proper_divs(num)), divsum)
            self.assertEqual(sum(proper_divs(divsum)), num)

if __name__ == '__main__':
    unittest.main()
