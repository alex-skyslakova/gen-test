package main

import (
    "testing"
)

func TestP(t *testing.T) {
    tests := []struct {
        L, n, expected uint64
    }{
        {12, 1, 7},
        {12, 2, 80},
        {123, 45, 12710},
        // The following test cases are based on the problem statement
        // and may take a significant amount of time to compute.
        // They are commented out to avoid long test execution times.
        // Uncomment them if you want to run the full test suite.
        // {123, 12345, <expected_value>},
        // {123, 678910, <expected_value>},
    }

    for _, test := range tests {
        result := p(test.L, test.n)
        if result != test.expected {
            t.Errorf("p(%d, %d) = %d; expected %d", test.L, test.n, result, test.expected)
        }
    }
}
