package main

import (
	"bytes"
	"os"
	"strings"
	"testing"
)

func TestLongestStringChallenge(t *testing.T) {
	tests := []struct {
		input    string
		expected []string
	}{
		{
			input:    "a\nbb\nccc\nddd\nee\nf\nggg\n",
			expected: []string{"ccc", "ddd", "ggg"},
		},
		{
			input:    "hello\nworld\nfoo\nbar\nbaz\n",
			expected: []string{"hello", "world"},
		},
		{
			input:    "short\nlonger\nlongest\n",
			expected: []string{"longest"},
		},
		{
			input:    "one\ntwo\nthree\nfour\nfive\nsix\nseven\n",
			expected: []string{"three"},
		},
		{
			input:    "",
			expected: []string{},
		},
	}

	for _, test := range tests {
		// Redirect standard input
		r, w, _ := os.Pipe()
		os.Stdin = r

		// Write test input to standard input
		w.WriteString(test.input)
		w.Close()

		// Capture standard output
		var buf bytes.Buffer
		os.Stdout = &buf

		// Run the main function
		main()

		// Reset standard output
		os.Stdout = os.NewFile(1, "/dev/stdout")

		// Get the output and split it into lines
		output := strings.TrimSpace(buf.String())
		lines := strings.Split(output, "\n")

		// Check if the output matches the expected result
		if len(lines) != len(test.expected) {
			t.Errorf("Expected %d lines, got %d lines", len(test.expected), len(lines))
		}

		for i, line := range lines {
			if line != test.expected[i] {
				t.Errorf("Expected line %d to be %q, got %q", i, test.expected[i], line)
			}
		}
	}
}
