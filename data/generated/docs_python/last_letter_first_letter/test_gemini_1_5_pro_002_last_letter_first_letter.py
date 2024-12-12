import unittest
from collections import defaultdict

from last_letter_first_letter import order_words, linkfirst, llfl


class TestLastLetterFirstLetter(unittest.TestCase):

    def test_order_words(self):
        words = ["dog", "goldfish", "hippopotamus", "snake"]
        expected = defaultdict(set, {'d': {'dog'}, 'g': {'goldfish'}, 'h': {'hippopotamus'}, 's': {'snake'}})
        self.assertEqual(order_words(words), expected)

        words = ["apple", "banana", "avocado", "apricot"]
        expected = defaultdict(set, {'a': {'apple', 'avocado', 'apricot'}, 'b': {'banana'}})
        self.assertEqual(order_words(words), expected)

        words = []
        expected = defaultdict(set)
        self.assertEqual(order_words(words), expected)

    def test_linkfirst(self):
        words = ["dog", "goldfish", "hippopotamus", "snake", "elephant"]
        byfirst = order_words(words)

        sofar = ["dog"]
        expected = ["dog", "goldfish"]
        self.assertEqual(linkfirst(byfirst, sofar), expected)

        sofar = ["snake"]
        expected = ["snake", "elephant"]
        self.assertEqual(linkfirst(byfirst, sofar), expected)
        
        sofar = ["goldfish"]
        expected = ["goldfish"]
        self.assertEqual(linkfirst(byfirst, sofar), expected)

        sofar = ["elephant"]
        expected = ["elephant"]
        self.assertEqual(linkfirst(byfirst, sofar), expected)



    def test_llfl(self):
        words = ["dog", "goldfish", "hippopotamus", "snake", "elephant"]
        expected = ["snake", "elephant"]  # Not necessarily this order if multiple solutions
        self.assertEqual(len(llfl(words)), len(expected))
        
        words = ["apple", "egg", "grape", "elephant"]
        expected = ["apple", "egg", "grape", "elephant"]
        self.assertEqual(len(llfl(words)), len(expected))

        words = ["abc", "bca", "cab"]
        expected = ["abc", "cab"]
        self.assertEqual(len(llfl(words)), len(expected))
        
        words = ["axe", "egg", "gear"]
        self.assertEqual(len(llfl(words)), 3)

        words = []
        self.assertEqual(len(llfl(words)), 0)


