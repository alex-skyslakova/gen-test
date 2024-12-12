import unittest
from average_loop_length import analytical, test

class TestAverageLoopLength(unittest.TestCase):

    def test_analytical_small_n(self):
        # Test the analytical function for small values of n
        self.assertAlmostEqual(analytical(1), 1.0, places=4)
        self.assertAlmostEqual(analytical(2), 1.5, places=4)
        self.assertAlmostEqual(analytical(3), 1.8889, places=4)
        self.assertAlmostEqual(analytical(4), 2.2188, places=4)
        self.assertAlmostEqual(analytical(5), 2.5104, places=4)

    def test_analytical_large_n(self):
        # Test the analytical function for larger values of n
        self.assertAlmostEqual(analytical(10), 3.6602, places=4)
        self.assertAlmostEqual(analytical(15), 4.5458, places=4)
        self.assertAlmostEqual(analytical(20), 5.2936, places=4)

    def test_test_function_small_n(self):
        # Test the test function for small values of n
        avg = test(1, 10000)
        self.assertAlmostEqual(avg, 1.0, delta=0.1)

        avg = test(2, 10000)
        self.assertAlmostEqual(avg, 1.5, delta=0.1)

        avg = test(3, 10000)
        self.assertAlmostEqual(avg, 1.8889, delta=0.1)

        avg = test(4, 10000)
        self.assertAlmostEqual(avg, 2.2188, delta=0.1)

        avg = test(5, 10000)
        self.assertAlmostEqual(avg, 2.5104, delta=0.1)

    def test_test_function_large_n(self):
        # Test the test function for larger values of n
        avg = test(10, 10000)
        self.assertAlmostEqual(avg, 3.6602, delta=0.1)

        avg = test(15, 10000)
        self.assertAlmostEqual(avg, 4.5458, delta=0.1)

        avg = test(20, 10000)
        self.assertAlmostEqual(avg, 5.2936, delta=0.1)

    def test_test_function_with_different_times(self):
        # Test the test function with different number of times
        avg = test(10, 1000)
        self.assertAlmostEqual(avg, 3.6602, delta=0.2)

        avg = test(10, 100000)
        self.assertAlmostEqual(avg, 3.6602, delta=0.05)

if __name__ == '__main__':
    unittest.main()
