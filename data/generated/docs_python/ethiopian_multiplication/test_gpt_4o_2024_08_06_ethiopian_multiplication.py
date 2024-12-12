import unittest
from ethiopian_multiplication import halve, double, even, ethiopian

class TestEthiopianMultiplication(unittest.TestCase):

    def test_halve(self):
        self.assertEqual(halve(10), 5)
        self.assertEqual(halve(9), 4)
        self.assertEqual(halve(1), 0)
        self.assertEqual(halve(0), 0)
        self.assertEqual(halve(-4), -2)

    def test_double(self):
        self.assertEqual(double(10), 20)
        self.assertEqual(double(9), 18)
        self.assertEqual(double(1), 2)
        self.assertEqual(double(0), 0)
        self.assertEqual(double(-4), -8)

    def test_even(self):
        self.assertTrue(even(10))
        self.assertFalse(even(9))
        self.assertTrue(even(0))
        self.assertTrue(even(-4))
        self.assertFalse(even(-3))

    def test_ethiopian(self):
        self.assertEqual(ethiopian(17, 34), 578)
        self.assertEqual(ethiopian(0, 34), 0)
        self.assertEqual(ethiopian(17, 0), 0)
        self.assertEqual(ethiopian(1, 1), 1)
        self.assertEqual(ethiopian(2, 3), 6)
        self.assertEqual(ethiopian(3, 2), 6)
        self.assertEqual(ethiopian(5, 5), 25)
        self.assertEqual(ethiopian(7, 8), 56)
        self.assertEqual(ethiopian(8, 7), 56)

if __name__ == '__main__':
    unittest.main()
