import (
	"testing"
	"fmt"
)

func TestSMA(t *testing.T) {
	sma3 := sma(3)
	sma5 := sma(5)

	testCases := []struct {
		period int
		smaFunc func(float64) float64
		inputs []float64
		expected []float64
	}{
		{3, sma3, []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1}, []float64{1, 1.5, 2, 3, 4, 4.666666666666667, 4.333333333333333, 3.6666666666666665, 2.6666666666666665, 2}},
		{5, sma5, []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1}, []float64{1, 1.5, 2, 2.5, 3, 3.8, 4, 4, 3.6, 3}},
		{1, sma(1), []float64{1, 2, 3, 4, 5}, []float64{1, 2, 3, 4, 5}}, // Test period of 1
		{0, sma(0), []float64{1, 2, 3}, []float64{0, 0, 0}},             // Test period of 0
		{2, sma(2), []float64{}, []float64{}},                           // Test empty input
	}


	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Period %d", tc.period), func(t *testing.T) {
			results := make([]float64, len(tc.inputs))
			for i, input := range tc.inputs {
				results[i] = tc.smaFunc(input)
			}

			for i, result := range results {
				if fmt.Sprintf("%f", result) != fmt.Sprintf("%f", tc.expected[i]) {
                        // Using fmt.Sprintf for comparing floating point to handle minute differences.

					t.Errorf("For input %v, expected %.6f but got %.6f", tc.inputs[:i+1], tc.expected[i], result)
				}
			}
		})
	}
}

