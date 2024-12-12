import unittest
from io import StringIO
import sys

# Mocking the input and output for testing
class TestAB(unittest.TestCase):

    def setUp(self):
        # Redirect standard input to a StringIO object
        self.input_stream = StringIO()
        self.output_stream = StringIO()
        self.original_input = sys.stdin
        self.original_output = sys.stdout
        sys.stdin = self.input_stream
        sys.stdout = self.output_stream

    def tearDown(self):
        # Restore the original input and output
        sys.stdin = self.original_input
        sys.stdout = self.original_output

    def test_positive_numbers(self):
        self.input_stream.write('2 2\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '4')

    def test_negative_numbers(self):
        self.input_stream.write('-3 -2\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '-5')

    def test_mixed_numbers(self):
        self.input_stream.write('3 -2\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '1')

    def test_zero_sum(self):
        self.input_stream.write('0 0\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '0')

    def test_boundary_values(self):
        self.input_stream.write('1000 1000\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '2000')

        self.input_stream.write('-1000 -1000\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '-2000')

    def test_single_space_input(self):
        self.input_stream.write('5 5\n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '10')

    def test_multiple_spaces_input(self):
        self.input_stream.write('  5    5  \n')
        self.input_stream.seek(0)
        exec(open('a_b.py').read())
        self.assertEqual(self.output_stream.getvalue().strip(), '10')

if __name__ == '__main__':
    unittest.main()
