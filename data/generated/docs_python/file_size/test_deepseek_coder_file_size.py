import unittest
from unittest.mock import patch, MagicMock
import os

class TestFileSize(unittest.TestCase):

    @patch('os.path.getsize')
    def test_file_size_current_directory(self, mock_getsize):
        # Mock the return value for the file in the current working directory
        mock_getsize.return_value = 1024
        
        # Import the function to be tested
        import file_size
        
        # Verify the size of the file in the current working directory
        self.assertEqual(file_size.size, 1024)
        mock_getsize.assert_called_once_with('input.txt')

    @patch('os.path.getsize')
    def test_file_size_root_directory(self, mock_getsize):
        # Mock the return value for the file in the root directory
        mock_getsize.return_value = 2048
        
        # Import the function to be tested
        import file_size
        
        # Verify the size of the file in the root directory
        self.assertEqual(file_size.size, 2048)
        mock_getsize.assert_called_once_with('/input.txt')

    @patch('os.path.getsize')
    def test_file_size_file_not_found(self, mock_getsize):
        # Mock the FileNotFoundError exception
        mock_getsize.side_effect = FileNotFoundError
        
        # Import the function to be tested
        import file_size
        
        # Verify that the exception is raised
        with self.assertRaises(FileNotFoundError):
            file_size.size
        mock_getsize.assert_called_once_with('input.txt')

if __name__ == '__main__':
    unittest.main()
