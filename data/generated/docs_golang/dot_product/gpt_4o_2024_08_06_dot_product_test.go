package main

import (
    "testing"
)

func TestDotProduct(t *testing.T) {
    tests := []struct {
        x        []int
        y        []int
        expected int
        err      bool
    }{
        {[]int{1, 3, -5}, []int{4, -2, -1}, 3, false},
        {[]int{1, 2, 3}, []int{4, 5, 6}, 32, false},
        {[]int{0, 0, 0}, []int{0, 0, 0}, 0, false},
        {[]int{1, 2}, []int{1, 2, 3}, 0, true}, // Different lengths
        {[]int{}, []int{}, 0, false},           // Empty vectors
    }

    for _, test := range tests {
        result, err := dot(test.x, test.y)
        if test.err && err == nil {
            t.Errorf("Expected error for inputs %v and %v, but got none", test.x, test.y)
        }
        if !test.err && err != nil {
            t.Errorf("Did not expect error for inputs %v and %v, but got %v", test.x, test.y, err)
        }
        if result != test.expected {
            t.Errorf("For inputs %v and %v, expected %d, but got %d", test.x, test.y, test.expected, result)
        }
    }
}
