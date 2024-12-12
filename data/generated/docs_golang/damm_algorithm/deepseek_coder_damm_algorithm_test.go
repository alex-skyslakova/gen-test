package main

import (
    "testing"
)

func TestDammAlgorithm(t *testing.T) {
    testCases := []struct {
        input    string
        expected bool
    }{
        {"5724", true},
        {"5727", false},
        {"112946", true},
        {"112949", false},
        {"0", true},
        {"1230", true},
        {"1231", false},
        {"9876543210", true},
        {"9876543211", false},
        {"1234567890", true},
        {"1234567891", false},
    }

    for _, tc := range testCases {
        t.Run(tc.input, func(t *testing.T) {
            result := damm(tc.input)
            if result != tc.expected {
                t.Errorf("For input %s, expected %v, but got %v", tc.input, tc.expected, result)
            }
        })
    }
}
