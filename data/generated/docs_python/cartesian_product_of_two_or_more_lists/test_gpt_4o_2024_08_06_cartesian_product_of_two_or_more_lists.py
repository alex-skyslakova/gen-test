import unittest
from cartesian_product_of_two_or_more_lists import cp

class TestCartesianProduct(unittest.TestCase):
    
    def test_two_lists(self):
        # Test case for {1, 2} × {3, 4}
        result = cp([[1, 2], [3, 4]])
        expected = [(1, 3), (1, 4), (2, 3), (2, 4)]
        self.assertEqual(result, expected)
        
        # Test case for {3, 4} × {1, 2}
        result = cp([[3, 4], [1, 2]])
        expected = [(3, 1), (3, 2), (4, 1), (4, 2)]
        self.assertEqual(result, expected)

    def test_empty_list(self):
        # Test case for {1, 2} × {}
        result = cp([[1, 2], []])
        expected = []
        self.assertEqual(result, expected)
        
        # Test case for {} × {1, 2}
        result = cp([[], [1, 2]])
        expected = []
        self.assertEqual(result, expected)

    def test_n_ary_product(self):
        # Test case for {1776, 1789} × {7, 12} × {4, 14, 23} × {0, 1}
        result = cp([[1776, 1789], [7, 12], [4, 14, 23], [0, 1]])
        expected = [
            (1776, 7, 4, 0), (1776, 7, 4, 1), (1776, 7, 14, 0), (1776, 7, 14, 1), 
            (1776, 7, 23, 0), (1776, 7, 23, 1), (1776, 12, 4, 0), (1776, 12, 4, 1), 
            (1776, 12, 14, 0), (1776, 12, 14, 1), (1776, 12, 23, 0), (1776, 12, 23, 1), 
            (1789, 7, 4, 0), (1789, 7, 4, 1), (1789, 7, 14, 0), (1789, 7, 14, 1), 
            (1789, 7, 23, 0), (1789, 7, 23, 1), (1789, 12, 4, 0), (1789, 12, 4, 1), 
            (1789, 12, 14, 0), (1789, 12, 14, 1), (1789, 12, 23, 0), (1789, 12, 23, 1)
        ]
        self.assertEqual(result, expected)

        # Test case for {1, 2, 3} × {30} × {500, 100}
        result = cp([[1, 2, 3], [30], [500, 100]])
        expected = [
            (1, 30, 500), (1, 30, 100), (2, 30, 500), (2, 30, 100), (3, 30, 500), (3, 30, 100)
        ]
        self.assertEqual(result, expected)

        # Test case for {1, 2, 3} × {} × {500, 100}
        result = cp([[1, 2, 3], [], [500, 100]])
        expected = []
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
