package main

import (
	"math"
	"testing"
)

func TestDeconv(t *testing.T) {
	h := []float64{-8, -9, -3, -1, -6, 7}
	f := []float64{-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1}
	g := []float64{24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7}

	t.Run("Test deconv(g, f) = h", func(t *testing.T) {
		result := deconv(g, f)
		if !equalSlices(result, h) {
			t.Errorf("Expected %v, got %v", h, result)
		}
	})

	t.Run("Test deconv(g, h) = f", func(t *testing.T) {
		result := deconv(g, h)
		if !equalSlices(result, f) {
			t.Errorf("Expected %v, got %v", f, result)
		}
	})
}

// Helper function to compare two slices of floats
func equalSlices(a, b []float64) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if !almostEqual(a[i], b[i]) {
			return false
		}
	}
	return true
}

// Helper function to compare two float64 values with a tolerance
func almostEqual(a, b float64) bool {
	const epsilon = 1e-9
	return math.Abs(a-b) < epsilon
}
