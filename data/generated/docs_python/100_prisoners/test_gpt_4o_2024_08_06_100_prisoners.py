import unittest
from unittest.mock import patch
import random
from io import StringIO
import sys

# Assuming the functions play_random and play_optimal are imported from 100_prisoners.py

class TestPrisonersProblem(unittest.TestCase):

    def test_play_random(self):
        # Test the play_random function with a fixed seed for reproducibility
        random.seed(0)
        result = play_random(1000)
        self.assertIsInstance(result, float)
        self.assertGreaterEqual(result, 0.0)
        self.assertLessEqual(result, 100.0)

    def test_play_optimal(self):
        # Test the play_optimal function with a fixed seed for reproducibility
        random.seed(0)
        result = play_optimal(1000)
        self.assertIsInstance(result, float)
        self.assertGreaterEqual(result, 0.0)
        self.assertLessEqual(result, 100.0)

    def test_output(self):
        # Test the output of the main block
        with patch('sys.stdout', new=StringIO()) as fake_out:
            n = 1000
            random.seed(0)
            print(" Simulation count:", n)
            print(f" Random play wins: {play_random(n):4.1f}% of simulations")
            print(f"Optimal play wins: {play_optimal(n):4.1f}% of simulations")
            output = fake_out.getvalue().strip()
            self.assertIn("Simulation count: 1000", output)
            self.assertIn("Random play wins:", output)
            self.assertIn("Optimal play wins:", output)

if __name__ == '__main__':
    unittest.main()
