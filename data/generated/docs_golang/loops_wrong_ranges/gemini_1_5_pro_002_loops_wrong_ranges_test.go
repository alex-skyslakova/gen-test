package main

import (
	"reflect"
	"testing"
)

func TestSequence(t *testing.T) {
	tests := []struct {
		s        S
		limit    int
		expected []int
	}{
		{S{-2, 2, 1, "Normal"}, 10, []int{-2, -1, 0, 1, 2}},
		{S{-2, 2, 0, "Zero increment"}, 10, []int{-2}},
		{S{-2, 2, -1, "Increments away from stop value"}, 10, []int{}},
		{S{-2, 2, 10, "First increment is beyond stop value"}, 10, []int{}},
		{S{2, -2, 1, "Start more than stop: positive increment"}, 10, []int{}},
		{S{2, 2, 1, "Start equal stop: positive increment"}, 10, []int{2}},
		{S{2, 2, -1, "Start equal stop: negative increment"}, 10, []int{}},
		{S{2, 2, 0, "Start equal stop: zero increment"}, 10, []int{2}},
		{S{0, 0, 0, "Start equal stop equal zero: zero increment"}, 10, []int{0}},
		{S{0, 10, 1}, 3, []int{0, 1, 2}}, // Test limit functionality
	}

	for _, test := range tests {
		t.Run(test.s.comment, func(t *testing.T) {
			got := sequence(test.s, test.limit)
			if !reflect.DeepEqual(got, test.expected) {
				t.Errorf("sequence(%+v, %d) = %v; want %v", test.s, test.limit, got, test.expected)
			}
		})
	}
}

