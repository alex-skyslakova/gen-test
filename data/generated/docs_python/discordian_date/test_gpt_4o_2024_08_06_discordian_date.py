import unittest
from discordian_date import ddate

class TestDiscordianDate(unittest.TestCase):
    
    def test_regular_date(self):
        # Test a regular date conversion
        self.assertEqual(ddate(2023, 1, 1), "Chaos 1, YOLD 3189")
        self.assertEqual(ddate(2023, 3, 1), "Discord 60, YOLD 3189")
        self.assertEqual(ddate(2023, 5, 1), "Confusion 59, YOLD 3189")
        self.assertEqual(ddate(2023, 7, 1), "Bureaucracy 59, YOLD 3189")
        self.assertEqual(ddate(2023, 9, 1), "The Aftermath 59, YOLD 3189")
        self.assertEqual(ddate(2023, 12, 31), "The Aftermath 73, YOLD 3189")

    def test_leap_year(self):
        # Test a date in a leap year
        self.assertEqual(ddate(2024, 1, 1), "Chaos 1, YOLD 3190")
        self.assertEqual(ddate(2024, 2, 28), "Chaos 59, YOLD 3190")
        self.assertEqual(ddate(2024, 3, 1), "Discord 59, YOLD 3190")
        self.assertEqual(ddate(2024, 12, 31), "The Aftermath 73, YOLD 3190")

    def test_st_tibs_day(self):
        # Test St. Tib's Day
        self.assertEqual(ddate(2024, 2, 29), "St. Tib's Day, YOLD 3190")

    def test_end_of_season(self):
        # Test the end of each Discordian season
        self.assertEqual(ddate(2023, 3, 15), "Chaos 73, YOLD 3189")
        self.assertEqual(ddate(2023, 5, 27), "Discord 73, YOLD 3189")
        self.assertEqual(ddate(2023, 8, 8), "Confusion 73, YOLD 3189")
        self.assertEqual(ddate(2023, 10, 19), "Bureaucracy 73, YOLD 3189")
        self.assertEqual(ddate(2023, 12, 31), "The Aftermath 73, YOLD 3189")

if __name__ == '__main__':
    unittest.main()
