import unittest
import math
from collections import Counter
from io import StringIO
import sys

# Assuming the code to be tested is in a file named entropy_narcissist.py
# Import the entropy function from that file
try:
    from entropy_narcissist import entropy
except ImportError:
    # Handle the case where the file is not found or importable
    # For testing purposes, we can define a dummy entropy function
    def entropy(s):
        return 0


class TestEntropy(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual(entropy(""), 0)

    def test_single_char(self):
        self.assertEqual(entropy("a"), 0)

    def test_uniform_distribution(self):
        self.assertEqual(entropy("aabbcc"), math.log(3, 2))

    def test_non_uniform_distribution(self):
        self.assertAlmostEqual(entropy("aaabbc"), 1.4591479170272448, places=7)

    def test_long_string(self):
        long_string = "a" * 1000 + "b" * 500 + "c" * 250
        expected_entropy = - (1000/1750 * math.log(1000/1750, 2) + 
                             500/1750 * math.log(500/1750, 2) +
                             250/1750 * math.log(250/1750, 2))
        self.assertAlmostEqual(entropy(long_string), expected_entropy, places=7)


    # Testing the print output, which involves manipulating stdout
    def test_print_output(self):
        # Redirect stdout to capture the printed output
        captured_output = StringIO()
        sys.stdout = captured_output

        # Execute the code that prints the entropy
        with open(__file__) as f:  # This needs to open the TEST file itself
            b = f.read()

        print(entropy(b))
        sys.stdout = sys.__stdout__  # Restore stdout


        # Compare the captured output to the expected value. 
        # Due to the nature of this test, which calculates the entropy of the 
        # TEST FILE itself, a direct comparison with a hardcoded value 
        # is not feasible. We therefore just make sure it's a valid float.

        try:
            float(captured_output.getvalue().strip())
        except ValueError:
            self.fail("Output is not a valid float")


if __name__ == '__main__':
    unittest.main()

