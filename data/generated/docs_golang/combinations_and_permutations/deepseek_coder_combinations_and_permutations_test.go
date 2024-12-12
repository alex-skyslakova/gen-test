package main

import (
	"math/big"
	"testing"
)

func TestFact(t *testing.T) {
	tests := []struct {
		n        int64
		expected string
	}{
		{0, "0"},
		{1, "1"},
		{2, "2"},
		{3, "6"},
		{4, "24"},
		{5, "120"},
		{10, "3628800"},
	}

	for _, test := range tests {
		result := fact(big.NewInt(test.n))
		expected := new(big.Int)
		expected.SetString(test.expected, 10)
		if result.Cmp(expected) != 0 {
			t.Errorf("fact(%d) = %s; expected %s", test.n, result.String(), test.expected)
		}
	}
}

func TestPerm(t *testing.T) {
	tests := []struct {
		n        int64
		k        int64
		expected string
	}{
		{1, 1, "1"},
		{2, 1, "2"},
		{3, 2, "6"},
		{4, 2, "12"},
		{5, 3, "60"},
		{10, 3, "720"},
	}

	for _, test := range tests {
		result := perm(big.NewInt(test.n), big.NewInt(test.k))
		expected := new(big.Int)
		expected.SetString(test.expected, 10)
		if result.Cmp(expected) != 0 {
			t.Errorf("perm(%d, %d) = %s; expected %s", test.n, test.k, result.String(), test.expected)
		}
	}
}

func TestComb(t *testing.T) {
	tests := []struct {
		n        int64
		k        int64
		expected string
	}{
		{1, 1, "1"},
		{2, 1, "2"},
		{3, 2, "3"},
		{4, 2, "6"},
		{5, 3, "10"},
		{10, 3, "120"},
	}

	for _, test := range tests {
		result := comb(big.NewInt(test.n), big.NewInt(test.k))
		expected := new(big.Int)
		expected.SetString(test.expected, 10)
		if result.Cmp(expected) != 0 {
			t.Errorf("comb(%d, %d) = %s; expected %s", test.n, test.k, result.String(), test.expected)
		}
	}
}

func TestCombEdgeCases(t *testing.T) {
	tests := []struct {
		n        int64
		k        int64
		expected string
	}{
		{5, 6, "0"}, // k > n
		{5, 5, "1"}, // k == n
	}

	for _, test := range tests {
		result := comb(big.NewInt(test.n), big.NewInt(test.k))
		expected := new(big.Int)
		expected.SetString(test.expected, 10)
		if result.Cmp(expected) != 0 {
			t.Errorf("comb(%d, %d) = %s; expected %s", test.n, test.k, result.String(), test.expected)
		}
	}
}
