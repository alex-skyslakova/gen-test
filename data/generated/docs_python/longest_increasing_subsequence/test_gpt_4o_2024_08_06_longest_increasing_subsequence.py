import unittest
from longest_increasing_subsequence import longest_increasing_subsequence

class TestLongestIncreasingSubsequence(unittest.TestCase):

    def test_example_case_1(self):
        data = [3, 2, 6, 4, 5, 1]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [2, 4, 5])  # One possible LIS

    def test_example_case_2(self):
        data = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [0, 2, 6, 9, 11, 15])  # One possible LIS

    def test_empty_list(self):
        data = []
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [])

    def test_single_element(self):
        data = [10]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [10])

    def test_all_decreasing(self):
        data = [5, 4, 3, 2, 1]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [5])  # Any single element is a valid LIS

    def test_all_increasing(self):
        data = [1, 2, 3, 4, 5]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [1, 2, 3, 4, 5])

    def test_duplicate_elements(self):
        data = [3, 3, 3, 3, 3]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [3])  # Any single element is a valid LIS

    def test_mixed_elements(self):
        data = [10, 22, 9, 33, 21, 50, 41, 60, 80]
        result = longest_increasing_subsequence(data)
        self.assertEqual(result, [10, 22, 33, 50, 60, 80])  # One possible LIS

if __name__ == '__main__':
    unittest.main()
