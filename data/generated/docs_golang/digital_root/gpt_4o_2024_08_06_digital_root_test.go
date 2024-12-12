package main

import (
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
		{"DigitalRoot", 30, 2, 26}, // 26 is Q base 30
		{"448944221089", 10, 3, 1},
		{"7e0", 16, 2, 0x6},
		{"14e344", 16, 2, 0xf},
		{"d60141", 16, 2, 0xa},
		{"12343210", 16, 2, 0x1},
		{"1101122201121110011000000", 3, 3, 1},
	}

	for _, tc := range testCases {
		t.Run(tc.n, func(t *testing.T) {
			n, err := strconv.ParseUint(tc.n, tc.base, 64)
			if err != nil {
				t.Fatalf("Failed to parse number: %v", err)
			}
			p, r := DigitalRoot(n, tc.base)
			if p != tc.persistence || r != tc.root {
				t.Errorf("For %s (base %d): expected persistence %d and root %d, got persistence %d and root %d",
					tc.n, tc.base, tc.persistence, tc.root, p, r)
			}
		})
	}
}
