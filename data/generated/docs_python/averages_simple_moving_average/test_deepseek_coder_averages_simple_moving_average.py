import unittest
from collections import deque

# Assuming the function simplemovingaverage is defined in a module named averages_simple_moving_average
from averages_simple_moving_average import simplemovingaverage

class TestSimpleMovingAverage(unittest.TestCase):

    def test_initialization(self):
        # Test that the period is correctly set and the deque is initialized with zeros
        sma = simplemovingaverage(3)
        self.assertEqual(sma.__closure__[0].cell_contents, 3)  # Period
        self.assertEqual(sma.__closure__[2].cell_contents, deque([0.0, 0.0, 0.0]))  # Values

    def test_single_value(self):
        # Test that a single value returns the value itself
        sma = simplemovingaverage(3)
        self.assertEqual(sma(5), 5.0)

    def test_period_values(self):
        # Test that the SMA is correctly calculated for the period number of values
        sma = simplemovingaverage(3)
        self.assertEqual(sma(1), 1.0)
        self.assertEqual(sma(2), 1.5)
        self.assertEqual(sma(3), 2.0)

    def test_more_than_period_values(self):
        # Test that the SMA is correctly calculated when more than the period number of values are given
        sma = simplemovingaverage(3)
        self.assertEqual(sma(1), 1.0)
        self.assertEqual(sma(2), 1.5)
        self.assertEqual(sma(3), 2.0)
        self.assertEqual(sma(4), 3.0)
        self.assertEqual(sma(5), 4.0)

    def test_negative_values(self):
        # Test that the SMA handles negative values correctly
        sma = simplemovingaverage(3)
        self.assertEqual(sma(-1), -1.0)
        self.assertEqual(sma(-2), -1.5)
        self.assertEqual(sma(-3), -2.0)
        self.assertEqual(sma(-4), -3.0)
        self.assertEqual(sma(-5), -4.0)

    def test_mixed_values(self):
        # Test that the SMA handles a mix of positive and negative values correctly
        sma = simplemovingaverage(3)
        self.assertEqual(sma(1), 1.0)
        self.assertEqual(sma(-1), 0.0)
        self.assertEqual(sma(2), 0.6666666666666666)
        self.assertEqual(sma(-2), -0.3333333333333333)
        self.assertEqual(sma(3), 1.0)

    def test_period_zero(self):
        # Test that the function raises an assertion error if the period is zero
        with self.assertRaises(AssertionError):
            simplemovingaverage(0)

    def test_period_negative(self):
        # Test that the function raises an assertion error if the period is negative
        with self.assertRaises(AssertionError):
            simplemovingaverage(-1)

    def test_period_non_integer(self):
        # Test that the function raises an assertion error if the period is not an integer
        with self.assertRaises(AssertionError):
            simplemovingaverage(3.5)

if __name__ == '__main__':
    unittest.main()
