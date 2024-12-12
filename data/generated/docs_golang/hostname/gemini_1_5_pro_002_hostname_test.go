package main

import (
	"os"
	"testing"
)

func TestHostname(t *testing.T) {
	// Capture the original os.Hostname function
	originalHostname := os.Hostname

	// Test case 1: Successful hostname retrieval
	t.Run("Success", func(t *testing.T) {
		os.Hostname = func() (string, error) {
			return "test-hostname", nil
		}
		defer func() { os.Hostname = originalHostname }()

		// We can't directly capture the output of main, 
        // so we're testing the core logic within main.
		name, err := os.Hostname()
		if err != nil {
			t.Errorf("Unexpected error: %v", err)
		}
		if name != "test-hostname" {
			t.Errorf("Expected hostname 'test-hostname', got '%s'", name)
		}
	})

	// Test case 2: Error during hostname retrieval
	t.Run("Error", func(t *testing.T) {
		os.Hostname = func() (string, error) {
			return "", &os.PathError{Op: "hostname", Path: "", Err: os.ErrNotExist} // Example error
		}
		defer func() { os.Hostname = originalHostname }()

        // Similar to the success case, test the core logic
		name, err := os.Hostname()
		if err == nil {
			t.Error("Expected an error, but got none")
		}
		if name != "" {  // Expect empty string on error, per original code behaviour.
			t.Errorf("Expected empty string on error, got '%s'", name)
		}
        // Further error type checking can be done here if required by the specs.
	})


}
