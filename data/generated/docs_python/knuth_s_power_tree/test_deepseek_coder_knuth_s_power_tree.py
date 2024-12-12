import unittest
from knuth_s_power_tree import path, tree_pow, show_pow

class TestKnuthsPowerTree(unittest.TestCase):

    def test_path_generation(self):
        self.assertEqual(path(0), [])
        self.assertEqual(path(1), [1])
        self.assertEqual(path(2), [1, 2])
        self.assertEqual(path(3), [1, 2, 3])
        self.assertEqual(path(4), [1, 2, 4])
        self.assertEqual(path(5), [1, 2, 4, 5])
        self.assertEqual(path(6), [1, 2, 3, 6])
        self.assertEqual(path(7), [1, 2, 3, 6, 7])
        self.assertEqual(path(8), [1, 2, 4, 8])
        self.assertEqual(path(9), [1, 2, 4, 8, 9])
        self.assertEqual(path(10), [1, 2, 4, 5, 10])
        self.assertEqual(path(11), [1, 2, 4, 5, 10, 11])
        self.assertEqual(path(12), [1, 2, 3, 6, 12])
        self.assertEqual(path(13), [1, 2, 4, 8, 9, 13])
        self.assertEqual(path(14), [1, 2, 3, 6, 7, 14])
        self.assertEqual(path(15), [1, 2, 3, 6, 7, 14, 15])
        self.assertEqual(path(16), [1, 2, 4, 8, 16])
        self.assertEqual(path(17), [1, 2, 4, 8, 16, 17])

    def test_tree_pow(self):
        self.assertEqual(tree_pow(2, 0), 1)
        self.assertEqual(tree_pow(2, 1), 2)
        self.assertEqual(tree_pow(2, 2), 4)
        self.assertEqual(tree_pow(2, 3), 8)
        self.assertEqual(tree_pow(2, 4), 16)
        self.assertEqual(tree_pow(2, 5), 32)
        self.assertEqual(tree_pow(2, 6), 64)
        self.assertEqual(tree_pow(2, 7), 128)
        self.assertEqual(tree_pow(2, 8), 256)
        self.assertEqual(tree_pow(2, 9), 512)
        self.assertEqual(tree_pow(2, 10), 1024)
        self.assertEqual(tree_pow(2, 11), 2048)
        self.assertEqual(tree_pow(2, 12), 4096)
        self.assertEqual(tree_pow(2, 13), 8192)
        self.assertEqual(tree_pow(2, 14), 16384)
        self.assertEqual(tree_pow(2, 15), 32768)
        self.assertEqual(tree_pow(2, 16), 65536)
        self.assertEqual(tree_pow(2, 17), 131072)
        self.assertEqual(tree_pow(3, 191), 13494588674281093803728157396523884917402502294030101914066705367021922008906273586058258347)
        self.assertAlmostEqual(tree_pow(1.1, 81), 2253.24023604401)

    def test_show_pow(self):
        # This function is for printing and does not return a value, so we just check if it runs without errors
        try:
            show_pow(2, 0)
            show_pow(2, 1)
            show_pow(2, 2)
            show_pow(2, 3)
            show_pow(2, 4)
            show_pow(2, 5)
            show_pow(2, 6)
            show_pow(2, 7)
            show_pow(2, 8)
            show_pow(2, 9)
            show_pow(2, 10)
            show_pow(2, 11)
            show_pow(2, 12)
            show_pow(2, 13)
            show_pow(2, 14)
            show_pow(2, 15)
            show_pow(2, 16)
            show_pow(2, 17)
            show_pow(3, 191)
            show_pow(1.1, 81)
        except Exception as e:
            self.fail(f"show_pow raised an exception: {e}")

if __name__ == '__main__':
    unittest.main()
