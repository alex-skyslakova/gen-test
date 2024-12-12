package main

import (
    "math"
    "testing"
)

func TestGaussPartial(t *testing.T) {
    tests := []struct {
        name    string
        a       [][]float64
        b       []float64
        x       []float64
        wantErr bool
    }{
        {
            name: "Common RC example",
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
            wantErr: false,
        },
        {
            name: "Singular matrix",
            a: [][]float64{
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9},
            },
            b:       []float64{1, 2, 3},
            x:       nil,
            wantErr: true,
        },
        {
            name: "Simple 2x2 matrix",
            a: [][]float64{
                {2, 3},
                {1, 2},
            },
            b:       []float64{8, 5},
            x:       []float64{1, 2},
            wantErr: false,
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            got, err := GaussPartial(tt.a, tt.b)
            if (err != nil) != tt.wantErr {
                t.Errorf("GaussPartial() error = %v, wantErr %v", err, tt.wantErr)
                return
            }
            if !tt.wantErr {
                for i := range got {
                    if math.Abs(got[i]-tt.x[i]) > ε {
                        t.Errorf("GaussPartial() = %v, want %v", got, tt.x)
                    }
                }
            }
        })
    }
}
