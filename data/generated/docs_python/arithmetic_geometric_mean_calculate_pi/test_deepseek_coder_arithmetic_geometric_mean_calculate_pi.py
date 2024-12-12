import unittest
from decimal import Decimal, getcontext
from arithmetic_geometric_mean_calculate_pi import *

class TestArithmeticGeometricMeanCalculatePi(unittest.TestCase):

    def setUp(self):
        getcontext().prec = 100

    def test_initial_values(self):
        self.assertEqual(a, Decimal(1))
        self.assertEqual(n, Decimal(1))
        self.assertEqual(g, Decimal(1) / Decimal(2).sqrt())
        self.assertEqual(z, Decimal(0.25))
        self.assertEqual(half, Decimal(0.5))

    def test_loop_iteration(self):
        # Test the first iteration of the loop
        x = [(a + g) * half, (a * g).sqrt()]
        var = x[0] - a
        z -= var * var * n
        n += n
        a, g = x

        self.assertAlmostEqual(a, Decimal(1.11803398874989484820458683436563811772030917980576))
        self.assertAlmostEqual(g, Decimal(0.84089641525371454303112547623321489504394172691159))
        self.assertAlmostEqual(z, Decimal(0.24999999999999999999999999999999999999999999999999))
        self.assertEqual(n, Decimal(2))

    def test_final_result(self):
        # Run the full loop to get the final result
        for i in range(18):
            x = [(a + g) * half, (a * g).sqrt()]
            var = x[0] - a
            z -= var * var * n
            n += n
            a, g = x

        pi_approx = a * a / z
        self.assertAlmostEqual(pi_approx, Decimal('3.14159265358979323846264338327950288419716939937510'))

if __name__ == '__main__':
    unittest.main()
