package main

import (
    "math/big"
    "testing"
)

func TestFactorial(t *testing.T) {
    tests := []struct {
        input    int64
        expected *big.Int
    }{
        {0, big.NewInt(1)}, // Factorial of 0 is 1
        {1, big.NewInt(1)}, // Factorial of 1 is 1
        {2, big.NewInt(2)}, // Factorial of 2 is 2
        {3, big.NewInt(6)}, // Factorial of 3 is 6
        {4, big.NewInt(24)}, // Factorial of 4 is 24
        {5, big.NewInt(120)}, // Factorial of 5 is 120
        {10, big.NewInt(3628800)}, // Factorial of 10 is 3628800
        {20, big.NewInt(2432902008176640000)}, // Factorial of 20 is 2432902008176640000
        {-1, nil}, // Factorial of negative number should return nil
    }

    for _, test := range tests {
        result := factorial(test.input)
        if result == nil && test.expected == nil {
            continue
        }
        if result == nil || test.expected == nil || result.Cmp(test.expected) != 0 {
            t.Errorf("factorial(%d) = %v; want %v", test.input, result, test.expected)
        }
    }
}
