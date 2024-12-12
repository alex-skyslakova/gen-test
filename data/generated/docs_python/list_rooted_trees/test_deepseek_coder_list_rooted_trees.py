import unittest
from list_rooted_trees import bags, replace_brackets

class TestBagsFunction(unittest.TestCase):

    def test_bags_with_1_bag(self):
        result = bags(1)
        self.assertEqual(len(result), 1)
        self.assertEqual(result[0][1], "()")

    def test_bags_with_2_bags(self):
        result = bags(2)
        self.assertEqual(len(result), 1)
        self.assertEqual(result[0][1], "(())")

    def test_bags_with_3_bags(self):
        result = bags(3)
        self.assertEqual(len(result), 2)
        self.assertIn("((()))", [bag[1] for bag in result])
        self.assertIn("(()())", [bag[1] for bag in result])

    def test_bags_with_4_bags(self):
        result = bags(4)
        self.assertEqual(len(result), 4)
        expected_results = {"(()()())", "((())())", "((()()))", "(((())))"}
        actual_results = {bag[1] for bag in result}
        self.assertEqual(actual_results, expected_results)

    def test_bags_with_5_bags(self):
        result = bags(5)
        self.assertEqual(len(result), 9)
        expected_results = {
            "(()()()())", "((())()())", "((()())())", "(((()())))",
            "((((()))))", "(()(()))()", "(()(()()))", "((())(()))",
            "((()()()))"
        }
        actual_results = {bag[1] for bag in result}
        self.assertEqual(actual_results, expected_results)

    def test_replace_brackets(self):
        self.assertEqual(replace_brackets("()"), "()")
        self.assertEqual(replace_brackets("(())"), "([])")
        self.assertEqual(replace_brackets("((()))"), "([{}])")
        self.assertEqual(replace_brackets("(()())"), "([]{})")
        self.assertEqual(replace_brackets("(()()())"), "([]{}{})")

if __name__ == '__main__':
    unittest.main()
