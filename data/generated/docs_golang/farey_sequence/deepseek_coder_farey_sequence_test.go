package main

import (
	"fmt"
	"testing"
)

func TestFareySequence(t *testing.T) {
	// Helper function to compare two slices of fractions
	compareSlices := func(a, b []frac) bool {
		if len(a) != len(b) {
			return false
		}
		for i := range a {
			if a[i] != b[i] {
				return false
			}
		}
		return true
	}

	// Test cases for Farey sequences of orders 1 to 5
	testCases := []struct {
		n    int
		want []frac
	}{
		{1, []frac{{0, 1}, {1, 1}}},
		{2, []frac{{0, 1}, {1, 2}, {1, 1}}},
		{3, []frac{{0, 1}, {1, 3}, {1, 2}, {2, 3}, {1, 1}}},
		{4, []frac{{0, 1}, {1, 4}, {1, 3}, {1, 2}, {2, 3}, {3, 4}, {1, 1}}},
		{5, []frac{{0, 1}, {1, 5}, {1, 4}, {1, 3}, {2, 5}, {1, 2}, {3, 5}, {2, 3}, {3, 4}, {4, 5}, {1, 1}}},
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Order %d", tc.n), func(t *testing.T) {
			var got []frac
			l := frac{0, 1}
			r := frac{1, 1}
			got = append(got, l)
			fRec(l, r, tc.n, &got)
			got = append(got, r)
			if !compareSlices(got, tc.want) {
				t.Errorf("Expected %v, got %v", tc.want, got)
			}
		})
	}
}

// Recursive function to generate Farey sequence and store it in a slice
func fRec(l, r frac, n int, result *[]frac) {
	m := frac{l.num + r.num, l.den + r.den}
	if m.den <= n {
		fRec(l, m, n, result)
		*result = append(*result, m)
		fRec(m, r, n, result)
	}
}

// Test for the number of fractions in the Farey sequence for order 100 through 1,000 by hundreds
func TestFareySequenceLength(t *testing.T) {
	// Expected lengths for orders 100, 200, ..., 1000
	expectedLengths := []int{
		3045, 12233, 27399, 48679, 76117, 109501, 149019, 194751, 246327,
	}

	// Generate primes and totients up to 1000
	var composite [1001]bool
	for _, p := range []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31} {
		for n := p * 2; n <= 1000; n += p {
			composite[n] = true
		}
	}
	var tot [1001]int
	for i := range tot {
		tot[i] = 1
	}
	for n := 2; n <= 1000; n++ {
		if !composite[n] {
			tot[n] = n - 1
			for a := n * 2; a <= 1000; a += n {
				f := n - 1
				for r := a / n; r%n == 0; r /= n {
					f *= n
				}
				tot[a] *= f
			}
		}
	}

	// Sum totients and compare with expected lengths
	for i, sum := 0, 1; i < len(expectedLengths); i++ {
		for n := 1; n <= (i+1)*100; n++ {
			sum += tot[n]
		}
		if sum != expectedLengths[i] {
			t.Errorf("Expected length for order %d to be %d, got %d", (i+1)*100, expectedLengths[i], sum)
		}
	}
}
