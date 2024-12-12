import unittest
from apply_a_callback_to_an_array import square, numbers

class TestApplyCallbackToArray(unittest.TestCase):

    def test_list_comprehension(self):
        expected = [1, 9, 25, 49]
        result = [square(n) for n in numbers]
        self.assertEqual(result, expected)

    def test_map_with_function(self):
        expected = [1, 9, 25, 49]
        result = list(map(square, numbers))
        self.assertEqual(result, expected)

    def test_map_with_lambda(self):
        expected = [1, 9, 25, 49]
        result = list(map(lambda x: x*x, numbers))
        self.assertEqual(result, expected)

    def test_list_comprehension_without_function(self):
        expected = [1, 9, 25, 49]
        result = [n * n for n in numbers]
        self.assertEqual(result, expected)

    def test_generator_expression(self):
        expected = [1, 9, 25, 49]
        result = list((n * n for n in numbers))
        self.assertEqual(result, expected)

    def test_itertools_imap(self):
        expected = [1, 9, 25, 49]
        import itertools
        result = list(itertools.imap(square, numbers))
        self.assertEqual(result, expected)

if __name__ == '__main__':
    unittest.main()
