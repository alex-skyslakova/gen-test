package main

import (
	"bytes"
	"os"
	"testing"
)

func TestPrintFileSize(t *testing.T) {
	// Create a buffer to capture the output
	var buf bytes.Buffer
	originalStdout := os.Stdout
	defer func() { os.Stdout = originalStdout }()
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Test case 1: File in the current working directory
	fileName := "input.txt"
	file, err := os.Create(fileName)
	if err != nil {
		t.Fatalf("Failed to create test file: %v", err)
	}
	defer os.Remove(fileName) // Clean up

	// Write some data to the file
	file.WriteString("Hello, World!")
	file.Close()

	printFileSize(fileName)

	// Capture the output
	w.Close()
	buf.ReadFrom(r)
	output := buf.String()

	expectedSize := "13\n" // "Hello, World!" is 13 bytes
	if output != expectedSize {
		t.Errorf("Expected file size %s, but got %s", expectedSize, output)
	}

	// Test case 2: File in the file system root
	// This test assumes that "/input.txt" does not exist
	buf.Reset()
	r, w, _ = os.Pipe()
	os.Stdout = w

	printFileSize("/input.txt")

	// Capture the output
	w.Close()
	buf.ReadFrom(r)
	output = buf.String()

	expectedError := "stat /input.txt: no such file or directory\n"
	if output != expectedError {
		t.Errorf("Expected error message %s, but got %s", expectedError, output)
	}
}
