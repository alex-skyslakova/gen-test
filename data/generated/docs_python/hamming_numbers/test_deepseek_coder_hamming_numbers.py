import unittest
from hamming_numbers import hamming2

class TestHammingNumbers(unittest.TestCase):

    def test_first_twenty_hamming_numbers(self):
        hamming_gen = hamming2()
        first_twenty = list(islice(hamming_gen, 20))
        expected = [1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 32, 36]
        self.assertEqual(first_twenty, expected)

    def test_1691st_hamming_number(self):
        hamming_gen = hamming2()
        hamming_1691st = next(islice(hamming_gen, 1690, 1691))
        expected = 2125764000
        self.assertEqual(hamming_1691st, expected)

    def test_one_millionth_hamming_number(self):
        hamming_gen = hamming2()
        hamming_1000000th = next(islice(hamming_gen, 999999, 1000000))
        expected = 519312780448388736089589843750000000000000000000000000000000000000000000000000000000
        self.assertEqual(hamming_1000000th, expected)

if __name__ == '__main__':
    unittest.main()
