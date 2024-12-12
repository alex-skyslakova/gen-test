package main

import (
	"bufio"
	"fmt"
	"regexp"
	"strings"
	"testing"
)

func TestCheck(t *testing.T) {
	testCases := []struct {
		a      int
		b      int
		s      string
		expect bool
		output string
	}{
		{10, 2, "test", true, `"test" is plausible (10 vs 2).` + "\n"},
		{10, 5, "test", false, `"test" is implausible (10 vs 5).` + "\n"},
		{2, 10, "test", false, `"test" is implausible and contra-indicated (2 vs 10).` + "\n"},
	}
	for _, tc := range testCases {
		got := check(tc.a, tc.b, tc.s)
		if got != tc.expect {
			t.Errorf("check(%d, %d, %q) = %v; want %v", tc.a, tc.b, tc.s, got, tc.expect)
		}
		if tc.output != getOutput() {
			t.Errorf("output mismatch, got: %q, want: %q", getOutput(), tc.output)
		}
		resetOutput()

	}
}


var outputBuffer strings.Builder

// getOutput retrieves the content captured by the intercepted fmt.Printf.
func getOutput() string {
	return outputBuffer.String()
}

// resetOutput clears the captured output.
func resetOutput() {
	outputBuffer.Reset()
}


// Override fmt.Printf to capture output for testing
func init() {
	fmt.Printf = func(format string, a ...interface{}) (n int, err error) {
		return outputBuffer.WriteString(fmt.Sprintf(format, a...))
	}
}

func TestMain(t *testing.T) {

	testCases := []struct {
		input    string
		expected string
	}{
		{
			input: `science
believe
receive
friend
ceiling
receipt`,
			expected: `"I before E when not preceded by C" is plausible (1 vs 0).
"E before I when preceded by C" is plausible (3 vs 0).
Both plausable.
"I before E, except after C" is plausable.
`,
		},
		{
			input: `believe
weird
friend`, expected: `"I before E when not preceded by C" is plausible (2 vs 1).
"E before I when preceded by C" is implausible and contra-indicated (0 vs 0).
One or both implausable.
"I before E, except after C" is implausable.
`,
		},

	}

	for _, tc := range testCases {
		// Create a temporary file with test data
		tmpFile, err := createTempFile(tc.input)
		if err != nil {
			t.Fatal(err)
		}
		defer os.Remove(tmpFile.Name())

		// Override os.Open to use the temporary file
		originalOpen := os.Open
		os.Open = func(name string) (file *os.File, err error) {
			if name == "unixdict.txt" {
				return tmpFile, nil
			}
			return originalOpen(name)

		}
		defer func() { os.Open = originalOpen }()

		// Call main and capture output
		resetOutput()

		main()

		// Check output

		got := getOutput()
		if got != tc.expected {
			t.Errorf("For input:\n%s\nGot:\n%s\nExpected:\n%s", tc.input, got, tc.expected)
		}
	}

}

func createTempFile(content string) (*os.File, error) {
	tmpFile, err := os.CreateTemp("", "test_data")
	if err != nil {
		return nil, err
	}

	if _, err := tmpFile.WriteString(content); err != nil {
		return nil, err
	}

	if _, err := tmpFile.Seek(0, 0); err != nil { // Rewind to the beginning
		return nil, err
	}

	return tmpFile, nil
}

// Stretch goal not implemented as requested. Would require significant changes to original code and test setup.

