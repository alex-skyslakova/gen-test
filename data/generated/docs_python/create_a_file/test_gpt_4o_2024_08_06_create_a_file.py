import os
import unittest

class TestCreateFileAndDirectory(unittest.TestCase):

    def setUp(self):
        # Ensure the test environment is clean
        self.files_and_dirs = ['output.txt', 'docs']
        for directory in ['/', './']:
            for item in self.files_and_dirs:
                path = os.path.join(directory, item)
                if os.path.isfile(path):
                    os.remove(path)
                elif os.path.isdir(path):
                    os.rmdir(path)

    def test_files_and_directories_created(self):
        # Run the code to create files and directories
        import create_a_file

        # Check if files and directories are created in the current directory
        for item in self.files_and_dirs:
            self.assertTrue(os.path.exists('./' + item), f"{item} should exist in the current directory")

        # Check if files and directories are created in the root directory
        for item in self.files_and_dirs:
            self.assertTrue(os.path.exists('/' + item), f"{item} should exist in the root directory")

    def tearDown(self):
        # Clean up after tests
        for directory in ['/', './']:
            for item in self.files_and_dirs:
                path = os.path.join(directory, item)
                if os.path.isfile(path):
                    os.remove(path)
                elif os.path.isdir(path):
                    os.rmdir(path)

if __name__ == '__main__':
    unittest.main()
