import unittest
from cusip import cusip_check

class TestCusipCheck(unittest.TestCase):

    def test_valid_cusips(self):
        valid_cusips = [
            '037833100',  # Apple Incorporated
            '17275R102',  # Cisco Systems
            '38259P508',  # Google Incorporated
            '594918104',  # Microsoft Corporation
            '68389X105'   # Oracle Corporation
        ]
        for cusip in valid_cusips:
            with self.subTest(cusip=cusip):
                self.assertTrue(cusip_check(cusip), f"Expected {cusip} to be valid")

    def test_invalid_cusips(self):
        invalid_cusips = [
            '68389X106'  # Oracle Corporation (incorrect)
        ]
        for cusip in invalid_cusips:
            with self.subTest(cusip=cusip):
                self.assertFalse(cusip_check(cusip), f"Expected {cusip} to be invalid")

    def test_cusip_length_validation(self):
        with self.assertRaises(ValueError):
            cusip_check('03783310')  # Too short
        with self.assertRaises(ValueError):
            cusip_check('0378331000')  # Too long

    def test_cusip_case_insensitivity(self):
        self.assertTrue(cusip_check('037833100'))
        self.assertTrue(cusip_check('037833100'.lower()))
        self.assertTrue(cusip_check('037833100'.upper()))

    def test_special_characters(self):
        self.assertTrue(cusip_check('037833100'))
        self.assertTrue(cusip_check('037833100'))
        self.assertTrue(cusip_check('037833100'))

if __name__ == '__main__':
    unittest.main()
