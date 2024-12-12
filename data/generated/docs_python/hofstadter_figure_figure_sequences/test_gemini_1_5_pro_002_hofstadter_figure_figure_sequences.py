import unittest
from hofstadter_figure_figure_sequences import ffr, ffs

class TestHofstadter(unittest.TestCase):

    def test_ffr_first10(self):
        expected = [1, 3, 7, 12, 18, 26, 35, 45, 56, 69]
        for i in range(1, 11):
            self.assertEqual(ffr(i), expected[i-1])

    def test_ffr_invalid_input(self):
        with self.assertRaises(ValueError):
            ffr(0)
        with self.assertRaises(ValueError):
            ffr(-1)
        with self.assertRaises(ValueError):
            ffr(1.5)
        with self.assertRaises(ValueError):
            ffr("a")

    def test_ffs_invalid_input(self):
        with self.assertRaises(ValueError):
            ffs(0)
        with self.assertRaises(ValueError):
            ffs(-1)
        with self.assertRaises(ValueError):
            ffs(1.5)
        with self.assertRaises(ValueError):
            ffs("a")


    def test_all_integers_1_to_1000(self):
        bin = [None] + [0]*1000
        for i in range(40, 0, -1):
            bin[ffr(i)] += 1
        for i in range(960, 0, -1):
            bin[ffs(i)] += 1
        self.assertTrue(all(b == 1 for b in bin[1:1000]))


if __name__ == '__main__':
    unittest.main()

