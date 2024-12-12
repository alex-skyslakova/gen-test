import unittest
from higher_order_functions import first, second

class TestHigherOrderFunctions(unittest.TestCase):

    def test_first_function(self):
        self.assertEqual(first(second), "second")

    def test_first_with_lambda(self):
        self.assertEqual(first(lambda: "lambda"), "lambda")

    def test_first_with_different_return_type(self):
        self.assertEqual(first(lambda: 123), 123)

    def test_first_with_no_arguments(self):
         self.assertEqual(first(lambda: None), None)

