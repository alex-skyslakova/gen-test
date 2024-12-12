package main

import (
    "testing"
)

func TestJacobi(t *testing.T) {
    // Test cases for the Jacobi symbol (a | n)
    testCases := []struct {
        a, n   uint64
        result int
    }{
        {0, 1, 0},
        {1, 1, 1},
        {2, 3, -1},
        {3, 5, -1},
        {4, 7, 1},
        {5, 9, 1},
        {6, 11, 1},
        {7, 13, 1},
        {8, 15, -1},
        {9, 17, 1},
        {10, 19, 1},
        {11, 21, 1},
        {12, 23, -1},
        {13, 25, 1},
        {14, 27, -1},
        {15, 29, 1},
        {16, 31, 1},
        {17, 33, 1},
        {18, 35, -1},
        {19, 37, 1},
        {20, 39, 1},
        {21, 41, 1},
        {22, 43, -1},
        {23, 45, -1},
        {24, 47, 1},
        {25, 49, 1},
        {26, 51, -1},
        {27, 53, 1},
        {28, 55, -1},
        {29, 57, 1},
        {30, 59, 1},
        {31, 61, 1},
        {32, 63, -1},
        {33, 65, 1},
        {34, 67, 1},
        {35, 69, -1},
        {36, 71, 1},
        {37, 73, 1},
        {38, 75, -1},
        {39, 77, 1},
        {40, 79, 1},
        {41, 81, 1},
        {42, 83, -1},
        {43, 85, 1},
        {44, 87, -1},
        {45, 89, 1},
        {46, 91, 1},
        {47, 93, 1},
        {48, 95, -1},
        {49, 97, 1},
        {50, 99, 1},
    }

    for _, tc := range testCases {
        t.Run(fmt.Sprintf("Jacobi(%d, %d)", tc.a, tc.n), func(t *testing.T) {
            result := jacobi(tc.a, tc.n)
            if result != tc.result {
                t.Errorf("Expected Jacobi(%d, %d) = %d, but got %d", tc.a, tc.n, tc.result, result)
            }
        })
    }
}

func TestJacobiInvalidN(t *testing.T) {
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("Expected panic for even 'n', but did not panic")
        }
    }()
    jacobi(1, 4) // 'n' must be a positive odd integer
}
