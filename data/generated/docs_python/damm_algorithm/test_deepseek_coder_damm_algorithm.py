import unittest
from damm_algorithm import damm

class TestDammAlgorithm(unittest.TestCase):

    def test_valid_checksum(self):
        self.assertTrue(damm(5724))
        self.assertTrue(damm(112946))

    def test_invalid_checksum(self):
        self.assertFalse(damm(5727))

    def test_single_digit_input(self):
        self.assertTrue(damm(0))
        self.assertTrue(damm(1))
        self.assertTrue(damm(2))
        self.assertTrue(damm(3))
        self.assertTrue(damm(4))
        self.assertTrue(damm(5))
        self.assertTrue(damm(6))
        self.assertTrue(damm(7))
        self.assertTrue(damm(8))
        self.assertTrue(damm(9))

    def test_multi_digit_input(self):
        self.assertTrue(damm(1230))
        self.assertFalse(damm(1231))
        self.assertTrue(damm(9870))
        self.assertFalse(damm(9871))

    def test_large_input(self):
        self.assertTrue(damm(1234567890))
        self.assertFalse(damm(1234567891))

    def test_edge_cases(self):
        self.assertTrue(damm(0))
        self.assertFalse(damm(1))
        self.assertFalse(damm(2))
        self.assertFalse(damm(3))
        self.assertFalse(damm(4))
        self.assertFalse(damm(5))
        self.assertFalse(damm(6))
        self.assertFalse(damm(7))
        self.assertFalse(damm(8))
        self.assertFalse(damm(9))

if __name__ == '__main__':
    unittest.main()
