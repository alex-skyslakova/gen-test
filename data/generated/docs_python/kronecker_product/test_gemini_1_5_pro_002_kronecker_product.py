import unittest
from kronecker_product import kronecker

class TestKronecker(unittest.TestCase):

    def test_wikipedia_example(self):
        a = [[1, 2], [3, 4]]
        b = [[0, 5], [6, 7]]
        expected_result = [[0, 5, 0, 10], [6, 7, 12, 14], [0, 15, 0, 20], [18, 21, 24, 28]]
        self.assertEqual(kronecker(a, b), expected_result)

    def test_second_example(self):
        a = [[0, 1, 0], [1, 1, 1], [0, 1, 0]]
        b = [[1, 1, 1, 1], [1, 0, 0, 1], [1, 1, 1, 1]]
        expected_result = [[0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0],
                          [0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0], 
                          [0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0],
                          [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                          [1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1],
                          [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                          [0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0],
                          [0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0],
                          [0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0]]
        self.assertEqual(kronecker(a, b), expected_result)

    def test_empty_matrices(self):
        self.assertEqual(kronecker([], []), [])
        self.assertEqual(kronecker([[1]], []), [])
        self.assertEqual(kronecker([], [[1]]), [])


    def test_single_element_matrices(self):
        self.assertEqual(kronecker([[2]], [[3]]), [[6]])

    def test_different_dimensions(self):
        a = [[1, 2], [3, 4]]
        b = [[5, 6, 7], [8, 9, 10]]
        expected_result = [[5, 6, 7, 10, 12, 14], [8, 9, 10, 16, 18, 20], [15, 18, 21, 20, 24, 28], [24, 27, 30, 32, 36, 40]]
        self.assertEqual(kronecker(a,b), expected_result)


if __name__ == '__main__':
    unittest.main()
