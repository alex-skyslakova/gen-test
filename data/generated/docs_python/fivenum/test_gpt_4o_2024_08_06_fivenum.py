import unittest
from fivenum import fivenum

class TestFiveNum(unittest.TestCase):

    def test_typical_case(self):
        array = [0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594, 
                 0.73438555, -0.03035726, 1.46675970, -0.74621349, -0.72588772, 
                 0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469, 
                 0.66206163, 1.04312009, -0.10305385, 0.75775634, 0.32566578]
        expected = [-1.95059594, -0.74621349, 0.32566578, 0.75775634, 1.73131507]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_empty_array(self):
        with self.assertRaises(SystemExit):
            fivenum([])

    def test_single_element_array(self):
        array = [42]
        expected = [42, 42, 42, 42, 42]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_two_elements_array(self):
        array = [1, 3]
        expected = [1, 1, 2, 3, 3]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_odd_number_of_elements(self):
        array = [1, 2, 3, 4, 5]
        expected = [1, 1.5, 3, 4.5, 5]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_even_number_of_elements(self):
        array = [1, 2, 3, 4, 5, 6]
        expected = [1, 1.75, 3.5, 5.25, 6]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_negative_numbers(self):
        array = [-5, -1, -3, -4, -2]
        expected = [-5, -4, -3, -2, -1]
        result = fivenum(array)
        self.assertEqual(result, expected)

    def test_identical_elements(self):
        array = [7, 7, 7, 7, 7]
        expected = [7, 7, 7, 7, 7]
        result = fivenum(array)
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
