import unittest
from bitcoin_address_validation import check_bc

class TestBitcoinAddressValidation(unittest.TestCase):

    def test_valid_address(self):
        # This is a known valid Bitcoin address
        self.assertTrue(check_bc('1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i'))

    def test_invalid_address_checksum(self):
        # This address has an invalid checksum
        self.assertFalse(check_bc('1AGNa15ZQXAZUgFiqJ3i7Z2DPU2J6hW62i'))

    def test_invalid_address_characters(self):
        # This address contains invalid characters (0, O, I, l)
        self.assertFalse(check_bc('1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O'))

    def test_empty_address(self):
        # An empty address should be invalid
        self.assertFalse(check_bc(''))

    def test_short_address(self):
        # A too short address should be invalid
        self.assertFalse(check_bc('1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW6'))

    def test_long_address(self):
        # A too long address should be invalid
        self.assertFalse(check_bc('1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i1'))

    def test_valid_address_with_different_version(self):
        # This is a valid address with a different version byte
        self.assertTrue(check_bc('17NdbrSGoUotzeGCcMMCqnFkEvLymoou9j'))

if __name__ == '__main__':
    unittest.main()
