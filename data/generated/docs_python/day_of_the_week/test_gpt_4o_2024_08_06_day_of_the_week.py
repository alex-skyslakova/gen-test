import unittest
from calendar import weekday, SUNDAY

def get_sundays_on_dec_25(start_year, end_year):
    return [year for year in range(start_year, end_year + 1) if weekday(year, 12, 25) == SUNDAY]

class TestXmasSunday(unittest.TestCase):
    
    def test_xmas_sundays(self):
        expected_years = [
            2011, 2016, 2022, 2033, 2039, 2044, 2050, 2061, 2067, 2072, 
            2078, 2089, 2095, 2100, 2106, 2117
        ]
        result = get_sundays_on_dec_25(2008, 2121)
        self.assertEqual(result, expected_years)

    def test_no_sundays_in_short_range(self):
        result = get_sundays_on_dec_25(2008, 2010)
        self.assertEqual(result, [])

    def test_single_year_sunday(self):
        result = get_sundays_on_dec_25(2016, 2016)
        self.assertEqual(result, [2016])

    def test_single_year_not_sunday(self):
        result = get_sundays_on_dec_25(2015, 2015)
        self.assertEqual(result, [])

    def test_edge_case_start_year(self):
        result = get_sundays_on_dec_25(2011, 2011)
        self.assertEqual(result, [2011])

    def test_edge_case_end_year(self):
        result = get_sundays_on_dec_25(2117, 2117)
        self.assertEqual(result, [2117])

if __name__ == '__main__':
    unittest.main()
