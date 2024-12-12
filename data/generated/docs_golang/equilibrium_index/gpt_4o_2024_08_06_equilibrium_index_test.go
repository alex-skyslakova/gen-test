package main

import (
	"testing"
	"reflect"
)

func TestEx(t *testing.T) {
	tests := []struct {
		input    []int32
		expected []int
	}{
		{
			input:    []int32{-7, 1, 5, 2, -4, 3, 0},
			expected: []int{3, 6},
		},
		{
			input:    []int32{1, 2, 3, 4, 5},
			expected: []int{},
		},
		{
			input:    []int32{0, 0, 0, 0, 0},
			expected: []int{0, 1, 2, 3, 4},
		},
		{
			input:    []int32{1, -1, 0},
			expected: []int{2},
		},
		{
			input:    []int32{10},
			expected: []int{0},
		},
		{
			input:    []int32{},
			expected: []int{},
		},
		{
			input:    []int32{1, 2, 1, 2, 1, 2, 1},
			expected: []int{3},
		},
	}

	for _, test := range tests {
		result := ex(test.input)
		if !reflect.DeepEqual(result, test.expected) {
			t.Errorf("For input %v, expected %v, but got %v", test.input, test.expected, result)
		}
	}
}
