package main

import (
	"testing"
	"reflect"
)

// Assume this is the function we want to test
func applyFunctionToSlice(slice []int, fn func(int) int) []int {
	result := make([]int, len(slice))
	for i, v := range slice {
		result[i] = fn(v)
	}
	return result
}

func TestApplyFunctionToSlice(t *testing.T) {
	tests := []struct {
		name     string
		input    []int
		fn       func(int) int
		expected []int
	}{
		{
			name:     "Square function",
			input:    []int{1, 2, 3, 4, 5},
			fn:       func(x int) int { return x * x },
			expected: []int{1, 4, 9, 16, 25},
		},
		{
			name:     "Double function",
			input:    []int{1, 2, 3, 4, 5},
			fn:       func(x int) int { return x * 2 },
			expected: []int{2, 4, 6, 8, 10},
		},
		{
			name:     "Identity function",
			input:    []int{1, 2, 3, 4, 5},
			fn:       func(x int) int { return x },
			expected: []int{1, 2, 3, 4, 5},
		},
		{
			name:     "Empty slice",
			input:    []int{},
			fn:       func(x int) int { return x * x },
			expected: []int{},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := applyFunctionToSlice(tt.input, tt.fn)
			if !reflect.DeepEqual(result, tt.expected) {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}
