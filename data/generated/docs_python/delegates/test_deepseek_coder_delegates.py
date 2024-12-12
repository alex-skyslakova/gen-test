import unittest
from delegates import Delegator, Delegate

class TestDelegator(unittest.TestCase):

    def test_no_delegate(self):
        delegator = Delegator()
        result = delegator.operation()
        self.assertEqual(result, 'default implementation')

    def test_delegate_without_thing(self):
        delegator = Delegator()
        delegator.delegate = 'A delegate may be any object'
        result = delegator.operation()
        self.assertEqual(result, 'default implementation')

    def test_delegate_with_thing(self):
        delegator = Delegator()
        delegator.delegate = Delegate()
        result = delegator.operation()
        self.assertEqual(result, 'delegate implementation')

if __name__ == '__main__':
    unittest.main()
