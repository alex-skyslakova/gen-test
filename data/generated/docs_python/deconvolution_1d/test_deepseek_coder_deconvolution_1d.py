import unittest

class TestDeconvolution(unittest.TestCase):

    def test_convolve(self):
        h = [-8, -9, -3, -1, -6, 7]
        f = [-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1]
        g = [24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7]
        self.assertEqual(convolve(f, h), g)

    def test_deconvolve(self):
        h = [-8, -9, -3, -1, -6, 7]
        f = [-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1]
        g = [24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7]
        
        # Test deconvolve(g, f) = h
        self.assertEqual(deconvolve(g, f), h)
        
        # Test deconvolve(g, h) = f
        self.assertEqual(deconvolve(g, h), f)

if __name__ == '__main__':
    unittest.main()
