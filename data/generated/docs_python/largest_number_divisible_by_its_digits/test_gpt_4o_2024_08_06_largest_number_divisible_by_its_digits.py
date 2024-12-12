import unittest
from largest_number_divisible_by_its_digits import intFromDigits, concatMap, delete, lcm

class TestLargestNumberDivisibleByItsDigits(unittest.TestCase):

    def test_intFromDigits(self):
        self.assertEqual(intFromDigits([1, 3, 5]), 135)
        self.assertEqual(intFromDigits([9, 8, 7, 6]), 9876)
        self.assertEqual(intFromDigits([]), 0)
        self.assertEqual(intFromDigits([0]), 0)

    def test_concatMap(self):
        f = lambda x: [x, x + 1]
        self.assertEqual(list(concatMap(f)([1, 2, 3])), [1, 2, 2, 3, 3, 4])
        self.assertEqual(list(concatMap(f)([])), [])

    def test_delete(self):
        self.assertEqual(delete([1, 2, 3, 4])(3), [1, 2, 4])
        self.assertEqual(delete([1, 2, 3, 4])(1), [2, 3, 4])
        self.assertEqual(delete([1, 2, 3, 4])(4), [1, 2, 3])
        self.assertEqual(delete([1, 2, 3, 4])(5), [1, 2, 3, 4])  # No change if element not found

    def test_lcm(self):
        self.assertEqual(lcm(3, 5), 15)
        self.assertEqual(lcm(10, 5), 10)
        self.assertEqual(lcm(0, 5), 0)
        self.assertEqual(lcm(7, 0), 0)
        self.assertEqual(lcm(6, 8), 24)

if __name__ == '__main__':
    unittest.main()
