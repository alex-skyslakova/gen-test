import unittest

class TestJacobiSymbol(unittest.TestCase):
    def test_jacobi_symbol_basic_cases(self):
        # Basic cases
        self.assertEqual(jacobi(1, 3), 1)
        self.assertEqual(jacobi(2, 3), -1)
        self.assertEqual(jacobi(3, 3), 0)
        self.assertEqual(jacobi(4, 3), 1)
        self.assertEqual(jacobi(5, 3), -1)

    def test_jacobi_symbol_with_prime_n(self):
        # n is prime, should match Legendre symbol
        self.assertEqual(jacobi(2, 7), 1)
        self.assertEqual(jacobi(3, 7), -1)
        self.assertEqual(jacobi(4, 7), 1)
        self.assertEqual(jacobi(5, 7), -1)
        self.assertEqual(jacobi(6, 7), -1)

    def test_jacobi_symbol_with_composite_n(self):
        # n is composite
        self.assertEqual(jacobi(2, 9), -1)
        self.assertEqual(jacobi(3, 9), 0)
        self.assertEqual(jacobi(5, 9), -1)
        self.assertEqual(jacobi(7, 9), -1)

    def test_jacobi_symbol_edge_cases(self):
        # Edge cases
        self.assertEqual(jacobi(0, 1), 0)
        self.assertEqual(jacobi(0, 3), 0)
        self.assertEqual(jacobi(1, 1), 1)
        self.assertEqual(jacobi(1, 9), 1)

    def test_jacobi_symbol_invalid_inputs(self):
        # Invalid inputs
        with self.assertRaises(ValueError):
            jacobi(1, 0)
        with self.assertRaises(ValueError):
            jacobi(1, -3)
        with self.assertRaises(ValueError):
            jacobi(1, 2)

if __name__ == '__main__':
    unittest.main()
