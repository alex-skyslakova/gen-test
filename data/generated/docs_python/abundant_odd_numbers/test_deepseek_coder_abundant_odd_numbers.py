import unittest
from abundant_odd_numbers import divisorSum

class TestAbundantOddNumbers(unittest.TestCase):

    def test_divisorSum(self):
        # Test cases for divisorSum function
        self.assertEqual(divisorSum(12), 16)  # 12 is abundant, proper divisors sum to 16
        self.assertEqual(divisorSum(945), 975)  # 945 is the smallest odd abundant number
        self.assertEqual(divisorSum(1), 1)  # 1 has only one divisor, itself
        self.assertEqual(divisorSum(28), 28)  # 28 is a perfect number, sum of divisors is 28

    def test_first_25_abundant_odd_numbers(self):
        # Test the first 25 abundant odd numbers
        odd_number = 1
        a_count = 0
        expected_results = [
            (945, 975), (1575, 1649), (2205, 2241), (2835, 2973), (3465, 4023),
            (4095, 4641), (4725, 5195), (5355, 5877), (5775, 6129), (5985, 6495),
            (6435, 6669), (6615, 6969), (6825, 7063), (7245, 7731), (7425, 7455),
            (7875, 8349), (8085, 8331), (8415, 8433), (8505, 8967), (8925, 8931),
            (9135, 9585), (9555, 9597), (9765, 10203), (10395, 12645), (11025, 11946)
        ]

        while a_count < 25:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                self.assertEqual((odd_number, d_sum), expected_results[a_count])
                a_count += 1
            odd_number += 2

    def test_1000th_abundant_odd_number(self):
        # Test the 1000th abundant odd number
        odd_number = 1
        a_count = 0

        while a_count < 1000:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                a_count += 1
            odd_number += 2

        self.assertEqual(odd_number - 2, 492975)
        self.assertEqual(d_sum, 519361)

    def test_first_abundant_odd_number_greater_than_billion(self):
        # Test the first abundant odd number greater than 1 billion
        odd_number = 1000000001
        found = False

        while not found:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                found = True
                self.assertEqual(odd_number, 1000000575)
                self.assertEqual(d_sum, 1083561009)
            odd_number += 2

if __name__ == '__main__':
    unittest.main()
