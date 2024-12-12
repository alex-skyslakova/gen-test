import unittest
from day_of_the_week import weekday, SUNDAY

class TestChristmasOnSunday(unittest.TestCase):

    def test_christmas_on_sunday_between_2008_and_2121(self):
        # Known years when Christmas falls on a Sunday between 2008 and 2121
        known_years = [2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 2078, 2089, 2095, 2101, 2107, 2112, 2118]
        
        # Calculate the years using the provided code
        calculated_years = [year for year in range(2008, 2122) if weekday(year, 12, 25) == SUNDAY]
        
        # Assert that the calculated years match the known years
        self.assertEqual(calculated_years, known_years)

    def test_christmas_not_on_sunday(self):
        # Test a few years where Christmas is not on a Sunday
        non_sunday_years = [2008, 2009, 2010, 2012, 2013, 2014, 2015, 2017, 2018, 2019, 2020, 2021]
        
        for year in non_sunday_years:
            with self.subTest(year=year):
                self.assertNotEqual(weekday(year, 12, 25), SUNDAY)

    def test_edge_cases(self):
        # Test the first and last year in the range
        self.assertNotEqual(weekday(2008, 12, 25), SUNDAY)
        self.assertNotEqual(weekday(2121, 12, 25), SUNDAY)

if __name__ == '__main__':
    unittest.main()
