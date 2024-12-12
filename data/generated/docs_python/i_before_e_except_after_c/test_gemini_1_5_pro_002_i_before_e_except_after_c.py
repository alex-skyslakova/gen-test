import unittest
from unittest.mock import patch, mock_open
import re
from i_before_e_except_after_c import plausibility_check, simple_stats, print_result, PLAUSIBILITY_RATIO

class Test_i_before_e(unittest.TestCase):

    @patch('urllib.request.urlopen')
    def test_simple_stats(self, mock_urlopen):
        mock_data = "cie\n cei\ntie\ntei\naie\naei\nbcie\nbcei".encode()
        mock_urlopen.return_value = mock_open(read_data=mock_data)
        cei, cie, not_c_ie, not_c_ei = simple_stats("dummy_url")  # Use dummy URL for testing
        self.assertEqual(cei, 1)
        self.assertEqual(cie, 1)
        self.assertEqual(not_c_ie, 2)
        self.assertEqual(not_c_ei, 2)
    
    @patch('urllib.request.urlopen')
    def test_simple_stats_edge_cases(self, mock_urlopen):
      # Test for no matches
      mock_data = "hello\nworld".encode()
      mock_urlopen.return_value = mock_open(read_data=mock_data)
      cei, cie, not_c_ie, not_c_ei = simple_stats("dummy_url")
      self.assertEqual(cei, 0)
      self.assertEqual(cie, 0)
      self.assertEqual(not_c_ie, 0)
      self.assertEqual(not_c_ei, 0)

      #Test for case sensitivity
      mock_data = "CIE\nCei\nTIE\nTEI\nAie\nAEi\nbCiE\nbCeI".encode()
      mock_urlopen.return_value = mock_open(read_data=mock_data)
      cei, cie, not_c_ie, not_c_ei = simple_stats("dummy_url")
      self.assertEqual(cei, 1)
      self.assertEqual(cie, 1)
      self.assertEqual(not_c_ie, 2)
      self.assertEqual(not_c_ei, 2)



    def test_plausibility_check_plausible(self):
        self.assertTrue(plausibility_check("test case", 5, 1))

    def test_plausibility_check_implausible_greater(self):
        self.assertFalse(plausibility_check("test case", 3, 2))
    
    def test_plausibility_check_implausible_lesser(self):
        self.assertFalse(plausibility_check("test case", 1, 2))
    
    @patch('builtins.print')
    def test_print_result_plausible(self, mock_print):
      print_result(3, 1, 5, 1)
      calls = [str(call) for call in mock_print.mock_calls]
      self.assertIn("OVERALL IT IS PLAUSIBLE!", calls)

    @patch('builtins.print')
    def test_print_result_implausible(self, mock_print):
        print_result(1, 1, 1, 1)  # Ensure implausible scenario
        calls = [str(call) for call in mock_print.mock_calls]
        self.assertIn("OVERALL IT IS IMPLAUSIBLE!", calls)



if __name__ == '__main__':
    unittest.main()

