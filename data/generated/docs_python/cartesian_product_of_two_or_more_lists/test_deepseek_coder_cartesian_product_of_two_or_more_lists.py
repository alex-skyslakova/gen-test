import unittest
from cartesian_product_of_two_or_more_lists import cp

class TestCartesianProduct(unittest.TestCase):

    def test_two_lists_product(self):
        self.assertEqual(cp([[1, 2], [3, 4]]), [(1, 3), (1, 4), (2, 3), (2, 4)])
        self.assertEqual(cp([[3, 4], [1, 2]]), [(3, 1), (3, 2), (4, 1), (4, 2)])

    def test_empty_list_product(self):
        self.assertEqual(cp([[1, 2], []]), [])
        self.assertEqual(cp([[], [1, 2]]), [])

    def test_n_ary_product(self):
        self.assertEqual(cp([[1776, 1789], [7, 12], [4, 14, 23], [0, 1]]),
                         [(1776, 7, 4, 0), (1776, 7, 4, 1), (1776, 7, 14, 0), (1776, 7, 14, 1), (1776, 7, 23, 0), (1776, 7, 23, 1),
                          (1776, 12, 4, 0), (1776, 12, 4, 1), (1776, 12, 14, 0), (1776, 12, 14, 1), (1776, 12, 23, 0), (1776, 12, 23, 1),
                          (1789, 7, 4, 0), (1789, 7, 4, 1), (1789, 7, 14, 0), (1789, 7, 14, 1), (1789, 7, 23, 0), (1789, 7, 23, 1),
                          (1789, 12, 4, 0), (1789, 12, 4, 1), (1789, 12, 14, 0), (1789, 12, 14, 1), (1789, 12, 23, 0), (1789, 12, 23, 1)])

        self.assertEqual(cp([[1, 2, 3], [30], [500, 100]]),
                         [(1, 30, 500), (1, 30, 100), (2, 30, 500), (2, 30, 100), (3, 30, 500), (3, 30, 100)])

        self.assertEqual(cp([[1, 2, 3], [], [500, 100]]), [])

if __name__ == '__main__':
    unittest.main()
