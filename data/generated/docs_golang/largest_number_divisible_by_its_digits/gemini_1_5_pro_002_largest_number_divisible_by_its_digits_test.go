package main

import (
	"strconv"
	"strings"
	"testing"
)

func TestDivByAll(t *testing.T) {
	testCases := []struct {
		num     int
		digits  []byte
		expected bool
	}{
		{123, []byte{'1', '2', '3'}, false},
		{12, []byte{'1', '2'}, true},
		{135, []byte{'1', '3', '5'}, true},
		{124, []byte{'1', '2', '4'}, true},
		{987654321, []byte{'1', '2', '3', '4', '5', '6', '7', '8', '9'}, false},
		{321, []byte{'1', '2', '3'}, false},
	}

	for _, tc := range testCases {
		actual := divByAll(tc.num, tc.digits)
		if actual != tc.expected {
			t.Errorf("divByAll(%d, %v) = %t; want %t", tc.num, tc.digits, actual, tc.expected)
		}
	}
}



func TestMainIntegration(t *testing.T) {

	// Capture standard output to verify the result
	rescueStdout := os.Stdout
	r, w, _ := os.Pipe()
	os.Stdout = w

	main()

	w.Close()
	out, _ := io.ReadAll(r)
	os.Stdout = rescueStdout
    
    expectedOutput := "Largest decimal number is 98765321\n"
	if string(out) != expectedOutput {
		t.Errorf("main() output = %q; want %q", string(out), expectedOutput)
	}
}


// Helper function to simulate main's logic for specific inputs, useful for smaller, targeted tests.
func findLargest(high int, magic int) int {
    for i := high; i >= magic; i -= magic {
        if i%10 == 0 {
            continue // can't end in '0'
        }
        s := strconv.Itoa(i)
        if strings.ContainsAny(s, "05") {
            continue // can't contain '0'or '5'
        }
        var set = make(map[byte]bool)
        var sd []byte // distinct digits
        for _, b := range []byte(s) {
            if !set[b] {
                set[b] = true
                sd = append(sd, b)
            }
        }
        if len(sd) != len(s) {
            continue // digits must be unique
        }
        if divByAll(i, sd) {
            return i
        }
    }
    return 0 // Return 0 if no number is found
}



func TestFindLargest(t *testing.T) {

	magic := 9 * 8 * 7
	high := 9876432 / magic * magic

	expected := 98765321
	actual := findLargest(high, magic)

	if actual != expected {
		t.Errorf("findLargest(%d, %d) = %d; want %d", high, magic, actual, expected)

	}

}

