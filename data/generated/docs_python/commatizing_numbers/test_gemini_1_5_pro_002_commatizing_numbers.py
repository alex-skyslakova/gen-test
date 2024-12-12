import unittest
import re as RegEx

def Commatize( _string, _startPos=0, _periodLen=3, _separator="," ):
    # ... (Implementation remains unchanged)


class TestCommatize(unittest.TestCase):

    def test_pi_with_spaces(self):
        self.assertEqual(Commatize("pi=3.14159265358979323846264338327950288419716939937510582097494459231", 0, 5, " "), "pi=3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59231")

    def test_zimbabwe_dollars(self):
        self.assertEqual(Commatize("The author has two Z$100000000000000 Zimbabwe notes (100 trillion).", 0, 3, "."), "The author has two Z$10.000.000.000.000 Zimbabwe notes (100 trillion).")

    def test_australian_dollars(self):
        self.assertEqual(Commatize("\"-in Aus$+1411.8millions\""), "\"-in Aus$+1,411.8millions\"")  # Default separator and period length

    def test_us_dollars(self):
        self.assertEqual(Commatize("===US$0017440 millions=== (in 2000 dollars)"), "===US$001,7440 millions=== (in 2000 dollars)") # Demonstrates leading zeros preservation

    def test_scientific_notation(self):
        self.assertEqual(Commatize("123.e8000 is pretty big."), "123.e8000 is pretty big.") # Exponent not commatized

    def test_land_area(self):
        self.assertEqual(Commatize("The land area of the earth is 57268900(29% of the surface) square miles."), "The land area of the earth is 57,268,900(29% of the surface) square miles.")

    def test_no_numbers(self):
        self.assertEqual(Commatize("Ain't no numbers in this here words, nohow, no way, Jose."), "Ain't no numbers in this here words, nohow, no way, Jose.")

    def test_leading_zeros_2(self):
        self.assertEqual(Commatize("James was never known as 0000000007"), "James was never known as 0000000007")

    def test_eddington_number(self):
        self.assertEqual(Commatize("Arthur Eddington wrote: I believe there are 15747724136275002577605653961181555468044717914527116709366231425076185631031296 protons in the universe."), "Arthur Eddington wrote: I believe there are 15,747,724,136,275,002,577,605,653,961,181,555,468,044,717,914,527,116,709,366,231,425,076,185,631,031,296 protons in the universe.")

    def test_leading_spaces_and_symbols(self):
        self.assertEqual(Commatize("␢␢␢$-140000±100 millions."), "␢␢␢$-140,000±100 millions.")

    def test_date(self):
        self.assertEqual(Commatize("6/9/1946 was a good year for some."), "6/9/1946 was a good year for some.")


if __name__ == '__main__':
    unittest.main()
