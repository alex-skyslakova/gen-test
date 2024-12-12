package main

import (
	"reflect"
	"testing"
)

func TestLIS(t *testing.T) {
	testCases := []struct {
		name     string
		input    []int
		expected []int
	}{
		{
			name:     "Example 1",
			input:    []int{3, 2, 6, 4, 5, 1},
			expected: []int{2, 4, 5},
		},
		{
			name:     "Example 2",
			input:    []int{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},
			expected: []int{0, 2, 6, 9, 11, 15},
		},
		{
			name:     "Empty List",
			input:    []int{},
			expected: []int{},
		},
		{
			name:     "Single Element List",
			input:    []int{1},
			expected: []int{1},
		},
		{
			name:     "All Elements Increasing",
			input:    []int{1, 2, 3, 4, 5},
			expected: []int{1, 2, 3, 4, 5},
		},
		{
			name:     "All Elements Decreasing",
			input:    []int{5, 4, 3, 2, 1},
			expected: []int{5},
		},
		{
			name:     "Mixed Elements",
			input:    []int{10, 22, 9, 33, 21, 50, 41, 60, 80},
			expected: []int{10, 22, 33, 41, 60, 80},
		},
	}

	for _, tc := range testCases {
		t.Run(tc.name, func(t *testing.T) {
			result := lis(tc.input)
			if !reflect.DeepEqual(result, tc.expected) {
				t.Errorf("Expected %v, but got %v", tc.expected, result)
			}
		})
	}
}
