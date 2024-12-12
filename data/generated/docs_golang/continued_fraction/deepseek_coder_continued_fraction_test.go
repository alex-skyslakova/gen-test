package main

import (
    "math"
    "testing"
)

func TestCfSqrt2(t *testing.T) {
    const tolerance = 1e-10
    const expected = math.Sqrt(2)
    result := cfSqrt2(20).real()
    if math.Abs(result-expected) > tolerance {
        t.Errorf("cfSqrt2(20) = %f; expected %f", result, expected)
    }
}

func TestCfNap(t *testing.T) {
    const tolerance = 1e-10
    const expected = math.E
    result := cfNap(20).real()
    if math.Abs(result-expected) > tolerance {
        t.Errorf("cfNap(20) = %f; expected %f", result, expected)
    }
}

func TestCfPi(t *testing.T) {
    const tolerance = 1e-10
    const expected = math.Pi
    result := cfPi(20).real()
    if math.Abs(result-expected) > tolerance {
        t.Errorf("cfPi(20) = %f; expected %f", result, expected)
    }
}

func TestCfReal(t *testing.T) {
    tests := []struct {
        name     string
        cf       cf
        expected float64
    }{
        {
            name:     "sqrt2",
            cf:       cfSqrt2(20),
            expected: math.Sqrt(2),
        },
        {
            name:     "nap",
            cf:       cfNap(20),
            expected: math.E,
        },
        {
            name:     "pi",
            cf:       cfPi(20),
            expected: math.Pi,
        },
    }

    const tolerance = 1e-10
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := tt.cf.real()
            if math.Abs(result-tt.expected) > tolerance {
                t.Errorf("%s: real() = %f; expected %f", tt.name, result, tt.expected)
            }
        })
    }
}
