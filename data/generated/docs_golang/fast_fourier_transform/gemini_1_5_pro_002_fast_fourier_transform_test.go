package main

import (
	"math"
	"math/cmplx"
	"testing"
)

func TestDitfft2(t *testing.T) {
	testCases := []struct {
		input []float64
		want  []complex128
	}{
		{
			input: []float64{1, 1, 1, 1, 0, 0, 0, 0},
			want: []complex128{
				complex(4, 0),
				complex(1, -2.4142135623730954),
				complex(0, 0),
				complex(1, -0.41421356237309515),
				complex(0, 0),
				complex(1, 0.41421356237309515),
				complex(0, 0),
				complex(1, 2.4142135623730954),
			},
		},
		{
			input: []float64{1, 2, 3, 4},
			want: []complex128{
				complex(10, 0),
				complex(-2, 2),
				complex(-2, 0),
				complex(-2, -2),
			},
		},
		{
			input: []float64{1},
			want:  []complex128{complex(1, 0)},
		},
		{
			input: []float64{0, 0, 0, 0},
			want:  []complex128{complex(0, 0), complex(0, 0), complex(0, 0), complex(0, 0)},
		},
		{ // Test with more varied input
			input: []float64{-2, 0.5, 3, -1, 2, 1.5, 0, -0.25},
			want:  []complex128{4.25 - 1.75i, -3.353553390593274 + 2.1213203435596424i, 1.75 - 2.25i, -0.14644660940672576 - 2.6213203435596428i, -0.75 + 3i, -0.14644660940672644 + 2.6213203435596428i, 1.75 + 2.25i, -3.353553390593273 - 2.121320343559643i},

		},
	}

	for _, tc := range testCases {
		y := make([]complex128, len(tc.input))
		ditfft2(tc.input, y, len(tc.input), 1)
		for i := range y {
			if !cmplx.ApproxEqual(y[i], tc.want[i], 0.000001) { // Added tolerance for floating-point comparison
				t.Errorf("For input %v: expected %v, got %v at index %d", tc.input, tc.want, y, i)
			}
		}

	}
}
