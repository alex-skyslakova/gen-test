package main

import (
	"testing"
)

func TestSMA(t *testing.T) {
	tests := []struct {
		period   int
		inputs   []float64
		expected []float64
	}{
		{
			period:   3,
			inputs:   []float64{1, 2, 3, 4, 5},
			expected: []float64{1.0, 1.5, 2.0, 3.0, 4.0},
		},
		{
			period:   5,
			inputs:   []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1},
			expected: []float64{1.0, 1.5, 2.0, 2.5, 3.0, 3.8, 4.2, 4.2, 3.8, 3.0},
		},
		{
			period:   1,
			inputs:   []float64{10, 20, 30},
			expected: []float64{10.0, 20.0, 30.0},
		},
		{
			period:   2,
			inputs:   []float64{5, 10, 15},
			expected: []float64{5.0, 7.5, 12.5},
		},
		{
			period:   4,
			inputs:   []float64{1, 2, 3, 4, 5, 6},
			expected: []float64{1.0, 1.5, 2.0, 2.5, 3.5, 4.5},
		},
	}

	for _, test := range tests {
		smaFunc := sma(test.period)
		for i, input := range test.inputs {
			result := smaFunc(input)
			if result != test.expected[i] {
				t.Errorf("For period %d, input %v, expected %v, but got %v", test.period, test.inputs, test.expected, result)
			}
		}
	}
}

func TestSMASeparateInstances(t *testing.T) {
	sma3 := sma(3)
	sma5 := sma(5)

	inputs := []float64{1, 2, 3, 4, 5}
	expectedSMA3 := []float64{1.0, 1.5, 2.0, 3.0, 4.0}
	expectedSMA5 := []float64{1.0, 1.5, 2.0, 2.5, 3.0}

	for i, input := range inputs {
		resultSMA3 := sma3(input)
		resultSMA5 := sma5(input)

		if resultSMA3 != expectedSMA3[i] {
			t.Errorf("SMA3: For input %v, expected %v, but got %v", inputs, expectedSMA3, resultSMA3)
		}

		if resultSMA5 != expectedSMA5[i] {
			t.Errorf("SMA5: For input %v, expected %v, but got %v", inputs, expectedSMA5, resultSMA5)
		}
	}
}
