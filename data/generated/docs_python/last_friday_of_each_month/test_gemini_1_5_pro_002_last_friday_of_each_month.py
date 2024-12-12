import unittest
import calendar
from last_friday_of_each_month import last_fridays
from io import StringIO
import sys

class LastFridaysTest(unittest.TestCase):

    def test_last_fridays_2012(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        last_fridays(2012)
        sys.stdout = sys.__stdout__
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
        self.assertEqual(captured_output.getvalue(), expected_output)

    def test_last_fridays_2023(self):
        captured_output = StringIO()
        sys.stdout = captured_output
        last_fridays(2023)
        sys.stdout = sys.__stdout__
        expected_output = (
            "2023-01-27\n"
            "2023-02-24\n"
            "2023-03-31\n"
            "2023-04-28\n"
            "2023-05-26\n"
            "2023-06-30\n"
            "2023-07-28\n"
            "2023-08-25\n"
            "2023-09-29\n"
            "2023-10-27\n"
            "2023-11-24\n"
            "2023-12-29\n"
        )
        self.assertEqual(captured_output.getvalue(), expected_output)

    def test_last_fridays_1900(self):  # Testing a different century
        captured_output = StringIO()
        sys.stdout = captured_output
        last_fridays(1900)
        sys.stdout = sys.__stdout__
        expected_output = (
            "1900-01-26\n"
            "1900-02-23\n"
            "1900-03-30\n"
            "1900-04-27\n"
            "1900-05-25\n"
            "1900-06-29\n"
            "1900-07-27\n"
            "1900-08-31\n"
            "1900-09-28\n"
            "1900-10-26\n"
            "1900-11-30\n"
            "1900-12-28\n"

        )
        self.assertEqual(captured_output.getvalue(), expected_output)

