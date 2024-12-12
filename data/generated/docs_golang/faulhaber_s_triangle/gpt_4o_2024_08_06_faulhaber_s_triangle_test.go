package main

import (
	"math/big"
	"testing"
)

// Test the bernoulli function
func TestBernoulli(t *testing.T) {
	tests := []struct {
		n        uint
		expected string
	}{
		{0, "1/1"},
		{1, "-1/2"},
		{2, "1/6"},
		{3, "0/1"},
		{4, "-1/30"},
	}

	for _, test := range tests {
		result := bernoulli(test.n)
		if result.RatString() != test.expected {
			t.Errorf("bernoulli(%d) = %s; expected %s", test.n, result.RatString(), test.expected)
		}
	}
}

// Test the binomial function
func TestBinomial(t *testing.T) {
	tests := []struct {
		n, k     int
		expected int64
	}{
		{5, 2, 10},
		{5, 0, 1},
		{5, 5, 1},
		{6, 2, 15},
		{0, 0, 1},
	}

	for _, test := range tests {
		result := binomial(test.n, test.k)
		if result != test.expected {
			t.Errorf("binomial(%d, %d) = %d; expected %d", test.n, test.k, result, test.expected)
		}
	}
}

// Test the faulhaberTriangle function
func TestFaulhaberTriangle(t *testing.T) {
	tests := []struct {
		p        int
		expected []string
	}{
		{0, []string{"1/1"}},
		{1, []string{"1/2", "1/2"}},
		{2, []string{"1/6", "1/2", "1/3"}},
		{3, []string{"0/1", "1/4", "1/2", "1/4"}},
		{4, []string{"-1/30", "0/1", "1/3", "1/2", "1/5"}},
	}

	for _, test := range tests {
		result := faulhaberTriangle(test.p)
		if len(result) != len(test.expected) {
			t.Errorf("faulhaberTriangle(%d) length = %d; expected %d", test.p, len(result), len(test.expected))
			continue
		}
		for i, coeff := range result {
			if coeff.RatString() != test.expected[i] {
				t.Errorf("faulhaberTriangle(%d)[%d] = %s; expected %s", test.p, i, coeff.RatString(), test.expected[i])
			}
		}
	}
}
