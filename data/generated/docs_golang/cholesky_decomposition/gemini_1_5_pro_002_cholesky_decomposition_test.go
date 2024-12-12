package main

import (
	"math"
	"testing"
)

func TestCholeskyLower(t *testing.T) {
	testCases := []struct {
		name string
		a    *symmetric
		want *lower
	}{
		{
			name: "Example 1",
			a: &symmetric{3, []float64{
				25,
				15, 18,
				-5, 0, 11}},
			want: &lower{3, []float64{
				5,
				3, 3,
				-1, 1, 3}},
		},
		{
			name: "Example 2",
			a: &symmetric{4, []float64{
				18,
				22, 70,
				54, 86, 174,
				42, 62, 134, 106}},
			want: &lower{4, []float64{
				4.24264,
				5.18545, 6.56591,
				12.72792, 3.04604, 1.64974,
				9.89949, 1.62455, 1.84971, 1.39262}},
		},
		{
			name: "Single Element Matrix",
			a: &symmetric{1, []float64{
				4,
			}},
			want: &lower{1, []float64{
				2,
			}},
		},
		{
			name: "Zero Matrix 2x2", //Should technically fail, as the matrix isn't positive definite
			a: &symmetric{2, []float64{
				0,
				0, 0,
			}},
			want: &lower{2, []float64{
				0,
				0, 0,
			}},
		},

		{
			name: "Pascal Triangle 3x3",
			a:    &symmetric{3, []float64{1, 1, 1, 1, 1, 1}},
			want: &lower{3, []float64{1, 1, 1, 1, 1, 1}},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			got := tc.a.choleskyLower()

			if got.order != tc.want.order {
				t.Errorf("Order mismatch: got %d, want %d", got.order, tc.want.order)
			}

			if len(got.ele) != len(tc.want.ele) {
				t.Errorf("Length mismatch: got %d, want %d", len(got.ele), len(tc.want.ele))
			}

			for i := range got.ele {
				if math.Abs(got.ele[i]-tc.want.ele[i]) > 1e-5 { // Using a tolerance for comparison of floats
					t.Errorf("Element mismatch at index %d: got %f, want %f", i, got.ele[i], tc.want.ele[i])
				}
			}
		})
	}
}

