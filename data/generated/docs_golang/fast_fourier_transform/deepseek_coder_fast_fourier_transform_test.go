package main

import (
    "math"
    "math/cmplx"
    "testing"
)

func TestDitfft2(t *testing.T) {
    tests := []struct {
        name     string
        input    []float64
        expected []complex128
    }{
        {
            name:     "Single element",
            input:    []float64{1},
            expected: []complex128{complex(1, 0)},
        },
        {
            name:     "Two elements",
            input:    []float64{1, 0},
            expected: []complex128{complex(1, 0), complex(1, 0)},
        },
        {
            name:     "Four elements",
            input:    []float64{1, 1, 1, 1},
            expected: []complex128{complex(4, 0), complex(0, 0), complex(0, 0), complex(0, 0)},
        },
        {
            name:     "Eight elements",
            input:    []float64{1, 1, 1, 1, 0, 0, 0, 0},
            expected: []complex128{complex(4, 0), complex(1, -2.4142), complex(0, 0), complex(1, -0.4142), complex(0, 0), complex(1, 0.4142), complex(0, 0), complex(1, 2.4142)},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            y := make([]complex128, len(tt.input))
            ditfft2(tt.input, y, len(tt.input), 1)

            for i := range tt.expected {
                if cmplx.Abs(y[i]-tt.expected[i]) > 1e-4 {
                    t.Errorf("Expected %v, got %v", tt.expected[i], y[i])
                }
            }
        })
    }
}

func TestDitfft2EmptyInput(t *testing.T) {
    x := []float64{}
    y := make([]complex128, len(x))
    ditfft2(x, y, len(x), 1)

    if len(y) != 0 {
        t.Errorf("Expected empty output, got %v", y)
    }
}

func TestDitfft2NonPowerOfTwo(t *testing.T) {
    x := []float64{1, 1, 1}
    y := make([]complex128, len(x))
    ditfft2(x, y, len(x), 1)

    // The function should not panic, but the output is not guaranteed to be correct
    // since the input length is not a power of two.
    // We just check that the function does not panic.
}
