import unittest
from unittest.mock import patch

# Assuming the code to be tested is in a module named 'extend_your_language'
import extend_your_language as ext

class TestControlStructures(unittest.TestCase):

    @patch('extend_your_language.print')
    def test_both_conditions_true(self, mock_print):
        ext.a, ext.b = 1, 3
        ext.c1 = ext.a == 1
        ext.c2 = ext.b == 3
        
        if ext.c1 and ext.c2:
            print('a = 1 and b = 3')
        elif ext.c1:
            print('a = 1 and b <> 3')
        elif ext.c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        mock_print.assert_called_once_with('a = 1 and b = 3')

    @patch('extend_your_language.print')
    def test_first_condition_true(self, mock_print):
        ext.a, ext.b = 1, 0
        ext.c1 = ext.a == 1
        ext.c2 = ext.b == 3
        
        if ext.c1 and ext.c2:
            print('a = 1 and b = 3')
        elif ext.c1:
            print('a = 1 and b <> 3')
        elif ext.c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        mock_print.assert_called_once_with('a = 1 and b <> 3')

    @patch('extend_your_language.print')
    def test_second_condition_true(self, mock_print):
        ext.a, ext.b = 0, 3
        ext.c1 = ext.a == 1
        ext.c2 = ext.b == 3
        
        if ext.c1 and ext.c2:
            print('a = 1 and b = 3')
        elif ext.c1:
            print('a = 1 and b <> 3')
        elif ext.c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        mock_print.assert_called_once_with('a <> 1 and b = 3')

    @patch('extend_your_language.print')
    def test_no_condition_true(self, mock_print):
        ext.a, ext.b = 0, 0
        ext.c1 = ext.a == 1
        ext.c2 = ext.b == 3
        
        if ext.c1 and ext.c2:
            print('a = 1 and b = 3')
        elif ext.c1:
            print('a = 1 and b <> 3')
        elif ext.c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        mock_print.assert_called_once_with('a <> 1 and b <> 3')

if __name__ == '__main__':
    unittest.main()
