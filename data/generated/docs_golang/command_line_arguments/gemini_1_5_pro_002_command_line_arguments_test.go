package main

import (
	"bytes"
	"fmt"
	"os"
	"testing"
)

func TestMain(t *testing.T) {
	testCases := []struct {
		name     string
		args     []string
		expected string
	}{
		{
			name: "No arguments",
			args: []string{},
			expected: "",
		},
		{
			name: "Single argument",
			args: []string{"-c"},
			expected: "the argument #0 is -c\n",
		},
		{
			name: "Multiple arguments",
			args: []string{"-c", "alpha beta", "-h", "gamma"},
			expected: "the argument #0 is -c\nthe argument #1 is alpha beta\nthe argument #2 is -h\nthe argument #3 is gamma\n",
		},
		{
			name: "Arguments with spaces",
			args: []string{"-c", "alpha beta gamma"},
			expected: "the argument #0 is -c\nthe argument #1 is alpha beta gamma\n",
		},
	}


	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			oldArgs := os.Args
			defer func() { os.Args = oldArgs }()

			os.Args = append([]string{"myprogram"}, tc.args...)

			out := &bytes.Buffer{}
			fmt.SetOutput(out) // Redirect stdout to buffer
			defer func() { fmt.SetOutput(os.Stdout) }() // Restore stdout

			main()

			if out.String() != tc.expected {
				t.Errorf("Expected output:\n%s\nGot:\n%s", tc.expected, out.String())
			}

		})
	}
}

