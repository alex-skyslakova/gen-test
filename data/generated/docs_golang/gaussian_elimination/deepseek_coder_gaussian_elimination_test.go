package main

import (
    "math"
    "testing"
)

func TestGaussPartial(t *testing.T) {
    tests := []struct {
        name string
        a    [][]float64
        b    []float64
        x    []float64
        err  error
    }{
        {
            name: "Test Case 1",
            a: [][]float64{
                {1.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                {1.00, 0.63, 0.39, 0.25, 0.16, 0.10},
                {1.00, 1.26, 1.58, 1.98, 2.49, 3.13},
                {1.00, 1.88, 3.55, 6.70, 12.62, 23.80},
                {1.00, 2.51, 6.32, 15.88, 39.90, 100.28},
                {1.00, 3.14, 9.87, 31.01, 97.41, 306.02},
            },
            b: []float64{-0.01, 0.61, 0.91, 0.99, 0.60, 0.02},
            x: []float64{-0.01, 1.602790394502114, -1.6132030599055613,
                1.2454941213714368, -0.4909897195846576, 0.065760696175232},
            err: nil,
        },
        {
            name: "Singular Matrix",
            a: [][]float64{
                {1.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                {1.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                {1.00, 1.26, 1.58, 1.98, 2.49, 3.13},
                {1.00, 1.88, 3.55, 6.70, 12.62, 23.80},
                {1.00, 2.51, 6.32, 15.88, 39.90, 100.28},
                {1.00, 3.14, 9.87, 31.01, 97.41, 306.02},
            },
            b:   []float64{-0.01, 0.61, 0.91, 0.99, 0.60, 0.02},
            x:   nil,
            err: errors.New("singular"),
        },
        {
            name: "Identity Matrix",
            a: [][]float64{
                {1.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                {0.00, 1.00, 0.00, 0.00, 0.00, 0.00},
                {0.00, 0.00, 1.00, 0.00, 0.00, 0.00},
                {0.00, 0.00, 0.00, 1.00, 0.00, 0.00},
                {0.00, 0.00, 0.00, 0.00, 1.00, 0.00},
                {0.00, 0.00, 0.00, 0.00, 0.00, 1.00},
            },
            b:   []float64{1.0, 2.0, 3.0, 4.0, 5.0, 6.0},
            x:   []float64{1.0, 2.0, 3.0, 4.0, 5.0, 6.0},
            err: nil,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            x, err := GaussPartial(tt.a, tt.b)
            if err != nil && err.Error() != tt.err.Error() {
                t.Errorf("expected error %v, got %v", tt.err, err)
            }
            if err == nil && tt.err != nil {
                t.Errorf("expected error %v, got nil", tt.err)
            }
            if err == nil {
                for i, xi := range x {
                    if math.Abs(tt.x[i]-xi) > ε {
                        t.Errorf("expected x[%d] = %f, got %f", i, tt.x[i], xi)
                    }
                }
            }
        })
    }
}
