import unittest

class TestEertree(unittest.TestCase):
    def setUp(self):
        self.eertree = Eertree()

    def test_initial_state(self):
        # Test initial state of the eertree
        self.assertEqual(len(self.eertree.nodes), 0)
        self.assertEqual(self.eertree.rto.len, -1)
        self.assertEqual(self.eertree.rte.len, 0)
        self.assertEqual(self.eertree.S, [0])
        self.assertIs(self.eertree.maxSufT, self.eertree.rte)

    def test_add_single_character(self):
        # Test adding a single character
        self.assertTrue(self.eertree.add('e'))
        self.assertEqual(len(self.eertree.nodes), 1)
        self.assertEqual(self.eertree.nodes[0].len, 1)

    def test_add_duplicate_character(self):
        # Test adding a duplicate character
        self.eertree.add('e')
        self.assertFalse(self.eertree.add('e'))
        self.assertEqual(len(self.eertree.nodes), 1)

    def test_add_multiple_characters(self):
        # Test adding multiple characters
        for ch in "eertree":
            self.eertree.add(ch)
        self.assertEqual(len(self.eertree.nodes), 6)

    def test_get_sub_palindromes(self):
        # Test getting sub-palindromes
        for ch in "eertree":
            self.eertree.add(ch)
        result = []
        self.eertree.get_sub_palindromes(self.eertree.rto, [self.eertree.rto], [], result)
        self.eertree.get_sub_palindromes(self.eertree.rte, [self.eertree.rte], [], result)
        expected_palindromes = ['e', 'ee', 'r', 'e', 'ee', 'eertree']
        self.assertCountEqual(result, expected_palindromes)

    def test_get_max_suffix_pal(self):
        # Test get_max_suffix_pal method
        for ch in "eertree":
            self.eertree.add(ch)
        node = self.eertree.get_max_suffix_pal(self.eertree.maxSufT, 'e')
        self.assertEqual(node.len, 1)

if __name__ == '__main__':
    unittest.main()
