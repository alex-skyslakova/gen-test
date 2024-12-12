package main

import (
	"testing"
	"math/big"
	"strings"
)

// TestCommatize tests the commatize function for various cases.
func TestCommatize(t *testing.T) {
	tests := []struct {
		input    uint64
		expected string
	}{
		{1000, "1,000"},
		{1000000, "1,000,000"},
		{123456789, "123,456,789"},
		{0, "0"},
		{999, "999"},
	}

	for _, test := range tests {
		result := commatize(test.input)
		if result != test.expected {
			t.Errorf("commatize(%d) = %s; expected %s", test.input, result, test.expected)
		}
	}
}

// TestCubanPrimes tests the generation of cuban primes.
func TestCubanPrimes(t *testing.T) {
	var z big.Int
	var cube1, cube2, diff uint64
	cubans := make([]string, 200)
	cube1 = 1
	count := 0
	var cube100k uint64

	for i := 1; ; i++ {
		j := i + 1
		cube2 = uint64(j * j * j)
		diff = cube2 - cube1
		z.SetUint64(diff)
		if z.ProbablyPrime(0) {
			if count < 200 {
				cubans[count] = commatize(diff)
			}
			count++
			if count == 100000 {
				cube100k = diff
				break
			}
		}
		cube1 = cube2
	}

	// Verify the first 200 cuban primes
	expectedFirst200 := []string{
		"7", "19", "37", "61", "127", "271", "397", "547", "757", "907",
		"1,081", "1,487", "1,867", "2,137", "2,197", "2,617", "3,097", "3,307", "3,757", "4,081",
		// Add more expected values as needed
	}

	for i, expected := range expectedFirst200 {
		if cubans[i] != expected {
			t.Errorf("First 200 cuban primes mismatch at index %d: got %s, expected %s", i, cubans[i], expected)
		}
	}

	// Verify the 100,000th cuban prime
	expected100k := "1,215,716,827,157"
	if commatize(cube100k) != expected100k {
		t.Errorf("100,000th cuban prime mismatch: got %s, expected %s", commatize(cube100k), expected100k)
	}
}

// TestOutputFormat tests the output format of the cuban primes.
func TestOutputFormat(t *testing.T) {
	var z big.Int
	var cube1, cube2, diff uint64
	cubans := make([]string, 200)
	cube1 = 1
	count := 0
	var cube100k uint64

	for i := 1; ; i++ {
		j := i + 1
		cube2 = uint64(j * j * j)
		diff = cube2 - cube1
		z.SetUint64(diff)
		if z.ProbablyPrime(0) {
			if count < 200 {
				cubans[count] = commatize(diff)
			}
			count++
			if count == 100000 {
				cube100k = diff
				break
			}
		}
		cube1 = cube2
	}

	// Check the format of the first 200 cuban primes
	output := strings.Join(cubans, ", ")
	if !strings.Contains(output, "7, 19, 37") {
		t.Errorf("Output format for first 200 cuban primes is incorrect: %s", output)
	}

	// Check the format of the 100,000th cuban prime
	if commatize(cube100k) != "1,215,716,827,157" {
		t.Errorf("Output format for 100,000th cuban prime is incorrect: %s", commatize(cube100k))
	}
}
