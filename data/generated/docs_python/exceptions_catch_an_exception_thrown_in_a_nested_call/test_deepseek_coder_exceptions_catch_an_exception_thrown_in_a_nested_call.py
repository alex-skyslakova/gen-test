import unittest
from exceptions_catch_an_exception_thrown_in_a_nested_call import U0, U1, foo, bar, baz

class TestExceptionHandling(unittest.TestCase):

    def test_foo_catches_U0_and_not_U1(self):
        with self.assertLogs() as captured:
            foo()
        
        # Check that the log contains the message for catching U0
        self.assertEqual(len(captured.records), 1)
        self.assertEqual(captured.records[0].getMessage(), "Function foo caught exception U0")

    def test_baz_raises_U0_on_first_call(self):
        with self.assertRaises(U0):
            baz(0)

    def test_baz_raises_U1_on_second_call(self):
        with self.assertRaises(U1):
            baz(1)

    def test_bar_calls_baz(self):
        with self.assertRaises(U0):
            bar(0)
        with self.assertRaises(U1):
            bar(1)

if __name__ == '__main__':
    unittest.main()
