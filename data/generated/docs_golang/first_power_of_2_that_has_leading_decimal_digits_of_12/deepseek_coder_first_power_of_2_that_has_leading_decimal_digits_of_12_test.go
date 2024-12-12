package main

import (
    "testing"
)

func TestP(t *testing.T) {
    testCases := []struct {
        L        uint64
        n        uint64
        expected uint64
    }{
        {12, 1, 7},
        {12, 2, 80},
        {123, 45, 12710},
        {123, 12345, 3510491},
        {123, 678910, 193060223},
    }

    for _, tc := range testCases {
        result := p(tc.L, tc.n)
        if result != tc.expected {
            t.Errorf("p(%d, %d) = %d; expected %d", tc.L, tc.n, result, tc.expected)
        }
    }
}
