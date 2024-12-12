import unittest
from decimal import Decimal, getcontext
from arithmetic_geometric_mean_calculate_pi import calculate_pi

class TestArithmeticGeometricMeanCalculatePi(unittest.TestCase):
    
    def setUp(self):
        # Set precision for Decimal calculations
        getcontext().prec = 100

    def test_calculate_pi_precision(self):
        # Test the precision of the calculated value of pi
        calculated_pi = calculate_pi()
        # Known value of pi to 100 decimal places
        known_pi = Decimal('3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679')
        # Allow a small margin of error due to precision limits
        self.assertAlmostEqual(calculated_pi, known_pi, places=98)

    def test_calculate_pi_type(self):
        # Test that the function returns a Decimal
        calculated_pi = calculate_pi()
        self.assertIsInstance(calculated_pi, Decimal)

    def test_calculate_pi_value(self):
        # Test that the calculated value of pi is greater than 3 and less than 4
        calculated_pi = calculate_pi()
        self.assertGreater(calculated_pi, Decimal(3))
        self.assertLess(calculated_pi, Decimal(4))

if __name__ == '__main__':
    unittest.main()
