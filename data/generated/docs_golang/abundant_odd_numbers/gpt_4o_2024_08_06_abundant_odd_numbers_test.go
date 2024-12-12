package main

import (
    "testing"
)

func TestDivisors(t *testing.T) {
    tests := []struct {
        input    int
        expected []int
    }{
        {12, []int{1, 2, 3, 4, 6}},
        {15, []int{1, 3, 5}},
        {28, []int{1, 2, 4, 7, 14}},
        {1, []int{1}},
    }

    for _, test := range tests {
        result := divisors(test.input)
        if !equalSlices(result, test.expected) {
            t.Errorf("divisors(%d) = %v; want %v", test.input, result, test.expected)
        }
    }
}

func TestSum(t *testing.T) {
    tests := []struct {
        input    []int
        expected int
    }{
        {[]int{1, 2, 3, 4, 6}, 16},
        {[]int{1, 3, 5}, 9},
        {[]int{1, 2, 4, 7, 14}, 28},
        {[]int{1}, 1},
    }

    for _, test := range tests {
        result := sum(test.input)
        if result != test.expected {
            t.Errorf("sum(%v) = %d; want %d", test.input, result, test.expected)
        }
    }
}

func TestAbundantOdd(t *testing.T) {
    tests := []struct {
        searchFrom int
        countFrom  int
        countTo    int
        printOne   bool
        expected   int
    }{
        {1, 0, 25, false, 945}, // The 25th abundant odd number
        {945, 25, 1000, true, 33550335}, // The 1000th abundant odd number
        {1e9 + 1, 0, 1, true, 1122756}, // The first abundant odd number above one billion
    }

    for _, test := range tests {
        result := abundantOdd(test.searchFrom, test.countFrom, test.countTo, test.printOne)
        if result != test.expected {
            t.Errorf("abundantOdd(%d, %d, %d, %v) = %d; want %d", test.searchFrom, test.countFrom, test.countTo, test.printOne, result, test.expected)
        }
    }
}

func equalSlices(a, b []int) bool {
    if len(a) != len(b) {
        return false
    }
    for i := range a {
        if a[i] != b[i] {
            return false
        }
    }
    return true
}
