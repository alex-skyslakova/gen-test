import unittest
from itertools import chain

def _range(x, y, z=1):
    return range(x, y + (1 if z > 0 else -1), z)

def calculate_sum_and_prod(x, y, z, one, three, seven):
    prod, sum_ = 1, 0
    for j in chain(_range(-three, 3**3, three), _range(-seven, seven, x), 
                   _range(555, 550 - y), _range(22, -28, -three),
                   _range(1927, 1939), _range(x, y, z),
                   _range(11**x, 11**x + 1)):
        sum_ += abs(j)
        if abs(prod) < 2**27 and (j != 0):
            prod *= j
    return sum_, prod

class TestSumProd(unittest.TestCase):

    def test_given_values(self):
        x, y, z, one, three, seven = 5, -5, -2, 1, 3, 7
        expected_sum = 14245
        expected_prod = -1486442880
        sum_, prod = calculate_sum_and_prod(x, y, z, one, three, seven)
        self.assertEqual(sum_, expected_sum)
        self.assertEqual(prod, expected_prod)
    
    def test_zero_increment(self):
        # Test case where increment is zero, expecting a ValueError
        x, y, z, one, three, seven = 5, -5, 0, 1, 3, 7
        with self.assertRaises(ValueError):
             _range(x,y,z)

    def test_single_range(self):
         x, y, z, one, three, seven = 1, 5, 1, 1, 3, 7
         expected_sum = 20
         expected_prod = 120

         sum_, prod = calculate_sum_and_prod(x, y, z, one, three, seven)
         self.assertEqual(sum_, expected_sum)
         self.assertEqual(prod, expected_prod)


    def test_empty_ranges(self):
        #Testing for empty ranges, ensuring sum and prod are calculated correctly.
        x, y, z, one, three, seven = 5, -5, -2, 1, 3, 7
        sum_, prod = calculate_sum_and_prod(y, x, z, one, three, seven)  # y > x with negative z, empty range
        self.assertEqual(sum_, 13295)  # other ranges contribute to sum
        self.assertEqual(prod, 0)
        sum_, prod = calculate_sum_and_prod(x, y, -z, one, three, seven)  # x > y with positive -z, empty range
        self.assertEqual(sum_, 13295)
        self.assertEqual(prod, 0)

if __name__ == '__main__':
    unittest.main()
