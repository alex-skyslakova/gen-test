import unittest
import math

class TestFactorialFunction(unittest.TestCase):
    
    def test_factorial_of_zero(self):
        # Test that the factorial of 0 is 1
        self.assertEqual(math.factorial(0), 1)
    
    def test_factorial_of_positive_integers(self):
        # Test factorial of positive integers
        self.assertEqual(math.factorial(1), 1)
        self.assertEqual(math.factorial(2), 2)
        self.assertEqual(math.factorial(3), 6)
        self.assertEqual(math.factorial(4), 24)
        self.assertEqual(math.factorial(5), 120)
        self.assertEqual(math.factorial(6), 720)
        self.assertEqual(math.factorial(10), 3628800)
    
    def test_factorial_of_large_number(self):
        # Test factorial of a larger number
        self.assertEqual(math.factorial(20), 2432902008176640000)
    
    def test_factorial_of_negative_number(self):
        # Test that factorial of a negative number raises a ValueError
        with self.assertRaises(ValueError):
            math.factorial(-1)
    
    def test_factorial_of_non_integer(self):
        # Test that factorial of a non-integer raises a ValueError
        with self.assertRaises(ValueError):
            math.factorial(3.5)
        with self.assertRaises(ValueError):
            math.factorial("string")

if __name__ == '__main__':
    unittest.main()
