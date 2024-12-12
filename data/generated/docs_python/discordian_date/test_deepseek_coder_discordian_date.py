import unittest
from discordian_date import ddate, DISCORDIAN_SEASONS

class TestDiscordianDate(unittest.TestCase):

    def test_normal_date(self):
        # Test a normal date in a non-leap year
        self.assertEqual(ddate(2023, 3, 15), "Discord 55, YOLD 3189")

    def test_leap_year_non_leap_day(self):
        # Test a normal date in a leap year
        self.assertEqual(ddate(2024, 3, 15), "Discord 55, YOLD 3190")

    def test_st_tibs_day(self):
        # Test St. Tib's Day in a leap year
        self.assertEqual(ddate(2024, 2, 29), "St. Tib's Day, YOLD 3190")

    def test_leap_year_after_st_tibs_day(self):
        # Test a date after St. Tib's Day in a leap year
        self.assertEqual(ddate(2024, 3, 1), "Discord 59, YOLD 3190")

    def test_first_day_of_year(self):
        # Test the first day of the year
        self.assertEqual(ddate(2023, 1, 1), "Chaos 1, YOLD 3189")

    def test_last_day_of_year(self):
        # Test the last day of the year
        self.assertEqual(ddate(2023, 12, 31), "The Aftermath 73, YOLD 3189")

    def test_last_day_of_season(self):
        # Test the last day of a season
        self.assertEqual(ddate(2023, 3, 28), "Discord 73, YOLD 3189")

    def test_first_day_of_season(self):
        # Test the first day of a season
        self.assertEqual(ddate(2023, 6, 1), "Confusion 1, YOLD 3189")

    def test_invalid_date(self):
        # Test an invalid date (should raise an error)
        with self.assertRaises(ValueError):
            ddate(2023, 2, 30)

if __name__ == '__main__':
    unittest.main()
