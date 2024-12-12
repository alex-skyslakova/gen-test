package main

import (
	"reflect"
	"testing"
)

func TestCart2(t *testing.T) {
	tests := []struct {
		name     string
		a, b     []int
		expected []pair
	}{
		{
			name:     "Test {1, 2} × {3, 4}",
			a:        []int{1, 2},
			b:        []int{3, 4},
			expected: []pair{{1, 3}, {1, 4}, {2, 3}, {2, 4}},
		},
		{
			name:     "Test {3, 4} × {1, 2}",
			a:        []int{3, 4},
			b:        []int{1, 2},
			expected: []pair{{3, 1}, {3, 2}, {4, 1}, {4, 2}},
		},
		{
			name:     "Test {1, 2} × {}",
			a:        []int{1, 2},
			b:        []int{},
			expected: []pair{},
		},
		{
			name:     "Test {} × {1, 2}",
			a:        []int{},
			b:        []int{1, 2},
			expected: []pair{},
		},
		{
			name:     "Test {1, 2} × nil",
			a:        []int{1, 2},
			b:        nil,
			expected: []pair{},
		},
		{
			name:     "Test nil × {1, 2}",
			a:        nil,
			b:        []int{1, 2},
			expected: []pair{},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := cart2(tt.a, tt.b)
			if !reflect.DeepEqual(result, tt.expected) {
				t.Errorf("Expected %v, but got %v", tt.expected, result)
			}
		})
	}
}
