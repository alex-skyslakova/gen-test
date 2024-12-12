package main

import (
	"testing"
)

// TestIsPrime tests the isPrime function.
func TestIsPrime(t *testing.T) {
	tests := []struct {
		input    int
		expected bool
	}{
		{1, false},
		{2, true},
		{3, true},
		{4, false},
		{5, true},
		{9, false},
		{11, true},
		{13, true},
		{17, true},
		{19, true},
		{20, false},
		{23, true},
		{29, true},
		{31, true},
		{37, true},
		{41, true},
		{43, true},
		{47, true},
		{53, true},
		{59, true},
		{61, true},
		{67, true},
		{71, true},
		{73, true},
		{79, true},
		{83, true},
		{89, true},
		{97, true},
		{101, true},
		{103, true},
		{107, true},
		{109, true},
		{113, true},
		{127, true},
		{131, true},
		{137, true},
		{139, true},
		{149, true},
		{151, true},
		{157, true},
		{163, true},
		{167, true},
		{173, true},
		{179, true},
		{181, true},
		{191, true},
		{193, true},
		{197, true},
		{199, true},
		{211, true},
		{223, true},
		{227, true},
		{229, true},
		{233, true},
		{239, true},
		{241, true},
		{251, true},
		{257, true},
		{263, true},
		{269, true},
		{271, true},
		{277, true},
		{281, true},
		{283, true},
		{293, true},
		{307, true},
		{311, true},
		{313, true},
		{317, true},
		{331, true},
		{337, true},
		{347, true},
		{349, true},
		{353, true},
		{359, true},
		{367, true},
		{373, true},
		{379, true},
		{383, true},
		{389, true},
		{397, true},
		{401, true},
		{409, true},
		{419, true},
		{421, true},
		{431, true},
		{433, true},
		{439, true},
		{443, true},
		{449, true},
		{457, true},
		{461, true},
		{463, true},
		{467, true},
		{479, true},
		{487, true},
		{491, true},
		{499, true},
	}

	for _, test := range tests {
		result := isPrime(test.input)
		if result != test.expected {
			t.Errorf("isPrime(%d) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

// TestSumDigits tests the sumDigits function.
func TestSumDigits(t *testing.T) {
	tests := []struct {
		input    int
		expected int
	}{
		{0, 0},
		{1, 1},
		{12, 3},
		{123, 6},
		{456, 15},
		{789, 24},
		{999, 27},
		{1001, 2},
		{1234, 10},
		{5678, 26},
	}

	for _, test := range tests {
		result := sumDigits(test.input)
		if result != test.expected {
			t.Errorf("sumDigits(%d) = %d; expected %d", test.input, result, test.expected)
		}
	}
}

// TestAdditivePrimes tests the main function for finding additive primes less than 500.
func TestAdditivePrimes(t *testing.T) {
	expectedAdditivePrimes := []int{2, 3, 5, 7, 11, 17, 23, 29, 31, 37, 41, 43, 47, 53, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499}
	var actualAdditivePrimes []int

	for i := 2; i < 500; i++ {
		if isPrime(i) && isPrime(sumDigits(i)) {
			actualAdditivePrimes = append(actualAdditivePrimes, i)
		}
	}

	if len(actualAdditivePrimes) != len(expectedAdditivePrimes) {
		t.Errorf("Expected %d additive primes, but got %d", len(expectedAdditivePrimes), len(actualAdditivePrimes))
	}

	for i, prime := range expectedAdditivePrimes {
		if actualAdditivePrimes[i] != prime {
			t.Errorf("Expected additive prime %d at index %d, but got %d", prime, i, actualAdditivePrimes[i])
		}
	}
}
