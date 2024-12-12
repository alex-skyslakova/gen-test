import unittest
from md5_implementation import md5, md5_to_hex

class TestMD5Implementation(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual(md5_to_hex(md5(b"")), "d41d8cd98f00b204e9800998ecf8427e")

    def test_single_character(self):
        self.assertEqual(md5_to_hex(md5(b"a")), "0cc175b9c0f1b6a831c399e269772661")

    def test_three_characters(self):
        self.assertEqual(md5_to_hex(md5(b"abc")), "900150983cd24fb0d6963f7d28e17f72")

    def test_message_digest(self):
        self.assertEqual(md5_to_hex(md5(b"message digest")), "f96b697d7cb7938d525a2f31aaf161d0")

    def test_alphabet_lowercase(self):
        self.assertEqual(md5_to_hex(md5(b"abcdefghijklmnopqrstuvwxyz")), "c3fcd3d76192e4007dfb496cca67e13b")

    def test_alphabet_uppercase_lowercase_digits(self):
        self.assertEqual(md5_to_hex(md5(b"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")), "d174ab98d277d9f5a5611c2c9f419d9f")

    def test_long_string_of_digits(self):
        self.assertEqual(md5_to_hex(md5(b"12345678901234567890123456789012345678901234567890123456789012345678901234567890")), "57edf4a22be3c955ac49da2e2107b67a")

if __name__ == '__main__':
    unittest.main()
