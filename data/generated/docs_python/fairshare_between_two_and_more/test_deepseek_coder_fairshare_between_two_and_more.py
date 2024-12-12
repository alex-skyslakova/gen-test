import unittest
from itertools import islice
from fairshare_between_two_and_more import fairshare, _basechange_int

class TestFairshareSequence(unittest.TestCase):

    def test_basechange_int(self):
        # Test cases for _basechange_int function
        self.assertEqual(_basechange_int(0, 2), [0])
        self.assertEqual(_basechange_int(1, 2), [1])
        self.assertEqual(_basechange_int(2, 2), [1, 0])
        self.assertEqual(_basechange_int(3, 2), [1, 1])
        self.assertEqual(_basechange_int(0, 3), [0])
        self.assertEqual(_basechange_int(1, 3), [1])
        self.assertEqual(_basechange_int(2, 3), [2])
        self.assertEqual(_basechange_int(3, 3), [1, 0])
        self.assertEqual(_basechange_int(4, 3), [1, 1])
        self.assertEqual(_basechange_int(5, 3), [1, 2])
        self.assertEqual(_basechange_int(6, 3), [2, 0])
        self.assertEqual(_basechange_int(7, 3), [2, 1])
        self.assertEqual(_basechange_int(8, 3), [2, 2])
        self.assertEqual(_basechange_int(9, 3), [1, 0, 0])
        self.assertEqual(_basechange_int(10, 3), [1, 0, 1])

    def test_fairshare_sequence(self):
        # Test cases for fairshare function
        self.assertEqual(list(islice(fairshare(2), 25)), [0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0])
        self.assertEqual(list(islice(fairshare(3), 25)), [0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 1])
        self.assertEqual(list(islice(fairshare(5), 25)), [0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3])
        self.assertEqual(list(islice(fairshare(11), 25)), [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4])

if __name__ == '__main__':
    unittest.main()
