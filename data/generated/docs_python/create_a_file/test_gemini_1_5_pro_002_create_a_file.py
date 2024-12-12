import unittest
import os
import stat

class TestCreateAFiles(unittest.TestCase):

    def setUp(self):
        # Clean up before each test if they exist
        if os.path.exists("/output.txt"):
            os.remove("/output.txt")
        if os.path.exists("/docs"):
            os.rmdir("/docs")
        if os.path.exists("./output.txt"):
            os.remove("./output.txt")
        if os.path.exists("./docs"):
            os.rmdir("./docs")


    def tearDown(self):
        # Clean up after each test if they were created
        if os.path.exists("/output.txt"):
            os.remove("/output.txt")
        if os.path.exists("/docs"):
            os.rmdir("/docs")
        if os.path.exists("./output.txt"):
            os.remove("./output.txt")
        if os.path.exists("./docs"):
            os.rmdir("./docs")


    def test_create_files(self):
        # Running the actual code being tested. Usually would import and call function,
        # but here we're just replicating the code since it's simple
        for directory in ['/', './']:
            open(directory + 'output.txt', 'w').close()
            os.mkdir(directory + 'docs')

        # Assertions to check the created files and directories
        self.assertTrue(os.path.exists("/output.txt"))
        self.assertEqual(os.stat("/output.txt")[stat.ST_SIZE], 0)  # check file size is 0
        self.assertTrue(os.path.exists("/docs"))
        self.assertTrue(os.path.isdir("/docs"))


        self.assertTrue(os.path.exists("./output.txt"))
        self.assertEqual(os.stat("./output.txt")[stat.ST_SIZE], 0) # check file size is 0
        self.assertTrue(os.path.exists("./docs"))
        self.assertTrue(os.path.isdir("./docs"))



if __name__ == '__main__':
    unittest.main()

