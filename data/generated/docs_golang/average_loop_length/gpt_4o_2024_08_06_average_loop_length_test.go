package main

import (
	"math"
	"testing"
)

// TestAnaFunction tests the ana function for known values.
func TestAnaFunction(t *testing.T) {
	tests := []struct {
		n        int
		expected float64
	}{
		{1, 1.0},
		{2, 1.5},
		{3, 1.8889},
		{4, 2.2188},
		{5, 2.5104},
		{6, 2.7747},
		{7, 3.0181},
		{8, 3.2450},
		{9, 3.4583},
		{10, 3.6602},
	}

	for _, tt := range tests {
		t.Run("AnaFunction", func(t *testing.T) {
			result := ana(tt.n)
			if math.Abs(result-tt.expected) > 0.0001 {
				t.Errorf("ana(%d) = %f; want %f", tt.n, result, tt.expected)
			}
		})
	}
}

// TestAvgFunction tests the avg function to ensure it runs without error.
// Note: This test checks if the function runs and returns a float, but due to randomness,
// it doesn't check for exact values.
func TestAvgFunction(t *testing.T) {
	for n := 1; n <= 10; n++ {
		t.Run("AvgFunction", func(t *testing.T) {
			result := avg(n)
			if result <= 0 {
				t.Errorf("avg(%d) = %f; want > 0", n, result)
			}
		})
	}
}
