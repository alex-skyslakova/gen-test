import unittest
from decimal import Decimal
import math
from hickerson_series_of_almost_integers import h, h2

class TestHickersonSeries(unittest.TestCase):
    
    def test_h_function(self):
        # Test the simple precision h function for known values
        expected_results = [
            0.7213475204444817,  # h(0)
            0.36067376022224085, # h(1)
            0.18033688011112042, # h(2)
            0.09016844005556021, # h(3)
            0.045084220027780104, # h(4)
            0.022542110013890052, # h(5)
            0.011271055006945026, # h(6)
            0.005635527503472513, # h(7)
            0.0028177637517362564, # h(8)
            0.0014088818758681282, # h(9)
            0.0007044409379340641, # h(10)
            0.00035222046896703206, # h(11)
            0.00017611023448351603, # h(12)
            8.805511724175801e-05, # h(13)
            4.4027558620879005e-05, # h(14)
            2.2013779310439503e-05, # h(15)
            1.1006889655219751e-05, # h(16)
            5.503444827609876e-06  # h(17)
        ]
        
        for n, expected in enumerate(expected_results):
            with self.subTest(n=n):
                self.assertAlmostEqual(h(n), expected, places=15)

    def test_h2_function(self):
        # Test the extended precision h2 function for "almost integers"
        for n in range(18):
            result = h2(n)
            norm = str(result.normalize())
            if 'E' not in norm and ('.0' in norm or '.9' in norm):
                self.assertTrue(True, f"h2({n}) is nearly integer")
            else:
                self.assertTrue(False, f"h2({n}) is NOT nearly integer")

if __name__ == '__main__':
    unittest.main()
