import unittest
from scipy import signal

class TestDigitalFilter(unittest.TestCase):

    def setUp(self):
        # Define the signal and filter coefficients
        self.sig = [-0.917843918645, 0.141984778794, 1.20536903482, 0.190286794412, -0.662370894973, -1.00700480494,
                    -0.404707073677, 0.800482325044, 0.743500089861, 1.01090520172, 0.741527555207,
                    0.277841675195, 0.400833448236, -0.2085993586, -0.172842103641, -0.134316096293,
                    0.0259303398477, 0.490105989562, 0.549391221511, 0.9047198589]

        self.a = [1.00000000, -2.77555756e-16, 3.33333333e-01, -1.85037171e-17]
        self.b = [0.16666667, 0.5, 0.5, 0.16666667]

    def test_filter_length(self):
        # Test if the filtered signal has the same length as the original signal
        filt = signal.lfilter(self.b, self.a, self.sig)
        self.assertEqual(len(filt), len(self.sig))

    def test_filter_output(self):
        # Test if the filter output matches the expected output
        expected_output = [
            -0.15297398644083334, -0.013663306465666667, 0.18422817247, 0.18718063240333333, -0.11039514916233333,
            -0.3356682683133333, -0.3369023745563333, -0.05643344751433333, 0.24783336328933334, 0.5036350672433333,
            0.58250851847, 0.5280770972433333, 0.42342786370666666, 0.26946457280666666, 0.10380151753666667,
            -0.016871306043333334, -0.07280222271033333, 0.031717996589666666, 0.21646870358966666, 0.4415733096233333
        ]
        filt = signal.lfilter(self.b, self.a, self.sig)
        for i, (actual, expected) in enumerate(zip(filt, expected_output)):
            self.assertAlmostEqual(actual, expected, places=10, msg=f"Mismatch at index {i}")

if __name__ == '__main__':
    unittest.main()
