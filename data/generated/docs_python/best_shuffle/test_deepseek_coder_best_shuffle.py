import unittest
from best_shuffle import best_shuffle, count

class TestBestShuffle(unittest.TestCase):

    def test_count_function(self):
        self.assertEqual(count('tree', 'eetr'), 0)
        self.assertEqual(count('abracadabra', 'baaracdabra'), 1)
        self.assertEqual(count('seesaw', 'eawess'), 0)
        self.assertEqual(count('elk', 'kel'), 0)
        self.assertEqual(count('grrrrrr', 'rrrrrrg'), 6)
        self.assertEqual(count('up', 'pu'), 0)
        self.assertEqual(count('a', 'a'), 1)

    def test_best_shuffle_function(self):
        test_cases = [
            ('tree', 'eetr', 0),
            ('abracadabra', 'baaracdabra', 1),
            ('seesaw', 'eawess', 0),
            ('elk', 'kel', 0),
            ('grrrrrr', 'rrrrrrg', 6),
            ('up', 'pu', 0),
            ('a', 'a', 1),
            ('antidisestablishmentarianism', 'aantidisshebltmnrimastnariai', 1),
            ('hounddogs', 'dohoundogs', 1),
            ('aardvarks are ant eaters', 'aardvarks are ant eaters', 23),
            ('immediately', 'mmediatelyi', 1),
            ('abba', 'baab', 2)
        ]

        for original, expected_shuffled, expected_score in test_cases:
            with self.subTest(original=original):
                shuffled, score = best_shuffle(original)
                self.assertEqual(shuffled, expected_shuffled)
                self.assertEqual(score, expected_score)

if __name__ == '__main__':
    unittest.main()
