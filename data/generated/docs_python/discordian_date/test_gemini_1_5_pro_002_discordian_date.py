import unittest
import discordian_date as dd
import datetime

class TestDdate(unittest.TestCase):

    def test_regular_date(self):
        self.assertEqual(dd.ddate(2024, 1, 1), "Chaos 1, YOLD 3190")
        self.assertEqual(dd.ddate(2024, 5, 10), "Discord 37, YOLD 3190")
        self.assertEqual(dd.ddate(2024, 12, 31), "The Aftermath 73, YOLD 3190")

    def test_leap_year_before_st_tibs(self):
        self.assertEqual(dd.ddate(2024, 2, 28), "Chaos 59, YOLD 3190")

    def test_st_tibs_day(self):
        self.assertEqual(dd.ddate(2024, 2, 29), "St. Tib's Day, YOLD 3190")

    def test_leap_year_after_st_tibs(self):
        self.assertEqual(dd.ddate(2024, 3, 1), "Chaos 60, YOLD 3190")
        self.assertEqual(dd.ddate(2024, 12, 31), "The Aftermath 73, YOLD 3190")

    def test_non_leap_year(self):
        self.assertEqual(dd.ddate(2023, 12, 31), "The Aftermath 73, YOLD 3189")
        self.assertEqual(dd.ddate(2023, 2, 28), "Chaos 59, YOLD 3189")


if __name__ == '__main__':
    unittest.main()
