import (
	"testing"
)

func TestCFSqrt2(t *testing.T) {
	tests := []struct {
		nTerms int
		want   float64
	}{
		{1, 1.0},
		{2, 1.5},
		{3, 1.4},
		{5, 1.4166666666666665},
		{10, 1.4142156862745097},
		{20, 1.4142135623746899},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("nTerms=%d", tt.nTerms), func(t *testing.T) {
			got := cfSqrt2(tt.nTerms).real()
			if got != tt.want {
				t.Errorf("cfSqrt2(%d).real() = %v, want %v", tt.nTerms, got, tt.want)
			}
		})
	}
}

func TestCFNap(t *testing.T) {
	tests := []struct {
		nTerms int
		want   float64
	}{
		{1, 2.0},
		{2, 3.0},
		{3, 2.6666666666666665},
		{5, 2.7166666666666663},
		{10, 2.7182815255731922},
		{20, 2.7182818284582298},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("nTerms=%d", tt.nTerms), func(t *testing.T) {
			got := cfNap(tt.nTerms).real()
			if got != tt.want {
				t.Errorf("cfNap(%d).real() = %v, want %v", tt.nTerms, got, tt.want)
			}
		})
	}
}

func TestCFPi(t *testing.T) {
	tests := []struct {
		nTerms int
		want   float64
	}{
		{1, 3.0},
		{2, 3.1666666666666665},
		{3, 3.13953488372093},
		{5, 3.1415687403990526},
		{10, 3.141592653300476},
		{20, 3.141592653589793},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("nTerms=%d", tt.nTerms), func(t *testing.T) {
			got := cfPi(tt.nTerms).real()
			if got != tt.want {
				t.Errorf("cfPi(%d).real() = %v, want %v", tt.nTerms, got, tt.want)
			}
		})
	}
}

import "fmt"
