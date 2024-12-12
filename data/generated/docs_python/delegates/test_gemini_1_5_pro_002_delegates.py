import unittest
from delegates import Delegator, Delegate

class TestDelegator(unittest.TestCase):

    def test_no_delegate(self):
        delegator = Delegator()
        self.assertEqual(delegator.operation(), 'default implementation')

    def test_delegate_without_thing(self):
        delegator = Delegator()
        delegator.delegate = object()  # Use a generic object without 'thing'
        self.assertEqual(delegator.operation(), 'default implementation')

    def test_delegate_with_thing(self):
        delegator = Delegator()
        delegator.delegate = Delegate()
        self.assertEqual(delegator.operation(), 'delegate implementation')

    def test_delegate_with_non_callable_thing(self):
        delegator = Delegator()
        delegator.delegate = type('FakeDelegate', (), {'thing': 'not_callable'})()
        self.assertEqual(delegator.operation(), 'default implementation')

