import unittest
from io import StringIO
import sys
from longest_string_challenge import longer

class TestLongestStringChallenge(unittest.TestCase):

    def setUp(self):
        # Redirect stdout to capture print output
        self.held_output = StringIO()
        sys.stdout = self.held_output

    def tearDown(self):
        # Reset stdout
        sys.stdout = sys.__stdout__

    def test_longer_function(self):
        self.assertTrue(longer("a", "bb"))
        self.assertFalse(longer("bb", "a"))
        self.assertFalse(longer("bb", "bb"))
        self.assertTrue(longer("bb", "ccc"))
        self.assertFalse(longer("ccc", "bb"))
        self.assertFalse(longer("ccc", "ccc"))

    def test_no_input(self):
        import longest_string_challenge
        longest_string_challenge.fileinput = self._mock_fileinput([])
        longest_string_challenge.main()
        self.assertEqual(self.held_output.getvalue(), "")

    def test_single_longest_line(self):
        import longest_string_challenge
        longest_string_challenge.fileinput = self._mock_fileinput(["a", "bb", "ccc", "ddd", "ee", "f", "ggg"])
        longest_string_challenge.main()
        self.assertEqual(self.held_output.getvalue(), "ccc\nddd\nggg\n")

    def test_multiple_longest_lines(self):
        import longest_string_challenge
        longest_string_challenge.fileinput = self._mock_fileinput(["a", "bb", "ccc", "ddd", "ccc", "ggg"])
        longest_string_challenge.main()
        self.assertEqual(self.held_output.getvalue(), "ccc\nddd\nccc\nggg\n")

    def test_all_same_length(self):
        import longest_string_challenge
        longest_string_challenge.fileinput = self._mock_fileinput(["a", "b", "c", "d"])
        longest_string_challenge.main()
        self.assertEqual(self.held_output.getvalue(), "a\nb\nc\nd\n")

    def test_empty_lines(self):
        import longest_string_challenge
        longest_string_challenge.fileinput = self._mock_fileinput(["", "a", "", "bb", "", "ccc"])
        longest_string_challenge.main()
        self.assertEqual(self.held_output.getvalue(), "ccc\n")

    def _mock_fileinput(self, lines):
        class MockFileInput:
            def __init__(self, lines):
                self.lines = lines
                self.index = 0

            def input(self):
                while self.index < len(self.lines):
                    yield self.lines[self.index]
                    self.index += 1

        return MockFileInput(lines)

if __name__ == '__main__':
    unittest.main()
