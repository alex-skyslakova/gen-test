import unittest
from itertools import islice
from fairshare_between_two_and_more import fairshare, _basechange_int

class TestFairshare(unittest.TestCase):

    def test_basechange_int(self):
        self.assertEqual(_basechange_int(0, 3), [0])
        self.assertEqual(_basechange_int(1, 3), [1])
        self.assertEqual(_basechange_int(2, 3), [2])
        self.assertEqual(_basechange_int(3, 3), [1, 0])
        self.assertEqual(_basechange_int(10, 3), [1, 0, 1])
        self.assertEqual(_basechange_int(11, 2), [1, 0, 1, 1])
        self.assertEqual(_basechange_int(5, 5), [1, 0])
        self.assertEqual(_basechange_int(10, 11), [10])  # Test with base > 10


    def test_fairshare_2(self):
        expected = [0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0]
        self.assertEqual(list(islice(fairshare(2), 25)), expected)

    def test_fairshare_3(self):
        expected = [0, 1, 2, 1, 2, 0, 2, 0, 1, 0, 1, 2, 1, 2, 0, 0, 1, 2, 2, 0, 1, 0, 1, 2, 0]
        self.assertEqual(list(islice(fairshare(3), 25)), expected)

    def test_fairshare_5(self):
        expected = [0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3]
        self.assertEqual(list(islice(fairshare(5), 25)), expected)

    def test_fairshare_11(self):
        expected = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4]
        self.assertEqual(list(islice(fairshare(11), 25)), expected)



if __name__ == '__main__':
    unittest.main()
