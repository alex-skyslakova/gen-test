import unittest
from list_rooted_trees import bags, replace_brackets

class TestBags(unittest.TestCase):

    def test_bags_0(self):
        self.assertEqual(bags(0), [(0, "")])

    def test_bags_1(self):
        self.assertEqual(bags(1), [(1, "()")])

    def test_bags_2(self):
        self.assertEqual(bags(2), [(2, "(())")])

    def test_bags_3(self):
        expected_3 = [(3, "((()))"), (3, "(()())")]
        self.assertEqual(sorted(bags(3)), sorted(expected_3))

    def test_bags_4(self):
        expected_4 = [(4, "((()()))"), (4, "((())())"), (4, "(()()())"), (4, "(()(()))")]  # Corrected order
        self.assertEqual(sorted(bags(4)), sorted(expected_4))


    def test_bags_5(self):
        expected_5 = [(5, "((((()))))"), (5, "((()(())))"), (5, "((()()()))"), (5, "(((()())))"), (5, "(((()())()))"), (5, "(()()()())"), (5, "(()(()()()))"), (5, "(()(()(())))"), (5, "(()()(())))")]
        self.assertEqual(sorted(bags(5)), sorted(expected_5))

    def test_replace_brackets_empty(self):
        self.assertEqual(replace_brackets(""), "")

    def test_replace_brackets_simple(self):
        self.assertEqual(replace_brackets("()"), "[]")

    def test_replace_brackets_nested(self):
        self.assertEqual(replace_brackets("(())"), "[[]]")

    def test_replace_brackets_complex(self):
        self.assertEqual(replace_brackets("(()()())"), "[[]([])]")

    def test_replace_brackets_very_nested(self):
        self.assertEqual(replace_brackets("((((()))))"), "[[[{}]]]")


if __name__ == '__main__':
    unittest.main()

