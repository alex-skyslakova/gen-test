package main

import (
	"bytes"
	"os"
	"testing"
)

func TestMainFunction(t *testing.T) {
	// Save the original os.Args
	originalArgs := os.Args
	defer func() { os.Args = originalArgs }()

	// Test cases
	tests := []struct {
		args     []string
		expected string
	}{
		{
			args:     []string{"myprogram", "-c", "alpha beta", "-h", "gamma"},
			expected: "the argument #0 is -c\nthe argument #1 is alpha beta\nthe argument #2 is -h\nthe argument #3 is gamma\n",
		},
		{
			args:     []string{"myprogram", "singleArg"},
			expected: "the argument #0 is singleArg\n",
		},
		{
			args:     []string{"myprogram"},
			expected: "",
		},
	}

	for _, test := range tests {
		// Set os.Args to the test case arguments
		os.Args = test.args

		// Capture the output
		var buf bytes.Buffer
		out := &buf
		original := os.Stdout
		defer func() { os.Stdout = original }()
		os.Stdout = out

		// Call the main function
		main()

		// Check the output
		if got := out.String(); got != test.expected {
			t.Errorf("For args %v, expected %q but got %q", test.args, test.expected, got)
		}
	}
}
