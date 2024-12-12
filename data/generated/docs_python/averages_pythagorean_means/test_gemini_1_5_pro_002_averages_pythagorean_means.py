import unittest
from averages_pythagorean_means import amean, gmean, hmean

class TestPythagoreanMeans(unittest.TestCase):

    def test_amean_simple(self):
        self.assertEqual(amean([1, 2, 3]), 2)
        self.assertEqual(amean([1, 1, 1]), 1)
        self.assertEqual(amean([0, 0, 0]), 0)

    def test_gmean_simple(self):
        self.assertEqual(gmean([1, 2, 4]), 2)
        self.assertEqual(gmean([2, 2, 2]), 2)
        self.assertEqual(gmean([1, 1, 1]), 1)

    def test_hmean_simple(self):
        self.assertEqual(hmean([1, 2, 4]), 12/7)
        self.assertEqual(hmean([2, 2, 2]), 2)
        self.assertEqual(hmean([1, 1, 1]), 1)
    
    def test_zero_input(self):
        with self.assertRaises(ZeroDivisionError):
             hmean([0,1,2])
        self.assertEqual(amean([0,1,2]), 1)
        self.assertEqual(gmean([0,1,2]), 0)

    def test_empty_input(self):
        with self.assertRaises(ZeroDivisionError):
            amean([])
        with self.assertRaises(ZeroDivisionError):
            gmean([])
        with self.assertRaises(ZeroDivisionError):
            hmean([])


    def test_example_range(self):
        numbers = range(1, 11)
        a = amean(numbers)
        g = gmean(numbers)
        h = hmean(numbers)
        self.assertGreaterEqual(a, g)
        self.assertGreaterEqual(g, h)

