import unittest
from cmath import isclose
from fast_fourier_transform import fft

class TestFFT(unittest.TestCase):

    def test_empty_input(self):
        self.assertEqual(fft([]), [])

    def test_single_element(self):
        self.assertEqual(fft([1]), [1])
        self.assertEqual(fft([1j]), [1j])
        self.assertEqual(fft([1.5 + 2.5j]), [1.5 + 2.5j])

    def test_two_elements(self):
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1, 1]), [2, 0])))
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1, -1]), [0, 2])))
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1j, 1j]), [2j, 0])))

    def test_four_elements(self):
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1, 1, 1, 1]), [4, 0, 0, 0])))
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1, 0, 1, 0]), [2, 0, 2, 0])))
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft([1, -1, 1, -1]), [0, 0, 0, 4])))
    
    def test_eight_elements(self):
        input_data = [1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0]
        expected_output = [4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]
        self.assertTrue(all(isclose(abs(a), b, abs_tol=1e-9) for a, b in zip(fft(input_data), expected_output)))


    def test_complex_input(self):
        input_data = [1+1j, -1-1j, 1+1j, -1-1j]
        expected_output = [0j, 0j, 0j, 4+4j]
        self.assertTrue(all(isclose(a, b, abs_tol=1e-9) for a, b in zip(fft(input_data), expected_output)))
