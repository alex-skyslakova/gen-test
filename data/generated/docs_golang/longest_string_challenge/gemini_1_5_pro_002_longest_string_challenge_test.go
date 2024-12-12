package main

import (
	"bufio"
	"bytes"
	"strings"
	"testing"
)

func TestLongestStringChallenge(t *testing.T) {
	testCases := []struct {
		input    string
		expected string
	}{
		{"", ""},
		{"a\n", "a\n"},
		{"a\nbb\n", "bb\n"},
		{"a\nbb\nccc\nddd\nee\nf\nggg\n", "ccc\nddd\nggg\n"},
		{"a\nbb\nccc\nddd\nee\nf\nggg", "ccc\nddd\nggg"}, // Test without final newline
		{"\n\n\n", "\n\n\n"},                             // Test with only newlines
		{"abc\nabc\n", "abc\nabc\n"},                   // Test with identical lines
		{"a\nbb\nccc\nddd\nccc\n", "ccc\nddd\nccc\n"}, // Test with duplicate longest lines
	}

	for _, tc := range testCases {
		input := strings.NewReader(tc.input)
		var output bytes.Buffer

		in = bufio.NewReader(input) // Redirect in for testing
		osStdout = &output          // Redirect os.Stdout for testing

		main()

		got := output.String()

		if got != tc.expected {
			t.Errorf("For input:\n%s\nExpected:\n%s\nGot:\n%s", tc.input, tc.expected, got)
		}
	}
}

// Mock os.Stdout for testing
var osStdout *bytes.Buffer

func init() {
	osStdout = &bytes.Buffer{} // Initialize for regular runs
}

// Rewrite os.Stdout.WriteString to use the mock
func osStdoutWriteString(s string) {
	osStdout.WriteString(s)
}
