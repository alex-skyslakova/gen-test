import unittest
from jacobi_symbol import jacobi

class TestJacobiSymbol(unittest.TestCase):

    def test_jacobi_basic(self):
        # Test cases where the result is 1
        self.assertEqual(jacobi(1, 1), 1)
        self.assertEqual(jacobi(1, 3), 1)
        self.assertEqual(jacobi(1, 5), 1)
        self.assertEqual(jacobi(1, 7), 1)
        self.assertEqual(jacobi(1, 11), 1)
        self.assertEqual(jacobi(1, 13), 1)

        # Test cases where the result is -1
        self.assertEqual(jacobi(2, 3), -1)
        self.assertEqual(jacobi(2, 5), -1)
        self.assertEqual(jacobi(2, 7), 1)
        self.assertEqual(jacobi(2, 11), 1)
        self.assertEqual(jacobi(2, 13), 1)

        # Test cases where the result is 0
        self.assertEqual(jacobi(0, 1), 0)
        self.assertEqual(jacobi(0, 3), 0)
        self.assertEqual(jacobi(0, 5), 0)
        self.assertEqual(jacobi(0, 7), 0)
        self.assertEqual(jacobi(0, 11), 0)
        self.assertEqual(jacobi(0, 13), 0)

    def test_jacobi_negative_values(self):
        # Test cases with negative values for 'a'
        self.assertEqual(jacobi(-1, 3), -1)
        self.assertEqual(jacobi(-1, 5), 1)
        self.assertEqual(jacobi(-1, 7), 1)
        self.assertEqual(jacobi(-1, 11), 1)
        self.assertEqual(jacobi(-1, 13), -1)

    def test_jacobi_large_values(self):
        # Test cases with large values
        self.assertEqual(jacobi(123456789, 987654321), 1)
        self.assertEqual(jacobi(987654321, 123456789), 1)
        self.assertEqual(jacobi(123456789, 987654323), -1)
        self.assertEqual(jacobi(987654323, 123456789), -1)

    def test_jacobi_invalid_n(self):
        # Test cases with invalid 'n' values
        with self.assertRaises(ValueError):
            jacobi(1, 0)
        with self.assertRaises(ValueError):
            jacobi(1, -1)
        with self.assertRaises(ValueError):
            jacobi(1, 2)
        with self.assertRaises(ValueError):
            jacobi(1, 4)
        with self.assertRaises(ValueError):
            jacobi(1, 6)

if __name__ == '__main__':
    unittest.main()
