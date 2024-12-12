package main

import (
	"testing"
	"os"
	"os/exec"
	"strings"
)

func TestMainOutput(t *testing.T) {
	// Start a new process to run the main function
	cmd := exec.Command("go", "run", "hello_world_newline_omission.go")

	// Capture the output
	output, err := cmd.CombinedOutput()
	if err != nil {
		t.Fatalf("Failed to run the main function: %v", err)
	}

	// Convert the output to a string and trim any whitespace
	outputStr := strings.TrimSpace(string(output))

	// Check if the output is exactly "Goodbye, World!" without a trailing newline
	expected := "Goodbye, World!"
	if outputStr != expected {
		t.Errorf("Expected output '%s', got '%s'", expected, outputStr)
	}
}

func TestMainOutputWithNewline(t *testing.T) {
	// Start a new process to run the main function
	cmd := exec.Command("go", "run", "hello_world_newline_omission.go")

	// Capture the output
	output, err := cmd.CombinedOutput()
	if err != nil {
		t.Fatalf("Failed to run the main function: %v", err)
	}

	// Convert the output to a string and check for a trailing newline
	outputStr := string(output)
	if strings.HasSuffix(outputStr, "\n") {
		t.Errorf("Output contains a trailing newline: '%s'", outputStr)
	}
}

func TestMainOutputWithStdout(t *testing.T) {
	// Redirect stdout to capture the output
	oldStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Run the main function
	main()

	// Close the writer and restore stdout
	w.Close()
	os.Stdout = oldStdout

	// Read the captured output
	output, _ := os.ReadFile(r.Name())

	// Convert the output to a string and trim any whitespace
	outputStr := strings.TrimSpace(string(output))

	// Check if the output is exactly "Goodbye, World!" without a trailing newline
	expected := "Goodbye, World!"
	if outputStr != expected {
		t.Errorf("Expected output '%s', got '%s'", expected, outputStr)
	}
}
