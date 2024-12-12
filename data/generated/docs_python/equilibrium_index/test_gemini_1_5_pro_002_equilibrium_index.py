import unittest
from equilibrium_index import eqindex2Pass

class TestEqIndex(unittest.TestCase):

    def test_empty_list(self):
        self.assertEqual(list(eqindex2Pass([])), [])

    def test_single_element_list(self):
        self.assertEqual(list(eqindex2Pass([5])), [0])

    def test_example_case(self):
        self.assertEqual(list(eqindex2Pass([-7, 1, 5, 2, -4, 3, 0])), [3, 6])

    def test_all_elements_same(self):
        self.assertEqual(list(eqindex2Pass([5, 5, 5, 5, 5])), [2])

    def test_no_equilibrium_index(self):
        self.assertEqual(list(eqindex2Pass([1, 2, 3, 4, 5])), [])

    def test_all_zeros(self):
        self.assertEqual(list(eqindex2Pass([0, 0, 0, 0, 0])), [0, 1, 2, 3, 4])

    def test_negative_numbers(self):
        self.assertEqual(list(eqindex2Pass([-1, -2, -3, 0, 3, 2, 1])), [3])

    def test_large_list(self):
        large_list = [i for i in range(1000)]
        large_list[499] = -sum(large_list[:499])
        self.assertEqual(list(eqindex2Pass(large_list)), [499])



