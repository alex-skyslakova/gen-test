import unittest
from dot_product import dotp

class TestDotProduct(unittest.TestCase):

    def test_matching_lengths(self):
        a = [1, 3, -5]
        b = [4, -2, -1]
        self.assertEqual(dotp(a, b), 3)

    def test_empty_vectors(self):
        a = []
        b = []
        self.assertEqual(dotp(a, b), 0)

    def test_different_lengths(self):
        a = [1, 2, 3]
        b = [4, 5]
        with self.assertRaises(AssertionError) as context:
            dotp(a, b)
        self.assertEqual(str(context.exception), 'Vector sizes must match')

    def test_longer_vectors(self):
        a = [1, 2, 3, 4, 5]
        b = [6, 7, 8, 9, 10]
        self.assertEqual(dotp(a, b), 130)

    def test_zero_vector(self):
        a = [1, 2, 3]
        b = [0, 0, 0]
        self.assertEqual(dotp(a, b), 0)

    def test_floating_point_numbers(self):
        a = [1.5, 2.5, 3.5]
        b = [4.5, 5.5, 6.5]
        self.assertEqual(dotp(a, b), 42.25)
