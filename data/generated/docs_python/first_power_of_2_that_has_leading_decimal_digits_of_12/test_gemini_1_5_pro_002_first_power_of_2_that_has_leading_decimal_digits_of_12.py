import unittest
from first_power_of_2_that_has_leading_decimal_digits_of_12 import p

class TestP(unittest.TestCase):

    def test_p_12_1(self):
        self.assertEqual(p(12, 1), 7)

    def test_p_12_2(self):
        self.assertEqual(p(12, 2), 80)

    def test_p_123_45(self):
        self.assertEqual(p(123, 45), 12710)

    def test_p_123_12345(self):
        self.assertEqual(p(123, 12345), 232718)

    def test_p_123_678910(self):
        self.assertEqual(p(123, 678910), 1318315)


if __name__ == '__main__':
    unittest.main()
