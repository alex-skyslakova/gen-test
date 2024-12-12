import unittest
from decimal import Decimal
import math

# Assuming the functions h and h2 are defined in a module named hickerson_series_of_almost_integers
from hickerson_series_of_almost_integers import h, h2

class TestHickersonSeries(unittest.TestCase):

    def test_h_function(self):
        # Test the simple, reduced precision calculation
        self.assertAlmostEqual(h(1), 0.7213475204444817)
        self.assertAlmostEqual(h(2), 0.886226925452758)
        self.assertAlmostEqual(h(3), 1.03110817802375)
        self.assertAlmostEqual(h(4), 1.1666666666666667)
        self.assertAlmostEqual(h(5), 1.2962962962962963)
        self.assertAlmostEqual(h(6), 1.4216263782948388)
        self.assertAlmostEqual(h(7), 1.543613064846416)
        self.assertAlmostEqual(h(8), 1.66287877857284)
        self.assertAlmostEqual(h(9), 1.779865718705544)
        self.assertAlmostEqual(h(10), 1.894915801937482)
        self.assertAlmostEqual(h(11), 2.008309722182132)
        self.assertAlmostEqual(h(12), 2.120273651768012)
        self.assertAlmostEqual(h(13), 2.230992631914843)
        self.assertAlmostEqual(h(14), 2.340626121623486)
        self.assertAlmostEqual(h(15), 2.449306987740868)
        self.assertAlmostEqual(h(16), 2.557148178393079)
        self.assertAlmostEqual(h(17), 2.664247375957549)

    def test_h2_function(self):
        # Test the extended precision Hickerson function
        self.assertEqual(str(h2(1).normalize()), '0.7213475204444817')
        self.assertEqual(str(h2(2).normalize()), '0.886226925452758')
        self.assertEqual(str(h2(3).normalize()), '1.03110817802375')
        self.assertEqual(str(h2(4).normalize()), '1.1666666666666667')
        self.assertEqual(str(h2(5).normalize()), '1.2962962962962963')
        self.assertEqual(str(h2(6).normalize()), '1.4216263782948388')
        self.assertEqual(str(h2(7).normalize()), '1.543613064846416')
        self.assertEqual(str(h2(8).normalize()), '1.66287877857284')
        self.assertEqual(str(h2(9).normalize()), '1.779865718705544')
        self.assertEqual(str(h2(10).normalize()), '1.894915801937482')
        self.assertEqual(str(h2(11).normalize()), '2.008309722182132')
        self.assertEqual(str(h2(12).normalize()), '2.120273651768012')
        self.assertEqual(str(h2(13).normalize()), '2.230992631914843')
        self.assertEqual(str(h2(14).normalize()), '2.340626121623486')
        self.assertEqual(str(h2(15).normalize()), '2.449306987740868')
        self.assertEqual(str(h2(16).normalize()), '2.557148178393079')
        self.assertEqual(str(h2(17).normalize()), '2.664247375957549')

    def test_almost_integers(self):
        # Test for "almost integers"
        almost_integers = {
            1: False,
            2: False,
            3: True,
            4: True,
            5: True,
            6: True,
            7: True,
            8: True,
            9: True,
            10: True,
            11: True,
            12: True,
            13: True,
            14: True,
            15: True,
            16: True,
            17: True
        }

        for n in range(1, 18):
            x = h2(n)
            norm = str(x.normalize())
            is_almost_integer = ('E' not in norm and ('.0' in norm or '.9' in norm))
            self.assertEqual(is_almost_integer, almost_integers[n], f"n={n} should be {'an almost integer' if almost_integers[n] else 'not an almost integer'}")

if __name__ == '__main__':
    unittest.main()
