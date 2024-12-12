import unittest
from abc_problem import can_make_word, blocks

class TestCanMakeWord(unittest.TestCase):

    def test_empty_word(self):
        self.assertFalse(can_make_word(""))

    def test_single_letter_word(self):
        self.assertTrue(can_make_word("A"))
        self.assertTrue(can_make_word("B"))
        self.assertTrue(can_make_word("Z"))

    def test_case_insensitivity(self):
        self.assertTrue(can_make_word("a"))
        self.assertTrue(can_make_word("b"))
        self.assertTrue(can_make_word("z"))
        self.assertTrue(can_make_word("A"))
        self.assertTrue(can_make_word("B"))
        self.assertTrue(can_make_word("Z"))

    def test_example_words(self):
        self.assertTrue(can_make_word("BARK"))
        self.assertFalse(can_make_word("BOOK"))
        self.assertTrue(can_make_word("TREAT"))
        self.assertFalse(can_make_word("COMMON"))
        self.assertTrue(can_make_word("SQUAD"))
        self.assertTrue(can_make_word("CONFUSE"))

    def test_custom_blocks(self):
        custom_blocks = [("A", "B"), ("A", "C")]
        self.assertTrue(can_make_word("AB", custom_blocks))
        self.assertTrue(can_make_word("AC", custom_blocks))
        self.assertFalse(can_make_word("ABC", custom_blocks))

    def test_repeated_letters(self):
        self.assertFalse(can_make_word("AA"))
        self.assertFalse(can_make_word("BB"))
        self.assertFalse(can_make_word("ZZ"))

    def test_all_blocks_used(self):
        self.assertTrue(can_make_word("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))

    def test_impossible_words(self):
        self.assertFalse(can_make_word("IMPOSSIBLE"))
        self.assertFalse(can_make_word("UNMAKABLE"))

if __name__ == "__main__":
    unittest.main()
