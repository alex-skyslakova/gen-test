import unittest
from average_loop_length import analytical, test

class TestAverageLoopLength(unittest.TestCase):

    def test_analytical(self):
        # Test analytical function with known values
        self.assertAlmostEqual(analytical(1), 1.0, places=4)
        self.assertAlmostEqual(analytical(2), 1.5, places=4)
        self.assertAlmostEqual(analytical(3), 1.8889, places=4)
        self.assertAlmostEqual(analytical(4), 2.2188, places=4)
        self.assertAlmostEqual(analytical(5), 2.5104, places=4)
        self.assertAlmostEqual(analytical(6), 2.7747, places=4)
        self.assertAlmostEqual(analytical(7), 3.0181, places=4)
        self.assertAlmostEqual(analytical(8), 3.2450, places=4)
        self.assertAlmostEqual(analytical(9), 3.4583, places=4)
        self.assertAlmostEqual(analytical(10), 3.6602, places=4)
        self.assertAlmostEqual(analytical(11), 3.8524, places=4)
        self.assertAlmostEqual(analytical(12), 4.0361, places=4)
        self.assertAlmostEqual(analytical(13), 4.2123, places=4)
        self.assertAlmostEqual(analytical(14), 4.3820, places=4)
        self.assertAlmostEqual(analytical(15), 4.5458, places=4)
        self.assertAlmostEqual(analytical(16), 4.7043, places=4)
        self.assertAlmostEqual(analytical(17), 4.8579, places=4)
        self.assertAlmostEqual(analytical(18), 5.0071, places=4)
        self.assertAlmostEqual(analytical(19), 5.1522, places=4)
        self.assertAlmostEqual(analytical(20), 5.2936, places=4)

    def test_simulation(self):
        # Test simulation function with a small number of iterations for quick tests
        # Note: These tests are not deterministic due to randomness, so we use a lower precision
        self.assertAlmostEqual(test(1, 1000), 1.0, delta=0.1)
        self.assertAlmostEqual(test(2, 1000), 1.5, delta=0.2)
        self.assertAlmostEqual(test(3, 1000), 1.8889, delta=0.3)
        self.assertAlmostEqual(test(4, 1000), 2.2188, delta=0.3)
        self.assertAlmostEqual(test(5, 1000), 2.5104, delta=0.4)
        self.assertAlmostEqual(test(6, 1000), 2.7747, delta=0.4)

if __name__ == '__main__':
    unittest.main()
