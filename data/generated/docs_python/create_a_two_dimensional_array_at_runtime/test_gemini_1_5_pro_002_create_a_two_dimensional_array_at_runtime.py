import unittest
import sys
from io import StringIO

class TestMyArray(unittest.TestCase):

    def setUp(self):
        self.held, sys.stdin = sys.stdin, StringIO("3\n2\n")  # Simulate user input

    def tearDown(self):
        sys.stdin = self.held  # Restore original stdin

    def test_create_and_access_array(self):
        # Execute the code (redirect stdout to capture print output)
        held, sys.stdout = sys.stdout, StringIO()
        import create_a_two_dimensional_array_at_runtime  # Import after setting stdin
        output = sys.stdout.getvalue().strip()
        sys.stdout = held

        self.assertEqual(output, "3.5")

    def test_different_dimensions(self):
        self.held, sys.stdin = sys.stdin, StringIO("5\n1\n")
        held, sys.stdout = sys.stdout, StringIO()
        import create_a_two_dimensional_array_at_runtime
        output = sys.stdout.getvalue().strip()
        sys.stdout = held
        self.assertEqual(output, "3.5")


    def test_zero_dimensions(self):
        self.held, sys.stdin = sys.stdin, StringIO("0\n0\n")
        held, sys.stdout = sys.stdout, StringIO()
        import create_a_two_dimensional_array_at_runtime
        output = sys.stdout.getvalue().strip()
        sys.stdout = held
        self.assertEqual(output, "3.5")

if __name__ == '__main__':
    unittest.main()

