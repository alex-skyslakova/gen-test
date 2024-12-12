import unittest
from fractions import Fraction
from check_machin_like_formulas import parse_eqn, tans

class TestMachinLikeFormulas(unittest.TestCase):

    def setUp(self):
        self.machins = parse_eqn()

    def test_machin_formulas(self):
        # Test each parsed equation
        for i, machin in enumerate(self.machins[:-1]):  # Exclude the last incorrect formula
            with self.subTest(i=i):
                result = tans(machin)
                self.assertEqual(result, 1, f"Formula {i+1} failed: {machin}")

    def test_incorrect_formula(self):
        # Test the last incorrect formula
        incorrect_machin = self.machins[-1]
        result = tans(incorrect_machin)
        self.assertNotEqual(result, 1, "The incorrect formula should not equal 1")

if __name__ == '__main__':
    unittest.main()
