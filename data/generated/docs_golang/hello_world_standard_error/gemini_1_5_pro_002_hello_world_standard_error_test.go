package main

import (
	"bytes"
	"fmt"
	"os"
	"testing"
)

func TestMainPrintsToStandardError(t *testing.T) {
	// Capture standard error output
	oldStderr := os.Stderr
	defer func() { os.Stderr = oldStderr }()

	r, w, _ := os.Pipe()
	os.Stderr = w

	// Call the main function (which we'll indirectly invoke)
	main()

	// Close the write end of the pipe to signal end of writing
	w.Close()

	// Read the captured output
	var buf bytes.Buffer
	_, err := buf.ReadFrom(r)
	if err != nil {
		t.Fatalf("Error reading from captured stderr: %v", err)
	}
	output := buf.String()


	expectedOutput := "Goodbye, World!\n"
	if output != expectedOutput {
		t.Errorf("Expected stderr output:\n%q\nGot:\n%q", expectedOutput, output)
	}
}

// A dummy main function to avoid actual program exit during testing
func TestMain(m *testing.M) {
    fmt.Println("Tests running - dummy main function to avoid exit") // This goes to standard *output* not error
    m.Run()
}


