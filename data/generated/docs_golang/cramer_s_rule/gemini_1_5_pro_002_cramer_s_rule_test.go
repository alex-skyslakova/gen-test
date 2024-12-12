package main

import (
	"testing"

	"gonum.org/v1/gonum/mat"
)

func TestCramersRule(t *testing.T) {
	testCases := []struct {
		matrix  *mat.Dense
		vector  []float64
		expected []float64
	}{
		{
			matrix: mat.NewDense(4, 4, []float64{
				2, -1, 5, 1,
				3, 2, 2, -6,
				1, 3, 3, -1,
				5, -2, -3, 3,
			}),
			vector:  []float64{-3, -32, -47, 49},
			expected: []float64{2, -5, 3, -7}, // Solution for the given example
		},
		{
			matrix: mat.NewDense(3, 3, []float64{
				1, 1, 1,
				2, -1, 3,
				3, 2, -1,
			}),
			vector:  []float64{6, 1, -2},
			expected: []float64{1, 2, 3}, // Simple 3x3 system
		},
		{
			matrix: mat.NewDense(2, 2, []float64{
				2, 1,
				1, -1,
			}),
			vector:  []float64{3, 0},
			expected: []float64{1, 1}, // 2x2 system
		}, {
			matrix: mat.NewDense(3, 3, []float64{
				1, 2, 3,
				4, 5, 6,
				7, 8, 9,
			}),
			vector:  []float64{1, 2, 3},
			expected: []float64{0, 0, 0},
		},
	}

	for _, tc := range testCases {
		actual := solveUsingCramersRule(tc.matrix, tc.vector)

		if len(actual) != len(tc.expected) {
			t.Fatalf("Expected solution vector length %d, got %d", len(tc.expected), len(actual))
		}

		for i := range tc.expected {
			if !almostEqual(actual[i], tc.expected[i]) {
				t.Errorf("For matrix %v and vector %v, expected %v at index %d, got %v", tc.matrix, tc.vector, tc.expected[i], i, actual[i])
			}
		}
	}
}



func solveUsingCramersRule(m *mat.Dense, v []float64) []float64 {
	x := make([]float64, len(v))
	b := make([]float64, len(v))
	d := mat.Det(m)
	if d == 0 {
		return []float64{} // or handle the error as needed
	}

	for c := range v {
		mat.Col(b, c, m)
		m.SetCol(c, v)
		x[c] = mat.Det(m) / d
		m.SetCol(c, b)
	}
	return x
}



const epsilon = 1e-9

func almostEqual(a, b float64) bool {
	return abs(a-b) <= epsilon
}

func abs(x float64) float64 {
	if x < 0 {
		return -x
	}
	return x
}

