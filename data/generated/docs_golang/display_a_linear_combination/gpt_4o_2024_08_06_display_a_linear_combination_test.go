package main

import (
    "testing"
)

func TestLinearCombo(t *testing.T) {
    tests := []struct {
        input    []int
        expected string
    }{
        {[]int{1, 2, 3}, "e(1) + 2*e(2) + 3*e(3)"},
        {[]int{0, 1, 2, 3}, "e(2) + 2*e(3) + 3*e(4)"},
        {[]int{1, 0, 3, 4}, "e(1) + 3*e(3) + 4*e(4)"},
        {[]int{1, 2, 0}, "e(1) + 2*e(2)"},
        {[]int{0, 0, 0}, "0"},
        {[]int{0}, "0"},
        {[]int{1, 1, 1}, "e(1) + e(2) + e(3)"},
        {[]int{-1, -1, -1}, "-e(1) - e(2) - e(3)"},
        {[]int{-1, -2, 0, -3}, "-e(1) - 2*e(2) - 3*e(4)"},
        {[]int{-1}, "-e(1)"},
    }

    for _, test := range tests {
        result := linearCombo(test.input)
        if result != test.expected {
            t.Errorf("For input %v, expected %s, but got %s", test.input, test.expected, result)
        }
    }
}
