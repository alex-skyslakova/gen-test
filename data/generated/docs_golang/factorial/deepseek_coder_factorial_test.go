package main

import (
    "math/big"
    "testing"
)

func TestFactorial(t *testing.T) {
    tests := []struct {
        n        int64
        expected string
    }{
        {0, "1"},
        {1, "1"},
        {2, "2"},
        {3, "6"},
        {4, "24"},
        {5, "120"},
        {10, "3628800"},
        {20, "2432902008176640000"},
        {30, "265252859812191058636308480000000"},
    }

    for _, test := range tests {
        result := factorial(test.n)
        if result == nil {
            t.Errorf("Factorial(%d) returned nil, expected %s", test.n, test.expected)
            continue
        }
        if result.String() != test.expected {
            t.Errorf("Factorial(%d) = %s, expected %s", test.n, result.String(), test.expected)
        }
    }
}

func TestFactorialNegativeInput(t *testing.T) {
    result := factorial(-1)
    if result != nil {
        t.Errorf("Factorial(-1) did not return nil, expected nil")
    }
}
