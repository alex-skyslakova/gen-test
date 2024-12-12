package main

import (
	"testing"
)

func TestDeranged(t *testing.T) {
	tests := []struct {
		a, b     string
		expected bool
	}{
		{"abc", "bca", true},
		{"abc", "abc", false},
		{"abc", "def", false},
		{"", "", false},
		{"a", "a", false},
		{"a", "b", true},
		{"listen", "silent", false},
		{"listen", "enlist", true},
		{"elvis", "lives", true},
		{"elvis", "levis", false},
	}

	for _, test := range tests {
		result := deranged(test.a, test.b)
		if result != test.expected {
			t.Errorf("deranged(%q, %q) = %v; expected %v", test.a, test.b, result, test.expected)
		}
	}
}
