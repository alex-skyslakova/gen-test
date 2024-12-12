package main

import (
	"math/big"
	"testing"
)

func TestReverseInt(t *testing.T) {
	tests := []struct {
		input    *big.Int
		expected *big.Int
	}{
		{big.NewInt(123), big.NewInt(321)},
		{big.NewInt(123456), big.NewInt(654321)},
		{big.NewInt(1), big.NewInt(1)},
		{big.NewInt(0), big.NewInt(0)},
		{big.NewInt(100), big.NewInt(1)},
		{big.NewInt(12321), big.NewInt(12321)},
	}

	for _, test := range tests {
		result := new(big.Int)
		reverseInt(test.input, result)
		if result.Cmp(test.expected) != 0 {
			t.Errorf("reverseInt(%v) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

func TestLychrel(t *testing.T) {
	tests := []struct {
		n           uint64
		iter        uint
		isLychrel   bool
		isSeed      bool
	}{
		{12, 500, false, false},
		{55, 500, false, false},
		{196, 500, true, true},
		{689, 500, true, false},
		{89, 500, true, true},
		{10, 500, false, false},
		{1, 500, false, false},
		{10000, 500, false, false},
	}

	for _, test := range tests {
		isLychrel, isSeed := Lychrel(test.n, test.iter)
		if isLychrel != test.isLychrel || isSeed != test.isSeed {
			t.Errorf("Lychrel(%v, %v) = (%v, %v); expected (%v, %v)", test.n, test.iter, isLychrel, isSeed, test.isLychrel, test.isSeed)
		}
	}
}

func TestReverseUint64(t *testing.T) {
	tests := []struct {
		input    uint64
		expected uint64
	}{
		{123, 321},
		{123456, 654321},
		{1, 1},
		{0, 0},
		{100, 1},
		{12321, 12321},
	}

	for _, test := range tests {
		result := reverseUint64(test.input)
		if result != test.expected {
			t.Errorf("reverseUint64(%v) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

func TestReverseString(t *testing.T) {
	tests := []struct {
		input    string
		expected string
	}{
		{"123", "321"},
		{"123456", "654321"},
		{"1", "1"},
		{"0", "0"},
		{"100", "001"},
		{"12321", "12321"},
	}

	for _, test := range tests {
		result := reverseString(test.input)
		if result != test.expected {
			t.Errorf("reverseString(%v) = %v; expected %v", test.input, result, test.expected)
		}
	}
}
