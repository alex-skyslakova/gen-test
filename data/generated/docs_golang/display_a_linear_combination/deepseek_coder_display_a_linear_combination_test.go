package main

import (
    "testing"
)

func TestLinearCombo(t *testing.T) {
    testCases := []struct {
        input    []int
        expected string
    }{
        // Test case 1: 1, 2, 3
        {
            input:    []int{1, 2, 3},
            expected: "e(1) + 2*e(2) + 3*e(3)",
        },
        // Test case 2: 0, 1, 2, 3
        {
            input:    []int{0, 1, 2, 3},
            expected: "e(2) + 2*e(3) + 3*e(4)",
        },
        // Test case 3: 1, 0, 3, 4
        {
            input:    []int{1, 0, 3, 4},
            expected: "e(1) + 3*e(3) + 4*e(4)",
        },
        // Test case 4: 1, 2, 0
        {
            input:    []int{1, 2, 0},
            expected: "e(1) + 2*e(2)",
        },
        // Test case 5: 0, 0, 0
        {
            input:    []int{0, 0, 0},
            expected: "0",
        },
        // Test case 6: 0
        {
            input:    []int{0},
            expected: "0",
        },
        // Test case 7: 1, 1, 1
        {
            input:    []int{1, 1, 1},
            expected: "e(1) + e(2) + e(3)",
        },
        // Test case 8: -1, -1, -1
        {
            input:    []int{-1, -1, -1},
            expected: "-e(1) - e(2) - e(3)",
        },
        // Test case 9: -1, -2, 0, -3
        {
            input:    []int{-1, -2, 0, -3},
            expected: "-e(1) - 2*e(2) - 3*e(4)",
        },
        // Test case 10: -1
        {
            input:    []int{-1},
            expected: "-e(1)",
        },
    }

    for _, tc := range testCases {
        result := linearCombo(tc.input)
        if result != tc.expected {
            t.Errorf("For input %v, expected %s, but got %s", tc.input, tc.expected, result)
        }
    }
}
