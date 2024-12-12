import unittest
import random
from unittest.mock import patch

class Test100Prisoners(unittest.TestCase):

    def setUp(self):
        random.seed(42)  # Set a seed for reproducibility

    def test_play_random_all_prisoners_find_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.reverse()  # Ensure each prisoner finds their number
            result = play_random(1)
            self.assertEqual(result, 100.0)

    def test_play_random_no_prisoner_finds_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.sort()  # Ensure no prisoner finds their number
            result = play_random(1)
            self.assertEqual(result, 0.0)

    def test_play_random_some_prisoners_find_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.sort(reverse=True)  # Some prisoners find their number
            result = play_random(1)
            self.assertGreater(result, 0.0)
            self.assertLess(result, 100.0)

    def test_play_optimal_all_prisoners_find_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.reverse()  # Ensure each prisoner finds their number
            result = play_optimal(1)
            self.assertEqual(result, 100.0)

    def test_play_optimal_no_prisoner_finds_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.sort()  # Ensure no prisoner finds their number
            result = play_optimal(1)
            self.assertEqual(result, 0.0)

    def test_play_optimal_some_prisoners_find_their_number(self):
        with patch('random.shuffle') as mock_shuffle:
            mock_shuffle.side_effect = lambda x: x.sort(reverse=True)  # Some prisoners find their number
            result = play_optimal(1)
            self.assertGreater(result, 0.0)
            self.assertLess(result, 100.0)

    def test_play_random_multiple_simulations(self):
        result = play_random(1000)
        self.assertGreaterEqual(result, 0.0)
        self.assertLessEqual(result, 100.0)

    def test_play_optimal_multiple_simulations(self):
        result = play_optimal(1000)
        self.assertGreaterEqual(result, 0.0)
        self.assertLessEqual(result, 100.0)

if __name__ == '__main__':
    unittest.main()
