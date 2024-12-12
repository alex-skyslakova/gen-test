import unittest
from collections import defaultdict

# Assuming the functions from last_letter_first_letter.py are imported
from last_letter_first_letter import order_words, linkfirst, llfl

class TestLastLetterFirstLetter(unittest.TestCase):

    def test_order_words(self):
        words = ["apple", "banana", "cherry", "date"]
        expected = {
            'a': {'apple'},
            'b': {'banana'},
            'c': {'cherry'},
            'd': {'date'}
        }
        self.assertEqual(order_words(words), expected)

    def test_linkfirst_single_word(self):
        byfirst = {'a': {'apple'}, 'b': {'banana'}}
        sofar = ['apple']
        expected = ['apple', 'banana']
        self.assertEqual(linkfirst(byfirst, sofar), expected)

    def test_linkfirst_no_options(self):
        byfirst = {'a': {'apple'}, 'b': {'banana'}}
        sofar = ['banana']
        expected = ['banana']
        self.assertEqual(linkfirst(byfirst, sofar), expected)

    def test_linkfirst_multiple_options(self):
        byfirst = {'a': {'apple', 'apricot'}, 'p': {'pear'}}
        sofar = ['apple']
        expected = ['apple', 'pear']
        self.assertEqual(linkfirst(byfirst, sofar), expected)

    def test_llfl_simple(self):
        words = ["apple", "banana", "cherry", "date"]
        expected = ["apple", "banana"]
        self.assertEqual(llfl(words), expected)

    def test_llfl_complex(self):
        words = ["audino", "bagon", "baltoy", "banette", "bidoof", "braviary", "bronzor", "carracosta", "charmeleon"]
        expected = ["bagon", "nidoran", "nidorina", "audino"]
        self.assertEqual(llfl(words), expected)

    def test_llfl_no_sequence(self):
        words = ["apple", "banana", "cherry"]
        expected = ["apple"]
        self.assertEqual(llfl(words), expected)

    def test_llfl_empty_list(self):
        words = []
        expected = []
        self.assertEqual(llfl(words), expected)

    def test_llfl_single_word(self):
        words = ["apple"]
        expected = ["apple"]
        self.assertEqual(llfl(words), expected)

if __name__ == '__main__':
    unittest.main()
