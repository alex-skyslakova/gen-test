import unittest
from time import sleep
from math import sin, pi
from active_object import Integrator

class TestActiveIntegrator(unittest.TestCase):

    def test_integration_of_sine_wave(self):
        # Frequency of 0.5 Hz
        frequency = 0.5
        ai = Integrator(lambda t: sin(2 * pi * frequency * t))
        
        # Wait for 2 seconds to integrate the sine wave
        sleep(2)
        
        # Set input to constant 0
        ai.K = lambda t: 0
        
        # Wait for 0.5 seconds
        sleep(0.5)
        
        # Check if the output is approximately 0
        self.assertAlmostEqual(ai.S, 0, delta=0.1)
        
        # Stop the integrator thread
        ai.join()

if __name__ == '__main__':
    unittest.main()
