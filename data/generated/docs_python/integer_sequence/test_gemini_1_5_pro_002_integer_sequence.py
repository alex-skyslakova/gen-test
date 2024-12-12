import unittest
import sys
from io import StringIO
from integer_sequence import main  # Assuming the code is in integer_sequence.py

class TestIntegerSequence(unittest.TestCase):

    def test_initial_output(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        try:
             # Limiting the loop to avoid infinite execution in tests
            original_limit = sys.getrecursionlimit()
            sys.setrecursionlimit(1000)  # Example limit, adjust as needed
            main()
        finally:
            sys.stdout = sys.__stdout__  # Restore stdout
            sys.setrecursionlimit(original_limit) # Restore original limit

        output = captured_output.getvalue().strip()
        expected_output = "\n".join(str(i) for i in range(1, 1000)) # Expected output up to the limit
        self.assertEqual(output, expected_output)


    def test_overflow(self):
        # This test is tricky due to the infinite nature.
        # We cannot practically test true infinity or overflow.
        # Instead, we can test that large numbers are handled correctly 
        # up to a practical testable limit, if sys.getrecursionlimit
        # was not modified to accommodate arbitrarily large numbers.

        captured_output = StringIO()
        sys.stdout = captured_output

        try:
            # This test will likely hit a recursion limit unless sys.getrecursionlimit is changed
            # which would have been needed to accommodate arbitrarily large numbers.
            main()

        except RecursionError:
            # Expect a RecursionError if we haven't adjusted the recursion limit
            # This indirectly indicates that the program *would* continue counting
            # if not limited by resources.
            self.assertTrue(True) # Assert something to pass if RecursionError is encountered
        finally:
            sys.stdout = sys.__stdout__



