import unittest
from general_fizzbuzz import genfizzbuzz

class TestGenFizzBuzz(unittest.TestCase):

    def test_standard_case(self):
        factors = [(3, 'Fizz'), (5, 'Buzz'), (7, 'Baxx')]
        numbers = range(1, 21)
        expected_output = (
            "1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n"
            "16\n17\nFizz\n19\nBuzz"
        )
        self.assertEqual(genfizzbuzz(factors, numbers), expected_output)

    def test_no_factors(self):
        factors = []
        numbers = range(1, 11)
        expected_output = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
        self.assertEqual(genfizzbuzz(factors, numbers), expected_output)

    def test_single_factor(self):
        factors = [(2, 'Even')]
        numbers = range(1, 6)
        expected_output = "1\nEven\n3\nEven\n5"
        self.assertEqual(genfizzbuzz(factors, numbers), expected_output)

    def test_multiple_factors_same_number(self):
        factors = [(2, 'Two'), (3, 'Three')]
        numbers = range(1, 7)
        expected_output = "1\nTwo\nThree\nTwo\n5\nTwoThree"
        self.assertEqual(genfizzbuzz(factors, numbers), expected_output)

    def test_large_range(self):
        factors = [(3, 'Fizz'), (5, 'Buzz')]
        numbers = range(1, 101)
        # Check only the first and last few elements to ensure performance
        expected_start = "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz"
        expected_end = "91\n92\nFizz\n94\nBuzz\nFizz\n97\n98\nFizz\nBuzz"
        result = genfizzbuzz(factors, numbers)
        self.assertTrue(result.startswith(expected_start))
        self.assertTrue(result.endswith(expected_end))

    def test_non_divisible_numbers(self):
        factors = [(10, 'Ten')]
        numbers = range(1, 10)
        expected_output = "1\n2\n3\n4\n5\n6\n7\n8\n9"
        self.assertEqual(genfizzbuzz(factors, numbers), expected_output)

if __name__ == '__main__':
    unittest.main()
