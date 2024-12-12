package main

import (
	"math"
	"testing"

	"gonum.org/v1/gonum/mat"
)

func TestCramersRule(t *testing.T) {
	// Define the matrix and vector as in the main function
	m := mat.NewDense(4, 4, []float64{
		2, -1, 5, 1,
		3, 2, 2, -6,
		1, 3, 3, -1,
		5, -2, -3, 3,
	})
	v := []float64{-3, -32, -47, 49}

	// Expected results calculated manually or using a reliable tool
	expected := []float64{1, -5, 2, -3}

	// Calculate the determinant of the original matrix
	d := mat.Det(m)

	// Solve using Cramer's rule
	x := make([]float64, len(v))
	b := make([]float64, len(v))
	for c := range v {
		mat.Col(b, c, m)
		m.SetCol(c, v)
		x[c] = mat.Det(m) / d
		m.SetCol(c, b)
	}

	// Compare the results with expected values
	for i := range x {
		if !almostEqual(x[i], expected[i], 1e-9) {
			t.Errorf("Expected x[%d] = %v, but got %v", i, expected[i], x[i])
		}
	}
}

// Helper function to compare floating-point numbers with a tolerance
func almostEqual(a, b, tolerance float64) bool {
	return math.Abs(a-b) <= tolerance
}
