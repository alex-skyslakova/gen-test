import unittest
from longest_increasing_subsequence import longest_increasing_subsequence

class TestLongestIncreasingSubsequence(unittest.TestCase):

    def test_empty_list(self):
        self.assertEqual(longest_increasing_subsequence([]), [])

    def test_single_element_list(self):
        self.assertEqual(longest_increasing_subsequence([5]), [5])

    def test_example_1(self):
        self.assertEqual(longest_increasing_subsequence([3, 2, 6, 4, 5, 1]), [2, 4, 5])

    def test_example_2(self):
        self.assertEqual(longest_increasing_subsequence([0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]), [0, 2, 6, 9, 11, 15])

    def test_decreasing_list(self):
        self.assertEqual(longest_increasing_subsequence([5, 4, 3, 2, 1]), [5])

    def test_all_same_elements(self):
        self.assertEqual(longest_increasing_subsequence([3, 3, 3, 3, 3]), [3])

    def test_already_sorted(self):
        self.assertEqual(longest_increasing_subsequence([1, 2, 3, 4, 5]), [1, 2, 3, 4, 5])

    def test_negative_numbers(self):
        self.assertEqual(longest_increasing_subsequence([-5, -2, 0, 3, 6]), [-5, -2, 0, 3, 6])


if __name__ == '__main__':
    unittest.main()
