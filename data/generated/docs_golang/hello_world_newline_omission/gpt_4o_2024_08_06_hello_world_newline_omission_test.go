package main

import (
	"bytes"
	"testing"
)

func TestMainOutput(t *testing.T) {
	// Capture the output of the main function
	var buf bytes.Buffer
	fmtPrint = func(a ...interface{}) (n int, err error) {
		return buf.WriteString(a[0].(string))
	}

	// Call the main function
	main()

	// Expected output
	expected := "Goodbye, World!"

	// Check if the output matches the expected string
	if buf.String() != expected {
		t.Errorf("Expected %q, but got %q", expected, buf.String())
	}
}

// Override fmt.Print for testing purposes
var fmtPrint = fmt.Print

func init() {
	// Redirect fmt.Print to our custom function
	fmt.Print = fmtPrint
}
