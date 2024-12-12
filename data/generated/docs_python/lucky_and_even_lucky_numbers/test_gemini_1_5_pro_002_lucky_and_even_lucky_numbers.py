import unittest
from lucky_and_even_lucky_numbers import lgen

class TestLuckyNumberGenerator(unittest.TestCase):

    def test_first_twenty_lucky(self):
        expected = [1, 3, 7, 9, 13, 15, 21, 25, 31, 33, 37, 43, 49, 51, 63, 67, 69, 73, 75, 79]
        actual = [x for i, x in enumerate(lgen()) if i < 20]
        self.assertEqual(actual, expected)

    def test_first_twenty_even_lucky(self):
        expected = [2, 4, 6, 10, 12, 18, 20, 26, 30, 36, 40, 46, 52, 60, 66, 72, 78, 88, 90, 96]
        actual = [x for i, x in enumerate(lgen(even=True)) if i < 20]
        self.assertEqual(actual, expected)
    
    def test_lucky_range_6000_6100(self):
        expected = [6007, 6009, 6013, 6021, 6027, 6033, 6037, 6043, 6045, 6049, 6063, 6069, 6075, 6087, 6093, 6099]
        actual = [x for x in lgen() if 6000 <= x <= 6100]
        self.assertEqual(actual, expected)

    def test_even_lucky_range_6000_6100(self):
        expected = [6002, 6004, 6010, 6012, 6018, 6020, 6036, 6042, 6052, 6060, 6070, 6078, 6084, 6096, 6100]
        actual = [x for x in lgen(even=True) if 6000 <= x <= 6100]
        self.assertEqual(actual, expected)

    def test_10000th_lucky(self):
        expected = 104729
        actual = [x for i, x in enumerate(lgen()) if i== 9999][0] # 0-indexed
        self.assertEqual(actual, expected)
    
    def test_10000th_even_lucky(self):
        expected = 127774
        actual = [x for i, x in enumerate(lgen(even=True)) if i == 9999][0] # 0-indexed
        self.assertEqual(actual, expected)


if __name__ == '__main__':
    unittest.main()

