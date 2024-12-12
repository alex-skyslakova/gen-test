package main

import (
	"bytes"
	"fmt"
	"testing"
)

func TestCreateTwoDimensionalArray(t *testing.T) {
	tests := []struct {
		row, col int
		expected string
	}{
		{2, 3, "a[0][0] = 0\na[1][2] = 7\n"},
		{1, 1, "a[0][0] = 0\na[0][0] = 7\n"},
		{3, 2, "a[0][0] = 0\na[2][1] = 7\n"},
	}

	for _, test := range tests {
		t.Run(fmt.Sprintf("row=%d,col=%d", test.row, test.col), func(t *testing.T) {
			// Redirect standard output to capture the prints
			var buf bytes.Buffer
			fmtPrint = func(a ...interface{}) (n int, err error) {
				return fmt.Fprint(&buf, a...)
			}
			fmtPrintf = func(format string, a ...interface{}) (n int, err error) {
				return fmt.Fprintf(&buf, format, a...)
			}

			// Simulate user input
			fmtScan = func(a ...interface{}) (n int, err error) {
				*a[0].(*int) = test.row
				*a[1].(*int) = test.col
				return 2, nil
			}

			main()

			// Check the output
			if buf.String() != test.expected {
				t.Errorf("expected %q, got %q", test.expected, buf.String())
			}
		})
	}
}

// Mock functions to replace fmt.Scan, fmt.Print, and fmt.Printf
var fmtScan = fmt.Scan
var fmtPrint = fmt.Print
var fmtPrintf = fmt.Printf
