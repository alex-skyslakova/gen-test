import unittest
from exceptions_catch_an_exception_thrown_in_a_nested_call import U0, U1, foo, bar, baz

class TestUserDefinedExceptions(unittest.TestCase):

    def test_foo_catches_U0(self):
        # Capture the output of foo to verify it catches U0
        with self.assertLogs() as log:
            foo()
        self.assertIn("Function foo caught exception U0", log.output[0])

    def test_baz_raises_U0(self):
        # Test that baz raises U0 when called with 0
        with self.assertRaises(U0):
            baz(0)

    def test_baz_raises_U1(self):
        # Test that baz raises U1 when called with 1
        with self.assertRaises(U1):
            baz(1)

    def test_bar_calls_baz(self):
        # Test that bar calls baz and raises U0 or U1 accordingly
        with self.assertRaises(U0):
            bar(0)
        with self.assertRaises(U1):
            bar(1)

if __name__ == '__main__':
    unittest.main()
