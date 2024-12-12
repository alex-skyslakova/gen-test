package main

import (
    "testing"
)

func TestSieve(t *testing.T) {
    tests := []struct {
        limit int
        expected []int
    }{
        {10, []int{3, 5, 7}},
        {20, []int{3, 5, 7, 11, 13, 17, 19}},
        {30, []int{3, 5, 7, 11, 13, 17, 19, 23, 29}},
    }

    for _, test := range tests {
        result := sieve(test.limit)
        if !equal(result, test.expected) {
            t.Errorf("Sieve(%d) = %v; want %v", test.limit, result, test.expected)
        }
    }
}

func TestFindPeriod(t *testing.T) {
    tests := []struct {
        n int
        expected int
    }{
        {7, 6},
        {11, 2},
        {13, 6},
        {17, 16},
        {19, 18},
    }

    for _, test := range tests {
        result := findPeriod(test.n)
        if result != test.expected {
            t.Errorf("findPeriod(%d) = %d; want %d", test.n, result, test.expected)
        }
    }
}

func TestMain(t *testing.T) {
    // This test is more complex as it involves the entire main function.
    // We can't directly test the output, but we can check if the function runs without errors.
    main()
}

func equal(a, b []int) bool {
    if len(a) != len(b) {
        return false
    }
    for i, v := range a {
        if v != b[i] {
            return false
        }
    }
    return true
}
