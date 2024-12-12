package main

import (
	"strconv"
	"testing"
)

func TestSum(t *testing.T) {
	tests := []struct {
		i    uint64
		base int
		sum  int
	}{
		{627615, 10, 27},
		{39390, 10, 24},
		{588225, 10, 30},
		{393900588225, 10, 54},
		{1, 10, 1},
		{11, 10, 2},
		{0xe, 16, 0xe},
		{0x87, 16, 0xf},
		{0xDigitalRoot, 30, 150}, // Assuming DigitalRoot is a valid number in base 30
		{0x448944221089, 10, 45},
		{0x7e0, 16, 0xf},
		{0x14e344, 16, 0x1f},
		{0xd60141, 16, 0x1a},
		{0x12343210, 16, 0x1},
		{0b1101122201121110011000000, 3, 27},
	}

	for _, tt := range tests {
		t.Run(strconv.FormatUint(tt.i, 10), func(t *testing.T) {
			sum := Sum(tt.i, tt.base)
			if sum != tt.sum {
				t.Errorf("Sum(%d, %d) = %d; want %d", tt.i, tt.base, sum, tt.sum)
			}
		})
	}
}

func TestDigitalRoot(t *testing.T) {
	tests := []struct {
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

	for _, tt := range tests {
		t.Run(tt.n, func(t *testing.T) {
			n, err := strconv.ParseUint(tt.n, tt.base, 64)
			if err != nil {
				t.Fatalf("Failed to parse %s in base %d: %v", tt.n, tt.base, err)
			}
			persistence, root := DigitalRoot(n, tt.base)
			if persistence != tt.persistence || root != tt.root {
				t.Errorf("DigitalRoot(%d, %d) = (%d, %d); want (%d, %d)", n, tt.base, persistence, root, tt.persistence, tt.root)
			}
		})
	}
}
