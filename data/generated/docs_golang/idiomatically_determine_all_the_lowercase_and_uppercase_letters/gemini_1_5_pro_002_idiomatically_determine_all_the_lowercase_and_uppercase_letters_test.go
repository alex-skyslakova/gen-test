package main

import (
	"bytes"
	"fmt"
	"os"
	"testing"
	"unicode"
)

func TestMain(t *testing.T) {
	// Capture stdout
	old := os.Stdout // keep backup of standard output
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

	// Check for ASCII lower case
	if !contains(out, lcASCII+"\n") {
		t.Errorf("Output does not contain expected ASCII lowercase string")
	}
	if !contains(out, lcASCII) {
		t.Errorf("Output does not contain expected lowercase characters from loop")
	}


	// Check for ASCII upper case
	if !contains(out, ucASCII+"\n") {
		t.Errorf("Output does not contain expected ASCII uppercase string")
	}
	if !contains(out, ucASCII) {
		t.Errorf("Output does not contain expected uppercase characters from loop")
	}

	// Check for Unicode version
	if !contains(out, "Unicode version "+unicode.Version) {
		t.Errorf("Output does not contain Unicode version string")
	}

	// Check for Range outputs (Difficult to precisely check characters, so we check for headers and range counts)
	if !contains(out, "Lower case 16-bit code points:") || !contains(out, fmt.Sprintf("%d ranges:", len(unicode.Lower.R16))) {
		t.Error("Output format for Lower case 16-bit code points is incorrect")
	}

	if !contains(out, "Lower case 32-bit code points:") || !contains(out, fmt.Sprintf("%d ranges:", len(unicode.Lower.R32))) {
		t.Error("Output format for Lower case 32-bit code points is incorrect")
	}
	
    if !contains(out, "Upper case 16-bit code points:") || !contains(out, fmt.Sprintf("%d ranges:", len(unicode.Upper.R16))) {
		t.Error("Output format for Upper case 16-bit code points is incorrect")
	}

    if !contains(out, "Upper case 32-bit code points:") || !contains(out, fmt.Sprintf("%d ranges:", len(unicode.Upper.R32))) {
		t.Error("Output format for Upper case 32-bit code points is incorrect")
	}

}



func contains(s, substr string) bool {
	return bytes.Contains([]byte(s), []byte(substr))
}
