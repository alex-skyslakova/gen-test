import unittest
from hofstadter_figure_figure_sequences import ffr, ffs

class TestHofstadterFigureFigureSequences(unittest.TestCase):

    def test_ffr_first_ten_values(self):
        expected_values = [1, 3, 7, 12, 18, 26, 35, 45, 56, 69]
        for i, expected in enumerate(expected_values, start=1):
            with self.subTest(i=i):
                self.assertEqual(ffr(i), expected)

    def test_ffs_first_ten_values(self):
        expected_values = [2, 4, 5, 6, 8, 9, 10, 11, 13, 14]
        for i, expected in enumerate(expected_values, start=1):
            with self.subTest(i=i):
                self.assertEqual(ffs(i), expected)

    def test_ffr_invalid_input(self):
        with self.assertRaises(ValueError):
            ffr(0)
        with self.assertRaises(ValueError):
            ffr(-1)
        with self.assertRaises(ValueError):
            ffr(1.5)

    def test_ffs_invalid_input(self):
        with self.assertRaises(ValueError):
            ffs(0)
        with self.assertRaises(ValueError):
            ffs(-1)
        with self.assertRaises(ValueError):
            ffs(1.5)

    def test_ffr_plus_ffs_cover_all_integers_1_to_1000(self):
        bin = [0] * 1001
        for i in range(1, 41):
            bin[ffr(i)] += 1
        for i in range(1, 961):
            bin[ffs(i)] += 1
        self.assertTrue(all(b == 1 for b in bin[1:1001]))

if __name__ == '__main__':
    unittest.main()
