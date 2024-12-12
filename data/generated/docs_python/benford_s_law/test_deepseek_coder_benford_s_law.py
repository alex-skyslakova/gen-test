import unittest
from collections import Counter
from math import log10
from itertools import islice

# Assuming the functions from the original code are available in the module `benford_s_law`
import benford_s_law

class TestBenfordsLaw(unittest.TestCase):

    def test_fib_generator(self):
        fib_gen = benford_s_law.fib()
        first_10_fibs = list(islice(fib_gen, 10))
        self.assertEqual(first_10_fibs, [1, 1, 2, 3, 5, 8, 13, 21, 34, 55])

    def test_power_of_threes_generator(self):
        threes_gen = benford_s_law.power_of_threes()
        first_10_powers_of_three = list(islice(threes_gen, 10))
        self.assertEqual(first_10_powers_of_three, [1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683])

    def test_heads_function(self):
        test_sequence = [123, 456, 789, 101, 202, 303, 404, 505, 606, 707]
        expected_heads = [1, 4, 7, 1, 2, 3, 4, 5, 6, 7]
        self.assertEqual(list(benford_s_law.heads(test_sequence)), expected_heads)

    def test_show_dist_function(self):
        # Mocking the Counter to simulate the distribution
        def mock_counter(s):
            return Counter({1: 300, 2: 177, 3: 125, 4: 97, 5: 79, 6: 67, 7: 58, 8: 51, 9: 46})
        
        # Patching the Counter with the mock
        with unittest.mock.patch('collections.Counter', side_effect=mock_counter):
            benford_s_law.show_dist("test", range(1000))

    def test_rand1000_generator(self):
        rand_gen = benford_s_law.rand1000()
        first_10_randoms = list(islice(rand_gen, 10))
        self.assertTrue(all(1 <= num <= 9999 for num in first_10_randoms))

    def test_expected_distribution(self):
        expected = [log10(1+1/d) for d in range(1,10)]
        self.assertEqual(len(expected), 9)
        self.assertAlmostEqual(expected[0], log10(2), places=5)
        self.assertAlmostEqual(expected[-1], log10(1.1111111111111112), places=5)

if __name__ == '__main__':
    unittest.main()
