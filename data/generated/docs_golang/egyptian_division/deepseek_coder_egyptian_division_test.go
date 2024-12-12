package main

import (
    "testing"
)

func TestEgyptianDivide(t *testing.T) {
    tests := []struct {
        dividend  int
        divisor   int
        quotient  int
        remainder int
    }{
        {580, 34, 17, 2}, // Example case
        {100, 10, 10, 0}, // Simple division with no remainder
        {100, 3, 33, 1},  // Division with remainder
        {0, 10, 0, 0},    // Dividend is zero
        {100, 1, 100, 0}, // Divisor is 1
        {100, 100, 1, 0}, // Dividend equals divisor
        {100, 99, 1, 1},  // Divisor just less than dividend
        {100, 101, 0, 100}, // Divisor greater than dividend
    }

    for _, test := range tests {
        quotient, remainder := egyptianDivide(test.dividend, test.divisor)
        if quotient != test.quotient || remainder != test.remainder {
            t.Errorf("egyptianDivide(%d, %d) = (%d, %d); want (%d, %d)",
                test.dividend, test.divisor, quotient, remainder, test.quotient, test.remainder)
        }
    }
}

func TestEgyptianDividePanic(t *testing.T) {
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("Expected panic for invalid arguments, but did not panic")
        }
    }()
    egyptianDivide(-1, 10) // Negative dividend
    egyptianDivide(10, 0)  // Zero divisor
    egyptianDivide(10, -1) // Negative divisor
}
