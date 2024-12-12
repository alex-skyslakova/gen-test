package main

import (
    "math/big"
    "testing"
)

func TestAddDigit(t *testing.T) {
    tests := []struct {
        base       int
        expected   string
        maxStack   int
        smallPrimes []int
    }{
        {3, "2", 128, []int{2, 3}},
        {4, "3", 128, []int{2, 3}},
        {5, "3", 128, []int{2, 3}},
        {6, "5", 128, []int{2, 3, 5}},
        {7, "3", 128, []int{2, 3, 5}},
        {8, "3", 128, []int{2, 3, 5}},
        {9, "7", 128, []int{2, 3, 5, 7}},
        {10, "3", 128, []int{2, 3, 5, 7}},
        {11, "3", 128, []int{2, 3, 5, 7}},
        {12, "5", 128, []int{2, 3, 5, 7}},
        {13, "3", 128, []int{2, 3, 5, 7}},
        {14, "7", 128, []int{2, 3, 5, 7}},
        {15, "7", 128, []int{2, 3, 5, 7}},
        {16, "3", 128, []int{2, 3, 5, 7}},
        {17, "3", 128, []int{2, 3, 5, 7}},
    }

    for _, tt := range tests {
        base = tt.base
        maxStack = tt.maxStack
        smallPrimes = tt.smallPrimes
        doBase()
        if answer.String() != tt.expected {
            t.Errorf("For base %d, expected %s but got %s", tt.base, tt.expected, answer.String())
        }
    }
}

func TestDoBase(t *testing.T) {
    tests := []struct {
        base       int
        expected   string
        maxStack   int
        smallPrimes []int
    }{
        {3, "2", 128, []int{2, 3}},
        {4, "3", 128, []int{2, 3}},
        {5, "3", 128, []int{2, 3}},
        {6, "5", 128, []int{2, 3, 5}},
        {7, "3", 128, []int{2, 3, 5}},
        {8, "3", 128, []int{2, 3, 5}},
        {9, "7", 128, []int{2, 3, 5, 7}},
        {10, "3", 128, []int{2, 3, 5, 7}},
        {11, "3", 128, []int{2, 3, 5, 7}},
        {12, "5", 128, []int{2, 3, 5, 7}},
        {13, "3", 128, []int{2, 3, 5, 7}},
        {14, "7", 128, []int{2, 3, 5, 7}},
        {15, "7", 128, []int{2, 3, 5, 7}},
        {16, "3", 128, []int{2, 3, 5, 7}},
        {17, "3", 128, []int{2, 3, 5, 7}},
    }

    for _, tt := range tests {
        base = tt.base
        maxStack = tt.maxStack
        smallPrimes = tt.smallPrimes
        doBase()
        if answer.String() != tt.expected {
            t.Errorf("For base %d, expected %s but got %s", tt.base, tt.expected, answer.String())
        }
    }
}

func TestMain(t *testing.T) {
    main()
    // Since main() prints to stdout, we can't directly test its output.
    // Instead, we rely on the fact that main() calls doBase() for each base,
    // and we have already tested doBase() in the previous test.
}
