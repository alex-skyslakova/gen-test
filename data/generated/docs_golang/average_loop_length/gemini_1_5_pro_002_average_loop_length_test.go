import (
	"math"
	"math/rand"
	"testing"
)

func Test_avg(t *testing.T) {
	rand.Seed(1) // Seed for consistent test results

	tests := []struct {
		n        int
		expected float64
		tol      float64 // Tolerance for comparison due to randomness
	}{
		{1, 1.0, 0.01},
		{2, 1.5, 0.05},
		{3, 1.88, 0.05},
		{4, 2.22, 0.05},
		{5, 2.5, 0.1},
		{10, 3.66, 0.1},
		{20, 5.27, 0.15}, // Higher tolerance for larger N due to higher variance
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := avg(tt.n)
			if math.Abs(got-tt.expected) > tt.tol {
				t.Errorf("avg(%d) = %v, want within %v of %v", tt.n, got, tt.tol, tt.expected)
			}
		})
	}
}



func Test_ana(t *testing.T) {
	tests := []struct {
		n        int
		expected float64
	}{
		{1, 1.0},
		{2, 1.5},
		{3, 1.8888888888888888},
		{4, 2.21875},
		{5, 2.5104166666666665},
		{10, 3.66015625},
		{20, 5.293570539360035},
	}
	for _, tt := range tests {
		t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
			got := ana(tt.n)
			if got != tt.expected {
				t.Errorf("ana(%d) = %v, want %v", tt.n, got, tt.expected)
			}
		})
	}
}
