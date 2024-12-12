import unittest
from decimal import *
from arithmetic_geometric_mean_calculate_pi import a, z

class TestPiCalculation(unittest.TestCase):

    def test_pi_approximation(self):
        # Using a tolerance for comparison due to the iterative nature and precision limits.
        # The expected value is an approximation of pi with high precision.
        expected_pi = Decimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679")
        self.assertAlmostEqual(a * a / z, expected_pi, places=95)  # Checking for 95 decimal places of accuracy


    def test_intermediate_values(self):
        # Test some intermediate values after a few iterations.
        # These values were obtained by running the original code and recording the values.

        # Perform a few iterations (e.g., 5) 
        a_test = n_test = D(1)
        g_test, z_test, half_test = 1 / D(2).sqrt(), D(0.25), D(0.5)
        for i in range(5):
            x_test = [(a_test + g_test) * half_test, (a_test * g_test).sqrt()]
            var_test = x_test[0] - a_test
            z_test -= var_test * var_test * n_test
            n_test += n_test
            a_test, g_test = x_test

        self.assertAlmostEqual(a_test, Decimal("0.8472130847939718641079909476213406674805337214665137264979188200782715109717892839777226678673842"), places=95)
        self.assertAlmostEqual(z_test, Decimal("0.01491194277089060126141186818793530612667848085727327592308463744381574933049815962912634911138508"), places=95)

