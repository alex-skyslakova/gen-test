import unittest
from decimal import Decimal
import math

from hickerson_series_of_almost_integers import h2

class TestHickerson(unittest.TestCase):

    def check_almost_integer(self, n, expected_almost_integer):
        x = h2(n)
        norm = str(x.normalize())
        is_almost_integer = 'E' not in norm and ('.0' in norm or '.9' in norm)
        self.assertEqual(is_almost_integer, expected_almost_integer, 
                         f"For n={n}, expected almost_integer={expected_almost_integer}, but got {is_almost_integer}")

    def test_hickerson_almost_integers(self):
        for n in range(18):
            if n <= 17:  # According to the problem description.
                self.check_almost_integer(n, True)
            else:
                self.check_almost_integer(n, False)

    def test_hickerson_n18_not_almost_integer(self):
        self.check_almost_integer(18, False)


if __name__ == '__main__':
    unittest.main()
