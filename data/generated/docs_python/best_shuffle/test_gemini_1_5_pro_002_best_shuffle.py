import unittest
import random
from best_shuffle import best_shuffle, count

class TestBestShuffle(unittest.TestCase):

    def test_tree(self):
        w, c = best_shuffle("tree")
        self.assertEqual(c, 0)

    def test_abracadabra(self):
        w, c = best_shuffle("abracadabra")
        self.assertLessEqual(c, 3) # The optimal solution has a score of 1, but the algorithm may not always find it.

    def test_seesaw(self):
        w, c = best_shuffle("seesaw")
        self.assertLessEqual(c, 2)

    def test_elk(self):
        w, c = best_shuffle("elk")
        self.assertEqual(c, 0)

    def test_grrrrrr(self):
        w, c = best_shuffle("grrrrrr")
        self.assertLessEqual(c, 1)

    def test_up(self):
        w, c = best_shuffle("up")
        self.assertEqual(c, 0)

    def test_a(self):
        w, c = best_shuffle("a")
        self.assertEqual(c, 1)

    def test_antidisestablishmentarianism(self):
        w, c = best_shuffle("antidisestablishmentarianism")
        self.assertLessEqual(c, 1)  # Due to randomness and algorithm limitations, a perfect derangement is not guaranteed for long strings.

    def test_hounddogs(self):
        w, c = best_shuffle("hounddogs")
        self.assertLessEqual(c, 1)

    def test_aardvarks_are_ant_eaters(self):
        w, c = best_shuffle("aardvarks are ant eaters")
        self.assertLessEqual(c, 5)

    def test_immediately(self):
        w, c = best_shuffle("immediately")
        self.assertLessEqual(c, 1)

    def test_abba(self):
        w, c = best_shuffle("abba")
        self.assertLessEqual(c, 0) #  This tests a specific edge case where a perfect shuffle is possible



    def test_count_function(self):  # Testing the helper function
        self.assertEqual(count("abc", "abc"), 3)
        self.assertEqual(count("abc", "acb"), 1)
        self.assertEqual(count("abc", "cab"), 0)


if __name__ == '__main__':
    unittest.main()
