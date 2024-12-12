import unittest
from extend_your_language import * # Assuming the code is saved in this file

class TestControlStructures(unittest.TestCase):

    def test_a_1_b_3(self):
        global a, b
        a, b = 1, 3
        with unittest.mock.patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            # Code under test
            if (c1 := a == 1) and (c2 := b == 3):
                print('a = 1 and b = 3')
            elif c1:
                print('a = 1 and b <> 3')
            elif c2:
                print('a <> 1 and b = 3')
            else:
                print('a <> 1 and b <> 3')

            self.assertEqual(mock_stdout.getvalue().strip(), 'a = 1 and b = 3')


    def test_a_1_b_not_3(self):
        global a, b
        a, b = 1, 0
        with unittest.mock.patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            # Code under test
            if (c1 := a == 1) and (c2 := b == 3):
                print('a = 1 and b = 3')
            elif c1:
                print('a = 1 and b <> 3')
            elif c2:
                print('a <> 1 and b = 3')
            else:
                print('a <> 1 and b <> 3')
            self.assertEqual(mock_stdout.getvalue().strip(), 'a = 1 and b <> 3')

    def test_a_not_1_b_3(self):
        global a, b
        a, b = 0, 3
        with unittest.mock.patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            # Code under test
            if (c1 := a == 1) and (c2 := b == 3):
                print('a = 1 and b = 3')
            elif c1:
                print('a = 1 and b <> 3')
            elif c2:
                print('a <> 1 and b = 3')
            else:
                print('a <> 1 and b <> 3')
            self.assertEqual(mock_stdout.getvalue().strip(), 'a <> 1 and b = 3')


    def test_a_not_1_b_not_3(self):
        global a, b
        a, b = 0, 0
        with unittest.mock.patch('sys.stdout', new_callable=io.StringIO) as mock_stdout:
            # Code under test
            if (c1 := a == 1) and (c2 := b == 3):
                print('a = 1 and b = 3')
            elif c1:
                print('a = 1 and b <> 3')
            elif c2:
                print('a <> 1 and b = 3')
            else:
                print('a <> 1 and b <> 3')

            self.assertEqual(mock_stdout.getvalue().strip(), 'a <> 1 and b <> 3')

