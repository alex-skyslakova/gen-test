import unittest
from itertools import groupby

def squeezer(s, txt):
    return ''.join(item if item == s else ''.join(grp)
                   for item, grp in groupby(txt))

class TestSqueezer(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual(squeezer(' ', ''), '')
        self.assertEqual(squeezer('-', ''), '')
        self.assertEqual(squeezer('7', ''), '')
        self.assertEqual(squeezer('.', ''), '')

    def test_string_with_hyphen(self):
        txt = '"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln '
        self.assertEqual(squeezer('-', txt), '"If I were two-faced, would I be wearing this one?" - Abraham Lincoln ')

    def test_string_with_seven(self):
        txt = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        self.assertEqual(squeezer('7', txt), "..1111111111111111111111111111111111111111111111111111111111111117888")

    def test_string_with_period(self):
        txt = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        self.assertEqual(squeezer('.', txt), "I never give 'em hell, I just tell the truth, and they think it's hell ")

    def test_string_with_multiple_blanks(self):
        txt = "                                                   ---  Harry S Truman  "
        self.assertEqual(squeezer(' ', txt), " --- Harry S Truman ")

    def test_string_with_repeated_character_e(self):
        txt = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        self.assertEqual(squeezer('e', txt), "The better the 4-whel drive, the further you'll be from help when ya get stuck!")

    def test_string_with_repeated_character_s(self):
        txt = "headmistressship"
        self.assertEqual(squeezer('s', txt), "headmistreship")

    def test_string_with_repeated_character_a(self):
        txt = "aardvark"
        self.assertEqual(squeezer('a', txt), "ardvark")

    def test_string_with_emoji(self):
        txt = "😍😀🙌💃😍😍😍🙌"
        self.assertEqual(squeezer('😍', txt), "😍😀🙌💃😍🙌")

if __name__ == '__main__':
    unittest.main()
