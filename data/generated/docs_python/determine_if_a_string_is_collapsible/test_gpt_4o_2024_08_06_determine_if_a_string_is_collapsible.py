import unittest
from itertools import groupby

def collapser(txt):
    return ''.join(item for item, grp in groupby(txt))

class TestCollapser(unittest.TestCase):

    def test_null_string(self):
        original = ""
        expected = ""
        self.assertEqual(collapser(original), expected)

    def test_quote_by_lincoln(self):
        original = '"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln '
        expected = '"If I were two-faced, would I be wearing this one?" - Abraham Lincoln '
        self.assertEqual(collapser(original), expected)

    def test_repeated_numbers(self):
        original = "..1111111111111111111111111111111111111111111111111111111111111117777888"
        expected = ".1.178"
        self.assertEqual(collapser(original), expected)

    def test_quote_by_truman(self):
        original = "I never give 'em hell, I just tell the truth, and they think it's hell. "
        expected = "I never give 'em hel, I just tel the truth, and they think it's hel. "
        self.assertEqual(collapser(original), expected)

    def test_repeated_blanks(self):
        original = "                                                   ---  Harry S Truman  "
        expected = " - Hary S Truman "
        self.assertEqual(collapser(original), expected)

    def test_better_drive(self):
        original = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!"
        expected = "The beter the 4-whel drive, the further you'll be from help when ya get stuck!"
        self.assertEqual(collapser(original), expected)

    def test_headmistressship(self):
        original = "headmistressship"
        expected = "headmistreship"
        self.assertEqual(collapser(original), expected)

    def test_aardvark(self):
        original = "aardvark"
        expected = "ardvark"
        self.assertEqual(collapser(original), expected)

    def test_emojis(self):
        original = "😍😀🙌💃😍😍😍🙌"
        expected = "😍😀🙌💃😍🙌"
        self.assertEqual(collapser(original), expected)

if __name__ == '__main__':
    unittest.main()
