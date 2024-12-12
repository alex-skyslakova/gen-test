import unittest
from datetime import date
from date_format import today

class TestDateFormat(unittest.TestCase):

    def test_iso_format(self):
        expected_format = today.isoformat()
        self.assertEqual(expected_format, date.today().isoformat())

    def test_strftime_format(self):
        expected_format = today.strftime("%A, %B %d, %Y")
        self.assertEqual(expected_format, date.today().strftime("%A, %B %d, %Y"))

    def test_positional_format(self):
        expected_format = "The date is {0:%A, %B %d, %Y}".format(today)
        self.assertEqual(expected_format, "The date is {0:%A, %B %d, %Y}".format(date.today()))

    def test_keyword_format(self):
        expected_format = "The date is {date:%A, %B %d, %Y}".format(date=today)
        self.assertEqual(expected_format, "The date is {date:%A, %B %d, %Y}".format(date=date.today()))

    def test_f_string_format(self):
        expected_format = f"The date is {today:%A, %B %d, %Y}"
        self.assertEqual(expected_format, f"The date is {date.today():%A, %B %d, %Y}")

if __name__ == '__main__':
    unittest.main()
