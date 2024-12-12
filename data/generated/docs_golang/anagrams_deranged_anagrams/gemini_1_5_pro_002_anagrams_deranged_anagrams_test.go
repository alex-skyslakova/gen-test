package main

import (
	"sort"
	"strings"
	"testing"
)

func TestDeranged(t *testing.T) {
	testCases := []struct {
		a        string
		b        string
		expected bool
	}{
		{"abc", "abc", false},
		{"abc", "acb", true},
		{"abc", "bac", true},
		{"abc", "bca", true},
		{"abc", "cab", true},
		{"abc", "cba", true},
		{"ab", "ab", false},
		{"ab", "ba", true},
		{"a", "a", false},
		{"abc", "abcd", false},
		{"abcd", "abc", false},
		{"", "", false},

	}

	for _, tc := range testCases {
		actual := deranged(tc.a, tc.b)
		if actual != tc.expected {
			t.Errorf("deranged(%s, %s) = %t; want %t", tc.a, tc.b, actual, tc.expected)
		}
	}
}



func TestMainIntegration(t *testing.T) {

	// Prepare a mock unixdict.txt file
	mockDictContent := "alerts\nlatest\nalter\nstaler\nangel\nglean\nleast\nslate\n"
	
    // ... (Rest of the test setup and assertions)
}



