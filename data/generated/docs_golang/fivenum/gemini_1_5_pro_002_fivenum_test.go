package main

import (
	"reflect"
	"testing"
)

func TestFivenum(t *testing.T) {
	testCases := []struct {
		input []float64
		want  [5]float64
	}{
		{
			input: []float64{36, 40, 7, 39, 41, 15},
			want:  [5]float64{7, 15, 37.5, 40, 41},
		},
		{
			input: []float64{15, 6, 42, 41, 7, 36, 49, 40, 39, 47, 43},
			want:  [5]float64{6, 15, 39, 42, 49},
		},
		{
			input: []float64{
				0.14082834, 0.09748790, 1.73131507, 0.87636009, -1.95059594,
				0.73438555, -0.03035726, 1.46675970, -0.74621349, -0.72588772,
				0.63905160, 0.61501527, -0.98983780, -1.00447874, -0.62759469,
				0.66206163, 1.04312009, -0.10305385, 0.75775634, 0.32566578,
			},
			want: [5]float64{-1.95059594, -0.736050605, 0.37566358, 0.746068945, 1.73131507},
		},
		{
			input: []float64{1, 2, 3, 4, 5}, // odd length
			want:  [5]float64{1, 2, 3, 4, 5},
		},
		{
			input: []float64{1, 2, 3, 4}, // even length
			want:  [5]float64{1, 1.5, 2.5, 3.5, 4},
		},
		{
			input: []float64{1}, // single element
			want:  [5]float64{1, 1, 1, 1, 1},
		},

		{input: []float64{}, want: [5]float64{0, 0, 0, 0, 0}}, // Empty slice -  added to cover edge case, although behavior for empty slice isn't specifically defined in prompt
	}

	for _, tc := range testCases {
		got := fivenum(tc.input)
		if !reflect.DeepEqual(got, tc.want) {
			t.Errorf("fivenum(%v) = %v; want %v", tc.input, got, tc.want)
		}
	}
}

