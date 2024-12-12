import unittest
from time import sleep
from math import sin, pi
from active_object import Integrator

class TestIntegrator(unittest.TestCase):

    def test_integrator_with_sin_input(self):
        # Initialize the integrator with a sine function (0.5 Hz)
        ai = Integrator(lambda t: sin(2 * pi * 0.5 * t))
        
        # Wait for 2 seconds
        sleep(2)
        
        # Set the input to constant 0
        ai.K = lambda t: 0
        
        # Wait for an additional 0.5 seconds
        sleep(0.5)
        
        # The output should be approximately 0 (sine has a period of 2s)
        self.assertAlmostEqual(ai.S, 0, delta=0.1)

if __name__ == "__main__":
    unittest.main()
