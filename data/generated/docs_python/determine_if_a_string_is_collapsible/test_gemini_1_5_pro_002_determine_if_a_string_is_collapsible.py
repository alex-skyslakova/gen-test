import unittest
from itertools import groupby

def collapser(txt):
    return ''.join(item for item, grp in groupby(txt))

class TestCollapser(unittest.TestCase):

    def test_empty_string(self):
        self.assertEqual(collapser(""), "")

    def test_no_repeats(self):
        self.assertEqual(collapser("aardvark"), "aardvark")

    def test_single_repeats(self):
        self.assertEqual(collapser("The better the 4-wheel drive, the further you'll be from help when ya get stuck!"), "The beter the 4-whel drive, the further you'l be from help when ya get stuck!")

    def test_multiple_repeats(self):
        self.assertEqual(collapser("headmistressship"), "headmistreship")

    def test_long_repeats(self):
        self.assertEqual(collapser("..1111111111111111111111111111111111111111111111111111111111111117777888"), "..178")

    def test_repeating_spaces(self):
        self.assertEqual(collapser("                                                   ---  Harry S Truman  "), " --- Harry S Truman ")

    def test_mixed_characters_and_repeats(self):
        self.assertEqual(collapser('"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln '), '"If I were two-faced, would I be wearing this one?" --- Abraham Lincoln ')
    
    def test_unicode(self):
        self.assertEqual(collapser("😍😀🙌💃😍😍😍🙌"), "😍😀🙌💃😍🙌")

if __name__ == '__main__':
    unittest.main()
