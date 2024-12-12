package main

import (
	"fmt"
	"io"
	"os"
	"testing"
)

func TestIntegerSequence(t *testing.T) {
	// Redirect stdout to a pipe
	oldStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	// Run the main function in a separate goroutine
	exitChan := make(chan bool)
	go func() {
		main()
		exitChan <- true // Signal when main finishes (never in this case)
	}()

	// Read the first few numbers from the pipe
	readBuf := make([]byte, 1024)
	n, err := r.Read(readBuf)
	if err != nil && err != io.EOF {
		t.Fatalf("Error reading from pipe: %v", err)
	}

	// Close the write end of the pipe to signal EOF to the reader
	w.Close()

	// Restore stdout
	os.Stdout = oldStdout

    // Construct the expected output string. Add a newline after each number
    // and account for leading spaces from fmt.Println.  Crucially, we must 
    // add a trailing newline, since the last number written to stdout
    // will have a trailing newline.
	expectedOutput := ""
	for i := 1; i <= 5; i++ {  // Check the first 5 numbers
        expectedOutput += fmt.Sprintf(" %d\n", i)
	}


	actualOutput := string(readBuf[:n])

	if actualOutput != expectedOutput {
		t.Errorf("Output mismatch:\nExpected:\n%q\nActual:\n%q", expectedOutput, actualOutput)
	}

	// This test will never reach here as main() runs indefinitely. 
    // The intent is to check the initial output and interrupt the test manually or with a timeout.
}

