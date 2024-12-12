import unittest

class TestDeconvolution(unittest.TestCase):

    def setUp(self):
        self.h = [-8, -9, -3, -1, -6, 7]
        self.f = [-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1]
        self.g = [24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7]

    def test_convolve(self):
        expected_g = self.g
        result_g = convolve(self.f, self.h)
        self.assertEqual(result_g, expected_g, "Convolution result does not match expected output.")

    def test_deconvolve_f(self):
        expected_f = self.f
        result_f = deconvolve(self.g, self.h)
        self.assertEqual(result_f, expected_f, "Deconvolution result for f does not match expected output.")

    def test_deconvolve_h(self):
        expected_h = self.h
        result_h = deconvolve(self.g, self.f)
        self.assertEqual(result_h, expected_h, "Deconvolution result for h does not match expected output.")

    def test_convolve_deconvolve(self):
        # Test that convolving and then deconvolving returns the original sequence
        convolved_g = convolve(self.f, self.h)
        deconvolved_h = deconvolve(convolved_g, self.f)
        self.assertEqual(deconvolved_h, self.h, "Deconvolution after convolution does not return the original h.")

        deconvolved_f = deconvolve(convolved_g, self.h)
        self.assertEqual(deconvolved_f, self.f, "Deconvolution after convolution does not return the original f.")

if __name__ == '__main__':
    unittest.main()
