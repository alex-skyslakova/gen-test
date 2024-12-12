package main

import (
	"testing"
)

func TestLCS(t *testing.T) {
	testCases := []struct {
		name     string
		a        string
		b        string
		expected string
	}{
		{
			name:     "Basic test",
			a:        "thisisatest",
			b:        "testing123testing",
			expected: "test",
		},
		{
			name:     "No common substring",
			a:        "abc",
			b:        "def",
			expected: "",
		},
		{
			name:     "Single character common substring",
			a:        "a1b2c3",
			b:        "d4e5f6a",
			expected: "a",
		},
		{
			name:     "Multiple common substrings, choose the longest",
			a:        "abcdef",
			b:        "cdefgh",
			expected: "cdef",
		},
		{
			name:     "Identical strings",
			a:        "hello",
			b:        "hello",
			expected: "hello",
		},
		{
			name:     "Substring at the beginning",
			a:        "abcdef",
			b:        "abc123",
			expected: "abc",
		},
		{
			name:     "Substring at the end",
			a:        "abcdef",
			b:        "123def",
			expected: "def",
		},
		{
			name:     "Substring in the middle",
			a:        "abcdef",
			b:        "12cde34",
			expected: "cde",
		},
		{
			name:     "Empty strings",
			a:        "",
			b:        "",
			expected: "",
		},
		{
			name:     "One empty string",
			a:        "abc",
			b:        "",
			expected: "",
		},
		{
			name:     "Long strings",
			a:        "averylongstringwithnocommonsubstring",
			b:        "anotherverylongstringwithnocommonsubstring",
			expected: "",
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := lcs(tc.a, tc.b)
			if result != tc.expected {
				t.Errorf("Expected '%s', but got '%s'", tc.expected, result)
			}
		})
	}
}
