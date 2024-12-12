package main

import (
	"testing"
)

func TestLevenshteinDistance(t *testing.T) {
	tests := []struct {
		s, t     string
		expected int
	}{
		{"kitten", "sitting", 3},
		{"rosettacode", "raisethysword", 8},
		{"", "", 0},
		{"a", "", 1},
		{"", "a", 1},
		{"abc", "abc", 0},
		{"abc", "def", 3},
		{"flaw", "lawn", 2},
		{"intention", "execution", 5},
	}

	for _, test := range tests {
		result := ld(test.s, test.t)
		if result != test.expected {
			t.Errorf("ld(%q, %q) = %d; expected %d", test.s, test.t, result, test.expected)
		}
	}
}
