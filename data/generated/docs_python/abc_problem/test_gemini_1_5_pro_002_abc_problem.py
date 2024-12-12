import unittest

blocks = [("B", "O"),
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
          ("Z", "M")]


def can_make_word(word, block_collection=blocks):
    if not word:
        return False

    word = word.upper()
    block_collection = list(block_collection)  # Create a copy to avoid modifying the original

    for char in word:
        found = False
        for i in range(len(block_collection)):
            if char in block_collection[i]:
                block_collection.pop(i)
                found = True
                break
        if not found:
            return False
    return True


class TestCanMakeWord(unittest.TestCase):

    def test_empty_word(self):
        self.assertFalse(can_make_word(""))

    def test_single_letter(self):
        self.assertTrue(can_make_word("A"))

    def test_bark(self):
        self.assertTrue(can_make_word("BARK"))

    def test_book(self):
        self.assertFalse(can_make_word("BOOK"))

    def test_treat(self):
        self.assertTrue(can_make_word("TREAT"))

    def test_common(self):
        self.assertFalse(can_make_word("COMMON"))

    def test_squad(self):
        self.assertTrue(can_make_word("SQUAD"))

    def test_confuse(self):
        self.assertTrue(can_make_word("CONFUSE"))
        
    def test_lowercase(self):
        self.assertTrue(can_make_word("coNFused"))

    def test_ab(self): # Test case highlighting original code's flaw
        test_blocks = [("A", "B"), ("A","C")]
        self.assertTrue(can_make_word("AB", test_blocks))
        
    def test_aa(self): # Test needing two As
        test_blocks = [("A", "B"), ("A","C")]
        self.assertTrue(can_make_word("AA", test_blocks))
        
    def test_aaa(self): # Test impossible to make from available blocks
         test_blocks = [("A", "B"), ("A","C")]
         self.assertFalse(can_make_word("AAA", test_blocks))



if __name__ == '__main__':
    unittest.main()
