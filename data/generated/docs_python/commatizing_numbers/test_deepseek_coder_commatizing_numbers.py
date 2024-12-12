import unittest
from commatizing_numbers import Commatize

class TestCommatize(unittest.TestCase):

    def test_pi_with_blanks(self):
        result = Commatize("pi=3.14159265358979323846264338327950288419716939937510582097494459231", 0, 5, " ")
        expected = "pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231"
        self.assertEqual(result, expected)

    def test_zimbabwe_dollar(self):
        result = Commatize("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, ".")
        expected = "The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion)."
        self.assertEqual(result, expected)

    def test_aus_dollar(self):
        result = Commatize("\"-in Aus$+1411.8millions\"")
        expected = "\"-in Aus$+1,411.8millions\""
        self.assertEqual(result, expected)

    def test_us_dollar(self):
        result = Commatize("===US$0017440 millions=== (in 2000 dollars)")
        expected = "===US$0017,440 millions=== (in 2000 dollars)"
        self.assertEqual(result, expected)

    def test_exponent_number(self):
        result = Commatize("123.e8000 is pretty big.")
        expected = "123.e8000 is pretty big."
        self.assertEqual(result, expected)

    def test_land_area(self):
        result = Commatize("The land area of the earth is 57268900(29% of the surface) square miles.")
        expected = "The land area of the earth is 57,268,900(29% of the surface) square miles."
        self.assertEqual(result, expected)

    def test_no_numbers(self):
        result = Commatize("Ain't no numbers in this here words, nohow, no way, Jose.")
        expected = "Ain't no numbers in this here words, nohow, no way, Jose."
        self.assertEqual(result, expected)

    def test_leading_zeroes(self):
        result = Commatize("James was never known as 0000000007")
        expected = "James was never known as 0000000007"
        self.assertEqual(result, expected)

    def test_eddington_number(self):
        result = Commatize("Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.")
        expected = "Arthur Eddington wrote: I believe there are 157,477,241,362,750,025,776,056,539,611,815,554,680,447,179,145,271,167,093,662,314,250,761,856,310,312,96 protons in the universe."
        self.assertEqual(result, expected)

    def test_negative_number_with_spaces(self):
        result = Commatize("␢␢␢$-140000±100 millions.")
        expected = "␢␢␢$-140,000±100 millions."
        self.assertEqual(result, expected)

    def test_date_format(self):
        result = Commatize("6/9/1946 was a good year for some.")
        expected = "6/9/1946 was a good year for some."
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
