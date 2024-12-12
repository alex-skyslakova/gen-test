import unittest
import sys

class TestRecursionLimit(unittest.TestCase):

    def test_recursion_limit(self):
        # Capture the printed output
        with unittest.mock.patch('sys.stdout') as fake_out:
            # Dynamically import to avoid actually running the print statement outside of test
            from find_limit_of_recursion import main  # Note: replace 'find_limit_of_recursion' with actual filename if different

            if hasattr(main, "main"): # Call main function if it exists
                main()
            
            # Check if the output matches the expected recursion limit (as a string)
            self.assertEqual(fake_out.getvalue().strip(), str(sys.getrecursionlimit()))

        # Directly check sys.getrecursionlimit() being printed
        with unittest.mock.patch('sys.stdout') as fake_out:
             import find_limit_of_recursion # Import directly in this test setup.

             self.assertEqual(fake_out.getvalue().strip(), str(sys.getrecursionlimit()))




if __name__ == '__main__':
    unittest.main()

