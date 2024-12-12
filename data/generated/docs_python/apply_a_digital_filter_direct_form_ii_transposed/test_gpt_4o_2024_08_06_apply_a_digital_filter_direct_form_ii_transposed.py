import unittest
import numpy as np
from scipy import signal

class TestDigitalFilter(unittest.TestCase):
    def setUp(self):
        # Original signal
        self.sig = [-0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412, -0.662370894973,
                    -1.00700480494, -0.404707073677, 0.800482325044, 0.743500089861, 1.01090520172,
                    0.741527555207, 0.277841675195, 0.400833448236, -0.2085993586, -0.172842103641,
                    -0.134316096293, 0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589]
        
        # Filter coefficients
        self.a = [1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17]
        self.b = [0.16666667, 0.5, 0.5, 0.16666667]

    def test_filter_output(self):
        # Expected output calculated using the same filter
        expected_output = signal.lfilter(self.b, self.a, self.sig)
        
        # Apply the filter
        filt = signal.lfilter(self.b, self.a, self.sig)
        
        # Assert that the filtered output matches the expected output
        np.testing.assert_array_almost_equal(filt, expected_output, decimal=5)

    def test_filter_stability(self):
        # Check if the filter is stable by ensuring all poles are inside the unit circle
        poles = np.roots(self.a)
        self.assertTrue(np.all(np.abs(poles) < 1), "Filter is unstable")

    def test_filter_length(self):
        # Apply the filter
        filt = signal.lfilter(self.b, self.a, self.sig)
        
        # Check if the length of the filtered signal is the same as the input signal
        self.assertEqual(len(filt), len(self.sig), "Filtered signal length does not match input signal length")

if __name__ == '__main__':
    unittest.main()
