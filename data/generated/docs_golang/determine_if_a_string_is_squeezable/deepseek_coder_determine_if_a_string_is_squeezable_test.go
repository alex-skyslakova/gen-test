package main

import (
    "testing"
)

func TestSqueeze(t *testing.T) {
    testCases := []struct {
        input       string
        char        rune
        expected    string
        expectedLen int
    }{
        // Test case 1: Empty string
        {"", ' ', "", 0},
        // Test case 2: String with no repeated characters
        {"abc", 'a', "abc", 3},
        // Test case 3: String with repeated characters
        {"aabbcc", 'a', "abbc", 4},
        // Test case 4: String with multiple repeated characters
        {"aaabbbccc", 'b', "aaabccc", 7},
        // Test case 5: String with repeated characters at the end
        {"abcdd", 'd', "abcd", 4},
        // Test case 6: String with repeated characters at the beginning
        {"aabc", 'a', "abc", 3},
        // Test case 7: String with mixed repeated and non-repeated characters
        {"aabbccddeeff", 'e', "aabbccddff", 10},
        // Test case 8: String with all characters the same
        {"aaaa", 'a', "a", 1},
        // Test case 9: String with special characters
        {"😍😀🙌💃😍😍😍🙌", '😍', "😍😀🙌💃😍🙌", 6},
        // Test case 10: String with spaces
        {"  a  b  c  ", ' ', " a b c ", 7},
    }

    for _, tc := range testCases {
        result, _, newLen := squeeze(tc.input, tc.char)
        if result != tc.expected || newLen != tc.expectedLen {
            t.Errorf("For input '%s' with char '%c', expected '%s' with length %d, but got '%s' with length %d",
                tc.input, tc.char, tc.expected, tc.expectedLen, result, newLen)
        }
    }
}
