import unittest
from fractions import Fraction
import itertools

# Assuming the functions CF, pRes, sqrt2_a, sqrt2_b, Napier_a, Napier_b, Pi_a, and Pi_b are defined in continued_fraction.py

class TestContinuedFraction(unittest.TestCase):

    def test_sqrt2(self):
        # Test the Continued Fraction for sqrt2
        cf = CF(sqrt2_a(), sqrt2_b(), 950)
        result = pRes(cf, 200)
        expected = "1.41421356237309504880168872420969807856967187537694807317667973799073247846210703885038753432764157273501384623091229702492483605585073721264412149709993583141322266592750559275579995050115278206057147"
        self.assertEqual(result, expected)

    def test_napier(self):
        # Test the Continued Fraction for Napier's Constant
        cf = CF(Napier_a(), Napier_b(), 950)
        result = pRes(cf, 200)
        expected = "2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516642742746639193200305992181741359662904357290033429526059563073813232862794349076323382988075319525101901"
        self.assertEqual(result, expected)

    def test_pi(self):
        # Test the Continued Fraction for Pi
        cf = CF(Pi_a(), Pi_b(), 950)
        result = pRes(cf, 10)
        expected = "3.1415926532"
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
