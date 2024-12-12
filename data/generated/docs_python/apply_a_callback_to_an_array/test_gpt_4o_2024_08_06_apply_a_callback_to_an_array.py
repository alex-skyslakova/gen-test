import unittest
from apply_a_callback_to_an_array import square

class TestApplyCallbackToAnArray(unittest.TestCase):

    def test_square_function(self):
        self.assertEqual(square(0), 0)
        self.assertEqual(square(1), 1)
        self.assertEqual(square(2), 4)
        self.assertEqual(square(-3), 9)
        self.assertEqual(square(10), 100)

    def test_list_comprehension(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        squares1 = [square(n) for n in numbers]
        self.assertEqual(squares1, expected_squares)

    def test_map_function(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        squares2a = list(map(square, numbers))
        self.assertEqual(squares2a, expected_squares)

    def test_map_lambda(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        squares2b = list(map(lambda x: x*x, numbers))
        self.assertEqual(squares2b, expected_squares)

    def test_no_function_list_comprehension(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        squares3 = [n * n for n in numbers]
        self.assertEqual(squares3, expected_squares)

    def test_iterator_lazy(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        isquares1 = (n * n for n in numbers)
        self.assertEqual(list(isquares1), expected_squares)

    def test_itertools_imap(self):
        numbers = [1, 3, 5, 7]
        expected_squares = [1, 9, 25, 49]
        # Since itertools.imap is not available in Python 3, using map instead
        isquares2 = map(square, numbers)
        self.assertEqual(list(isquares2), expected_squares)

if __name__ == '__main__':
    unittest.main()
