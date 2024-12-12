import unittest
from general_fizzbuzz import genfizzbuzz

class TestGenFizzBuzz(unittest.TestCase):

    def test_empty_factors(self):
        self.assertEqual(genfizzbuzz([], range(1, 6)), "1\n2\n3\n4\n5")

    def test_single_factor(self):
        self.assertEqual(genfizzbuzz([(3, 'Fizz')], range(1, 6)), "1\n2\nFizz\n4\n5")

    def test_multiple_factors(self):
        self.assertEqual(genfizzbuzz([(3, 'Fizz'), (5, 'Buzz')], range(1, 16)), "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz")

    def test_multiple_factors_with_overlap(self):
        self.assertEqual(genfizzbuzz([(3, 'Fizz'), (5, 'Buzz'), (7, 'Baxx')], range(1, 21)), "1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n16\n17\nFizz\n19\nBuzz")
    
    def test_large_number_with_overlap(self):
        self.assertEqual(genfizzbuzz([(3, 'Fizz'), (5, 'Buzz'), (7, 'Baxx')], range(105, 106)), "FizzBuzzBaxx")

    def test_factors_out_of_order(self):
        self.assertEqual(genfizzbuzz([(5, 'Buzz'), (3, 'Fizz'), (7, 'Baxx')], range(1, 21)), "1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n16\n17\nFizz\n19\nBuzz")

    def test_empty_range(self):
        self.assertEqual(genfizzbuzz([(3, 'Fizz'), (5, 'Buzz')], range(0)), "")


