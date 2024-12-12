import unittest
from binary_strings import *

class TestBinaryStrings(unittest.TestCase):

    def test_string_creation(self):
        s1 = "A 'string' literal \n"
        s2 = 'You may use any of \' or " as delimiter'
        s3 = """This text 
           goes over several lines
               up to the closing triple quote"""
        self.assertEqual(s1, "A 'string' literal \n")
        self.assertEqual(s2, 'You may use any of \' or " as delimiter')
        self.assertEqual(s3, """This text 
           goes over several lines
               up to the closing triple quote""")

    def test_string_assignment(self):
        s1 = "original string"
        s2 = s1
        self.assertEqual(s1, s2)

    def test_string_comparison(self):
        s1 = "same string"
        s2 = "same string"
        s3 = "different string"
        self.assertTrue(s1 == s2)
        self.assertFalse(s1 == s3)

    def test_string_cloning_and_copying(self):
        s1 = "original string"
        s2 = s1[:]
        self.assertEqual(s1, s2)
        self.assertIsNot(s1, s2)

    def test_check_if_string_is_empty(self):
        s1 = ""
        s2 = "not empty"
        self.assertTrue(len(s1) == 0)
        self.assertFalse(len(s2) == 0)

    def test_append_byte_to_string(self):
        s1 = "start"
        s1 += "end"
        self.assertEqual(s1, "startend")

    def test_extract_substring(self):
        s1 = "abcdef"
        self.assertEqual(s1[1:4], "bcd")

    def test_replace_every_occurrence(self):
        s1 = "aabbcc"
        s2 = s1.replace("a", "x")
        self.assertEqual(s2, "xxbbcc")

    def test_join_strings(self):
        s1 = "hello"
        s2 = "world"
        joined = " ".join([s1, s2])
        self.assertEqual(joined, "hello world")

if __name__ == '__main__':
    unittest.main()
