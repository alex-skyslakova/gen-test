package main

import (
	"math/big"
	"testing"
)

// Mock function to replace fmt.Printf in doBase
func findLargestLeftTruncatablePrime(base int) *big.Int {
	answer.SetUint64(0)
	tens[0].SetUint64(1)
	bigTemp.SetUint64(uint64(base))
	seenDepth = 0
	for i := 1; i < maxStack; i++ {
		tens[i].Mul(&tens[i-1], bigTemp)
	}
	for i := 0; smallPrimes[i] < base; i++ {
		values[0].SetUint64(uint64(smallPrimes[i]))
		addDigit(1)
	}
	return new(big.Int).Set(answer)
}

func TestFindLargestLeftTruncatablePrime(t *testing.T) {
	tests := []struct {
		base     int
		expected string
	}{
		{3, "2"},   // Base 3
		{4, "3"},   // Base 4
		{5, "23"},  // Base 5
		{6, "5"},   // Base 6
		{7, "53"},  // Base 7
		{8, "73"},  // Base 8
		{9, "7"},   // Base 9
		{10, "739397"}, // Base 10
		{11, "23399339"}, // Base 11
		{12, "11"},  // Base 12
		{13, "3137"}, // Base 13
		{14, "13"},  // Base 14
		{15, "13"},  // Base 15
		{16, "13"},  // Base 16
		{17, "13"},  // Base 17
	}

	for _, test := range tests {
		base = test.base
		result := findLargestLeftTruncatablePrime(base)
		if result.String() != test.expected {
			t.Errorf("For base %d, expected %s, got %s", test.base, test.expected, result.String())
		}
	}
}
