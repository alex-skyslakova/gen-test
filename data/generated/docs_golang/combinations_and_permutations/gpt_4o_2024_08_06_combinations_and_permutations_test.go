package main

import (
	"math/big"
	"testing"
)

// TestFactorial tests the fact function for calculating factorials.
func TestFactorial(t *testing.T) {
	tests := []struct {
		input    int64
		expected string
	}{
		{0, "0"},
		{1, "1"},
		{5, "120"},
		{10, "3628800"},
	}

	for _, test := range tests {
		result := fact(big.NewInt(test.input))
		if result.String() != test.expected {
			t.Errorf("Expected factorial of %d to be %s, got %s", test.input, test.expected, result.String())
		}
	}
}

// TestPermutations tests the perm function for calculating permutations.
func TestPermutations(t *testing.T) {
	tests := []struct {
		n, k     int64
		expected string
	}{
		{5, 3, "60"},
		{10, 5, "30240"},
		{12, 4, "11880"},
	}

	for _, test := range tests {
		result := perm(big.NewInt(test.n), big.NewInt(test.k))
		if result.String() != test.expected {
			t.Errorf("Expected P(%d, %d) to be %s, got %s", test.n, test.k, test.expected, result.String())
		}
	}
}

// TestCombinations tests the comb function for calculating combinations.
func TestCombinations(t *testing.T) {
	tests := []struct {
		n, k     int64
		expected string
	}{
		{5, 3, "10"},
		{10, 5, "252"},
		{12, 4, "495"},
	}

	for _, test := range tests {
		result := comb(big.NewInt(test.n), big.NewInt(test.k))
		if result.String() != test.expected {
			t.Errorf("Expected C(%d, %d) to be %s, got %s", test.n, test.k, test.expected, result.String())
		}
	}
}

// TestEdgeCases tests edge cases for permutations and combinations.
func TestEdgeCases(t *testing.T) {
	tests := []struct {
		n, k     int64
		expectedP string
		expectedC string
	}{
		{0, 0, "1", "1"},
		{5, 0, "1", "1"},
		{5, 5, "120", "1"},
		{5, 6, "0", "0"},
	}

	for _, test := range tests {
		resultP := perm(big.NewInt(test.n), big.NewInt(test.k))
		resultC := comb(big.NewInt(test.n), big.NewInt(test.k))
		if resultP.String() != test.expectedP {
			t.Errorf("Expected P(%d, %d) to be %s, got %s", test.n, test.k, test.expectedP, resultP.String())
		}
		if resultC.String() != test.expectedC {
			t.Errorf("Expected C(%d, %d) to be %s, got %s", test.n, test.k, test.expectedC, resultC.String())
		}
	}
}
