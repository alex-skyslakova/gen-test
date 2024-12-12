import unittest
from io import StringIO
import sys
from department_numbers import solve

class TestDepartmentNumbers(unittest.TestCase):
    def test_solve_output(self):
        # Redirect stdout to capture print statements
        captured_output = StringIO()
        sys.stdout = captured_output
        
        # Call the solve function
        solve()
        
        # Reset redirect.
        sys.stdout = sys.__stdout__
        
        # Get the output
        output = captured_output.getvalue().strip()
        
        # Expected output
        expected_output = (
            "  \\  Police Fire Sanitation\n"
            "  1:   2     3       7     \n"
            "  2:   2     7       3     \n"
            "  3:   4     1       7     \n"
            "  4:   4     7       1     \n"
            "  5:   4     5       3     \n"
            "  6:   4     3       5     \n"
            "  7:   6     1       5     \n"
            "  8:   6     5       1     \n"
            "  9:   6     3       3     "
        )
        
        # Assert the output is as expected
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()
