import unittest
from largest_int_from_concatenated_ints import maxnum

class TestMaxNum(unittest.TestCase):

    def test_example_1(self):
        numbers = (1, 34, 3, 98, 9, 76, 45, 4)
        expected = "998764543431"
        self.assertEqual(maxnum(numbers), expected)

    def test_example_2(self):
        numbers = (54, 546, 548, 60)
        expected = "6054854654"
        self.assertEqual(maxnum(numbers), expected)

    def test_single_digit(self):
        numbers = (1,)
        expected = "1"
        self.assertEqual(maxnum(numbers), expected)

    def test_empty_input(self):
        numbers = ()
        expected = ""
        self.assertEqual(maxnum(numbers), expected)
        
    def test_all_same_digits(self):
        numbers = (5, 5, 5, 5)
        expected = "5555"
        self.assertEqual(maxnum(numbers), expected)
        
    def test_leading_zeros(self): # Added test case for leading zeros
        numbers = (0, 100, 20)
        expected = "201000"
        self.assertEqual(maxnum(numbers), expected)


