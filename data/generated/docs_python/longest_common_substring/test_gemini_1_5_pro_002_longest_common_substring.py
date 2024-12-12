import unittest

def longest_common_substring(s1, s2):
    len1, len2 = len(s1), len(s2)
    ir, jr = 0, -1
    for i1 in range(len1):
        i2 = s2.find(s1[i1])
        while i2 >= 0:
            j1, j2 = i1, i2
            while j1 < len1 and j2 < len2 and s2[j2] == s1[j1]:
                if j1-i1 >= jr-ir:
                    ir, jr = i1, j1
                j1 += 1; j2 += 1
            i2 = s2.find(s1[i1], i2+1)
    return s1[ir:jr+1]


class TestLongestCommonSubstring(unittest.TestCase):

    def test_example_case(self):
        self.assertEqual(longest_common_substring("thisisatest", "testing123testing"), "test")

    def test_empty_strings(self):
        self.assertEqual(longest_common_substring("", ""), "")
        self.assertEqual(longest_common_substring("abc", ""), "")
        self.assertEqual(longest_common_substring("", "def"), "")

    def test_no_common_substring(self):
        self.assertEqual(longest_common_substring("abc", "def"), "")

    def test_substring_at_beginning(self):
        self.assertEqual(longest_common_substring("abcde", "abxyz"), "ab")

    def test_substring_at_end(self):
        self.assertEqual(longest_common_substring("xyzabc", "defabc"), "abc")

    def test_substring_in_middle(self):
        self.assertEqual(longest_common_substring("defabcxyz", "xyzabcghi"), "abc")

    def test_multiple_occurrences(self):
        self.assertEqual(longest_common_substring("ababab", "bababa"), "abab")

    def test_one_string_contained_in_other(self):
        self.assertEqual(longest_common_substring("abc", "abcdef"), "abc")
        self.assertEqual(longest_common_substring("abcdef", "abc"), "abc")

    def test_overlapping_occurrences(self):
        self.assertEqual(longest_common_substring("banana", "ananab"), "anan")


if __name__ == '__main__':
    unittest.main()

