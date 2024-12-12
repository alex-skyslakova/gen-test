import unittest
from itertools import groupby

def squeezer(s, txt):
    return ''.join(item if item == s else ''.join(grp)
                   for item, grp in groupby(txt))

class TestSqueezerFunction(unittest.TestCase):

    def test_null_string(self):
        self.assertEqual(squeezer(' ', ""), "")

    def test_string_with_hyphen(self):
        original = '"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln '
        expected = '"If I were two-faced, would I be wearing this one?" - Abraham Lincoln '
        self.assertEqual(squeezer('-', original), expected)

    def test_string_with_sevens(self):
        original = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        expected = "..1111111111111111111111111111111111111111111111111111111111111117888"
        self.assertEqual(squeezer('7', original), expected)

    def test_string_with_periods(self):
        original = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        expected = "I never give 'em hell, I just tell the truth, and they think it's hell "
        self.assertEqual(squeezer('.', original), expected)

    def test_truman_signature_with_blank(self):
        original = "                                                   ---  Harry S Truman  "
        expected = " --- Harry S Truman "
        self.assertEqual(squeezer(' ', original), expected)

    def test_truman_signature_with_minus(self):
        original = "                                                   ---  Harry S Truman  "
        expected = "                                                   -  Harry S Truman  "
        self.assertEqual(squeezer('-', original), expected)

    def test_truman_signature_with_r(self):
        original = "                                                   ---  Harry S Truman  "
        expected = "                                                   ---  Hary S Tuman  "
        self.assertEqual(squeezer('r', original), expected)

    def test_better_4_wheel_drive(self):
        original = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        expected = "The better the 4-whel drive, the further you'll be from help when ya get stuck!"
        self.assertEqual(squeezer('e', original), expected)

    def test_headmistressship(self):
        original = "headmistressship"
        expected = "headmistreship"
        self.assertEqual(squeezer('s', original), expected)

    def test_aardvark(self):
        original = "aardvark"
        expected = "ardvark"
        self.assertEqual(squeezer('a', original), expected)

    def test_emoji_string(self):
        original = "😍😀🙌💃😍😍😍🙌"
        expected = "😍😀🙌💃😍🙌"
        self.assertEqual(squeezer('😍', original), expected)

if __name__ == '__main__':
    unittest.main()
