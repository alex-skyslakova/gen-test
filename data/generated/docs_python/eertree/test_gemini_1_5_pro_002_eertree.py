import unittest
from eertree import Eertree, Node

class TestEertree(unittest.TestCase):

    def test_empty_string(self):
        eertree = Eertree()
        self.assertEqual(len(eertree.nodes), 0)
        result = []
        eertree.get_sub_palindromes(eertree.rto, [eertree.rto], [], result)
        eertree.get_sub_palindromes(eertree.rte, [eertree.rte], [], result)
        self.assertEqual(result, [])

    def test_single_char(self):
        eertree = Eertree()
        eertree.add('a')
        self.assertEqual(len(eertree.nodes), 1)
        result = []
        eertree.get_sub_palindromes(eertree.rto, [eertree.rto], [], result)
        eertree.get_sub_palindromes(eertree.rte, [eertree.rte], [], result)
        self.assertEqual(result, ['a'])

    def test_eertree(self):
        st = "eertree"
        eertree = Eertree()
        for ch in st:
            eertree.add(ch)
        self.assertEqual(len(eertree.nodes), 6)
        result = []
        eertree.get_sub_palindromes(eertree.rto, [eertree.rto], [], result)
        eertree.get_sub_palindromes(eertree.rte, [eertree.rte], [], result)
        self.assertEqual(sorted(result), sorted(['e', 'ee', 'r', 'e', 't', 'r', 'ee'])) # Sorted to avoid order issues

    def test_palindrome(self):
        st = "abaaba"
        eertree = Eertree()
        for ch in st:
            eertree.add(ch)
        result = []
        eertree.get_sub_palindromes(eertree.rto, [eertree.rto], [], result)
        eertree.get_sub_palindromes(eertree.rte, [eertree.rte], [], result)
        self.assertIn("abaaba", result)
        self.assertIn("baab", result)


    def test_get_max_suffix_pal(self):
         eertree = Eertree()
         eertree.add('a')
         eertree.add('b')
         # Test when a match is found immediately.
         self.assertEqual(eertree.get_max_suffix_pal(eertree.maxSufT, 'b').len, 0)
         # Test when traversal through suffix links is needed to find a match.
         eertree.add('a')
         self.assertEqual(eertree.get_max_suffix_pal(eertree.maxSufT, 'a').len, 1)
         # Test when no match is found and the root is reached
         self.assertEqual(eertree.get_max_suffix_pal(eertree.maxSufT, 'c').len, -1)

if __name__ == '__main__':
    unittest.main()
