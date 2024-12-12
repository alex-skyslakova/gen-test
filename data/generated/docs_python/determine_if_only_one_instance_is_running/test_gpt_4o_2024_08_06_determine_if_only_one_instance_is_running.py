import unittest
from unittest.mock import patch
import determine_if_only_one_instance_is_running

class TestIsOnlyInstance(unittest.TestCase):

    @patch('determine_if_only_one_instance_is_running.os.system')
    def test_single_instance_running(self, mock_system):
        # Simulate the command returning 0, indicating only one instance is running
        mock_system.return_value = 0
        self.assertTrue(determine_if_only_one_instance_is_running.isOnlyInstance())

    @patch('determine_if_only_one_instance_is_running.os.system')
    def test_multiple_instances_running(self, mock_system):
        # Simulate the command returning non-zero, indicating multiple instances are running
        mock_system.return_value = 1
        self.assertFalse(determine_if_only_one_instance_is_running.isOnlyInstance())

if __name__ == '__main__':
    unittest.main()
