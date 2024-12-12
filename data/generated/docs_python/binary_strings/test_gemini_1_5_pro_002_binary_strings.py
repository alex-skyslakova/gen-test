import unittest
import binary_strings

class TestBinaryStrings(unittest.TestCase):

    def test_create_and_destroy(self):
        # Python handles memory management automatically, so no explicit test for creation/destruction is needed.
        # This test asserts that basic string creation works.
        s = binary_strings.BinaryString(b"test")
        self.assertEqual(s.data, b"test")

    def test_assignment(self):
        s = binary_strings.BinaryString(b"")
        s.assign(b"hello")
        self.assertEqual(s.data, b"hello")

    def test_comparison(self):
        s1 = binary_strings.BinaryString(b"hello")
        s2 = binary_strings.BinaryString(b"hello")
        s3 = binary_strings.BinaryString(b"world")
        self.assertTrue(s1 == s2)
        self.assertFalse(s1 == s3)
        self.assertFalse(s1 != s2)
        self.assertTrue(s1 != s3)


    def test_cloning_and_copying(self):
        s1 = binary_strings.BinaryString(b"hello")
        s2 = s1.clone()
        self.assertEqual(s1.data, s2.data)
        s1.data = b"world"  # Modifying the original should not affect the clone
        self.assertNotEqual(s1.data, s2.data)

    def test_is_empty(self):
        s1 = binary_strings.BinaryString(b"")
        s2 = binary_strings.BinaryString(b"hello")
        self.assertTrue(s1.is_empty())
        self.assertFalse(s2.is_empty())


    def test_append_byte(self):
        s = binary_strings.BinaryString(b"hello")
        s.append(b"!")
        self.assertEqual(s.data, b"hello!")

    def test_substring(self):
        s = binary_strings.BinaryString(b"hello world")
        sub = s.substring(6, 5) # "world"
        self.assertEqual(sub.data, b"world")

        sub = s.substring(0, 5)
        self.assertEqual(sub.data, b'hello')

        sub = s.substring(0, 0)
        self.assertEqual(sub.data, b'')

    def test_replace(self):
        s = binary_strings.BinaryString(b"hello world")
        s.replace(b"l", b"L")
        self.assertEqual(s.data, b"heLLo worLd")

        s = binary_strings.BinaryString(b"hello world")
        s.replace(b"world", b"friend")
        self.assertEqual(s.data, b"hello friend")


    def test_join(self):
       s1 = binary_strings.BinaryString(b"hello")
       s2 = binary_strings.BinaryString(b" ")
       s3 = binary_strings.BinaryString(b"world")
       joined = binary_strings.join_strings([s1, s2, s3])
       self.assertEqual(joined.data, b"hello world")


if __name__ == '__main__':
    unittest.main()
