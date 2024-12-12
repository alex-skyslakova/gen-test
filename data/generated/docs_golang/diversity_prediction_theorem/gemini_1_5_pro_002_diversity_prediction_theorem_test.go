package main

import (
	"testing"
	"fmt"
)

func TestAverageSquareDiff(t *testing.T) {
	tests := []struct {
		f     float64
		preds []float64
		want  float64
	}{
		{49.0, []float64{48, 47, 51}, 2.0},
		{49.0, []float64{48, 47, 51, 42}, 10.5},
		{0, []float64{}, 0}, // Empty input
		{10, []float64{10, 10, 10}, 0}, // All same values
		{5, []float64{0, 10}, 25},
	}

	for _, tt := range tests {
		t.Run(fmt.Sprintf("f=%.1f, preds=%v", tt.f, tt.preds), func(t *testing.T) {
			got := averageSquareDiff(tt.f, tt.preds)
			if got != tt.want {
				t.Errorf("averageSquareDiff() = %v, want %v", got, tt.want)
			}
		})
	}
}

func TestDiversityTheorem(t *testing.T) {
	tests := []struct {
		truth float64
		preds []float64
		wantAvErr, wantCrowdErr, wantDiv float64
	}{
		{49.0, []float64{48, 47, 51}, 2.0, 1.0, 2.0},
		{49.0, []float64{48, 47, 51, 42}, 10.5, 16.0, 5.5},
		{49, []float64{}, 0, 2401, 0}, // Empty input
		{10, []float64{10, 10, 10}, 0, 0, 0}, // All same values
		{5, []float64{0, 10}, 25, 0, 25}, 
	}

    for _, tt := range tests {
		t.Run(fmt.Sprintf("truth=%.1f, preds=%v", tt.truth, tt.preds), func(t *testing.T) {
			gotAvErr, gotCrowdErr, gotDiv := diversityTheorem(tt.truth, tt.preds)
			if gotAvErr != tt.wantAvErr {
				t.Errorf("diversityTheorem() avErr = %v, want %v", gotAvErr, tt.wantAvErr)
			}
			if gotCrowdErr != tt.wantCrowdErr {
				t.Errorf("diversityTheorem() crowdErr = %v, want %v", gotCrowdErr, tt.wantCrowdErr)
			}
			if gotDiv != tt.wantDiv {
				t.Errorf("diversityTheorem() div = %v, want %v", gotDiv, tt.wantDiv)
			}
		})
	}
}

