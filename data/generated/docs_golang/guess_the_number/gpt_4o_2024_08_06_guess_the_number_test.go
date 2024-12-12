package main

import (
	"bytes"
	"fmt"
	"math/rand"
	"strings"
	"testing"
	"time"
)

func TestGuessTheNumber(t *testing.T) {
	// Seed the random number generator for reproducibility in tests
	rand.Seed(time.Now().UnixNano())

	// Test cases
	tests := []struct {
		name     string
		input    string
		expected string
	}{
		{
			name:     "Correct guess on first try",
			input:    "5\n",
			expected: "Well guessed!\n",
		},
		{
			name:     "Correct guess after wrong attempts",
			input:    "3\n7\n5\n",
			expected: "No. Try again: No. Try again: Well guessed!\n",
		},
		{
			name:     "Non-numeric input",
			input:    "abc\n5\n",
			expected: "\nexpected integer\nSo, bye.\n",
		},
	}

	for _, tc := range tests {
		t.Run(tc.name, func(t *testing.T) {
			// Mock input and output
			input := strings.NewReader(tc.input)
			output := &bytes.Buffer{}

			// Redirect standard input and output
			fmt.Scan = func(a ...interface{}) (n int, err error) {
				return fmt.Fscan(input, a...)
			}
			fmt.Print = func(a ...interface{}) (n int, err error) {
				return fmt.Fprint(output, a...)
			}
			fmt.Println = func(a ...interface{}) (n int, err error) {
				return fmt.Fprintln(output, a...)
			}

			// Run the main function
			main()

			// Check the output
			if got := output.String(); got != tc.expected {
				t.Errorf("expected %q, got %q", tc.expected, got)
			}
		})
	}
}
