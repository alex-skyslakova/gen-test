package main

import (
    "math/big"
    "testing"
)

func TestLargestIntFromConcatenatedInts(t *testing.T) {
    tests := []struct {
        input    []int
        expected string
    }{
        {
            input:    []int{1, 34, 3, 98, 9, 76, 45, 4},
            expected: "998764543431",
        },
        {
            input:    []int{54, 546, 548, 60},
            expected: "6054854654",
        },
    }

    for _, test := range tests {
        result := li(test.input...).String()
        if result != test.expected {
            t.Errorf("For input %v, expected %s but got %s", test.input, test.expected, result)
        }
    }
}

func TestLargestIntFromConcatenatedIntsEdgeCases(t *testing.T) {
    tests := []struct {
        input    []int
        expected string
    }{
        {
            input:    []int{0, 0, 0},
            expected: "0",
        },
        {
            input:    []int{10, 2},
            expected: "210",
        },
        {
            input:    []int{121, 12},
            expected: "12121",
        },
    }

    for _, test := range tests {
        result := li(test.input...).String()
        if result != test.expected {
            t.Errorf("For input %v, expected %s but got %s", test.input, test.expected, result)
        }
    }
}
