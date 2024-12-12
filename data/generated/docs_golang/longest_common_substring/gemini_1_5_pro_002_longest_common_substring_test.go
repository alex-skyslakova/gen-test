package main

import "testing"

func TestLCS(t *testing.T) {
	testCases := []struct {
		str1   string
		str2   string
		expect string
	}{
		{"thisisatest", "testing123testing", "test"},
		{"", "", ""},
		{"a", "", ""},
		{"", "a", ""},
		{"abc", "def", ""},
		{"ab", "babc", "ab"},
		{"abcd", "bcd", "bcd"},
		{"testing", "thisisatest", "test"},
		{"aabbcc", "bbaacc", "a"},
		{"abcdef", "abcdef", "abcdef"},
		{"aaaaaa", "aa", "aa"},
		{"zxabcde", "fghicde", "cde"},
        {"ababab", "bababa", "abab"},
        {"bananas", "anas", "anas"},
	}

	for _, tc := range testCases {
		t.Run(tc.str1+" "+tc.str2, func(t *testing.T) {
			actual := lcs(tc.str1, tc.str2)
			if actual != tc.expect {
				t.Errorf("Expected '%s', got '%s'", tc.expect, actual)
			}
		})
	}
}

