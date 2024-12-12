import unittest
from cusip import cusip_check

class TestCusipCheck(unittest.TestCase):

    def test_valid_cusips(self):
        self.assertTrue(cusip_check('037833100'))  # Apple
        self.assertTrue(cusip_check('17275R102'))  # Cisco
        self.assertTrue(cusip_check('38259P508'))  # Google
        self.assertTrue(cusip_check('594918104'))  # Microsoft
        self.assertTrue(cusip_check('68389X105'))  # Oracle (correct)

    def test_invalid_cusip(self):
        self.assertFalse(cusip_check('68389X106')) # Oracle (incorrect)

    def test_invalid_length(self):
        with self.assertRaises(ValueError) as context:
            cusip_check('03783310')
        self.assertEqual(str(context.exception), 'CUSIP must be 9 characters')

    def test_lowercase_input(self):
        self.assertTrue(cusip_check('037833100')) #Lowercase should also work

    def test_special_characters(self):
        self.assertTrue(cusip_check('12345*107'))
        self.assertTrue(cusip_check('12345@105'))
        self.assertTrue(cusip_check('12345#102'))


