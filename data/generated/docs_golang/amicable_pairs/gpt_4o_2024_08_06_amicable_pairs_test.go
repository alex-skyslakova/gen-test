package main

import (
	"testing"
)

// Test function for pfacSum
func TestPfacSum(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{1, 0},    // Proper divisors of 1 are none
		{6, 6},    // Proper divisors of 6 are 1, 2, 3
		{28, 28},  // Proper divisors of 28 are 1, 2, 4, 7, 14
		{12, 16},  // Proper divisors of 12 are 1, 2, 3, 4, 6
		{15, 9},   // Proper divisors of 15 are 1, 3, 5
		{220, 284}, // Proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110
		{284, 220}, // Proper divisors of 284 are 1, 2, 4, 71, 142
	}

	for _, test := range tests {
		result := pfacSum(test.input)
		if result != test.expected {
			t.Errorf("pfacSum(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

// Test function for finding amicable pairs
func TestAmicablePairs(t *testing.T) {
	expectedPairs := [][2]int{
		{220, 284},
		{1184, 1210},
		{2620, 2924},
		{5020, 5564},
		{6232, 6368},
		{10744, 10856},
		{12285, 14595},
		{17296, 18416},
	}

	var a [20000]int
	for i := 1; i < 20000; i++ {
		a[i] = pfacSum(i)
	}

	var foundPairs [][2]int
	for n := 2; n < 19999; n++ {
		m := a[n]
		if m > n && m < 20000 && n == a[m] {
			foundPairs = append(foundPairs, [2]int{n, m})
		}
	}

	if len(foundPairs) != len(expectedPairs) {
		t.Fatalf("Expected %d amicable pairs, but found %d", len(expectedPairs), len(foundPairs))
	}

	for i, pair := range foundPairs {
		if pair != expectedPairs[i] {
			t.Errorf("Expected pair %v, but found %v", expectedPairs[i], pair)
		}
	}
}
