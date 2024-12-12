import unittest
from hashlib import sha256
from bitcoin_address_validation import decode_base58, check_bc

class TestBitcoinAddressValidation(unittest.TestCase):

    def test_valid_bitcoin_address(self):
        # Valid Bitcoin address
        valid_address = '1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i'
        self.assertTrue(check_bc(valid_address))

    def test_invalid_bitcoin_address_checksum(self):
        # Invalid Bitcoin address with incorrect checksum
        invalid_address = '1AGNa15ZQXAZUgFiqJ3i7Z2DPU2J6hW62i'
        self.assertFalse(check_bc(invalid_address))

    def test_invalid_bitcoin_address_length(self):
        # Invalid Bitcoin address with incorrect length
        invalid_address = '1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62'
        self.assertFalse(check_bc(invalid_address))

    def test_invalid_bitcoin_address_characters(self):
        # Invalid Bitcoin address with invalid characters
        invalid_address = '1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O'
        self.assertFalse(check_bc(invalid_address))

    def test_empty_bitcoin_address(self):
        # Empty Bitcoin address
        empty_address = ''
        self.assertFalse(check_bc(empty_address))

    def test_decode_base58_valid(self):
        # Valid base58 decoding
        valid_address = '1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i'
        decoded = decode_base58(valid_address, 25)
        self.assertEqual(len(decoded), 25)

    def test_decode_base58_invalid(self):
        # Invalid base58 decoding
        invalid_address = '1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O'
        with self.assertRaises(ValueError):
            decode_base58(invalid_address, 25)

if __name__ == '__main__':
    unittest.main()
