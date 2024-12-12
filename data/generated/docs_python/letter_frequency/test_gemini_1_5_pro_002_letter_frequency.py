import unittest
import sys
from io import StringIO
from letter_frequency import filecharcount

class TestFileCharCount(unittest.TestCase):

    def test_empty_file(self):
        with open("test_empty.txt", "w") as f:
            pass  # Create an empty file
        with open("test_empty.txt", "r") as f:
            self.assertEqual(filecharcount(f), [])

    def test_single_character(self):
        with open("test_single.txt", "w") as f:
            f.write("a")
        with open("test_single.txt", "r") as f:
            self.assertEqual(filecharcount(f), [('a', 1)])

    def test_multiple_characters(self):
        with open("test_multiple.txt", "w") as f:
            f.write("abcabcabc")
        with open("test_multiple.txt", "r") as f:
            self.assertEqual(filecharcount(f), [('a', 3), ('b', 3), ('c', 3)])
    
    def test_mixed_case_and_symbols(self):
        with open("test_mixed.txt", "w") as f:
            f.write("Hello, World! 123")
        with open("test_mixed.txt", "r") as f:
            expected_result = [(' ', 2), ('!', 1), ('1', 1), ('2', 1), ('3', 1), (',', 1), ('H', 1), ('W', 1), ('d', 1), ('e', 1), ('l', 3), ('o', 2), ('r', 1)]
            self.assertEqual(filecharcount(f), expected_result)

    def test_newline_characters(self):
        with open("test_newline.txt", "w") as f:
            f.write("a\nb\nc")
        with open("test_newline.txt", "r") as f:
            self.assertEqual(filecharcount(f), [('\n', 2), ('a', 1), ('b', 1), ('c', 1)])



if __name__ == '__main__':
    unittest.main()

