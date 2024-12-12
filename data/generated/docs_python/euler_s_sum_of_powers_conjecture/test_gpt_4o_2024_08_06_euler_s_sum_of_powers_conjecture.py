import unittest
from euler_s_sum_of_powers_conjecture import eulers_sum_of_powers

class TestEulersSumOfPowers(unittest.TestCase):
    def test_eulers_sum_of_powers(self):
        # Run the function to get the result
        result = eulers_sum_of_powers()
        
        # Check if the result is a tuple of 5 distinct integers
        self.assertEqual(len(result), 5, "The result should be a tuple of 5 integers.")
        self.assertTrue(all(isinstance(i, int) for i in result), "All elements in the result should be integers.")
        self.assertEqual(len(set(result)), 5, "All integers in the result should be distinct.")
        
        # Check if the integers are within the specified range
        self.assertTrue(all(0 < i < 250 for i in result), "All integers should be between 0 and 250 (exclusive).")
        
        # Check if the equation holds true
        x0, x1, x2, x3, y = result
        self.assertEqual(x0**5 + x1**5 + x2**5 + x3**5, y**5, "The equation should hold true for the result.")

if __name__ == '__main__':
    unittest.main()
