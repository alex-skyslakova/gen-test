import unittest
from unittest.mock import patch, MagicMock

class TestDetermineLetters(unittest.TestCase):

    @patch('builtins.print')
    def test_determine_all_lowercase_letters(self, mock_print):
        # Mock the behavior of str.islower to only return True for lowercase letters
        with patch('builtins.str.islower', side_effect=lambda x: x in 'abcdefghijklmnopqrstuvwxyz'):
            # Import the function to be tested
            from idiomatically_determine_all_the_lowercase_and_uppercase_letters import classes
            
            # Execute the code
            for stringclass in classes:
                if stringclass.__name__ == 'islower':
                    chars = ''.join(chr(i) for i in range(0x10FFFF+1) if stringclass(chr(i)))
                    mock_print.assert_called_with('\nString class islower has 26 characters the first of which are:\n  abcdefghijklmnopqrstuvwxyz')
                    self.assertEqual(chars, 'abcdefghijklmnopqrstuvwxyz')

    @patch('builtins.print')
    def test_determine_all_uppercase_letters(self, mock_print):
        # Mock the behavior of str.isupper to only return True for uppercase letters
        with patch('builtins.str.isupper', side_effect=lambda x: x in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'):
            # Import the function to be tested
            from idiomatically_determine_all_the_lowercase_and_uppercase_letters import classes
            
            # Execute the code
            for stringclass in classes:
                if stringclass.__name__ == 'isupper':
                    chars = ''.join(chr(i) for i in range(0x10FFFF+1) if stringclass(chr(i)))
                    mock_print.assert_called_with('\nString class isupper has 26 characters the first of which are:\n  ABCDEFGHIJKLMNOPQRSTUVWXYZ')
                    self.assertEqual(chars, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')

    @patch('builtins.print')
    def test_determine_all_letters(self, mock_print):
        # Mock the behavior of str.isalpha to only return True for letters
        with patch('builtins.str.isalpha', side_effect=lambda x: x in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'):
            # Import the function to be tested
            from idiomatically_determine_all_the_lowercase_and_uppercase_letters import classes
            
            # Execute the code
            for stringclass in classes:
                if stringclass.__name__ == 'isalpha':
                    chars = ''.join(chr(i) for i in range(0x10FFFF+1) if stringclass(chr(i)))
                    mock_print.assert_called_with('\nString class isalpha has 52 characters the first of which are:\n  abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ')
                    self.assertEqual(chars, 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ')

if __name__ == '__main__':
    unittest.main()
