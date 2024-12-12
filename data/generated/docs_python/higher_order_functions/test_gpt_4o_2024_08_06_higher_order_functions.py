import unittest
from higher_order_functions import first, second

class TestHigherOrderFunctions(unittest.TestCase):

    def test_first_with_second(self):
        # Test if the first function correctly calls the second function
        self.assertEqual(first(second), "second")

    def test_first_with_lambda(self):
        # Test if the first function can handle a lambda function
        self.assertEqual(first(lambda: "lambda"), "lambda")

    def test_first_with_different_function(self):
        # Test if the first function can handle a different function
        def another_function():
            return "another"
        
        self.assertEqual(first(another_function), "another")

    def test_first_with_no_return_function(self):
        # Test if the first function can handle a function with no return value
        def no_return_function():
            pass
        
        self.assertIsNone(first(no_return_function))

if __name__ == '__main__':
    unittest.main()
