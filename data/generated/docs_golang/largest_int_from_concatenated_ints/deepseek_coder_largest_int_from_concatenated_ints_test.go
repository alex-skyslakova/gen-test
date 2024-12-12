package main

import (
    "math/big"
    "testing"
)

func TestLargestIntFromConcatenatedInts(t *testing.T) {
    testCases := []struct {
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

    for _, tc := range testCases {
        result := li(tc.input...).String()
        if result != tc.expected {
            t.Errorf("For input %v, expected %s, but got %s", tc.input, tc.expected, result)
        }
    }
}
