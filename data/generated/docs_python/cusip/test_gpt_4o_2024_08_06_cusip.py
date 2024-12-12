import unittest
from cusip import cusip_check

class TestCusipCheck(unittest.TestCase):

    def test_valid_cusips(self):
        # Valid CUSIPs
        self.assertTrue(cusip_check('037833100'))  # Apple Incorporated
        self.assertTrue(cusip_check('17275R102'))  # Cisco Systems
        self.assertTrue(cusip_check('38259P508'))  # Google Incorporated
        self.assertTrue(cusip_check('594918104'))  # Microsoft Corporation
        self.assertTrue(cusip_check('68389X105'))  # Oracle Corporation

    def test_invalid_cusips(self):
        # Invalid CUSIPs
        self.assertFalse(cusip_check('68389X106'))  # Incorrect Oracle Corporation

    def test_invalid_length(self):
        # CUSIP with invalid length
        with self.assertRaises(ValueError):
            cusip_check('12345678')  # 8 characters
        with self.assertRaises(ValueError):
            cusip_check('1234567890')  # 10 characters

    def test_invalid_characters(self):
        # CUSIP with invalid characters
        with self.assertRaises(ValueError):
            cusip_check('12345678!')  # Invalid character '!'
        with self.assertRaises(ValueError):
            cusip_check('12345678$')  # Invalid character '$')

if __name__ == '__main__':
    unittest.main()
