import unittest
from unittest.mock import patch, MagicMock
import os
import __main__

# Assuming the function to be tested is in a module named `instance_checker`
from instance_checker import isOnlyInstance

class TestIsOnlyInstance(unittest.TestCase):

    @patch('os.system')
    def test_isOnlyInstance_when_no_other_instance_running(self, mock_system):
        # Mock the return value of os.system to simulate no other instance running
        mock_system.return_value = 0
        
        result = isOnlyInstance()
        
        self.assertFalse(result, "Expected False when no other instance is running")

    @patch('os.system')
    def test_isOnlyInstance_when_another_instance_running(self, mock_system):
        # Mock the return value of os.system to simulate another instance running
        mock_system.return_value = 1
        
        result = isOnlyInstance()
        
        self.assertTrue(result, "Expected True when another instance is running")

    @patch('os.system')
    def test_isOnlyInstance_with_error_in_os_system(self, mock_system):
        # Mock the return value of os.system to simulate an error
        mock_system.return_value = -1
        
        result = isOnlyInstance()
        
        self.assertFalse(result, "Expected False when there is an error in os.system")

    @patch('__main__.__file__', new='/path/to/your/script.py')
    @patch('os.system')
    def test_isOnlyInstance_with_specific_file_path(self, mock_system):
        # Mock the return value of os.system to simulate another instance running
        mock_system.return_value = 1
        
        result = isOnlyInstance()
        
        self.assertTrue(result, "Expected True when another instance is running with specific file path")

if __name__ == '__main__':
    unittest.main()
