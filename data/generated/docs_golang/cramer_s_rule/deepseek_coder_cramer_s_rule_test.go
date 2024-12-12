package main

import (
    "math"
    "testing"

    "gonum.org/v1/gonum/mat"
)

func TestCramersRule(t *testing.T) {
    // Expected solution for the given system of equations
    expected := []float64{2, -12, -4, 1}

    // Calculate the solution using the provided code
    x := make([]float64, len(v))
    b := make([]float64, len(v))
    d := mat.Det(m)
    for c := range v {
        mat.Col(b, c, m)
        m.SetCol(c, v)
        x[c] = mat.Det(m) / d
        m.SetCol(c, b)
    }

    // Compare the calculated solution with the expected solution
    for i := range expected {
        if math.Abs(x[i]-expected[i]) > 1e-9 {
            t.Errorf("Expected x[%d] = %f, but got %f", i, expected[i], x[i])
        }
    }
}
