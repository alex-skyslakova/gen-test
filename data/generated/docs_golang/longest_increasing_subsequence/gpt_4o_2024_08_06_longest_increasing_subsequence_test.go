package main

import (
	"reflect"
	"testing"
)

func TestLIS(t *testing.T) {
	tests := []struct {
		input    []int
		expected []int
	}{
		{
			input:    []int{3, 2, 6, 4, 5, 1},
			expected: []int{2, 4, 5}, // One possible LIS
		},
		{
			input:    []int{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},
			expected: []int{0, 2, 6, 9, 11, 15}, // One possible LIS
		},
		{
			input:    []int{10, 22, 9, 33, 21, 50, 41, 60, 80},
			expected: []int{10, 22, 33, 50, 60, 80}, // One possible LIS
		},
		{
			input:    []int{3, 10, 2, 1, 20},
			expected: []int{3, 10, 20}, // One possible LIS
		},
		{
			input:    []int{3, 2},
			expected: []int{2}, // One possible LIS
		},
		{
			input:    []int{50, 3, 10, 7, 40, 80},
			expected: []int{3, 7, 40, 80}, // One possible LIS
		},
		{
			input:    []int{},
			expected: []int{}, // Empty input should return empty LIS
		},
	}

	for _, test := range tests {
		result := lis(test.input)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("For input %v, expected %v, but got %v", test.input, test.expected, result)
		}
	}
}
