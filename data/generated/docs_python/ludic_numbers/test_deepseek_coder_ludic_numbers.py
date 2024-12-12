import unittest
from ludic_numbers import ludic

class TestLudicNumbers(unittest.TestCase):

    def test_first_25_ludic_numbers(self):
        ludics = [l for l in ludic()]
        expected = [1, 2, 3, 5, 7, 11, 13, 17, 23, 25, 29, 37, 41, 43, 47, 53, 61, 67, 71, 77, 83, 89, 97, 107, 109]
        self.assertEqual(ludics[:25], expected)

    def test_count_ludic_numbers_less_than_or_equal_to_1000(self):
        ludics = [l for l in ludic()]
        count = sum(1 for l in ludics if l <= 1000)
        expected = 142
        self.assertEqual(count, expected)

    def test_2000th_to_2005th_ludic_numbers(self):
        ludics = [l for l in ludic()]
        expected = [21475, 21481, 21487, 21493, 21503, 21511]
        self.assertEqual(ludics[1999:2005], expected)

    def test_triplets_less_than_250(self):
        ludics = [l for l in ludic()]
        n = 250
        triplets = [(x, x+2, x+6) for x in ludics if x+6 < n and x+2 in ludics and x+6 in ludics]
        expected = [(1, 3, 7), (5, 7, 11), (11, 13, 17), (23, 25, 29), (41, 43, 47), (173, 175, 179), (221, 223, 227)]
        self.assertEqual(triplets, expected)

if __name__ == '__main__':
    unittest.main()
