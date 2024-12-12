package main

import (
    "testing"
)

// TestJacobiSymbol tests the jacobi function with various inputs
func TestJacobiSymbol(t *testing.T) {
    tests := []struct {
        a, n   uint64
        result int
    }{
        {0, 1, 0},
        {0, 3, 0},
        {1, 3, 1},
        {2, 3, -1},
        {3, 3, 0},
        {4, 3, 1},
        {5, 3, -1},
        {6, 3, 0},
        {7, 3, 1},
        {8, 3, -1},
        {9, 3, 0},
        {2, 5, -1},
        {3, 5, -1},
        {4, 5, 1},
        {5, 5, 0},
        {6, 5, -1},
        {7, 5, 1},
        {8, 5, 1},
        {9, 5, -1},
        {10, 5, 0},
        {2, 7, 1},
        {3, 7, -1},
        {4, 7, 1},
        {5, 7, -1},
        {6, 7, -1},
        {7, 7, 0},
        {8, 7, 1},
        {9, 7, -1},
        {10, 7, 1},
        {11, 7, -1},
        {12, 7, 1},
        {13, 7, -1},
        {14, 7, 0},
    }

    for _, test := range tests {
        t.Run("", func(t *testing.T) {
            result := jacobi(test.a, test.n)
            if result != test.result {
                t.Errorf("jacobi(%d, %d) = %d; want %d", test.a, test.n, result, test.result)
            }
        })
    }
}

// TestJacobiSymbolInvalidN tests the jacobi function with invalid n
func TestJacobiSymbolInvalidN(t *testing.T) {
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("Expected panic for even n, but did not panic")
        }
    }()
    jacobi(2, 4)
}
