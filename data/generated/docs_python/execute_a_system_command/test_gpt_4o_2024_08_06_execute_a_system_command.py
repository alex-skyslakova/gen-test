import unittest
import os
import platform

class TestExecuteSystemCommand(unittest.TestCase):

    def test_ls_command_exit_code(self):
        if platform.system() != 'Windows':
            exit_code = os.system('ls')
            self.assertEqual(exit_code, 0, "The 'ls' command should return an exit code of 0 on success.")

    def test_ls_command_output(self):
        if platform.system() != 'Windows':
            output = os.popen('ls').read()
            self.assertIsInstance(output, str, "The output of 'ls' command should be a string.")
            self.assertGreater(len(output), 0, "The output of 'ls' command should not be empty.")

    def test_dir_command_exit_code(self):
        if platform.system() == 'Windows':
            exit_code = os.system('dir')
            self.assertEqual(exit_code, 0, "The 'dir' command should return an exit code of 0 on success.")

    def test_dir_command_output(self):
        if platform.system() == 'Windows':
            output = os.popen('dir').read()
            self.assertIsInstance(output, str, "The output of 'dir' command should be a string.")
            self.assertGreater(len(output), 0, "The output of 'dir' command should not be empty.")

    def test_pause_command_exit_code(self):
        if platform.system() == 'Windows':
            exit_code = os.system('pause')
            self.assertNotEqual(exit_code, 0, "The 'pause' command should not return an exit code of 0 when run in a non-interactive shell.")

if __name__ == '__main__':
    unittest.main()
