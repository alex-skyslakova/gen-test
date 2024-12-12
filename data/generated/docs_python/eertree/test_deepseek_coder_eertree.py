import unittest
from eertree import Eertree, Node

class TestEertree(unittest.TestCase):

    def setUp(self):
        self.eertree = Eertree()

    def test_initial_state(self):
        self.assertEqual(len(self.eertree.nodes), 0)
        self.assertEqual(self.eertree.rto.len, -1)
        self.assertEqual(self.eertree.rte.len, 0)
        self.assertEqual(self.eertree.S, [0])
        self.assertEqual(self.eertree.maxSufT, self.eertree.rte)

    def test_add_single_character(self):
        self.assertTrue(self.eertree.add('a'))
        self.assertEqual(len(self.eertree.nodes), 1)
        self.assertEqual(self.eertree.S, [0, 'a'])
        self.assertEqual(self.eertree.maxSufT.len, 1)

    def test_add_multiple_characters(self):
        self.assertTrue(self.eertree.add('e'))
        self.assertTrue(self.eertree.add('e'))
        self.assertTrue(self.eertree.add('r'))
        self.assertTrue(self.eertree.add('t'))
        self.assertTrue(self.eertree.add('r'))
        self.assertTrue(self.eertree.add('e'))
        self.assertTrue(self.eertree.add('e'))
        self.assertEqual(len(self.eertree.nodes), 7)
        self.assertEqual(self.eertree.S, [0, 'e', 'e', 'r', 't', 'r', 'e', 'e'])
        self.assertEqual(self.eertree.maxSufT.len, 2)

    def test_get_sub_palindromes(self):
        st = "eertree"
        for ch in st:
            self.eertree.add(ch)

        result = []
        self.eertree.get_sub_palindromes(self.eertree.rto, [self.eertree.rto], [], result)
        self.eertree.get_sub_palindromes(self.eertree.rte, [self.eertree.rte], [], result)

        expected_result = ['e', 'ee', 'eertree', 'ertre', 'r', 't', 'rtr', 'eertree', 'ertre', 'r', 't', 'rtr']
        self.assertEqual(sorted(result), sorted(expected_result))

    def test_get_max_suffix_pal(self):
        self.eertree.add('e')
        self.eertree.add('e')
        self.eertree.add('r')
        self.eertree.add('t')
        self.eertree.add('r')
        self.eertree.add('e')
        self.eertree.add('e')

        node = self.eertree.get_max_suffix_pal(self.eertree.maxSufT, 'e')
        self.assertEqual(node.len, 2)

    def test_node_creation(self):
        node = Node()
        self.assertEqual(node.edges, {})
        self.assertIsNone(node.link)
        self.assertEqual(node.len, 0)

if __name__ == '__main__':
    unittest.main()
