import os
import unittest

class TestFileAndDirectoryExistence(unittest.TestCase):

    def test_current_directory_existence(self):
        # Test for current working directory
        self.assertTrue(os.path.isfile("input.txt"), "input.txt should exist in the current directory")
        self.assertTrue(os.path.isdir("docs"), "docs directory should exist in the current directory")

    def test_root_directory_existence(self):
        # Test for filesystem root
        self.assertTrue(os.path.isfile("/input.txt"), "input.txt should exist in the root directory")
        self.assertTrue(os.path.isdir("/docs"), "docs directory should exist in the root directory")

    def test_zero_length_file_existence(self):
        # Test for zero-length file in current directory
        self.assertTrue(os.path.isfile("input.txt"), "input.txt should exist and be a zero-length file in the current directory")
        self.assertEqual(os.path.getsize("input.txt"), 0, "input.txt should be a zero-length file")

    def test_unusual_filename_existence(self):
        # Test for unusual filename in current directory
        unusual_filename = "Abdu'l-Bahá.txt"
        self.assertTrue(os.path.isfile(unusual_filename), f"{unusual_filename} should exist in the current directory")

if __name__ == '__main__':
    unittest.main()
