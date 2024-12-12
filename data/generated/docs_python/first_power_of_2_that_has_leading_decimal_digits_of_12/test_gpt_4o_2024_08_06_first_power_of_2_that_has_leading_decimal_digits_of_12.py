import unittest
from first_power_of_2_that_has_leading_decimal_digits_of_12 import p

class TestFirstPowerOf2(unittest.TestCase):
    def test_p_12_1(self):
        self.assertEqual(p(12, 1), 7)

    def test_p_12_2(self):
        self.assertEqual(p(12, 2), 80)

    def test_p_123_45(self):
        self.assertEqual(p(123, 45), 12710)

    def test_p_123_12345(self):
        # This is a large computation, ensure the function can handle it
        result = p(123, 12345)
        self.assertIsInstance(result, int)  # Just checking if it returns an integer
        # The actual value is not provided, so we can't assert the exact result

    def test_p_123_678910(self):
        # This is another large computation, ensure the function can handle it
        result = p(123, 678910)
        self.assertIsInstance(result, int)  # Just checking if it returns an integer
        # The actual value is not provided, so we can't assert the exact result

if __name__ == '__main__':
    unittest.main()
