package main

import "testing"

func TestLD(t *testing.T) {
	testCases := []struct {
		s        string
		t        string
		expected int
	}{
		{"kitten", "sitting", 3},
		{"rosettacode", "raisethysword", 8},
		{"", "", 0},
		{"a", "", 1},
		{"", "a", 1},
		{"a", "a", 0},
		{"ab", "ac", 1},
		{"abc", "abd", 1},
		{"abcdef", "abcdef", 0},
		{"Saturday", "Sunday", 3}, // Example from Wikipedia
		{"book", "back", 2},       // Another example
		{"edcba", "abcde", 4},       // Reverse strings
		{"abcde", "edcba", 4},
	}

	for _, tc := range testCases {
		actual := ld(tc.s, tc.t)
		if actual != tc.expected {
			t.Errorf("ld(%s, %s) = %d; want %d", tc.s, tc.t, actual, tc.expected)
		}
		// Test with reversed strings
		actualReversed := ld(reverse(tc.s), reverse(tc.t))
		if actualReversed != tc.expected {
			t.Errorf("ld(%s reversed, %s reversed) = %d; want %d", tc.s, tc.t, actualReversed, tc.expected)
		}

	}
}

func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

