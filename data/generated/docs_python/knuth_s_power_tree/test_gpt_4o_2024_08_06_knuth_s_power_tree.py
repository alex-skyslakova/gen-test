import unittest
from knuth_s_power_tree import path, tree_pow, show_pow

class TestKnuthsPowerTree(unittest.TestCase):

    def test_path(self):
        # Test path generation for various powers
        self.assertEqual(path(0), [])
        self.assertEqual(path(1), [1])
        self.assertEqual(path(2), [1, 2])
        self.assertEqual(path(3), [1, 2, 3])
        self.assertEqual(path(4), [1, 2, 4])
        self.assertEqual(path(5), [1, 2, 3, 5])
        self.assertEqual(path(6), [1, 2, 3, 6])
        self.assertEqual(path(7), [1, 2, 3, 5, 7])
        self.assertEqual(path(8), [1, 2, 4, 8])
        self.assertEqual(path(9), [1, 2, 3, 6, 9])
        self.assertEqual(path(10), [1, 2, 3, 5, 10])

    def test_tree_pow(self):
        # Test power calculation for various bases and exponents
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
        
        self.assertEqual(tree_pow(3, 191), 3**191)
        self.assertAlmostEqual(tree_pow(1.1, 81), 1.1**81, places=6)

    def test_show_pow(self):
        # Test the output format of show_pow function
        # This test will capture the printed output and check if it matches expected format
        import io
        import sys

        captured_output = io.StringIO()
        sys.stdout = captured_output
        show_pow(2, 5)
        sys.stdout = sys.__stdout__
        expected_output = "5: [1, 2, 3, 5]\n2^5 = 32\n"
        self.assertEqual(captured_output.getvalue(), expected_output)

        captured_output = io.StringIO()
        sys.stdout = captured_output
        show_pow(3, 191)
        sys.stdout = sys.__stdout__
        expected_output = "191: [1, 2, 3, 5, 10, 20, 40, 80, 160, 191]\n3^191 = {}\n".format(3**191)
        self.assertEqual(captured_output.getvalue(), expected_output)

        captured_output = io.StringIO()
        sys.stdout = captured_output
        show_pow(1.1, 81)
        sys.stdout = sys.__stdout__
        expected_output = "81: [1, 2, 3, 5, 10, 20, 40, 80, 81]\n1.1^81 = {}\n".format(1.1**81)
        self.assertEqual(captured_output.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()
