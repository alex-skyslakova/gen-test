import unittest
from linear_congruential_generator import bsd_rand, msvcrt_rand

class TestLCG(unittest.TestCase):

    def test_bsd_rand_initial_values(self):
        rng = bsd_rand(1)
        self.assertEqual(rng(), 1)
        self.assertEqual(rng(), 1103527590)
        self.assertEqual(rng(), 2086073533)

    def test_bsd_rand_large_seed(self):
        rng = bsd_rand(2147483647)  # Max seed value
        self.assertEqual(rng(), 2147483647)
        self.assertEqual(rng(), 2522449678)

    def test_bsd_rand_zero_seed(self):
        rng = bsd_rand(0)
        self.assertEqual(rng(), 0)
        self.assertEqual(rng(), 12345)


    def test_msvcrt_rand_initial_values(self):
        rng = msvcrt_rand(1)
        self.assertEqual(rng(), 0)
        self.assertEqual(rng(), 8864)
        self.assertEqual(rng(), 28816)


    def test_msvcrt_rand_large_seed(self):
        rng = msvcrt_rand(2147483647) # Max seed value
        self.assertEqual(rng(), 32767)
        self.assertEqual(rng(), 32470)

    def test_msvcrt_rand_zero_seed(self):
        rng = msvcrt_rand(0)
        self.assertEqual(rng(), 0)
        self.assertEqual(rng(), 9765)

