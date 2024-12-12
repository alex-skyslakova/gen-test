package main

import (
	"fmt"
	"math"
	"testing"
)

func TestShow(t *testing.T) {
	testCases := []struct {
		input    []float64
		title    string
		expected [9]float64
	}{
		{
			input: []float64{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
			title: "Test Case 1",
			expected: [9]float64{
				0.1111111111111111, // Leading 1s (1, 10-19)
				0.05555555555555555, // Leading 2s (2, 20)
				0,                   // ...
				0,
				0,
				0,
				0,
				0,
				0,
			},
		},
		{
			input: []float64{100, 200, 300, 400, 500, 600, 700, 800, 900},
			title: "Test Case 2",
			expected: [9]float64{
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
			}, // Because string conversion of float64 values like 100.0 result in "100" not "1e+02"
		},
		{
			input: []float64{1e2, 2e2, 3e2, 4e2, 5e2, 6e2, 7e2, 8e2, 9e2},
			title: "Test Case 3",
			expected: [9]float64{
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
			}, // Similar issue as above, uses scientific notation
		},
		{
			input:    []float64{},
			title:    "Empty Slice",
			expected: [9]float64{0, 0, 0, 0, 0, 0, 0, 0, 0}, // Handle empty input
		},
	}

	for _, tc := range testCases {
		t.Run(tc.title, func(t *testing.T) {
			// Capture the output of show()
			captureStdout := func(f func([]float64, string)) string {
				originalStdout := fmt.Printf // need to restore it after the test!
				defer func() {
					fmt.Printf = originalStdout
				}()
				var output string
				fmt.Printf = func(format string, a ...interface{}) (n int, err error) {
					output += fmt.Sprintf(format, a...)
					return len(output), nil
				}
				f(tc.input, tc.title)
				return output
			}

			output := captureStdout(show)

			for i := 0; i < 9; i++ {
				expectedPercentage := tc.expected[i]

				// Format the expected string for comparison including a small tolerance because float64 is not precise
				expectedString := fmt.Sprintf(" %d    %0.3f", i+1, expectedPercentage)

				if !containsWithTolerance(output, expectedString, 0.001) {

					t.Errorf("For digit %d, expected '%s' (with tolerance), but got: %s", i+1, expectedString, output)
				}
			}
		})

	}
}

// Helper function to check if a string contains a float with a given tolerance
func containsWithTolerance(s, substr string, tolerance float64) bool {
	var actual float64
	n, err := fmt.Sscanf(s, substr+"%f", &actual) // Check anywhere in the string
	if n == 1 && err == nil {
		expected := getFloatFromString(substr)
		return math.Abs(actual-expected) <= tolerance
	}
	return false
}

// Extract the float value from a string designed for more robust comparisons
func getFloatFromString(s string) float64 {
	var f float64
	fmt.Sscanf(s, "%f", &f)
	return f
}

// Test for Fib1000 - could be expanded with specific checks
func TestFib1000(t *testing.T) {
	fibs := Fib1000()
	if len(fibs) != 1000 {
		t.Error("Fib1000 did not return 1000 numbers")
	}
	if fibs[0] != 1 || fibs[1] != 1 || fibs[2] != 2 {
		t.Error("First few Fibonacci numbers are incorrect")
	}

}


