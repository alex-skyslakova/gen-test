package main

import (
	"testing"
)

// TestKPrime tests the kPrime function for various cases
func TestKPrime(t *testing.T) {
	tests := []struct {
		n, k     int
		expected bool
	}{
		{2, 1, true},  // 2 is a prime number
		{4, 2, true},  // 4 = 2 * 2, a semiprime
		{6, 2, true},  // 6 = 2 * 3, a semiprime
		{8, 3, true},  // 8 = 2 * 2 * 2, 3-almost-prime
		{30, 3, true}, // 30 = 2 * 3 * 5, 3-almost-prime
		{16, 4, true}, // 16 = 2 * 2 * 2 * 2, 4-almost-prime
		{18, 2, false}, // 18 = 2 * 3 * 3, not a 2-almost-prime
		{18, 3, true},  // 18 = 2 * 3 * 3, 3-almost-prime
	}

	for _, test := range tests {
		result := kPrime(test.n, test.k)
		if result != test.expected {
			t.Errorf("kPrime(%d, %d) = %v; expected %v", test.n, test.k, result, test.expected)
		}
	}
}

// TestGen tests the gen function for generating k-almost primes
func TestGen(t *testing.T) {
	tests := []struct {
		k, n     int
		expected []int
	}{
		{1, 10, []int{2, 3, 5, 7, 11, 13, 17, 19, 23, 29}}, // First 10 primes
		{2, 10, []int{4, 6, 9, 10, 14, 15, 21, 22, 25, 26}}, // First 10 semiprimes
		{3, 10, []int{8, 12, 18, 20, 27, 28, 30, 32, 42, 44}}, // First 10 3-almost-primes
		{4, 10, []int{16, 24, 36, 40, 54, 56, 60, 64, 72, 80}}, // First 10 4-almost-primes
		{5, 10, []int{32, 48, 72, 80, 108, 112, 120, 128, 144, 160}}, // First 10 5-almost-primes
	}

	for _, test := range tests {
		result := gen(test.k, test.n)
		for i, v := range result {
			if v != test.expected[i] {
				t.Errorf("gen(%d, %d)[%d] = %d; expected %d", test.k, test.n, i, v, test.expected[i])
			}
		}
	}
}
