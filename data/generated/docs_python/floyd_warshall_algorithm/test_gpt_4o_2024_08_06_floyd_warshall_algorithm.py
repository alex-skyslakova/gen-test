import unittest
from io import StringIO
import sys

# Assuming the floyd_warshall function is defined in a module named floyd_warshall_algorithm
from floyd_warshall_algorithm import floyd_warshall

class TestFloydWarshall(unittest.TestCase):
    
    def setUp(self):
        # Redirect stdout to capture print statements
        self.held_stdout = StringIO()
        sys.stdout = self.held_stdout

    def tearDown(self):
        # Reset redirect.
        sys.stdout = sys.__stdout__

    def test_floyd_warshall(self):
        # Test case from the example
        floyd_warshall(4, [[1, 3, -2], [2, 1, 4], [2, 3, 3], [3, 4, 2], [4, 2, -1]])
        
        # Expected output
        expected_output = (
            "pair     dist    path\n"
            "1 → 2    -1       1 → 3 → 4 → 2\n"
            "1 → 3    -2       1 → 3\n"
            "1 → 4     0       1 → 3 → 4\n"
            "2 → 1     4       2 → 1\n"
            "2 → 3     2       2 → 1 → 3\n"
            "2 → 4     4       2 → 1 → 3 → 4\n"
            "3 → 1     5       3 → 4 → 2 → 1\n"
            "3 → 2     1       3 → 4 → 2\n"
            "3 → 4     2       3 → 4\n"
            "4 → 1     3       4 → 2 → 1\n"
            "4 → 2    -1       4 → 2\n"
            "4 → 3     1       4 → 2 → 1 → 3\n"
        )
        
        # Get the output and compare
        output = self.held_stdout.getvalue()
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()
