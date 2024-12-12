package main

import (
	"testing"
)

func TestDiversityTheorem(t *testing.T) {
	tests := []struct {
		truth     float64
		preds     []float64
		expAvErr  float64
		expCrowdErr float64
		expDiv    float64
	}{
		{
			truth:     49.0,
			preds:     []float64{48, 47, 51},
			expAvErr:  2.6666666666666665,
			expCrowdErr: 0.44444444444444425,
			expDiv:    2.2222222222222223,
		},
		{
			truth:     49.0,
			preds:     []float64{48, 47, 51, 42},
			expAvErr:  12.75,
			expCrowdErr: 1.5625,
			expDiv:    11.1875,
		},
	}

	for _, tt := range tests {
		t.Run("", func(t *testing.T) {
			avErr, crowdErr, div := diversityTheorem(tt.truth, tt.preds)
			if avErr != tt.expAvErr {
				t.Errorf("expected average error %v, got %v", tt.expAvErr, avErr)
			}
			if crowdErr != tt.expCrowdErr {
				t.Errorf("expected crowd error %v, got %v", tt.expCrowdErr, crowdErr)
			}
			if div != tt.expDiv {
				t.Errorf("expected diversity %v, got %v", tt.expDiv, div)
			}
		})
	}
}
