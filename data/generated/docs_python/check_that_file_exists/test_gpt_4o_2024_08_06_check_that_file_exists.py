import os
import unittest

class TestFileAndDirectoryExistence(unittest.TestCase):
    
    def test_file_exists_in_current_directory(self):
        self.assertTrue(os.path.isfile("input.txt"), "input.txt should exist in the current directory")
        
    def test_directory_exists_in_current_directory(self):
        self.assertTrue(os.path.isdir("docs"), "docs directory should exist in the current directory")
        
    def test_file_exists_in_root_directory(self):
        self.assertTrue(os.path.isfile("/input.txt"), "/input.txt should exist in the root directory")
        
    def test_directory_exists_in_root_directory(self):
        self.assertTrue(os.path.isdir("/docs"), "/docs directory should exist in the root directory")
        
    def test_zero_length_file_exists(self):
        # Create a zero-length file for testing
        open("zero_length.txt", 'a').close()
        self.assertTrue(os.path.isfile("zero_length.txt"), "zero_length.txt should exist in the current directory")
        self.assertEqual(os.path.getsize("zero_length.txt"), 0, "zero_length.txt should be zero-length")
        os.remove("zero_length.txt")  # Clean up after test
        
    def test_unusual_filename_exists(self):
        # Create a file with an unusual name for testing
        unusual_filename = "Abdu'l-Bahá.txt"
        open(unusual_filename, 'a').close()
        self.assertTrue(os.path.isfile(unusual_filename), f"{unusual_filename} should exist in the current directory")
        os.remove(unusual_filename)  # Clean up after test

if __name__ == '__main__':
    unittest.main()
