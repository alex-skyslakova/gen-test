import unittest
from io import StringIO
import sys
import calendar

# Assuming the function to test is named `last_fridays` and is in a module named `last_friday_of_each_month`
from last_friday_of_each_month import last_fridays

class TestLastFridays(unittest.TestCase):

    def test_last_fridays_2012(self):
        expected_output = (
            "2012-01-27\n"
            "2012-02-24\n"
            "2012-03-30\n"
            "2012-04-27\n"
            "2012-05-25\n"
            "2012-06-29\n"
            "2012-07-27\n"
            "2012-08-31\n"
            "2012-09-28\n"
            "2012-10-26\n"
            "2012-11-30\n"
            "2012-12-28\n"
        )
        self._assert_last_fridays_output(2012, expected_output)

    def test_last_fridays_2020(self):
        expected_output = (
            "2020-01-31\n"
            "2020-02-28\n"
            "2020-03-27\n"
            "2020-04-24\n"
            "2020-05-29\n"
            "2020-06-26\n"
            "2020-07-31\n"
            "2020-08-28\n"
            "2020-09-25\n"
            "2020-10-30\n"
            "2020-11-27\n"
            "2020-12-25\n"
        )
        self._assert_last_fridays_output(2020, expected_output)

    def test_last_fridays_2021(self):
        expected_output = (
            "2021-01-29\n"
            "2021-02-26\n"
            "2021-03-26\n"
            "2021-04-30\n"
            "2021-05-28\n"
            "2021-06-25\n"
            "2021-07-30\n"
            "2021-08-27\n"
            "2021-09-24\n"
            "2021-10-29\n"
            "2021-11-26\n"
            "2021-12-31\n"
        )
        self._assert_last_fridays_output(2021, expected_output)

    def _assert_last_fridays_output(self, year, expected_output):
        captured_output = StringIO()
        sys.stdout = captured_output
        last_fridays(year)
        sys.stdout = sys.__stdout__
        self.assertEqual(captured_output.getvalue(), expected_output)

if __name__ == '__main__':
    unittest.main()
