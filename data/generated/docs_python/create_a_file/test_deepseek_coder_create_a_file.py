import os
import unittest
from unittest.mock import patch, mock_open

class TestCreateAFile(unittest.TestCase):

    @patch('os.mkdir')
    @patch('builtins.open', new_callable=mock_open)
    def test_create_files_and_directories(self, mock_open_file, mock_mkdir):
        # Import the function to be tested
        from create_a_file import create_files_and_directories

        # Call the function
        create_files_and_directories()

        # Assert that the open function was called with the correct paths
        mock_open_file.assert_any_call('/output.txt', 'w')
        mock_open_file.assert_any_call('./output.txt', 'w')

        # Assert that the mkdir function was called with the correct paths
        mock_mkdir.assert_any_call('/docs')
        mock_mkdir.assert_any_call('./docs')

        # Assert that the open function was called twice
        self.assertEqual(mock_open_file.call_count, 2)

        # Assert that the mkdir function was called twice
        self.assertEqual(mock_mkdir.call_count, 2)

if __name__ == '__main__':
    unittest.main()
