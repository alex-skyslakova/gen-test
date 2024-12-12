import unittest
from damm_algorithm import damm

class TestDamm(unittest.TestCase):

    def test_valid_checksum(self):
        self.assertTrue(damm(5724))

    def test_invalid_checksum(self):
        self.assertFalse(damm(5727))

    def test_longer_number_valid(self):
        self.assertTrue(damm(112946))

    def test_single_digit_error(self):
        # Change 5 to 6 in 5724 (valid)
        self.assertFalse(damm(6724))

    def test_transposition_error(self):
        # Swap 7 and 2 in 5724 (valid)
        self.assertFalse(damm(5274))

    def test_zero_input(self):
        self.assertTrue(damm(0))

    def test_single_digit_input(self):
        self.assertTrue(damm(1))
        self.assertTrue(damm(5))


