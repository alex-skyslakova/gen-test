import unittest
import apply_a_callback_to_an_array
import itertools

class TestApplyCallbackToArray(unittest.TestCase):

    def test_list_comprehension(self):
        self.assertEqual(apply_a_callback_to_an_array.squares1, [1, 9, 25, 49])

    def test_map_function(self):
        self.assertEqual(list(apply_a_callback_to_an_array.squares2a), [1, 9, 25, 49])

    def test_map_lambda(self):
        self.assertEqual(list(apply_a_callback_to_an_array.squares2b), [1, 9, 25, 49])

    def test_list_comprehension_no_function(self):
        self.assertEqual(apply_a_callback_to_an_array.squares3, [1, 9, 25, 49])

    def test_iterator_generator(self):
        self.assertEqual(list(apply_a_callback_to_an_array.isquares1), [1, 9, 25, 49])

    def test_iterator_imap(self):
        self.assertEqual(list(apply_a_callback_to_an_array.isquares2), [1, 9, 25, 49])


if __name__ == '__main__':
    unittest.main()
