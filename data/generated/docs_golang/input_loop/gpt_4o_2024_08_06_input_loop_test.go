package main

import (
	"bufio"
	"bytes"
	"io"
	"strings"
	"testing"
)

func TestInputLoop(t *testing.T) {
	tests := []struct {
		name     string
		input    string
		expected []string
	}{
		{
			name:     "Single line input",
			input:    "Hello, World!\n",
			expected: []string{"Hello, World!\n"},
		},
		{
			name:     "Multiple lines input",
			input:    "Hello, World!\nThis is a test.\n",
			expected: []string{"Hello, World!\n", "This is a test.\n"},
		},
		{
			name:     "Empty input",
			input:    "",
			expected: []string{},
		},
		{
			name:     "Input with multiple newlines",
			input:    "\n\n\n",
			expected: []string{"\n", "\n", "\n"},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Redirect input to a custom reader
			in := bufio.NewReader(strings.NewReader(tt.input))
			var results []string

			for {
				s, err := in.ReadString('\n')
				if err != nil {
					if err != io.EOF {
						t.Fatalf("unexpected error: %v", err)
					}
					break
				}
				results = append(results, s)
			}

			if len(results) != len(tt.expected) {
				t.Fatalf("expected %d lines, got %d", len(tt.expected), len(results))
			}

			for i, line := range results {
				if line != tt.expected[i] {
					t.Errorf("expected line %d to be %q, got %q", i, tt.expected[i], line)
				}
			}
		})
	}
}
