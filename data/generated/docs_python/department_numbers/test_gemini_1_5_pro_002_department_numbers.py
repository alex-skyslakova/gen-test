import unittest
from unittest.mock import patch
from io import StringIO
from department_numbers import solve

class TestDepartmentNumbers(unittest.TestCase):

    @patch('sys.stdout', new_callable=StringIO)
    def test_solve(self, mock_stdout):
        solve()
        expected_output = "\\,Police,Fire,Sanitation\n" \
                         "  1:   2     3      7     \n" \
                         "  2:   2     7      3     \n" \
                         "  3:   4     1      7     \n" \
                         "  4:   4     7      1     \n" \
                         "  5:   6     1      5     \n" \
                         "  6:   6     5      1     \n"

        self.assertEqual(mock_stdout.getvalue(), expected_output)
