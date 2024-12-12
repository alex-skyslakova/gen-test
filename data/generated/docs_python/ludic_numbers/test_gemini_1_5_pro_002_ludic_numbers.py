import unittest
from ludic_numbers import ludic

class TestLudicNumbers(unittest.TestCase):

    def test_first_25_ludics(self):
        expected = [1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 37, 41, 43, 47, 49, 53, 59, 61, 67, 71, 73, 79]
        self.assertEqual(list(ludic(80))[:25], expected)  # Generate enough ludics

    def test_ludics_less_than_1001(self):
        count = sum(1 for l in ludic(1001) if l <= 1000) # Check inclusive condition and generator limit
        self.assertEqual(count, 142)

    def test_2000th_to_2005th_ludics(self):
        ludics_gen = ludic(23000) # Generate enough ludics
        ludics_list = list(ludics_gen)
        expected = [21257, 21269, 21283, 21287, 21293, 21317]
        self.assertEqual(ludics_list[2000-1:2005], expected)

    def test_triplets_less_than_250(self):
        n = 250
        ludics_gen = ludic(n)
        ludics_set = set(ludics_gen) # Use set for efficient membership check.
        triplets = [(x, x+2, x+6)
                    for x in ludics_set
                    if x+6 < n and x+2 in ludics_set and x+6 in ludics_set]
        expected = [(1, 3, 7), (11, 13, 17), (101, 103, 107), (191, 193, 197), (231, 233, 237)]
        self.assertEqual(triplets, expected)


if __name__ == '__main__':
    unittest.main()
