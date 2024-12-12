import unittest
from lucky_and_even_lucky_numbers import lgen

class TestLuckyNumbers(unittest.TestCase):

    def test_lucky_numbers(self):
        # Test the first 20 lucky numbers
        expected_lucky_numbers = [1, 3, 7, 9, 13, 15, 21, 25, 31, 33, 37, 43, 49, 51, 63, 67, 69, 73, 75, 79]
        lucky_gen = lgen(even=False)
        actual_lucky_numbers = [next(lucky_gen) for _ in range(20)]
        self.assertEqual(actual_lucky_numbers, expected_lucky_numbers)

    def test_even_lucky_numbers(self):
        # Test the first 20 even lucky numbers
        expected_even_lucky_numbers = [2, 4, 6, 10, 12, 18, 20, 22, 26, 34, 36, 42, 44, 50, 52, 54, 58, 68, 70, 76]
        even_lucky_gen = lgen(even=True)
        actual_even_lucky_numbers = [next(even_lucky_gen) for _ in range(20)]
        self.assertEqual(actual_even_lucky_numbers, expected_even_lucky_numbers)

    def test_lucky_numbers_range(self):
        # Test lucky numbers between 6000 and 6100
        lucky_gen = lgen(even=False)
        lucky_numbers = []
        while True:
            num = next(lucky_gen)
            if num > 6100:
                break
            if num >= 6000:
                lucky_numbers.append(num)
        expected_lucky_numbers_range = [6009, 6019, 6031, 6049, 6055, 6061, 6079, 6093, 6099]
        self.assertEqual(lucky_numbers, expected_lucky_numbers_range)

    def test_even_lucky_numbers_range(self):
        # Test even lucky numbers between 6000 and 6100
        even_lucky_gen = lgen(even=True)
        even_lucky_numbers = []
        while True:
            num = next(even_lucky_gen)
            if num > 6100:
                break
            if num >= 6000:
                even_lucky_numbers.append(num)
        expected_even_lucky_numbers_range = [6018, 6020, 6022, 6026, 6036, 6038, 6044, 6050, 6052, 6066, 6068, 6076, 6084, 6086, 6092, 6098]
        self.assertEqual(even_lucky_numbers, expected_even_lucky_numbers_range)

    def test_10000th_lucky_number(self):
        # Test the 10,000th lucky number
        lucky_gen = lgen(even=False)
        for _ in range(9999):
            next(lucky_gen)
        self.assertEqual(next(lucky_gen), 115591)

    def test_10000th_even_lucky_number(self):
        # Test the 10,000th even lucky number
        even_lucky_gen = lgen(even=True)
        for _ in range(9999):
            next(even_lucky_gen)
        self.assertEqual(next(even_lucky_gen), 115578)

if __name__ == '__main__':
    unittest.main()
