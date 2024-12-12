import unittest
from unittest.mock import patch
import urllib.request

class TestIbeforeE(unittest.TestCase):
    
    def setUp(self):
        self.mock_wordlist = (
            "receive\n"
            "ceiling\n"
            "deceive\n"
            "perceive\n"
            "receipt\n"
            "ceiling\n"
            "science\n"
            "species\n"
            "society\n"
            "ancient\n"
            "efficient\n"
            "sufficient\n"
            "weird\n"
            "foreign\n"
            "seize\n"
            "leisure\n"
            "neighbor\n"
            "height\n"
            "weight\n"
            "veil\n"
            "rein\n"
            "deceit\n"
            "conceit\n"
            "receipt\n"
            "ceiling\n"
            "science\n"
            "species\n"
            "society\n"
            "ancient\n"
            "efficient\n"
            "sufficient\n"
            "weird\n"
            "foreign\n"
            "seize\n"
            "leisure\n"
            "neighbor\n"
            "height\n"
            "weight\n"
            "veil\n"
            "rein\n"
            "deceit\n"
            "conceit\n"
        )

    def test_plausibility_check(self):
        from i_before_e_except_after_c import plausibility_check
        
        self.assertTrue(plausibility_check("Test plausible", 10, 4))
        self.assertFalse(plausibility_check("Test implausible", 5, 3))
        self.assertFalse(plausibility_check("Test contra-indicated", 2, 5))

    @patch('urllib.request.urlopen')
    def test_simple_stats(self, mock_urlopen):
        from i_before_e_except_after_c import simple_stats
        
        mock_urlopen.return_value.read.return_value = self.mock_wordlist.encode()
        
        cei, cie, not_c_ie, not_c_ei = simple_stats()
        
        self.assertEqual(cei, 6)  # 'cei' words
        self.assertEqual(cie, 0)  # 'cie' words
        self.assertEqual(not_c_ie, 8)  # 'ie' not preceded by 'c'
        self.assertEqual(not_c_ei, 4)  # 'ei' not preceded by 'c'

    @patch('urllib.request.urlopen')
    def test_print_result(self, mock_urlopen):
        from i_before_e_except_after_c import print_result
        
        mock_urlopen.return_value.read.return_value = self.mock_wordlist.encode()
        
        with patch('builtins.print') as mock_print:
            print_result(6, 0, 8, 4)
            mock_print.assert_any_call('\nOVERALL IT IS PLAUSIBLE!')
        
        with patch('builtins.print') as mock_print:
            print_result(3, 2, 4, 3)
            mock_print.assert_any_call('\nOVERALL IT IS IMPLAUSIBLE!')

if __name__ == '__main__':
    unittest.main()
