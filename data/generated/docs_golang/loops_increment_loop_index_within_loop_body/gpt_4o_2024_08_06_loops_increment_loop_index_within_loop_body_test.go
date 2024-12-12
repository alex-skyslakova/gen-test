package main

import (
	"testing"
)

// TestIsPrime tests the isPrime function for various cases
func TestIsPrime(t *testing.T) {
	tests := []struct {
		input    uint64
		expected bool
	}{
		{2, true},
		{3, true},
		{4, false},
		{5, true},
		{9, false},
		{11, true},
		{15, false},
		{17, true},
		{19, true},
		{23, true},
		{25, false},
		{29, true},
		{31, true},
		{37, true},
		{41, true},
		{42, false},
		{43, true},
		{47, true},
		{53, true},
		{59, true},
	}

	for _, test := range tests {
		result := isPrime(test.input)
		if result != test.expected {
			t.Errorf("isPrime(%d) = %v; expected %v", test.input, result, test.expected)
		}
	}
}

// TestMainFunction tests the main function to ensure it outputs the correct number of primes
func TestMainFunction(t *testing.T) {
	// Capture the output of the main function
	output := captureOutput(main)

	// Check if the output contains 42 prime numbers
	expectedCount := 42
	actualCount := countPrimesInOutput(output)
	if actualCount != expectedCount {
		t.Errorf("Expected %d primes, but got %d", expectedCount, actualCount)
	}
}

// Helper function to capture the output of a function
func captureOutput(f func()) string {
	// Redirect stdout
	r, w, _ := os.Pipe()
	stdout := os.Stdout
	defer func() {
		os.Stdout = stdout
	}()
	os.Stdout = w

	// Call the function
	f()

	// Read the output
	w.Close()
	var buf bytes.Buffer
	io.Copy(&buf, r)
	return buf.String()
}

// Helper function to count the number of primes in the output
func countPrimesInOutput(output string) int {
	lines := strings.Split(output, "\n")
	count := 0
	for _, line := range lines {
		if strings.Contains(line, "n =") {
			count++
		}
	}
	return count
}
