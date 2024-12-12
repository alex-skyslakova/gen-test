package main

import (
	"errors"
	"testing"
)

func TestDot(t *testing.T) {
	tests := []struct {
		x        []int
		y        []int
		expected int
		err      error
	}{
		{[]int{1, 3, -5}, []int{4, -2, -1}, 4, nil},
		{[]int{1, 2, 3}, []int{4, 5, 6}, 32, nil},
		{[]int{}, []int{}, 0, nil},
		{[]int{1}, []int{2}, 2, nil},
		{[]int{1, 2}, []int{3, 4}, 11, nil},
		{[]int{1, 2, 3}, []int{4, 5}, 0, errors.New("incompatible lengths")},
		{[]int{1, 2}, []int{3, 4, 5}, 0, errors.New("incompatible lengths")},
	}

	for _, test := range tests {
		result, err := dot(test.x, test.y)
		if !equalError(err, test.err) {
			t.Errorf("dot(%v, %v) error = %v, wantErr %v", test.x, test.y, err, test.err)
			continue
		}
		if result != test.expected {
			t.Errorf("dot(%v, %v) = %v, want %v", test.x, test.y, result, test.expected)
		}
	}
}

// Helper function to compare errors
func equalError(e1, e2 error) bool {
	if e1 == nil && e2 == nil {
		return true
	}
	if e1 == nil || e2 == nil {
		return false
	}
	return e1.Error() == e2.Error()
}

