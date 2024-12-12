package main

import (
	"math/big"
	"strconv"
	"testing"
)

func TestContainsAll(t *testing.T) {
	testCases := []struct {
		sq    string
		base  int
		want bool
	}{
		{"102", 3, true},
		{"1023", 4, true},
		{"10233", 4, false},  // Duplicate digit
		{"10234", 5, true},
		{"102344", 5, false}, // Duplicate digit
		{"1234567890", 10, true},
		{"1234567899", 10, false}, // Duplicate digit
		{"123456789", 10, false},  // Not enough digits
	}

	for _, tc := range testCases {
		got := containsAll(tc.sq, tc.base)
		if got != tc.want {
			t.Errorf("containsAll(%q, %d) = %t, want %t", tc.sq, tc.base, got, tc.want)
		}
	}
}



func TestSumDigits(t *testing.T) {
	testCases := []struct {
		n    *big.Int
		base *big.Int
		want *big.Int
	}{
		{big.NewInt(123), big.NewInt(10), big.NewInt(6)},
		{big.NewInt(1234), big.NewInt(10), big.NewInt(10)},
		{big.NewInt(101), big.NewInt(2), big.NewInt(2)},  // Binary
		{big.NewInt(255), big.NewInt(16), big.NewInt(22)}, // Hexadecimal
	}

	for _, tc := range testCases {
		got := sumDigits(tc.n, tc.base)
		if got.Cmp(tc.want) != 0 {
			t.Errorf("sumDigits(%v, %v) = %v, want %v", tc.n, tc.base, got, tc.want)
		}
	}
}

func TestDigitalRoot(t *testing.T) {
	testCases := []struct {
		n    *big.Int
		base int
		want int
	}{
		{big.NewInt(123), 10, 6},
		{big.NewInt(1234), 10, 1},
		{big.NewInt(101), 2, 2},    // Binary
		{big.NewInt(255), 16, 14}, // Hexadecimal
		{big.NewInt(99999),10, 9},
	}

	for _, tc := range testCases {
		got := digitalRoot(tc.n, tc.base)
		if got != tc.want {
			t.Errorf("digitalRoot(%v, %d) = %d, want %d", tc.n, tc.base, got, tc.want)
		}
	}
}

func TestMinStart(t *testing.T) {
	testCases := []struct {
		base int
		wantStr string
		wantInc uint64
		wantBdr int
	}{
		{2, "10", 1, 2},
		{3, "102", 2, 3},
		{10, "1023456789", 1, 1},
		{16, "1023456789abcdef", 1, 15},

	}
	for _, tc := range testCases {
		gotStr, gotInc, gotBdr := minStart(tc.base)
		if gotStr != tc.wantStr || gotInc != tc.wantInc || gotBdr != tc.wantBdr {
			t.Errorf("minStart(%d) = (%q, %d, %d), want (%q, %d, %d)", tc.base, gotStr, gotInc, gotBdr, tc.wantStr, tc.wantInc, tc.wantBdr)
		}
	}
}


