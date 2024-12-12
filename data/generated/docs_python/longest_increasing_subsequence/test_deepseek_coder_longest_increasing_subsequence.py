import unittest
from longest_increasing_subsequence import longest_increasing_subsequence

class TestLongestIncreasingSubsequence(unittest.TestCase):

    def test_example_1(self):
        X = [3, 2, 6, 4, 5, 1]
        expected = [2, 4, 5]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_example_2(self):
        X = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
        expected = [0, 2, 6, 9, 11, 15]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_single_element(self):
        X = [1]
        expected = [1]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_all_increasing(self):
        X = [1, 2, 3, 4, 5]
        expected = [1, 2, 3, 4, 5]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_all_decreasing(self):
        X = [5, 4, 3, 2, 1]
        expected = [1]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_mixed_sequence(self):
        X = [10, 22, 9, 33, 21, 50, 41, 60, 80]
        expected = [10, 22, 33, 41, 60, 80]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_duplicate_elements(self):
        X = [1, 3, 5, 4, 7, 5, 6]
        expected = [1, 3, 4, 5, 6]
        self.assertEqual(longest_increasing_subsequence(X), expected)

    def test_empty_list(self):
        X = []
        expected = []
        self.assertEqual(longest_increasing_subsequence(X), expected)

if __name__ == '__main__':
    unittest.main()
