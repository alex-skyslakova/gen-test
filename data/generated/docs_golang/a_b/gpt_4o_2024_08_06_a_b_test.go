package main

import (
	"bytes"
	"fmt"
	"os"
	"strconv"
	"strings"
	"testing"
)

func TestMain(t *testing.T) {
	tests := []struct {
		input    string
		expected string
	}{
		{"2 2\n", "4\n"},
		{"3 2\n", "5\n"},
		{"-1000 1000\n", "0\n"},
		{"-1000 -1000\n", "-2000\n"},
		{"1000 1000\n", "2000\n"},
		{"0 0\n", "0\n"},
		{"-500 500\n", "0\n"},
		{"-1 -1\n", "-2\n"},
		{"1 -1\n", "0\n"},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("input: %s", tt.input), func(t *testing.T) {
			// Redirect stdin
			oldStdin := os.Stdin
			defer func() { os.Stdin = oldStdin }()
			os.Stdin = bytes.NewBufferString(tt.input)

			// Capture stdout
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			main()

			w.Close()
			out, _ := os.ReadFile(r.Name())
			os.Stdout = oldStdout

			// Compare output
			output := strings.TrimSpace(string(out))
			expected := strings.TrimSpace(tt.expected)
			if output != expected {
				t.Errorf("expected %s, got %s", expected, output)
			}
		})
	}
}
