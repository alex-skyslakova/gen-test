import unittest
from euler_s_sum_of_powers_conjecture import eulers_sum_of_powers

class TestEulerSumOfPowersConjecture(unittest.TestCase):

    def test_eulers_sum_of_powers(self):
        # Test that the function returns a tuple of 5 integers
        result = eulers_sum_of_powers()
        self.assertIsInstance(result, tuple)
        self.assertEqual(len(result), 5)
        for num in result:
            self.assertIsInstance(num, int)

        # Test that the integers are distinct and within the range (1, 250)
        self.assertEqual(len(set(result)), 5)
        for num in result:
            self.assertTrue(1 <= num < 250)

        # Test that the sum of the 5th powers of the first four integers equals the 5th power of the fifth integer
        x0, x1, x2, x3, y = result
        self.assertEqual(x0**5 + x1**5 + x2**5 + x3**5, y**5)

if __name__ == '__main__':
    unittest.main()
