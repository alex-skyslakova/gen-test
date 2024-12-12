import unittest
from itertools import islice

# Assuming the functions _basechange_int and fairshare are imported from fairshare_between_two_and_more.py

class TestFairShare(unittest.TestCase):
    
    def test_basechange_int(self):
        # Test base conversion for base 3
        b = 3
        expected_results = [
            [0], [1], [2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2], [1, 0, 0], [1, 0, 1]
        ]
        for num, expected in enumerate(expected_results):
            self.assertEqual(_basechange_int(num, b), expected)
        
        # Test base conversion for base 5
        b = 5
        expected_results = [
            [0], [1], [2], [3], [4], [1, 0], [1, 1], [1, 2], [1, 3], [1, 4], [2, 0]
        ]
        for num, expected in enumerate(expected_results):
            self.assertEqual(_basechange_int(num, b), expected)
        
    def test_fairshare_two_people(self):
        # Test fairshare sequence for two people
        expected_sequence = [0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1]
        self.assertEqual(list(islice(fairshare(2), 25)), expected_sequence)
        
    def test_fairshare_three_people(self):
        # Test fairshare sequence for three people
        expected_sequence = [0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 2, 0, 1, 1, 2, 0, 2, 0, 1, 2]
        self.assertEqual(list(islice(fairshare(3), 25)), expected_sequence)
        
    def test_fairshare_five_people(self):
        # Test fairshare sequence for five people
        expected_sequence = [0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3]
        self.assertEqual(list(islice(fairshare(5), 25)), expected_sequence)
        
    def test_fairshare_eleven_people(self):
        # Test fairshare sequence for eleven people
        expected_sequence = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4]
        self.assertEqual(list(islice(fairshare(11), 25)), expected_sequence)

if __name__ == '__main__':
    unittest.main()
