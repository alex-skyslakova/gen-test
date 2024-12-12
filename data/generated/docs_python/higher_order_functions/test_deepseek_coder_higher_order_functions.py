import unittest
from higher_order_functions import first, second

class TestHigherOrderFunctions(unittest.TestCase):

    def test_first_with_second_function(self):
        # Test that the first function correctly calls the second function
        result = first(second)
        self.assertEqual(result, "second")

    def test_first_with_lambda(self):
        # Test that the first function works with a lambda function
        result = first(lambda: "lambda")
        self.assertEqual(result, "lambda")

    def test_first_with_none(self):
        # Test that the first function raises a TypeError if no function is passed
        with self.assertRaises(TypeError):
            first(None)

    def test_first_with_non_callable(self):
        # Test that the first function raises a TypeError if a non-callable is passed
        with self.assertRaises(TypeError):
            first("not a function")

if __name__ == '__main__':
    unittest.main()
