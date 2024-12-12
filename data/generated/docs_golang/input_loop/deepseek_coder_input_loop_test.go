package main

import (
	"bytes"
	"io"
	"testing"
)

func TestInputLoop(t *testing.T) {
	tests := []struct {
		name     string
		input    string
		expected int // Number of lines expected to be read
	}{
		{
			name:     "Single line",
			input:    "Hello, World!\n",
			expected: 1,
		},
		{
			name:     "Multiple lines",
			input:    "Line 1\nLine 2\nLine 3\n",
			expected: 3,
		},
		{
			name:     "No newline at the end",
			input:    "Line 1\nLine 2\nLine 3",
			expected: 3,
		},
		{
			name:     "Empty input",
			input:    "",
			expected: 0,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			// Create a buffer with the test input
			input := bytes.NewBufferString(tt.input)

			// Replace os.Stdin with the test input buffer
			oldStdin := os.Stdin
			defer func() { os.Stdin = oldStdin }()
			os.Stdin = input

			// Capture the output of the function
			var linesRead int
			readFunc := func(s string) {
				linesRead++
			}

			// Run the input loop with the custom read function
			inputLoop(readFunc)

			// Verify the number of lines read
			if linesRead != tt.expected {
				t.Errorf("Expected %d lines to be read, but got %d", tt.expected, linesRead)
			}
		})
	}
}

// inputLoop is a modified version of the main function that accepts a read function
func inputLoop(readFunc func(string)) {
	in := bufio.NewReader(os.Stdin)
	for {
		s, err := in.ReadString('\n')
		if err != nil {
			if err != io.EOF {
				log.Fatal(err)
			}
			break
		}
		readFunc(s)
	}
}
