import unittest
from amicable_pairs import amicable
from proper_divisors import proper_divs

class TestAmicablePairs(unittest.TestCase):

    def test_amicable_pairs_below_20000(self):
        # Known amicable pairs below 20000
        known_pairs = [
            (220, 284),
            (1184, 1210),
            (2620, 2924),
            (5020, 5564),
            (6232, 6368),
            (10744, 10856),
            (12285, 14595),
            (17296, 18416)
        ]
        
        # Generate amicable pairs using the function
        generated_pairs = list(amicable(20000))
        
        # Check if all known pairs are in the generated pairs
        for pair in known_pairs:
            self.assertIn(pair, generated_pairs, f"Pair {pair} not found in generated pairs")
        
        # Check if the generated pairs match the known pairs
        self.assertEqual(len(generated_pairs), len(known_pairs), "Number of generated pairs does not match known pairs")
    
    def test_proper_divisors_sum(self):
        # Test proper divisors sum for known amicable pairs
        test_cases = [
            (220, 284),
            (1184, 1210),
            (2620, 2924),
            (5020, 5564),
            (6232, 6368),
            (10744, 10856),
            (12285, 14595),
            (17296, 18416)
        ]
        
        for n, m in test_cases:
            self.assertEqual(sum(proper_divs(n)), m, f"Sum of proper divisors of {n} should be {m}")
            self.assertEqual(sum(proper_divs(m)), n, f"Sum of proper divisors of {m} should be {n}")
    
    def test_no_self_amicable(self):
        # Ensure no number is amicable with itself
        for n in range(1, 20001):
            self.assertNotEqual(sum(proper_divs(n)), n, f"Number {n} should not be amicable with itself")

if __name__ == '__main__':
    unittest.main()
