import unittest
from exceptions_catch_an_exception_thrown_in_a_nested_call import U0, U1, foo, bar, baz

class TestNestedExceptions(unittest.TestCase):

    def test_baz_raises_U0(self):
        with self.assertRaises(U0):
            baz(0)

    def test_baz_raises_U1(self):
        with self.assertRaises(U1):
            baz(1)

    def test_bar_propagates_U0(self):
        with self.assertRaises(U0):
            bar(0)

    def test_bar_propagates_U1(self):
        with self.assertRaises(U1):
            bar(1)

    def test_foo_catches_U0(self):
        with self.assertRaises(U1):  # foo catches U0, but U1 will still propagate
            foo()



