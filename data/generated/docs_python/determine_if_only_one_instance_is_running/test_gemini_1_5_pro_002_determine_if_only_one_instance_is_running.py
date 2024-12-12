import unittest
import os
import subprocess
from unittest.mock import patch, MagicMock
import __main__
from determine_if_only_one_instance_is_running import isOnlyInstance

class TestIsOnlyInstance(unittest.TestCase):

    @patch('os.system')
    def test_no_other_instance(self, mock_system):
        # Simulate no other instance running
        mock_system.return_value = 1 # Shell command returns 1 (true) if no other instance

        self.assertTrue(isOnlyInstance())
        mock_system.assert_called_once_with("(( $(ps -ef | grep python | grep '[" + __main__.__file__[0] + "]" + __main__.__file__[1:] + "' | wc -l) > 1 ))")

    @patch('os.system')
    def test_another_instance_running(self, mock_system):
        # Simulate another instance running
        mock_system.return_value = 0 # Shell command returns 0 (false) if another instance exists

        self.assertFalse(isOnlyInstance())
        mock_system.assert_called_once_with("(( $(ps -ef | grep python | grep '[" + __main__.__file__[0] + "]" + __main__.__file__[1:] + "' | wc -l) > 1 ))")

    @patch('os.system')
    def test_filename_with_spaces(self, mock_system):
        # Test with filename containing spaces
        __main__.__file__ = "my test file.py"
        mock_system.return_value = 1

        self.assertTrue(isOnlyInstance())
        mock_system.assert_called_once_with("(( $(ps -ef | grep python | grep '[m]y test file.py' | wc -l) > 1 ))")


    @patch('os.system')
    def test_filename_with_special_characters(self, mock_system):
        # Test with filename containing special characters that might break shell commands
        __main__.__file__ = "test'file$.py"  # Example with a single quote and a dollar sign
        mock_system.return_value = 1

        self.assertTrue(isOnlyInstance())
        mock_system.assert_called_once_with("(( $(ps -ef | grep python | grep '[t]est'file$.py' | wc -l) > 1 ))")


if __name__ == '__main__':
    unittest.main()

