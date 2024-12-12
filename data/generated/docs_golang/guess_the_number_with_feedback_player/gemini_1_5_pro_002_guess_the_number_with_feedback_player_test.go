package main

import (
	"bytes"
	"fmt"
	"io"
	"os"
	"sort"
	"strings"
	"testing"
)

func TestGuessTheNumber(t *testing.T) {
	testCases := []struct {
		target   int
		expected int
		input    string
	}{
		{50, 50, "yes\nyes\nno\nyes\nyes\n"},
		{0, 0, "yes\n"},
		{99, 99, "no\nno\nno\nyes\n"},
		{1, 1, "yes\nno\nyes\n"},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Target %d", tc.target), func(t *testing.T) {
			oldStdout := os.Stdout
			r, w, _ := os.Pipe()
			os.Stdout = w

			oldStdin := os.Stdin
			os.Stdin = strings.NewReader(tc.input)

			main()

			os.Stdout = oldStdout
			os.Stdin = oldStdin
			w.Close()

			var buf bytes.Buffer
			io.Copy(&buf, r)
			output := buf.String()

			expectedOutput := fmt.Sprintf("Your number is %d.\n", tc.expected)
			if !strings.Contains(output, expectedOutput) {
				t.Errorf("Expected output to contain '%s', but got '%s'", expectedOutput, output)
			}

		})
	}

	// Test invalid input
	t.Run("Invalid input", func(t *testing.T) {
		oldStdin := os.Stdin
		os.Stdin = strings.NewReader("invalid\nyes\n")
		defer func() { os.Stdin = oldStdin }()

		oldStdout := os.Stdout
		r, w, _ := os.Pipe()
		os.Stdout = w
		defer func() { os.Stdout = oldStdout }()
        defer w.Close()
		main()

		var buf bytes.Buffer
		io.Copy(&buf, r)
		output := buf.String()

		expectedOutput := "Your number is"
		if !strings.Contains(output, expectedOutput) {
			t.Errorf("Expected output to contain '%s', but got '%s'", expectedOutput, output)
		}


	})

}


func TestBinarySearch(t *testing.T) {
    // Testing the underlying binary search logic directly
    lower, upper := 0, 100
    target := 55

    answer := sort.Search(upper-lower, func(i int) bool {
        return lower+i >= target
    })

    if lower + answer != target{
        t.Errorf("Binary search failed. Expected %d, got %d", target, lower+answer)
    }

}
