import unittest
from collections import deque
from averages_simple_moving_average import simplemovingaverage

class TestSimpleMovingAverage(unittest.TestCase):

    def test_initialization(self):
        # Test with valid period
        sma = simplemovingaverage(3)
        self.assertIsNotNone(sma)

        # Test with invalid period (zero)
        with self.assertRaises(AssertionError):
            simplemovingaverage(0)

        # Test with invalid period (negative)
        with self.assertRaises(AssertionError):
            simplemovingaverage(-1)

        # Test with invalid period (non-integer)
        with self.assertRaises(AssertionError):
            simplemovingaverage(2.5)

    def test_sma_calculation(self):
        sma = simplemovingaverage(3)

        # Test with fewer numbers than the period
        self.assertAlmostEqual(sma(10), 10.0)
        self.assertAlmostEqual(sma(20), 15.0)
        
        # Test with numbers equal to the period
        self.assertAlmostEqual(sma(30), 20.0)

        # Test with more numbers than the period
        self.assertAlmostEqual(sma(40), 30.0)
        self.assertAlmostEqual(sma(50), 40.0)

    def test_separate_instances(self):
        sma1 = simplemovingaverage(2)
        sma2 = simplemovingaverage(3)

        # Test sma1
        self.assertAlmostEqual(sma1(10), 10.0)
        self.assertAlmostEqual(sma1(20), 15.0)
        self.assertAlmostEqual(sma1(30), 25.0)

        # Test sma2
        self.assertAlmostEqual(sma2(10), 10.0)
        self.assertAlmostEqual(sma2(20), 15.0)
        self.assertAlmostEqual(sma2(30), 20.0)
        self.assertAlmostEqual(sma2(40), 30.0)

    def test_edge_cases(self):
        sma = simplemovingaverage(1)

        # Test with period of 1 (should always return the last number)
        self.assertAlmostEqual(sma(10), 10.0)
        self.assertAlmostEqual(sma(20), 20.0)
        self.assertAlmostEqual(sma(30), 30.0)

if __name__ == '__main__':
    unittest.main()
