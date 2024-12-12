import unittest
import subprocess
import sys

class TestCommandLineArguments(unittest.TestCase):

    def run_script(self, *args):
        """Helper method to run the script with given arguments."""
        result = subprocess.run(
            [sys.executable, 'command_line_arguments.py'] + list(args),
            capture_output=True,
            text=True
        )
        return result

    def test_no_arguments(self):
        """Test the script with no command-line arguments."""
        result = self.run_script()
        self.assertEqual(result.returncode, 0)
        self.assertIn('arguments: []', result.stdout)
        self.assertIn('count: 0', result.stdout)

    def test_single_argument(self):
        """Test the script with a single command-line argument."""
        result = self.run_script('-c')
        self.assertEqual(result.returncode, 0)
        self.assertIn("arguments: ['-c']", result.stdout)
        self.assertIn('count: 1', result.stdout)

    def test_multiple_arguments(self):
        """Test the script with multiple command-line arguments."""
        result = self.run_script('-c', 'alpha beta', '-h', 'gamma')
        self.assertEqual(result.returncode, 0)
        self.assertIn("arguments: ['-c', 'alpha beta', '-h', 'gamma']", result.stdout)
        self.assertIn('count: 4', result.stdout)

    def test_program_name(self):
        """Test that the program name is correctly identified."""
        result = self.run_script()
        self.assertEqual(result.returncode, 0)
        self.assertIn('program_name: command_line_arguments.py', result.stdout)

if __name__ == '__main__':
    unittest.main()
