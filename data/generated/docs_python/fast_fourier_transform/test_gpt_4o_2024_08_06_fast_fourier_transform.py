import unittest
from fast_fourier_transform import fft
from cmath import isclose

class TestFFT(unittest.TestCase):

    def test_fft_real_numbers(self):
        # Test with a simple real number sequence
        input_sequence = [1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0]
        expected_output_magnitudes = [4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
        result = fft(input_sequence)
        result_magnitudes = [abs(x) for x in result]
        for r, e in zip(result_magnitudes, expected_output_magnitudes):
            self.assertTrue(isclose(r, e, rel_tol=1e-9))

    def test_fft_complex_numbers(self):
        # Test with a sequence of complex numbers
        input_sequence = [1+1j, 1+1j, 1+1j, 1+1j, 0+0j, 0+0j, 0+0j, 0+0j]
        expected_output_magnitudes = [5.656854, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
        result = fft(input_sequence)
        result_magnitudes = [abs(x) for x in result]
        for r, e in zip(result_magnitudes, expected_output_magnitudes):
            self.assertTrue(isclose(r, e, rel_tol=1e-6))

    def test_fft_single_element(self):
        # Test with a single element
        input_sequence = [1.0]
        expected_output = [1.0]
        result = fft(input_sequence)
        self.assertEqual(result, expected_output)

    def test_fft_two_elements(self):
        # Test with two elements
        input_sequence = [1.0, -1.0]
        expected_output_magnitudes = [0.0, 2.0]
        result = fft(input_sequence)
        result_magnitudes = [abs(x) for x in result]
        for r, e in zip(result_magnitudes, expected_output_magnitudes):
            self.assertTrue(isclose(r, e, rel_tol=1e-9))

    def test_fft_power_of_two(self):
        # Test with a power of two length sequence
        input_sequence = [0, 1, 2, 3, 4, 5, 6, 7]
        expected_output_magnitudes = [28.0, 10.3923, 4.0, 2.3923, 4.0, 2.3923, 4.0, 10.3923]
        result = fft(input_sequence)
        result_magnitudes = [abs(x) for x in result]
        for r, e in zip(result_magnitudes, expected_output_magnitudes):
            self.assertTrue(isclose(r, e, rel_tol=1e-4))

if __name__ == '__main__':
    unittest.main()
