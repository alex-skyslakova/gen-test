package main

import (
	"math/big"
	"testing"
)

func TestFact(t *testing.T) {
	testCases := []struct {
		n        *big.Int
		expected *big.Int
	}{
		{big.NewInt(0), big.NewInt(1)},
		{big.NewInt(1), big.NewInt(1)},
		{big.NewInt(5), big.NewInt(120)},
		{big.NewInt(10), big.NewInt(3628800)},
		{big.NewInt(-1), big.NewInt(0)}, 
	}

	for _, tc := range testCases {
		result := fact(tc.n)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("fact(%v) = %v; want %v", tc.n, result, tc.expected)
		}
	}
}

func TestPerm(t *testing.T) {
	testCases := []struct {
		n        *big.Int
		k        *big.Int
		expected *big.Int
	}{
		{big.NewInt(5), big.NewInt(2), big.NewInt(20)},
		{big.NewInt(10), big.NewInt(3), big.NewInt(720)},
		{big.NewInt(10), big.NewInt(0), big.NewInt(1)},
		{big.NewInt(5), big.NewInt(5), big.NewInt(120)},
		{big.NewInt(5), big.NewInt(6), big.NewInt(120)}, //nPk for k > n should be equivalent to nPn
	}

	for _, tc := range testCases {
		result := perm(tc.n, tc.k)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("perm(%v, %v) = %v; want %v", tc.n, tc.k, result, tc.expected)
		}
	}
}

func TestComb(t *testing.T) {
	testCases := []struct {
		n        *big.Int
		r        *big.Int
		expected *big.Int
	}{
		{big.NewInt(5), big.NewInt(2), big.NewInt(10)},
		{big.NewInt(10), big.NewInt(3), big.NewInt(120)},
		{big.NewInt(10), big.NewInt(0), big.NewInt(1)},
		{big.NewInt(5), big.NewInt(5), big.NewInt(1)},
		{big.NewInt(5), big.NewInt(6), big.NewInt(0)},
	}

	for _, tc := range testCases {
		result := comb(tc.n, tc.r)
		if result.Cmp(tc.expected) != 0 {
			t.Errorf("comb(%v, %v) = %v; want %v", tc.n, tc.r, result, tc.expected)
		}
	}
}

