import unittest
from ethiopian_multiplication import halve, double, even, ethiopian

class TestEthiopianMultiplication(unittest.TestCase):

    def test_halve(self):
        self.assertEqual(halve(10), 5)
        self.assertEqual(halve(9), 4)
        self.assertEqual(halve(0), 0)
        self.assertEqual(halve(1), 0)

    def test_double(self):
        self.assertEqual(double(5), 10)
        self.assertEqual(double(0), 0)
        self.assertEqual(double(1), 2)

    def test_even(self):
        self.assertTrue(even(10))
        self.assertFalse(even(9))
        self.assertTrue(even(0))
        self.assertFalse(even(1))

    def test_ethiopian(self):
        global tutor
        tutor = False  # Disable printing during tests
        self.assertEqual(ethiopian(17, 34), 578)
        self.assertEqual(ethiopian(0, 34), 0)
        self.assertEqual(ethiopian(17, 0), 0)
        self.assertEqual(ethiopian(1, 1), 1)
        self.assertEqual(ethiopian(2, 3), 0)
        self.assertEqual(ethiopian(5, 7), 7 + 28 + 0 + 0 + 0) # Testing an odd number example step-by-step


