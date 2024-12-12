import unittest
from dot_product import dotp

class TestDotProduct(unittest.TestCase):

    def test_dot_product_same_length(self):
        a = [1, 3, -5]
        b = [4, -2, -1]
        self.assertEqual(dotp(a, b), 3)

    def test_dot_product_different_length(self):
        a = [1, 3, -5]
        b = [4, -2]
        with self.assertRaises(AssertionError) as context:
            dotp(a, b)
        self.assertEqual(str(context.exception), 'Vector sizes must match')

    def test_dot_product_empty_vectors(self):
        a = []
        b = []
        self.assertEqual(dotp(a, b), 0)

    def test_dot_product_single_element_vectors(self):
        a = [2]
        b = [3]
        self.assertEqual(dotp(a, b), 6)

    def test_dot_product_negative_elements(self):
        a = [-1, -2, -3]
        b = [4, 5, 6]
        self.assertEqual(dotp(a, b), -32)

    def test_dot_product_mixed_elements(self):
        a = [1, -2, 3]
        b = [-4, 5, -6]
        self.assertEqual(dotp(a, b), -32)

    def test_dot_product_large_vectors(self):
        a = [i for i in range(1, 1001)]
        b = [i for i in range(1, 1001)]
        self.assertEqual(dotp(a, b), sum(i**2 for i in range(1, 1001)))

if __name__ == '__main__':
    unittest.main()
