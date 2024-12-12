import unittest
from deconvolution_1d import deconvolve, convolve

class TestDeconvolution(unittest.TestCase):

    def test_deconvolve_given_example(self):
        h = [-8, -9, -3, -1, -6, 7]
        f = [-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1]
        g = [24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7]
        
        h_recovered = deconvolve(g, f)
        self.assertEqual(h_recovered, h)

        f_recovered = deconvolve(g, h)
        self.assertEqual(f_recovered, f)


    def test_deconvolve_simple_case(self):
        f = [1, 2, 1]
        h = [1, 1, 1]
        g = convolve(f, h)  # Expected: [1, 3, 4, 3, 1]
        
        h_recovered = deconvolve(g, f)
        self.assertEqual(h_recovered, h)

        f_recovered = deconvolve(g, h)
        self.assertEqual(f_recovered, f)
    

    def test_deconvolve_single_element_kernel(self):
        f = [1, 2, 3, 4]
        h = [5]
        g = convolve(f, h)  # Expected: [5, 10, 15, 20]

        h_recovered = deconvolve(g, f)
        self.assertEqual(h_recovered, h)

        f_recovered = deconvolve(g, h)
        self.assertEqual(f_recovered, f)

    def test_deconvolve_all_zeros(self):
        f = [0,0,0]
        g = [0,0,0,0]
        h = [0,0] # len(g) - len(f) + 1
        h_recovered = deconvolve(g, f)
        self.assertEqual(h_recovered, h) # Interesting case, could have multiple solutions



    def test_deconvolve_g_shorter_than_f(self):
        f = [1, 2, 3]
        g = [1, 2]
        with self.assertRaises(IndexError): # Expecting an error in this case
            deconvolve(g,f)
    

    def test_deconvolve_empty_inputs(self):
         with self.assertRaises(IndexError): # Expecting an error in this case
            deconvolve([],[])

if __name__ == '__main__':
    unittest.main()
