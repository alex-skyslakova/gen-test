package main

import (
	"testing"
)

func TestFilter(t *testing.T) {
	testCases := []struct {
		name string
		f    filter
		in   []float64
		want []float64
	}{
		{
			name: "ButterworthFilter",
			f:    bwf,
			in:   sig,
			want: []float64{
				-0.152974, -0.025740, 0.272584, 0.326732,
				0.044588, -0.267498, -0.452309, -0.154125,
				0.325745, 0.619957, 0.657559, 0.515924,
				0.389533, 0.188908, -0.004424, -0.109513,
				-0.114065, -0.030197, 0.103752, 0.229721,
			},
		},
		{
			name: "EmptyInput",
			f:    bwf,
			in:   []float64{},
			want: []float64{},
		},
		{
			name: "SingleInput",
			f:    bwf,
			in:   []float64{1.0},
			want: []float64{0.166667},
		},
		{
			name: "ZeroCoefficients",
			f:    filter{a: []float64{1}, b: []float64{0, 0, 0, 0}},
			in:   sig,
			want: make([]float64, len(sig)), // All zeros
		},
		{
			name: "UnityGain",
			f: filter{a: []float64{1}, b: []float64{1}},
			in:   sig,
			want: sig,
		},

	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			got := tc.f.filter(tc.in)
			if len(got) != len(tc.want) {
				t.Fatalf("Length mismatch: got %d, want %d", len(got), len(tc.want))
			}
			for i := range got {
				if !closeEnough(got[i], tc.want[i]) {
        t.Errorf("Index %d: got %.6f, want %.6f", i, got[i], tc.want[i])

				}
			}
		})
	}
}


const tolerance = 0.000001

func closeEnough(a, b float64) bool {
	return abs(a-b) < tolerance
}

func abs(x float64) float64 {
	if x < 0 {
		return -x
	}
	return x
}

