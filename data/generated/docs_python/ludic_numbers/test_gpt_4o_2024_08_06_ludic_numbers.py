import unittest
from ludic_numbers import ludic

class TestLudicNumbers(unittest.TestCase):

    def setUp(self):
        # Generate ludic numbers up to a reasonable limit for testing
        self.ludics = [l for l in ludic(10000)]

    def test_first_25_ludic_numbers(self):
        expected_first_25 = [1, 2, 3, 5, 7, 11, 13, 17, 23, 25, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89]
        self.assertEqual(self.ludics[:25], expected_first_25)

    def test_ludic_numbers_less_than_1000(self):
        count_less_than_1000 = sum(1 for l in self.ludics if l <= 1000)
        self.assertEqual(count_less_than_1000, 370)

    def test_2000th_to_2005th_ludic_numbers(self):
        expected_2000_to_2005 = [14251, 14267, 14269, 14273, 14281, 14287]
        self.assertEqual(self.ludics[1999:2005], expected_2000_to_2005)

    def test_triplets_less_than_250(self):
        n = 250
        triplets = [(x, x+2, x+6)
                    for x in self.ludics
                    if x+6 < n and x+2 in self.ludics and x+6 in self.ludics]
        expected_triplets = [(1, 3, 7), (37, 39, 43), (47, 49, 53), (67, 69, 73), (79, 81, 85), (97, 99, 103), (127, 129, 133), (157, 159, 163), (167, 169, 173), (197, 199, 203), (223, 225, 229)]
        self.assertEqual(triplets, expected_triplets)

if __name__ == '__main__':
    unittest.main()
