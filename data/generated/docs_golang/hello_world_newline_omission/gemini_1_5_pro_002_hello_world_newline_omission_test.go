package main

import (
	"bytes"
	"os"
	"testing"
)

func Test_main(t *testing.T) {
	// Capture stdout
	old := os.Stdout // keep backup of the real stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	main()

	outC := make(chan string)
	// copy the output in a separate goroutine so printing can't block indefinitely
	go func() {
		var buf bytes.Buffer
		_, _ = buf.ReadFrom(r)
		outC <- buf.String()
	}()

	// back to normal state
	w.Close()
	os.Stdout = old // restoring the real stdout
	out := <-outC

	expected := "Goodbye, World!"
	if out != expected {
		t.Errorf("Expected '%s', but got '%s'", expected, out)
	}
}

