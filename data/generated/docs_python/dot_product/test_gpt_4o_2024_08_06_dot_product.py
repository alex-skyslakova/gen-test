import unittest

class TestDotProduct(unittest.TestCase):

    def test_positive_numbers(self):
        a = [1, 2, 3]
        b = [4, 5, 6]
        result = dotp(a, b)
        expected = 32  # 1*4 + 2*5 + 3*6
        self.assertEqual(result, expected)

    def test_negative_numbers(self):
        a = [-1, -2, -3]
        b = [-4, -5, -6]
        result = dotp(a, b)
        expected = 32  # (-1)*(-4) + (-2)*(-5) + (-3)*(-6)
        self.assertEqual(result, expected)

    def test_mixed_numbers(self):
        a = [1, -2, 3]
        b = [-4, 5, -6]
        result = dotp(a, b)
        expected = -28  # 1*(-4) + (-2)*5 + 3*(-6)
        self.assertEqual(result, expected)

    def test_zero_vector(self):
        a = [0, 0, 0]
        b = [1, 2, 3]
        result = dotp(a, b)
        expected = 0  # 0*1 + 0*2 + 0*3
        self.assertEqual(result, expected)

    def test_single_element_vectors(self):
        a = [3]
        b = [4]
        result = dotp(a, b)
        expected = 12  # 3*4
        self.assertEqual(result, expected)

    def test_different_lengths(self):
        a = [1, 2]
        b = [3, 4, 5]
        with self.assertRaises(AssertionError):
            dotp(a, b)

    def test_empty_vectors(self):
        a = []
        b = []
        result = dotp(a, b)
        expected = 0  # No elements to multiply
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
