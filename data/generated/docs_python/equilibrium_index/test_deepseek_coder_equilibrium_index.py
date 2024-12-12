import unittest

class TestEquilibriumIndex(unittest.TestCase):

    def test_no_equilibrium_index(self):
        data = [1, 2, 3, 4, 5]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [])

    def test_single_equilibrium_index(self):
        data = [-7, 1, 5, 2, -4, 3, 0]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [3, 6])

    def test_multiple_equilibrium_indices(self):
        data = [0, -3, 5, -4, -2, 3, 1, 0]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [0, 3, 7])

    def test_all_zeros(self):
        data = [0, 0, 0, 0, 0]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [0, 1, 2, 3, 4])

    def test_single_element(self):
        data = [42]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [0])

    def test_empty_list(self):
        data = []
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [])

    def test_negative_numbers_only(self):
        data = [-1, -2, -3, -4, -5]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [])

    def test_positive_numbers_only(self):
        data = [1, 2, 3, 4, 5]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [])

    def test_mixed_numbers(self):
        data = [1, -1, 1, -1, 1]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [0, 1, 2, 3, 4])

    def test_large_numbers(self):
        data = [1000000, -1000000, 1000000, -1000000]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, [1, 2])

if __name__ == '__main__':
    unittest.main()
