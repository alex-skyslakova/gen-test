import unittest
from best_shuffle import best_shuffle

class TestBestShuffle(unittest.TestCase):

    def test_shuffle_abracadabra(self):
        original = "abracadabra"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_seesaw(self):
        original = "seesaw"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_elk(self):
        original = "elk"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_grrrrrr(self):
        original = "grrrrrr"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_up(self):
        original = "up"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_a(self):
        original = "a"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertEqual(score, 1)  # Single character cannot be shuffled

    def test_shuffle_antidisestablishmentarianism(self):
        original = "antidisestablishmentarianism"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_hounddogs(self):
        original = "hounddogs"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_aardvarks_are_ant_eaters(self):
        original = "aardvarks are ant eaters"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_immediately(self):
        original = "immediately"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

    def test_shuffle_abba(self):
        original = "abba"
        shuffled, score = best_shuffle(original)
        self.assertEqual(len(original), len(shuffled))
        self.assertLessEqual(score, len(original))
        self.assertNotEqual(original, shuffled)

if __name__ == '__main__':
    unittest.main()
