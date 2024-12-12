import unittest
from binary_strings import (
    create_string,
    assign_string,
    compare_strings,
    clone_string,
    is_empty,
    append_byte,
    extract_substring,
    replace_occurrences,
    join_strings
)

class TestBinaryStrings(unittest.TestCase):

    def test_create_string(self):
        s = create_string(b"hello")
        self.assertEqual(s, b"hello")

    def test_assign_string(self):
        s = create_string(b"initial")
        assign_string(s, b"new value")
        self.assertEqual(s, b"new value")

    def test_compare_strings(self):
        s1 = create_string(b"test")
        s2 = create_string(b"test")
        s3 = create_string(b"other")
        self.assertTrue(compare_strings(s1, s2))
        self.assertFalse(compare_strings(s1, s3))

    def test_clone_string(self):
        s1 = create_string(b"clone me")
        s2 = clone_string(s1)
        self.assertEqual(s1, s2)
        self.assertIsNot(s1, s2)  # Ensure they are different objects

    def test_is_empty(self):
        s1 = create_string(b"")
        s2 = create_string(b"not empty")
        self.assertTrue(is_empty(s1))
        self.assertFalse(is_empty(s2))

    def test_append_byte(self):
        s = create_string(b"hello")
        append_byte(s, b"!")
        self.assertEqual(s, b"hello!")

    def test_extract_substring(self):
        s = create_string(b"hello world")
        sub = extract_substring(s, 0, 5)
        self.assertEqual(sub, b"hello")
        sub = extract_substring(s, 6, 11)
        self.assertEqual(sub, b"world")

    def test_replace_occurrences(self):
        s = create_string(b"banana")
        result = replace_occurrences(s, b"na", b"NA")
        self.assertEqual(result, b"baNANA")

    def test_join_strings(self):
        s1 = create_string(b"hello")
        s2 = create_string(b"world")
        result = join_strings([s1, s2], b" ")
        self.assertEqual(result, b"hello world")

if __name__ == '__main__':
    unittest.main()
