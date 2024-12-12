import unittest
from itertools import islice, count
from collections import Counter
from math import log10
from random import randint

expected = [log10(1 + 1 / d) for d in range(1, 10)]


def fib():
    a, b = 1, 1
    while True:
        yield a
        a, b = b, a + b


# powers of 3 as a test sequence
def power_of_threes():
    return (3**k for k in count(0))


def heads(s):
    for a in s:
        yield int(str(a)[0])


def show_dist(title, s):
    c = Counter(s)
    size = sum(c.values())
    res = [c[d] / size for d in range(1, 10)]

    # Returning the result instead of printing
    return res




class TestBenford(unittest.TestCase):

    def test_fib_distribution(self):
        fib_dist = show_dist("fibbed", islice(heads(fib()), 1000))
        self.assertEqual(len(fib_dist), 9) # Check for correct number of digits
        # Add assertions for specific values or ranges if needed.  Example for digit '1':
        # self.assertAlmostEqual(fib_dist[0], expected[0], delta=0.05) # Allow some deviation


    def test_threes_distribution(self):
        threes_dist = show_dist("threes", islice(heads(power_of_threes()), 1000))
        self.assertEqual(len(threes_dist), 9)
        # Add assertions for specific values or ranges if needed



    def test_random_distribution(self):
        rand_dist = show_dist("random", islice(heads(rand1000()), 10000))
        self.assertEqual(len(rand_dist), 9)
        # Add assertions that verify this distribution deviates more from Benford's law, e.g.,
        # for i in range(9):
        #    self.assertGreater(abs(rand_dist[i] - expected[i]), 0.02) # Example threshold


    def test_heads_function_empty(self):
        self.assertEqual(list(heads([])), [])

    def test_heads_function_single_digit(self):
        self.assertEqual(list(heads([1])), [1])

    def test_heads_function_multiple_digits(self):
        self.assertEqual(list(heads([123, 456, 789])), [1, 4, 7])

    def test_heads_function_zero_leading(self):
        self.assertEqual(list(heads([012, 003])), [1, 3])



if __name__ == '__main__':
    unittest.main()

