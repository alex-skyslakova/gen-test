import unittest

class TestCanMakeWord(unittest.TestCase):
    def setUp(self):
        self.blocks = [
            ("B", "O"),
            ("X", "K"),
            ("D", "Q"),
            ("C", "P"),
            ("N", "A"),
            ("G", "T"),
            ("R", "E"),
            ("T", "G"),
            ("Q", "D"),
            ("F", "S"),
            ("J", "W"),
            ("H", "U"),
            ("V", "I"),
            ("A", "N"),
            ("O", "B"),
            ("E", "R"),
            ("F", "S"),
            ("L", "Y"),
            ("P", "C"),
            ("Z", "M")
        ]

    def test_empty_string(self):
        self.assertFalse(can_make_word("", self.blocks))

    def test_single_letter(self):
        self.assertTrue(can_make_word("A", self.blocks))
        self.assertTrue(can_make_word("a", self.blocks))

    def test_word_bark(self):
        self.assertTrue(can_make_word("BARK", self.blocks))
        self.assertTrue(can_make_word("bark", self.blocks))

    def test_word_book(self):
        self.assertFalse(can_make_word("BOOK", self.blocks))
        self.assertFalse(can_make_word("book", self.blocks))

    def test_word_treat(self):
        self.assertTrue(can_make_word("TREAT", self.blocks))
        self.assertTrue(can_make_word("treat", self.blocks))

    def test_word_common(self):
        self.assertFalse(can_make_word("COMMON", self.blocks))
        self.assertFalse(can_make_word("common", self.blocks))

    def test_word_squad(self):
        self.assertTrue(can_make_word("SQUAD", self.blocks))
        self.assertTrue(can_make_word("squad", self.blocks))

    def test_word_confuse(self):
        self.assertTrue(can_make_word("CONFUSE", self.blocks))
        self.assertTrue(can_make_word("confuse", self.blocks))

    def test_case_insensitivity(self):
        self.assertTrue(can_make_word("CoNfUsE", self.blocks))

    def test_repeated_letters(self):
        self.assertFalse(can_make_word("AA", [("A", "B"), ("A", "C")]))
        self.assertTrue(can_make_word("AB", [("A", "B"), ("A", "C")]))

if __name__ == '__main__':
    unittest.main()
