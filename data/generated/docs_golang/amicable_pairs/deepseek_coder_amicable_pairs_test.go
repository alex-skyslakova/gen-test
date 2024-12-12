package main

import (
	"testing"
)

func TestPfacSum(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{1, 0},       // Proper divisors of 1 are none, so sum is 0
		{6, 6},       // Proper divisors of 6 are 1, 2, 3, sum is 6
		{28, 28},     // Proper divisors of 28 are 1, 2, 4, 7, 14, sum is 28
		{1184, 1210}, // Proper divisors of 1184 are 1, 2, 4, 8, 16, 32, 37, 74, 148, 296, 592, sum is 1210
		{1210, 1184}, // Proper divisors of 1210 are 1, 2, 5, 10, 11, 22, 55, 110, 121, 242, 605, sum is 1184
	}

	for _, test := range tests {
		result := pfacSum(test.input)
		if result != test.expected {
			t.Errorf("pfacSum(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

func TestAmicablePairs(t *testing.T) {
	// Expected amicable pairs below 20,000
	expectedPairs := []struct {
		n int
		m int
	}{
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

	foundPairs := make(map[[2]int]bool)
	for n := 2; n < 19999; n++ {
		m := a[n]
		if m > n && m < 20000 && n == a[m] {
			foundPairs[[2]int{n, m}] = true
		}
	}

	for _, pair := range expectedPairs {
		if !foundPairs[[2]int{pair.n, pair.m}] {
			t.Errorf("Expected amicable pair %d and %d not found", pair.n, pair.m)
		}
	}

	if len(foundPairs) != len(expectedPairs) {
		t.Errorf("Found %d amicable pairs, expected %d", len(foundPairs), len(expectedPairs))
	}
}
