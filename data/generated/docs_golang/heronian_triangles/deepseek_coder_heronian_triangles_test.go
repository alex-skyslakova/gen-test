package main

import (
    "math"
    "testing"
)

func TestGCD(t *testing.T) {
    testCases := []struct {
        a, b, expected int
    }{
        {10, 5, 5},
        {25, 15, 5},
        {14, 28, 14},
        {17, 19, 1},
        {0, 5, 5},
        {5, 0, 5},
    }

    for _, tc := range testCases {
        result := gcd(tc.a, tc.b)
        if result != tc.expected {
            t.Errorf("GCD(%d, %d) = %d; expected %d", tc.a, tc.b, result, tc.expected)
        }
    }
}

func TestIsHeron(t *testing.T) {
    testCases := []struct {
        h        float64
        expected bool
    }{
        {6.0, true},
        {5.5, false},
        {0.0, false},
        {-6.0, false},
        {10.0, true},
        {10.1, false},
    }

    for _, tc := range testCases {
        result := is_heron(tc.h)
        if result != tc.expected {
            t.Errorf("is_heron(%f) = %v; expected %v", tc.h, result, tc.expected)
        }
    }
}

func TestByAreaPerimeterSort(t *testing.T) {
    testCases := []struct {
        input    [][]int
        expected [][]int
    }{
        {
            [][]int{{3, 4, 5, 12, 6}, {5, 5, 6, 16, 12}, {6, 8, 10, 24, 24}},
            [][]int{{3, 4, 5, 12, 6}, {5, 5, 6, 16, 12}, {6, 8, 10, 24, 24}},
        },
        {
            [][]int{{6, 8, 10, 24, 24}, {3, 4, 5, 12, 6}, {5, 5, 6, 16, 12}},
            [][]int{{3, 4, 5, 12, 6}, {5, 5, 6, 16, 12}, {6, 8, 10, 24, 24}},
        },
    }

    for _, tc := range testCases {
        sort.Sort(by_area_perimeter(tc.input))
        if !slicesEqual(tc.input, tc.expected) {
            t.Errorf("Sorted result = %v; expected %v", tc.input, tc.expected)
        }
    }
}

func slicesEqual(a, b [][]int) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if len(a[i]) != len(b[i]) {
            return false
        }
        for j := range a[i] {
            if a[i][j] != b[i][j] {
                return false
            }
        }
    }
    return true
}

func TestMainFunctionality(t *testing.T) {
    // This test is more complex and would require mocking or capturing output,
    // which is beyond the scope of this simple unit test example.
    // However, you can manually verify the output of the main function.
}
