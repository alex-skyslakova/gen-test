import unittest
from abundant_odd_numbers import divisorSum

class TestAbundantOddNumbers(unittest.TestCase):

    def test_divisor_sum(self):
        # Test divisorSum function with known values
        self.assertEqual(divisorSum(1), 1)
        self.assertEqual(divisorSum(3), 1)
        self.assertEqual(divisorSum(5), 1)
        self.assertEqual(divisorSum(7), 1)
        self.assertEqual(divisorSum(9), 4)  # 1 + 3
        self.assertEqual(divisorSum(15), 9)  # 1 + 3 + 5

    def test_first_25_abundant_odd_numbers(self):
        # Expected first 25 abundant odd numbers and their proper divisor sums
        expected_results = [
            (945, 975), (1575, 1965), (2205, 2655), (2835, 3459), (3465, 4179),
            (4095, 4965), (4725, 5775), (5355, 6585), (5775, 7137), (6435, 7995),
            (6615, 8265), (6825, 8559), (7245, 9111), (7425, 9369), (7875, 9945),
            (8190, 10395), (8505, 10851), (8925, 11439), (9555, 12285), (10395, 13365),
            (10545, 13545), (11385, 14625), (11655, 14985), (12285, 15885), (12615, 16335)
        ]
        
        odd_number = 1
        a_count = 0
        results = []
        
        while a_count < 25:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                results.append((odd_number, d_sum))
                a_count += 1
            odd_number += 2
        
        self.assertEqual(results, expected_results)

    def test_1000th_abundant_odd_number(self):
        # Expected 1000th abundant odd number and its proper divisor sum
        expected_result = (2480625, 3359235)
        
        odd_number = 1
        a_count = 0
        
        while a_count < 1000:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                a_count += 1
            odd_number += 2
        
        self.assertEqual((odd_number - 2, d_sum), expected_result)

    def test_first_abundant_odd_number_greater_than_one_billion(self):
        # Expected first abundant odd number greater than one billion and its proper divisor sum
        expected_result = (1000000001, 1342177281)
        
        odd_number = 1000000001
        found = False
        
        while not found:
            d_sum = divisorSum(odd_number)
            if d_sum > odd_number:
                found = True
                result = (odd_number, d_sum)
            odd_number += 2
        
        self.assertEqual(result, expected_result)

if __name__ == '__main__':
    unittest.main()
