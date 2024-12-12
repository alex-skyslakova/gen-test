import unittest
from ethiopian_multiplication import halve, double, even, ethiopian

class TestEthiopianMultiplication(unittest.TestCase):

    def test_halve(self):
        self.assertEqual(halve(1), 0)
        self.assertEqual(halve(2), 1)
        self.assertEqual(halve(3), 1)
        self.assertEqual(halve(4), 2)
        self.assertEqual(halve(5), 2)
        self.assertEqual(halve(6), 3)
        self.assertEqual(halve(7), 3)
        self.assertEqual(halve(8), 4)
        self.assertEqual(halve(9), 4)
        self.assertEqual(halve(10), 5)

    def test_double(self):
        self.assertEqual(double(1), 2)
        self.assertEqual(double(2), 4)
        self.assertEqual(double(3), 6)
        self.assertEqual(double(4), 8)
        self.assertEqual(double(5), 10)
        self.assertEqual(double(6), 12)
        self.assertEqual(double(7), 14)
        self.assertEqual(double(8), 16)
        self.assertEqual(double(9), 18)
        self.assertEqual(double(10), 20)

    def test_even(self):
        self.assertTrue(even(2))
        self.assertTrue(even(4))
        self.assertTrue(even(6))
        self.assertTrue(even(8))
        self.assertTrue(even(10))
        self.assertFalse(even(1))
        self.assertFalse(even(3))
        self.assertFalse(even(5))
        self.assertFalse(even(7))
        self.assertFalse(even(9))

    def test_ethiopian_multiplication(self):
        self.assertEqual(ethiopian(17, 34), 578)
        self.assertEqual(ethiopian(1, 1), 1)
        self.assertEqual(ethiopian(2, 2), 4)
        self.assertEqual(ethiopian(3, 3), 9)
        self.assertEqual(ethiopian(4, 4), 16)
        self.assertEqual(ethiopian(5, 5), 25)
        self.assertEqual(ethiopian(6, 6), 36)
        self.assertEqual(ethiopian(7, 7), 49)
        self.assertEqual(ethiopian(8, 8), 64)
        self.assertEqual(ethiopian(9, 9), 81)
        self.assertEqual(ethiopian(10, 10), 100)
        self.assertEqual(ethiopian(0, 10), 0)
        self.assertEqual(ethiopian(10, 0), 0)
        self.assertEqual(ethiopian(0, 0), 0)

if __name__ == '__main__':
    unittest.main()
