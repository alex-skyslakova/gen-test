import unittest
from unittest.mock import patch
import command_line_arguments

class TestCommandLineArguments(unittest.TestCase):

    @patch('sys.argv', ['command_line_arguments.py'])
    def test_no_arguments(self):
        # Test when no arguments are provided
        program_name, arguments, count = command_line_arguments.parse_arguments()
        self.assertEqual(program_name, 'command_line_arguments.py')
        self.assertEqual(arguments, [])
        self.assertEqual(count, 0)

    @patch('sys.argv', ['command_line_arguments.py', '-c', 'alpha beta', '-h', 'gamma'])
    def test_with_arguments(self):
        # Test when arguments are provided
        program_name, arguments, count = command_line_arguments.parse_arguments()
        self.assertEqual(program_name, 'command_line_arguments.py')
        self.assertEqual(arguments, ['-c', 'alpha beta', '-h', 'gamma'])
        self.assertEqual(count, 4)

    @patch('sys.argv', ['command_line_arguments.py', '-c', 'alpha beta'])
    def test_partial_arguments(self):
        # Test when only some arguments are provided
        program_name, arguments, count = command_line_arguments.parse_arguments()
        self.assertEqual(program_name, 'command_line_arguments.py')
        self.assertEqual(arguments, ['-c', 'alpha beta'])
        self.assertEqual(count, 2)

    @patch('sys.argv', ['command_line_arguments.py', '-h', 'gamma'])
    def test_different_arguments(self):
        # Test with different arguments
        program_name, arguments, count = command_line_arguments.parse_arguments()
        self.assertEqual(program_name, 'command_line_arguments.py')
        self.assertEqual(arguments, ['-h', 'gamma'])
        self.assertEqual(count, 2)

    @patch('sys.argv', ['command_line_arguments.py', '-c', 'alpha beta', '-h', 'gamma', '-v'])
    def test_additional_arguments(self):
        # Test with additional arguments
        program_name, arguments, count = command_line_arguments.parse_arguments()
        self.assertEqual(program_name, 'command_line_arguments.py')
        self.assertEqual(arguments, ['-c', 'alpha beta', '-h', 'gamma', '-v'])
        self.assertEqual(count, 5)

if __name__ == '__main__':
    unittest.main()
