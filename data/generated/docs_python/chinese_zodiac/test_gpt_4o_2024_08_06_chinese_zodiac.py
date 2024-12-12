import unittest
from io import StringIO
import sys
from chinese_zodiac import calculate

class TestChineseZodiac(unittest.TestCase):
    
    def setUp(self):
        # Redirect stdout to capture print statements
        self.held, sys.stdout = sys.stdout, StringIO()

    def tearDown(self):
        # Restore stdout
        sys.stdout = self.held

    def test_year_2022(self):
        calculate(2022)
        output = sys.stdout.getvalue().strip()
        expected_output = "2022: 壬寅 (rén-yín, Water Tiger; yang - year 39 of the cycle)"
        self.assertEqual(output, expected_output)

    def test_year_1984(self):
        calculate(1984)
        output = sys.stdout.getvalue().strip()
        expected_output = "1984: 甲子 (jiă-zĭ, Wood Rat; yang - year 1 of the cycle)"
        self.assertEqual(output, expected_output)

    def test_year_1935(self):
        calculate(1935)
        output = sys.stdout.getvalue().strip()
        expected_output = "1935: 乙亥 (yĭ-hài, Wood Pig; yin - year 32 of the cycle)"
        self.assertEqual(output, expected_output)

    def test_year_1968(self):
        calculate(1968)
        output = sys.stdout.getvalue().strip()
        expected_output = "1968: 戊申 (wù-shēn, Earth Monkey; yang - year 25 of the cycle)"
        self.assertEqual(output, expected_output)

    def test_year_1972(self):
        calculate(1972)
        output = sys.stdout.getvalue().strip()
        expected_output = "1972: 壬子 (rén-zĭ, Water Rat; yang - year 29 of the cycle)"
        self.assertEqual(output, expected_output)

    def test_year_1976(self):
        calculate(1976)
        output = sys.stdout.getvalue().strip()
        expected_output = "1976: 丙辰 (bĭng-chén, Fire Dragon; yang - year 33 of the cycle)"
        self.assertEqual(output, expected_output)

if __name__ == '__main__':
    unittest.main()
