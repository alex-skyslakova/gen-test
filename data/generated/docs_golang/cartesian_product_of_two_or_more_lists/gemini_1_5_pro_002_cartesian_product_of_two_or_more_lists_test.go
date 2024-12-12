package main

import (
	"reflect"
	"testing"
)

func TestCart2(t *testing.T) {
	tests := []struct {
		name     string
		a        []int
		b        []int
		expected []pair
	}{
		{
			name:     "1_2_x_3_4",
			a:        []int{1, 2},
			b:        []int{3, 4},
			expected: []pair{{1, 3}, {1, 4}, {2, 3}, {2, 4}},
		},
		{
			name:     "3_4_x_1_2",
			a:        []int{3, 4},
			b:        []int{1, 2},
			expected: []pair{{3, 1}, {3, 2}, {4, 1}, {4, 2}},
		},
		{
			name:     "1_2_x_empty",
			a:        []int{1, 2},
			b:        []int{},
			expected: []pair{},
		},
		{
			name:     "empty_x_1_2",
			a:        []int{},
			b:        []int{1, 2},
			expected: []pair{},
		},
	}

	for _, test := range tests {
		t.Run(test.name, func(t *testing.T) {
			result := cart2(test.a, test.b)
			if !reflect.DeepEqual(result, test.expected) {
				t.Errorf("Expected %v, but got %v", test.expected, result)
			}
		})
	}
}

//  No n-ary cartesian product function was provided, so no tests can be written.
//  If one were provided, tests similar to the above would be appropriate.
