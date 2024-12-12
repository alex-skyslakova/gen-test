import unittest
from list_rooted_trees import bags, replace_brackets

class TestBagsFunction(unittest.TestCase):
    def test_bags_0(self):
        # Test for 0 bags
        result = bags(0)
        expected = [(0, "")]
        self.assertEqual(result, expected)

    def test_bags_1(self):
        # Test for 1 bag
        result = bags(1)
        expected = [(1, "()")]
        self.assertEqual(result, expected)

    def test_bags_2(self):
        # Test for 2 bags
        result = bags(2)
        expected = [(2, "(())")]
        self.assertEqual(result, expected)

    def test_bags_3(self):
        # Test for 3 bags
        result = bags(3)
        expected = [(3, "((()))"), (3, "(()())")]
        self.assertEqual(sorted(result), sorted(expected))

    def test_bags_4(self):
        # Test for 4 bags
        result = bags(4)
        expected = [(4, "(()()())"), (4, "((())())"), (4, "((()()))"), (4, "(((())))")]
        self.assertEqual(sorted(result), sorted(expected))

    def test_bags_5(self):
        # Test for 5 bags
        result = bags(5)
        expected_count = 9  # As per the problem statement
        self.assertEqual(len(result), expected_count)

class TestReplaceBracketsFunction(unittest.TestCase):
    def test_replace_brackets(self):
        # Test the replace_brackets function
        input_str = "((()))"
        expected_output = "[[[]]]"
        self.assertEqual(replace_brackets(input_str), expected_output)

        input_str = "(()())"
        expected_output = "[[]()]"
        self.assertEqual(replace_brackets(input_str), expected_output)

        input_str = "(()()())"
        expected_output = "[[]()()]"
        self.assertEqual(replace_brackets(input_str), expected_output)

if __name__ == '__main__':
    unittest.main()
