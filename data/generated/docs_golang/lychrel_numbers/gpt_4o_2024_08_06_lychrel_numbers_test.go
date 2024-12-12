package main

import (
	"testing"
	"math/big"
)

// TestReverseInt tests the reverseInt function for various cases.
func TestReverseInt(t *testing.T) {
	tests := []struct {
		input    string
		expected string
	}{
		{"123", "321"},
		{"100", "001"},
		{"9876543210", "0123456789"},
		{"1", "1"},
		{"0", "0"},
	}

	for _, test := range tests {
		v := new(big.Int)
		v.SetString(test.input, 10)
		result := new(big.Int)
		reverseInt(v, result)
		if result.String() != test.expected {
			t.Errorf("reverseInt(%s) = %s; expected %s", test.input, result.String(), test.expected)
		}
	}
}

// TestReverseUint64 tests the reverseUint64 function for various cases.
func TestReverseUint64(t *testing.T) {
	tests := []struct {
		input    uint64
		expected uint64
	}{
		{123, 321},
		{100, 1},
		{9876543210, 123456789},
		{1, 1},
		{0, 0},
	}

	for _, test := range tests {
		result := reverseUint64(test.input)
		if result != test.expected {
			t.Errorf("reverseUint64(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

// TestReverseString tests the reverseString function for various cases.
func TestReverseString(t *testing.T) {
	tests := []struct {
		input    string
		expected string
	}{
		{"123", "321"},
		{"100", "001"},
		{"9876543210", "0123456789"},
		{"1", "1"},
		{"0", "0"},
	}

	for _, test := range tests {
		result := reverseString(test.input)
		if result != test.expected {
			t.Errorf("reverseString(%s) = %s; expected %s", test.input, result, test.expected)
		}
	}
}

// TestLychrel tests the Lychrel function for various cases.
func TestLychrel(t *testing.T) {
	tests := []struct {
		input    uint64
		iter     uint
		expectedIsLychrel bool
		expectedIsSeed    bool
	}{
		{12, 500, false, false},  // 12 becomes 33, a palindrome
		{55, 500, false, false},  // 55 becomes 121, a palindrome
		{196, 500, true, true},   // 196 is a known Lychrel candidate
		{689, 500, true, false},  // 689 is related to 196
		{1, 500, false, false},   // 1 is a palindrome
	}

	for _, test := range tests {
		isLychrel, isSeed := Lychrel(test.input, test.iter)
		if isLychrel != test.expectedIsLychrel || isSeed != test.expectedIsSeed {
			t.Errorf("Lychrel(%d, %d) = (%v, %v); expected (%v, %v)", test.input, test.iter, isLychrel, isSeed, test.expectedIsLychrel, test.expectedIsSeed)
		}
	}
}
