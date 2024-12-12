import unittest
from cmath import exp, pi

def fft(x):
    N = len(x)
    if N <= 1: return x
    even = fft(x[0::2])
    odd =  fft(x[1::2])
    T= [exp(-2j*pi*k/N)*odd[k] for k in range(N//2)]
    return [even[k] + T[k] for k in range(N//2)] + \
           [even[k] - T[k] for k in range(N//2)]

class TestFFT(unittest.TestCase):

    def test_fft_empty_input(self):
        self.assertEqual(fft([]), [])

    def test_fft_single_element(self):
        self.assertEqual(fft([1.0]), [1.0])

    def test_fft_two_elements(self):
        self.assertEqual(fft([1.0, 0.0]), [1.0 + 0.0j, 1.0 + 0.0j])

    def test_fft_four_elements(self):
        result = fft([1.0, 1.0, 1.0, 1.0])
        expected = [4.0 + 0.0j, 0.0 + 0.0j, 0.0 + 0.0j, 0.0 + 0.0j]
        for r, e in zip(result, expected):
            self.assertAlmostEqual(r, e, places=7)

    def test_fft_eight_elements(self):
        result = fft([1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0])
        expected = [4.0 + 0.0j, 1.0 - 2.41421356j, 0.0 + 0.0j, 1.0 - 0.41421356j, 
                    0.0 + 0.0j, 1.0 + 0.41421356j, 0.0 + 0.0j, 1.0 + 2.41421356j]
        for r, e in zip(result, expected):
            self.assertAlmostEqual(r, e, places=7)

    def test_fft_complex_input(self):
        result = fft([1+1j, 2+2j, 3+3j, 4+4j])
        expected = [10.0 + 10.0j, -4.0 + 0.0j, -2.0 - 2.0j, 0.0 + 0.0j]
        for r, e in zip(result, expected):
            self.assertAlmostEqual(r, e, places=7)

if __name__ == '__main__':
    unittest.main()
