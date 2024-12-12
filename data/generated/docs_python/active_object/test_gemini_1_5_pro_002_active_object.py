import unittest
from active_object import Integrator
from time import sleep
from math import sin, pi

class TestIntegrator(unittest.TestCase):

    def test_integration_sine_wave(self):
        # Test integration of sine wave over one period
        ai = Integrator(lambda t: sin(pi * t))
        sleep(2)  # Wait for one period (f = 0.5Hz, T = 2s)
        ai.K = lambda t: 0 # Set input to 0
        sleep(0.5) # Give time for the integrator to process the 0 input
        ai.join() # Stop the thread
        self.assertAlmostEqual(ai.S, 0, tolerance=0.01)  # Tolerance due to timing inaccuracies


    def test_integration_constant(self):
        # Test integration of a constant function
        ai = Integrator(lambda t: 2)  # Constant input of 2
        sleep(1)
        ai.join()
        self.assertAlmostEqual(ai.S, 2, delta=0.01) # Expected output is 2 * 1 = 2


    def test_initial_state(self):
         # Test initial state of the integrator
        ai = Integrator() # Default input is 0
        self.assertEqual(ai.S, 0.0)
        ai.join()



    def test_changing_input(self):
        # Test changing input function mid-integration
        ai = Integrator(lambda t: t)  # Input starts as t
        sleep(1)
        ai.K = lambda t: 2*t # Input changes to 2t
        sleep(1)
        ai.join()
        # Expected output: (1/2 * 1^2) + ((3 + 1)/2 * 1) = 0.5 + 2 = 2.5 
        self.assertAlmostEqual(ai.S, 2.5, delta=0.01)  


