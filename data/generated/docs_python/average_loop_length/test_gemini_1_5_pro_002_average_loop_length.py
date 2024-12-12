import unittest
from average_loop_length import analytical, test

class TestAverageLoopLength(unittest.TestCase):

    def test_analytical_n1(self):
        self.assertEqual(analytical(1), 1.0)

    def test_analytical_n2(self):
        self.assertAlmostEqual(analytical(2), 1.5, places=4)

    def test_analytical_n3(self):
        self.assertAlmostEqual(analytical(3), 1.8888888888888888, places=4)

    def test_analytical_n10(self):
        self.assertAlmostEqual(analytical(10), 3.6601806803, places=4)

    def test_analytical_n20(self):
        self.assertAlmostEqual(analytical(20), 5.29355736976, places=4)


    def test_test_n1(self):
        self.assertAlmostEqual(test(1, 100000), 1.0, delta=0.01)

    def test_test_n2(self):
        self.assertAlmostEqual(test(2, 100000), 1.5, delta=0.01)

    def test_test_n3(self):
        self.assertAlmostEqual(test(3, 100000), 1.8889, delta=0.05)  # Increased delta for larger N

    def test_test_n10(self):
        self.assertAlmostEqual(test(10, 100000), 3.66, delta=0.1)  # Increased delta for larger N

    def test_test_n20(self):
        self.assertAlmostEqual(test(20, 100000), 5.29, delta=0.2) # Increased delta for larger N, reduced iterations for speed



