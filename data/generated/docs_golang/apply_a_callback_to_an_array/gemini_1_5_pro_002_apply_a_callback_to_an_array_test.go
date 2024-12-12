package main

import (
	"fmt"
	"testing"
)

func TestApplyCallback(t *testing.T) {
	testCases := []struct {
		input []int
		expected []int
	}{
		{[]int{1, 2, 3, 4, 5}, []int{1, 4, 9, 16, 25}},
		{[]int{-1, -2, -3}, []int{1, 4, 9}},
		{[]int{0}, []int{0}},
		{[]int{}, []int{}},  // Empty input
	}

	for _, tc := range testCases {
		t.Run(fmt.Sprintf("Input: %v", tc.input), func(t *testing.T) {
			actual := applyCallback(tc.input)
			if !slicesEqual(actual, tc.expected) {
				t.Errorf("Expected: %v, Got: %v", tc.expected, actual)
			}
		})
	}
}


// Helper function to compare slices since we can't directly compare slices with ==
func slicesEqual(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}

// Function under test - extracted from main to make it testable.
func applyCallback(input []int) []int {
	var result []int
	for _, i := range input {
		result = append(result, i*i) // Applying the square function.
	}
	return result
}


