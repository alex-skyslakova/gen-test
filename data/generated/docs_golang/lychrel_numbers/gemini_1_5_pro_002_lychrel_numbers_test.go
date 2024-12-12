package main

import (
	"math/big"
	"testing"
)

func TestReverseInt(t *testing.T) {
	testCases := []struct {
		input    *big.Int
		expected *big.Int
	}{
		{big.NewInt(12), big.NewInt(21)},
		{big.NewInt(55), big.NewInt(55)},
		{big.NewInt(110), big.NewInt(11)},
		{big.NewInt(1234567890), big.NewInt(987654321)},
		{big.NewInt(0), big.NewInt(0)},
	}

	for _, tc := range testCases {
		result := new(big.Int)
		reverseInt(tc.input, result)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("reverseInt(%v) = %v, expected %v", tc.input, result, tc.expected)
		}
	}
}


func TestReverseUint64(t *testing.T) {
	testCases := []struct {
		input    uint64
		expected uint64
	}{
		{12, 21},
		{55, 55},
		{110, 11},
		{12345, 54321},
		{0, 0},
	}

	for _, tc := range testCases {
		result := reverseUint64(tc.input)
		if result != tc.expected {
			t.Errorf("reverseUint64(%v) = %v, expected %v", tc.input, result, tc.expected)
		}
	}
}

func TestReverseString(t *testing.T) {
	testCases := []struct {
		input    string
		expected string
	}{
		{"12", "21"},
		{"55", "55"},
		{"110", "011"},
		{"12345", "54321"},
		{"", ""},
	}

	for _, tc := range testCases {
		result := reverseString(tc.input)
		if result != tc.expected {
			t.Errorf("reverseString(%v) = %v, expected %v", tc.input, result, tc.expected)
		}
	}
}



func TestLychrel(t *testing.T) {
	testCases := []struct {
		input    uint64
		iter     uint
		lychrel  bool
		isSeed bool
	}{
		{12, 500, false, false},
		{55, 500, false, false},
		{196, 500, true, true},
		{689, 500, true, false},
		{4994, 500, false, false}, // Palindrome input
		{89, 10, true, true}, // Becomes palindrome after several iterations 
		{196, 1, true, true},  // Lychrel detection with limited iterations
		{56, 3, false, false},
	}

	for i, tc := range testCases{
		known = make(map[string]bool) // Reset known for each test case
		lychrel, isSeed := Lychrel(tc.input, tc.iter)

		if lychrel != tc.lychrel {
			t.Errorf("Test case %d: Lychrel(%v, %v) = %v, expected %v", i+1, tc.input, tc.iter, lychrel, tc.lychrel)
		}
		if isSeed != tc.isSeed {
			t.Errorf("Test case %d: Lychrel(%v, %v) - isSeed = %v, expected %v", i+1, tc.input, tc.iter, isSeed, tc.isSeed)
		}
	}
}

