import unittest
from bitcoin_address_validation import check_bc

class TestBitcoinAddressValidation(unittest.TestCase):

    def test_valid_address(self):
        self.assertTrue(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
        self.assertTrue(check_bc("17NdbrSGoUotzeGCcMMCqnFkEvLymoou9j"))  # Additional valid address

    def test_invalid_address_checksum(self):
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ3i7Z2DPU2J6hW62i")) # Modified checksum
        self.assertFalse(check_bc("17NdbrSGoUotzeGCcMMCqnFkEvLymoou9k")) # Modified checksum

    def test_invalid_address_length(self):
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62")) # Too short
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iii")) # Too long

    def test_invalid_address_characters(self):
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62o")) # Contains 'o'
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW620"))  # Contains '0'
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I")) # Contains 'I'
        self.assertFalse(check_bc("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62l")) # Contains 'l'


    def test_empty_address(self):
        self.assertFalse(check_bc(""))

    def test_none_address(self):
        self.assertFalse(check_bc(None))


if __name__ == '__main__':
    unittest.main()
