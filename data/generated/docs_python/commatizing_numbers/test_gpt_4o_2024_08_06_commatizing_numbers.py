import unittest

class TestCommatize(unittest.TestCase):
    
    def test_pi_with_spaces(self):
        result = Commatize("pi=3.14159265358979323846264338327950288419716939937510582097494459231", 0, 5, " ")
        self.assertEqual(result, "pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231")

    def test_zimbabwe_dollars_with_period(self):
        result = Commatize("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, ".")
        self.assertEqual(result, "The author has two Z$100.000.000.000.000 Zimbabwe notes (100 trillion).")

    def test_aus_dollars_default(self):
        result = Commatize("\"-in Aus$+1411.8millions\"")
        self.assertEqual(result, "\"-in Aus$+1,411.8millions\"")

    def test_us_dollars_with_leading_zeros(self):
        result = Commatize("===US$0017440 millions=== (in 2000 dollars)")
        self.assertEqual(result, "===US$0017,440 millions=== (in 2000 dollars)")

    def test_exponent_not_commatized(self):
        result = Commatize("123.e8000 is pretty big.")
        self.assertEqual(result, "123.e8000 is pretty big.")

    def test_land_area_of_earth(self):
        result = Commatize("The land area of the earth is 57268900(29% of the surface) square miles.")
        self.assertEqual(result, "The land area of the earth is 57,268,900(29% of the surface) square miles.")

    def test_no_numbers(self):
        result = Commatize("Ain't no numbers in this here words, nohow, no way, Jose.")
        self.assertEqual(result, "Ain't no numbers in this here words, nohow, no way, Jose.")

    def test_leading_zeros_not_commatized(self):
        result = Commatize("James was never known as 0000000007")
        self.assertEqual(result, "James was never known as 0000000007")

    def test_eddington_number(self):
        result = Commatize("Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe.")
        self.assertEqual(result, "Arthur Eddington wrote: I believe there are 15,747,724,136,275,002,577,605,653,961,181,555,468,044,717,914,527,116,709,366,231,425,076,185,631,031,296 protons in the universe.")

    def test_leading_blanks(self):
        result = Commatize("   $-140000±100 millions.")
        self.assertEqual(result, "   $-140,000±100 millions.")

    def test_date_format(self):
        result = Commatize("6/9/1946 was a good year for some.")
        self.assertEqual(result, "6/9/1946 was a good year for some.")

if __name__ == '__main__':
    unittest.main()
