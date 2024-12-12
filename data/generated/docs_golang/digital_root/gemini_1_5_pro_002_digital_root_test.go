package main

import (
	"log"
	"strconv"
	"testing"
)

func TestDigitalRoot(t *testing.T) {
	testCases := []struct {
		n           string
		base        int
		persistence int
		root        int
	}{
		{"627615", 10, 2, 9},
		{"39390", 10, 2, 6},
		{"588225", 10, 2, 3},
		{"393900588225", 10, 2, 9},
		{"1", 10, 0, 1},
		{"11", 10, 1, 2},
		{"e", 16, 0, 0xe},
		{"87", 16, 1, 0xf},
		{"DigitalRoot", 30, 2, 26},
		{"448944221089", 10, 3, 1},
		{"7e0", 16, 2, 0x6},
		{"14e344", 16, 2, 0xf},
		{"d60141", 16, 2, 0xa},
		{"12343210", 16, 2, 0x1},
		{"1101122201121110011000000", 3, 3, 1},
		{"0", 10, 0, 0},      // Test zero
		{"", 10, 0, 0},       // Test empty string
		{"ffffffffffffffff", 16, 3, 0xf}, // Test large hex number
		{"2", 2, 0, 0}, //Test base 2 edge case

	}

	for _, tc := range testCases {
		n, err := strconv.ParseUint(tc.n, tc.base, 64)
		if err != nil {
			if tc.n == "" { // handle empty string case where parseuint expects a value
				n = 0
			} else {
				t.Errorf("Error parsing input: %v", err)
				continue
			}
			
		}

		p, r := DigitalRoot(n, tc.base)
		if p != tc.persistence || r != tc.root {
			t.Errorf("For n = %s, base = %d, expected persistence = %d, root = %d, got persistence = %d, root = %d", tc.n, tc.base, tc.persistence, tc.root, p, r)
		}
	}

    // Test invalid base
    _, _ = DigitalRoot(123, 1)
    _, _ = DigitalRoot(123, 0)
    _, _ = DigitalRoot(123, -1)
}



func TestSum(t *testing.T) {
	testCases := []struct {
		n    uint64
		base int
		sum  int
	}{
		{12345, 10, 15},
		{0xabcdef, 16, 0xabc + 0xdef},
		{0, 10, 0},
		{1, 2, 1},
		{10, 2, 1},
		{01234567, 8, 299},

	}

	for _, tc := range testCases {
		sum := Sum(tc.n, tc.base)

		if sum != tc.sum {
			t.Errorf("For n = %d, base = %d, expected sum = %d, got sum = %d", tc.n, tc.base, tc.sum, sum)
		}
	}

    // Test invalid base
    _ = Sum(123, 1)
    _ = Sum(123, 0)
    _ = Sum(123, -1)
}
