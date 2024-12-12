import unittest
from equilibrium_index import eqindex2Pass

class TestEquilibriumIndex(unittest.TestCase):
    
    def test_single_equilibrium_index(self):
        data = [-7, 1, 5, 2, -4, 3, 0]
        expected = [3, 6]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_no_equilibrium_index(self):
        data = [1, 2, 3, 4, 5]
        expected = []
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_all_zeros(self):
        data = [0, 0, 0, 0, 0]
        expected = [0, 1, 2, 3, 4]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_empty_list(self):
        data = []
        expected = []
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_single_element(self):
        data = [10]
        expected = [0]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_two_elements(self):
        data = [1, -1]
        expected = []
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_large_numbers(self):
        data = [1000000, -999999, -1, 0, 0, 0, 0]
        expected = [3]
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)
    
    def test_negative_numbers(self):
        data = [-1, -2, -3, -4, -5, -6]
        expected = []
        result = list(eqindex2Pass(data))
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
