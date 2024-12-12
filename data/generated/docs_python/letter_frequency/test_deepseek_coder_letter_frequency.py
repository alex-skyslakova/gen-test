import unittest
from collections import Counter
from io import StringIO
from letter_frequency import filecharcount

class TestFileCharCount(unittest.TestCase):

    def test_empty_file(self):
        # Test with an empty file
        f = StringIO("")
        result = filecharcount(f)
        self.assertEqual(result, [])

    def test_single_character_file(self):
        # Test with a file containing a single character
        f = StringIO("a")
        result = filecharcount(f)
        self.assertEqual(result, [('a', 1)])

    def test_multiple_characters_file(self):
        # Test with a file containing multiple characters
        f = StringIO("abbccc")
        result = filecharcount(f)
        self.assertEqual(result, [('a', 1), ('b', 2), ('c', 3)])

    def test_mixed_characters_file(self):
        # Test with a file containing mixed characters (letters and punctuation)
        f = StringIO("a,b.c!d?e")
        result = filecharcount(f)
        self.assertEqual(result, [('!', 1), (',', 1), ('.', 1), ('?', 1), ('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)])

    def test_case_sensitivity(self):
        # Test with a file containing both uppercase and lowercase letters
        f = StringIO("aAaBbCc")
        result = filecharcount(f)
        self.assertEqual(result, [('A', 1), ('B', 1), ('C', 1), ('a', 2), ('b', 1), ('c', 1)])

    def test_newline_characters(self):
        # Test with a file containing newline characters
        f = StringIO("a\nb\nc\n")
        result = filecharcount(f)
        self.assertEqual(result, [('\n', 3), ('a', 1), ('b', 1), ('c', 1)])

    def test_large_file(self):
        # Test with a large file containing repeated characters
        f = StringIO("a" * 100 + "b" * 50 + "c" * 25)
        result = filecharcount(f)
        self.assertEqual(result, [('a', 100), ('b', 50), ('c', 25)])

if __name__ == '__main__':
    unittest.main()
