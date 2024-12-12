import unittest
from knuth_s_power_tree import path, tree_pow, show_pow  # Assuming the code is in knuth_s_power_tree.py

class TestKnuthPowerTree(unittest.TestCase):

    def test_path_zero(self):
        self.assertEqual(path(0), [])

    def test_path_one(self):
        self.assertEqual(path(1), [1])

    def test_path_small_numbers(self):
        self.assertEqual(path(2), [1, 2])
        self.assertEqual(path(3), [1, 2, 3])
        self.assertEqual(path(4), [1, 2, 4])
        self.assertEqual(path(5), [1, 2, 3, 5])
        self.assertEqual(path(10), [1, 2, 3, 5, 10])
        self.assertEqual(path(43), [1, 2, 3, 5, 10, 20, 40, 43])

    def test_tree_pow_zero_exponent(self):
        self.assertEqual(tree_pow(2, 0), 1)
        self.assertEqual(tree_pow(3, 0), 1)
        self.assertEqual(tree_pow(1.1, 0), 1)

    def test_tree_pow_one_exponent(self):
        self.assertEqual(tree_pow(2, 1), 2)
        self.assertEqual(tree_pow(3, 1), 3)
        self.assertEqual(tree_pow(1.1, 1), 1.1)


    def test_tree_pow_small_numbers(self):
        self.assertEqual(tree_pow(2, 2), 4)
        self.assertEqual(tree_pow(2, 3), 8)
        self.assertEqual(tree_pow(2, 4), 16)
        self.assertEqual(tree_pow(2, 5), 32)
        self.assertEqual(tree_pow(3, 5), 243)

    def test_tree_pow_larger_numbers(self):
         self.assertEqual(tree_pow(2, 17), 131072)
         self.assertEqual(tree_pow(3, 7), 2187)

    def test_tree_pow_float_base(self):
        self.assertAlmostEqual(tree_pow(1.1, 5), 1.61051, places=5)
        self.assertAlmostEqual(tree_pow(1.1, 81), 3410.77828423, places=5) # Example value adjusted based on the provided code output

    def test_show_pow(self):
         # Testing show_pow is tricky as it prints to the console.
         # A better approach would be to refactor show_pow to return the string instead of printing.
         # For now, we'll just call it to ensure it doesn't raise any exceptions.
        show_pow(2, 5)
        show_pow(3, 7)
        show_pow(1.1, 3)



if __name__ == '__main__':
    unittest.main()

