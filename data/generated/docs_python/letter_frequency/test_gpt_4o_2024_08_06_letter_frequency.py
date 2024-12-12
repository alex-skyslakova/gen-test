import unittest
from unittest.mock import mock_open, patch
from collections import Counter
from letter_frequency import filecharcount

class TestFileCharCount(unittest.TestCase):
    def test_empty_file(self):
        mock_file_data = ""
        with patch('builtins.open', mock_open(read_data=mock_file_data)):
            with open('dummy_file.txt') as f:
                result = filecharcount(f)
        expected = []
        self.assertEqual(result, expected)

    def test_file_with_letters_only(self):
        mock_file_data = "abcABC"
        with patch('builtins.open', mock_open(read_data=mock_file_data)):
            with open('dummy_file.txt') as f:
                result = filecharcount(f)
        expected = sorted(Counter(mock_file_data).items())
        self.assertEqual(result, expected)

    def test_file_with_mixed_characters(self):
        mock_file_data = "abc123!@#ABC"
        with patch('builtins.open', mock_open(read_data=mock_file_data)):
            with open('dummy_file.txt') as f:
                result = filecharcount(f)
        expected = sorted(Counter(mock_file_data).items())
        self.assertEqual(result, expected)

    def test_file_with_newlines(self):
        mock_file_data = "a\nb\nc\nA\nB\nC\n"
        with patch('builtins.open', mock_open(read_data=mock_file_data)):
            with open('dummy_file.txt') as f:
                result = filecharcount(f)
        expected = sorted(Counter(mock_file_data).items())
        self.assertEqual(result, expected)

    def test_file_with_repeated_characters(self):
        mock_file_data = "aaabbbccc"
        with patch('builtins.open', mock_open(read_data=mock_file_data)):
            with open('dummy_file.txt') as f:
                result = filecharcount(f)
        expected = sorted(Counter(mock_file_data).items())
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
