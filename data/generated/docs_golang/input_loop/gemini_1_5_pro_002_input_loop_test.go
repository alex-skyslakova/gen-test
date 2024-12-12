package main

import (
	"bufio"
	"bytes"
	"io"
	"log"
	"os"
	"strings"
	"testing"
)

func TestMain(t *testing.T) {
	// Test case 1: Multiple lines of input
	input := "line1\nline2\nline3\n"
	reader := strings.NewReader(input)
	restoreStdin := redirectStdin(reader)
	defer restoreStdin()

	// Capture logs
	var buf bytes.Buffer
	log.SetOutput(&buf)
	defer func() {
		log.SetOutput(os.Stderr) // Restore default log output
	}()

	main()

	// Assertions
	expectedLog := ""
	if buf.String() != expectedLog {
		t.Errorf("Expected log: '%s', got: '%s'", expectedLog, buf.String())
	}

	// Test case 2: Single line of input
	input = "single line\n"
	reader = strings.NewReader(input)
	restoreStdin = redirectStdin(reader)
	defer restoreStdin()

	buf.Reset() // Clear the log buffer

	main()

	if buf.String() != expectedLog {
		t.Errorf("Expected log: '%s', got: '%s'", expectedLog, buf.String())
	}


	// Test case 3: Empty input
	input = ""
	reader = strings.NewReader(input)
	restoreStdin = redirectStdin(reader)
	defer restoreStdin()

	buf.Reset() // Clear the log buffer

	main()

	if buf.String() != expectedLog {
		t.Errorf("Expected log: '%s', got: '%s'", expectedLog, buf.String())
	}

	// Test case 4: Input with an error (other than EOF) -  Difficult to simulate reliably with ReadString.
}


// Helper function to redirect stdin
func redirectStdin(r io.Reader) func() {
	originalStdin := os.Stdin
	os.Stdin = r

	return func() {
		os.Stdin = originalStdin
	}
}

