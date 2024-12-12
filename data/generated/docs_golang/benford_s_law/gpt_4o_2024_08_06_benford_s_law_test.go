package main

import (
	"math"
	"testing"
)

// TestFib1000Length checks if the Fib1000 function generates exactly 1000 Fibonacci numbers.
func TestFib1000Length(t *testing.T) {
	fibNumbers := Fib1000()
	if len(fibNumbers) != 1000 {
		t.Errorf("Expected 1000 Fibonacci numbers, got %d", len(fibNumbers))
	}
}

// TestFirstDigitDistribution checks if the first digit distribution of the Fibonacci numbers matches the expected distribution according to Benford's Law.
func TestFirstDigitDistribution(t *testing.T) {
	fibNumbers := Fib1000()
	var observed [9]int
	for _, v := range fibNumbers {
		observed[fmt.Sprintf("%g", v)[0]-'1']++
	}

	for i, count := range observed {
		observedProbability := float64(count) / float64(len(fibNumbers))
		expectedProbability := math.Log10(1 + 1/float64(i+1))
		if math.Abs(observedProbability-expectedProbability) > 0.05 { // Allowing a small tolerance
			t.Errorf("For digit %d, expected probability %.3f, but got %.3f", i+1, expectedProbability, observedProbability)
		}
	}
}

// TestShowFunctionOutput checks if the show function outputs the expected format.
func TestShowFunctionOutput(t *testing.T) {
	fibNumbers := Fib1000()
	// Redirect output to a buffer
	output := captureOutput(func() {
		show(fibNumbers, "Test Title")
	})

	// Check if the output contains the expected title
	if !contains(output, "Test Title") {
		t.Errorf("Expected output to contain 'Test Title', but it did not.")
	}

	// Check if the output contains the expected headers
	if !contains(output, "Digit  Observed  Predicted") {
		t.Errorf("Expected output to contain 'Digit  Observed  Predicted', but it did not.")
	}
}

// Helper function to capture output
func captureOutput(f func()) string {
	// Implementation to capture standard output
	// ...
	return ""
}

// Helper function to check if a string contains a substring
func contains(s, substr string) bool {
	return strings.Contains(s, substr)
}
