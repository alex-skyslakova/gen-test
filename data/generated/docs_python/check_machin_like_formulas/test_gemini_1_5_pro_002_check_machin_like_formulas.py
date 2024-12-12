import unittest
from fractions import Fraction
from check_machin_like_formulas import parse_eqn, tans

class TestMachinLikeFormulas(unittest.TestCase):

    def test_parse_eqn(self):
        equationtext = "pi/4 = arctan(1/2) + arctan(1/3)"
        expected = [[(1, Fraction(1, 2)), (1, Fraction(1, 3))]]
        self.assertEqual(parse_eqn(equationtext), expected)

        equationtext = "pi/4 = 2*arctan(1/3) + arctan(1/7)"
        expected = [[(2, Fraction(1, 3)), (1, Fraction(1, 7))]]
        self.assertEqual(parse_eqn(equationtext), expected)

        equationtext = "pi/4 = 4*arctan(1/5) - arctan(1/239)"
        expected = [[(4, Fraction(1, 5)), (-1, Fraction(1, 239))]]
        self.assertEqual(parse_eqn(equationtext), expected)

        equationtext = "pi/4 = 5*arctan(29/278) + 7*arctan(3/79)"
        expected = [[(5, Fraction(29, 278)), (7, Fraction(3, 79))]]
        self.assertEqual(parse_eqn(equationtext), expected)


    def test_tans(self):
        self.assertEqual(tans([[(1, Fraction(1, 2)), (1, Fraction(1, 3))]]), 1)
        self.assertEqual(tans([[(2, Fraction(1, 3)), (1, Fraction(1, 7))]]), 1)
        self.assertEqual(tans([[(4, Fraction(1, 5)), (-1, Fraction(1, 239))]]), 1)
        self.assertEqual(tans([[(5, Fraction(1, 7)), (2, Fraction(3, 79))]]), 1)
        self.assertEqual(tans([[(5, Fraction(29, 278)), (7, Fraction(3, 79))]]), 1)
        self.assertEqual(tans([[(1, Fraction(1, 2)), (1, Fraction(1, 5)), (1, Fraction(1, 8))]]), 1)
        # ... add more test cases for other equations

    def test_incorrect_formula(self):
        incorrect_formula = [[(88, Fraction(1, 172)), (51, Fraction(1, 239)), (32, Fraction(1, 682)), (44, Fraction(1, 5357)), (68, Fraction(1, 12944))]]
        self.assertNotEqual(tans(incorrect_formula), 1)


if __name__ == '__main__':
    unittest.main()
