import unittest
from general_fizzbuzz import genfizzbuzz

class TestGenFizzBuzz(unittest.TestCase):

    def test_basic_fizzbuzz(self):
        factors_and_words = [(3, 'Fizz'), (5, 'Buzz')]
        numbers = range(1, 16)
        expected_output = (
            "1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

    def test_custom_fizzbuzz(self):
        factors_and_words = [(3, 'Fizz'), (5, 'Buzz'), (7, 'Baxx')]
        numbers = range(1, 21)
        expected_output = (
            "1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n16\n17\nFizz\n19\nBuzz"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

    def test_single_factor(self):
        factors_and_words = [(2, 'Fizz')]
        numbers = range(1, 7)
        expected_output = (
            "1\nFizz\n3\nFizz\n5\nFizz"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

    def test_no_factors(self):
        factors_and_words = []
        numbers = range(1, 4)
        expected_output = (
            "1\n2\n3"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

    def test_multiple_factors_same_number(self):
        factors_and_words = [(2, 'Fizz'), (3, 'Buzz'), (6, 'Baxx')]
        numbers = range(1, 7)
        expected_output = (
            "1\nFizz\nBuzz\nFizz\n5\nFizzBuzzBaxx"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

    def test_large_numbers(self):
        factors_and_words = [(3, 'Fizz'), (5, 'Buzz'), (7, 'Baxx')]
        numbers = range(1, 106)
        expected_output = (
            "1\n2\nFizz\n4\nBuzz\nFizz\nBaxx\n8\nFizz\nBuzz\n11\nFizz\n13\nBaxx\nFizzBuzz\n16\n17\nFizz\n19\nBuzz\nFizzBaxx\n22\n23\nFizz\nBuzz\n26\nFizz\nBaxx\n29\nFizzBuzz\n31\n32\nFizz\n34\nBuzzBaxx\nFizz\n37\n38\nFizz\nBuzz\n41\nFizzBaxx\n43\n44\nFizzBuzz\n46\n47\nFizz\nBaxx\nBuzz\nFizz\n52\n53\nFizz\nBuzz\nBaxx\nFizz\n58\n59\nFizzBuzz\n61\n62\nFizzBaxx\n64\nBuzz\nFizz\n67\n68\nFizz\nBuzzBaxx\n71\nFizz\n73\n74\nFizzBuzz\n76\nBaxx\nFizz\n79\nBuzz\nFizz\n82\n83\nFizzBaxx\nBuzz\n86\nFizz\n88\n89\nFizzBuzz\nBaxx\n92\nFizz\n94\nBuzz\nFizz\n97\nBaxx\nFizz\nBuzz\n101\nFizz\n103\n104\nFizzBuzzBaxx"
        )
        self.assertEqual(genfizzbuzz(factors_and_words, numbers), expected_output)

if __name__ == '__main__':
    unittest.main()
