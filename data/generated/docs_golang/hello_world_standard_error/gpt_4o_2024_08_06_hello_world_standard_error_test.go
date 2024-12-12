package main

import (
	"bytes"
	"os"
	"testing"
)

func TestMainOutput(t *testing.T) {
	// Save the original stderr
	originalStderr := os.Stderr
	defer func() { os.Stderr = originalStderr }()

	// Create a pipe to capture stderr
	r, w, err := os.Pipe()
	if err != nil {
		t.Fatalf("Failed to create pipe: %v", err)
	}

	// Redirect stderr to the write end of the pipe
	os.Stderr = w

	// Run the main function
	main()

	// Close the write end of the pipe
	w.Close()

	// Read the captured output from the read end of the pipe
	var buf bytes.Buffer
	if _, err := buf.ReadFrom(r); err != nil {
		t.Fatalf("Failed to read from pipe: %v", err)
	}

	// Check if the output matches the expected message
	expected := "Goodbye, World!\n"
	if buf.String() != expected {
		t.Errorf("Expected %q, but got %q", expected, buf.String())
	}
}
