import unittest
from damm_algorithm import damm

class TestDammAlgorithm(unittest.TestCase):
    
    def test_valid_checksum(self):
        # Test cases with valid checksums
        self.assertTrue(damm(5724))  # Valid checksum
        self.assertTrue(damm(112946))  # Valid checksum

    def test_invalid_checksum(self):
        # Test cases with invalid checksums
        self.assertFalse(damm(5727))  # Invalid checksum
        self.assertFalse(damm(112947))  # Invalid checksum

    def test_single_digit(self):
        # Test cases with single digit numbers
        self.assertTrue(damm(0))  # Single digit, valid checksum
        self.assertFalse(damm(1))  # Single digit, invalid checksum

    def test_empty_input(self):
        # Test case with empty input
        with self.assertRaises(ValueError):
            damm('')  # Empty input should raise an error

    def test_non_integer_input(self):
        # Test case with non-integer input
        with self.assertRaises(ValueError):
            damm('abc')  # Non-integer input should raise an error

    def test_large_number(self):
        # Test case with a large number
        self.assertTrue(damm(12345678901234567890))  # Valid checksum for a large number

if __name__ == '__main__':
    unittest.main()
