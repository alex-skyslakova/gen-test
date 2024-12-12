import unittest
from fractions import Fraction
import re

# Assuming the provided code is in a module named `machin_like_formulas`
import machin_like_formulas as mlf

class TestMachinLikeFormulas(unittest.TestCase):

    def test_all_formulas(self):
        # Parse the equations
        machins = mlf.parse_eqn()

        # Test each equation
        for machin, eqn in zip(machins, mlf.equationtext.split('\n')):
            with self.subTest(eqn=eqn):
                ans = mlf.tans(machin)
                self.assertEqual(ans, 1, f"Equation {eqn} failed")

    def test_incorrect_formula(self):
        # Parse the equations
        machins = mlf.parse_eqn()

        # The incorrect formula is the last one in the list
        incorrect_formula = machins[-1]
        incorrect_eqn = mlf.equationtext.split('\n')[-1]

        # Test the incorrect formula
        ans = mlf.tans(incorrect_formula)
        self.assertNotEqual(ans, 1, f"Incorrect equation {incorrect_eqn} should not equal 1")

if __name__ == '__main__':
    unittest.main()
