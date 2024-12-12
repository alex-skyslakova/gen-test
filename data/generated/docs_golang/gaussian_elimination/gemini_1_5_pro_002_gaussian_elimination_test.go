package main

import (
	"errors"
	"testing"
)

func TestGaussPartial(t *testing.T) {
	tests := []struct {
		name    string
		a       [][]float64
		b       []float64
		want    []float64
		wantErr error
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
			want: []float64{-0.01, 1.602790394502114, -1.6132030599055613,
				1.2454941213714368, -0.4909897195846576, 0.065760696175232},
			wantErr: nil,
		},
		{
			name: "Singular Matrix",
			a: [][]float64{
				{1, 2, 3},
				{2, 4, 6},
				{1, 1, 1},
			},
			b:       []float64{1, 2, 3},
			want:    nil,
			wantErr: errors.New("singular"),
		},
		{
			name: "2x2 Matrix",
			a: [][]float64{
				{2, 1},
				{1, -1},
			},
			b:    []float64{8, 0},
			want: []float64{3, 2},
			wantErr: nil,

		},
        {
			name: "3x3 Matrix with zero pivot after swap",
			a: [][]float64{
				{0, 1, 1},
				{1, 0, 1},
				{1, 1, 0},
			},
			b:    []float64{2, 2, 2},
			want: []float64{1, 1, 1},
			wantErr: nil,

		},
        {
            name: "Empty input",
            a: [][]float64{},
            b: []float64{},
            want: []float64{},
            wantErr: nil,
        },

	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, err := GaussPartial(tt.a, tt.b)
			if !errors.Is(err, tt.wantErr) {
				t.Errorf("GaussPartial() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if tt.want != nil { // Check for non-nil want to avoid panics
				for i, v := range got {
				    if math.Abs(v - tt.want[i]) > ε {
				        t.Errorf("GaussPartial() = %v, want %v", got, tt.want)
				        return
                    }


				}


			}


		})
	}
}

