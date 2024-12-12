import unittest
from itertools import islice

# Assuming the hamming2 function is defined in a module named hamming_numbers
from hamming_numbers import hamming2

class TestHammingNumbers(unittest.TestCase):

    def test_first_twenty_hamming_numbers(self):
        expected_first_twenty = [
            1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 32, 36
        ]
        generated_first_twenty = list(islice(hamming2(), 20))
        self.assertEqual(generated_first_twenty, expected_first_twenty)

    def test_1691st_hamming_number(self):
        # The 1691st Hamming number is known to be 2125764000
        hamming_1691st = next(islice(hamming2(), 1690, 1691))
        self.assertEqual(hamming_1691st, 2125764000)

    def test_one_millionth_hamming_number(self):
        # This test assumes the environment supports arbitrary-precision integers
        # The one millionth Hamming number is known to be 519312780448000
        hamming_one_millionth = next(islice(hamming2(), 999999, 1000000))
        self.assertEqual(hamming_one_millionth, 519312780448000)

if __name__ == '__main__':
    unittest.main()
