import unittest
from jacobi_symbol import jacobi

class TestJacobi(unittest.TestCase):

    def test_invalid_n(self):
        with self.assertRaises(ValueError):
            jacobi(1, 0)
        with self.assertRaises(ValueError):
            jacobi(1, -1)
        with self.assertRaises(ValueError):
            jacobi(1, 4)

    def test_a_zero(self):
        self.assertEqual(jacobi(0, 1), 0)
        self.assertEqual(jacobi(0, 3), 0)
        self.assertEqual(jacobi(0, 5), 0)

    def test_a_one(self):
        self.assertEqual(jacobi(1, 1), 1)
        self.assertEqual(jacobi(1, 3), 1)
        self.assertEqual(jacobi(1, 5), 1)
        self.assertEqual(jacobi(1, 9), 1)


    def test_simple_cases(self):
        self.assertEqual(jacobi(2, 3), -1)
        self.assertEqual(jacobi(2, 5), -1)
        self.assertEqual(jacobi(2, 7), 1)
        self.assertEqual(jacobi(3, 5), -1)
        self.assertEqual(jacobi(4, 5), 1)

    def test_larger_numbers(self):
        self.assertEqual(jacobi(1001, 9907), -1)
        self.assertEqual(jacobi(19, 45), 1)
        self.assertEqual(jacobi(8, 21), -1)
        self.assertEqual(jacobi(20, 21), 1)

    def test_quadratic_residue(self):
        self.assertEqual(jacobi(4, 7), 1) # 4 is a quadratic residue mod 7 (2^2 = 4)
        self.assertEqual(jacobi(2, 7), 1) # 2 is a quadratic residue mod 7 (3^2 = 9 ≡ 2)
        self.assertEqual(jacobi(6, 11), -1) # 6 is not a quadratic residue mod 11

    def test_negative_a(self):
        self.assertEqual(jacobi(-1, 3), -1)
        self.assertEqual(jacobi(-1, 5), 1)
        self.assertEqual(jacobi(-2, 7), -1)

    def test_large_a(self):
        self.assertEqual(jacobi(1001, 3), 1)  # 1001 % 3 = 1
        self.assertEqual(jacobi(123456, 17), jacobi(123456 % 17, 17))

    def test_law_of_quadratic_reciprocity(self):
        p = 11
        q = 19
        self.assertEqual(jacobi(p, q) * jacobi(q, p), (-1)**((p-1)//2 * (q-1)//2))
