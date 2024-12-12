import unittest
import io
from unittest.mock import patch
import longest_string_challenge

class TestLongestStringChallenge(unittest.TestCase):

    @patch('sys.stdin', io.StringIO("a\nbb\nccc\nddd\nee\nf\nggg\n"))
    def test_multiple_longest_strings(self):
        with patch('sys.stdout', new = io.StringIO()) as output:
            longest_string_challenge.main()  # Assuming you rename your script or wrap it in a main function
            self.assertIn("ccc\nddd\nggg\n", output.getvalue()) # Order doesn't matter

    @patch('sys.stdin', io.StringIO("hello\nworld\n"))
    def test_two_strings_same_length(self):
        with patch('sys.stdout', new = io.StringIO()) as output:
            longest_string_challenge.main()
            self.assertIn("hello\nworld\n", output.getvalue())

    @patch('sys.stdin', io.StringIO("short\nlongstring\n"))
    def test_one_string_longer(self):
       with patch('sys.stdout', new = io.StringIO()) as output:
            longest_string_challenge.main()
            self.assertEqual("longstring\n", output.getvalue())

    @patch('sys.stdin', io.StringIO(""))
    def test_empty_input(self):
        with patch('sys.stdout', new = io.StringIO()) as output:
            longest_string_challenge.main()
            self.assertEqual("", output.getvalue())

    @patch('sys.stdin', io.StringIO("singleline\n"))
    def test_single_line_input(self):
        with patch('sys.stdout', new = io.StringIO()) as output:
            longest_string_challenge.main()
            self.assertEqual("singleline\n", output.getvalue())
    
    def test_longer_function(self):
        self.assertTrue(longest_string_challenge.longer("a", "bb"))
        self.assertFalse(longest_string_challenge.longer("bbb", "bb"))
        self.assertFalse(longest_string_challenge.longer("bb", "bb"))

if __name__ == '__main__':
    unittest.main()

