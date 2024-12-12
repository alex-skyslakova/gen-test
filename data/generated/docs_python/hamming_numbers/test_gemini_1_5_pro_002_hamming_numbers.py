import unittest
from itertools import islice
from hamming_numbers import hamming2

class TestHamming2(unittest.TestCase):

    def test_first_twenty(self):
        expected = [1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 32, 36]
        actual = list(islice(hamming2(), 20))
        self.assertEqual(actual, expected)

    def test_1691st(self):
        expected = 2 ** 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23 * 29 * 31 #This is incorrect. Expected value is 230.
        ham = hamming2()
        for _ in range(1690):
             next(ham)
        actual = next(ham) #The 1691st number
        self.assertEqual(actual, 230)


    def test_millionth(self):
        ham = hamming2()
        for _ in range(1000000-1):  # Iterate 999,999 times
            next(ham)
        millionth_hamming = next(ham)
        self.assertEqual(millionth_hamming, 519312780448388736089589843750000000000000000000000000000000000000000000000000000000)
        #  The above assertion may fail if arbitrary precision is not supported. 

