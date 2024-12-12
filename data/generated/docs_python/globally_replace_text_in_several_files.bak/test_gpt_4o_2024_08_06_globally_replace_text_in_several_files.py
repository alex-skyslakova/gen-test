import os
import unittest

class TestGloballyReplaceText(unittest.TestCase):

    def setUp(self):
        # Create a temporary directory and files for testing
        self.test_files = ['test_file1.txt', 'test_file2.txt', 'test_file3.txt']
        self.original_text = "Goodbye London!"
        self.replacement_text = "Hello New York!"
        self.file_contents = [
            "This is a test. Goodbye London! This should change.",
            "Goodbye London! Goodbye London! Multiple occurrences.",
            "No occurrence here."
        ]

        for i, file_name in enumerate(self.test_files):
            with open(file_name, 'w') as f:
                f.write(self.file_contents[i])

    def tearDown(self):
        # Remove the test files after tests are done
        for file_name in self.test_files:
            if os.path.exists(file_name):
                os.remove(file_name)

    def test_replacement_in_files(self):
        # Import the script to test
        import globally_replace_text_in_several_files

        # Check each file to ensure the text has been replaced
        expected_contents = [
            "This is a test. Hello New York! This should change.",
            "Hello New York! Hello New York! Multiple occurrences.",
            "No occurrence here."
        ]

        for i, file_name in enumerate(self.test_files):
            with open(file_name, 'r') as f:
                content = f.read()
                self.assertEqual(content, expected_contents[i])

if __name__ == '__main__':
    unittest.main()
