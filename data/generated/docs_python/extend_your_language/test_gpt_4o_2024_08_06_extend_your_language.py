import unittest
from io import StringIO
import sys

class TestControlStructures(unittest.TestCase):
    def setUp(self):
        # Redirect stdout to capture print statements
        self.held, sys.stdout = sys.stdout, StringIO()

    def tearDown(self):
        # Reset redirect.
        sys.stdout = self.held

    def test_both_conditions_true(self):
        a, b = 1, 3
        if (c1 := a == 1) and (c2 := b == 3):
            print('a = 1 and b = 3')
        elif c1:
            print('a = 1 and b <> 3')
        elif c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        self.assertEqual(sys.stdout.getvalue().strip(), 'a = 1 and b = 3')

    def test_first_condition_true(self):
        a, b = 1, 0
        if (c1 := a == 1) and (c2 := b == 3):
            print('a = 1 and b = 3')
        elif c1:
            print('a = 1 and b <> 3')
        elif c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        self.assertEqual(sys.stdout.getvalue().strip(), 'a = 1 and b <> 3')

    def test_second_condition_true(self):
        a, b = 0, 3
        if (c1 := a == 1) and (c2 := b == 3):
            print('a = 1 and b = 3')
        elif c1:
            print('a = 1 and b <> 3')
        elif c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        self.assertEqual(sys.stdout.getvalue().strip(), 'a <> 1 and b = 3')

    def test_no_conditions_true(self):
        a, b = 0, 0
        if (c1 := a == 1) and (c2 := b == 3):
            print('a = 1 and b = 3')
        elif c1:
            print('a = 1 and b <> 3')
        elif c2:
            print('a <> 1 and b = 3')
        else:
            print('a <> 1 and b <> 3')
        
        self.assertEqual(sys.stdout.getvalue().strip(), 'a <> 1 and b <> 3')

if __name__ == '__main__':
    unittest.main()
