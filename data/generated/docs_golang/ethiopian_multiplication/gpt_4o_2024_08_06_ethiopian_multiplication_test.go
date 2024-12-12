package main

import "testing"

// Test for the halve function
func TestHalve(t *testing.T) {
    tests := []struct {
        input    int
        expected int
    }{
        {10, 5},
        {9, 4},
        {1, 0},
        {0, 0},
        {-4, -2},
    }

    for _, test := range tests {
        result := halve(test.input)
        if result != test.expected {
            t.Errorf("halve(%d) = %d; expected %d", test.input, result, test.expected)
        }
    }
}

// Test for the double function
func TestDouble(t *testing.T) {
    tests := []struct {
        input    int
        expected int
    }{
        {10, 20},
        {0, 0},
        {-3, -6},
    }

    for _, test := range tests {
        result := double(test.input)
        if result != test.expected {
            t.Errorf("double(%d) = %d; expected %d", test.input, result, test.expected)
        }
    }
}

// Test for the isEven function
func TestIsEven(t *testing.T) {
    tests := []struct {
        input    int
        expected bool
    }{
        {10, true},
        {9, false},
        {0, true},
        {-2, true},
        {-3, false},
    }

    for _, test := range tests {
        result := isEven(test.input)
        if result != test.expected {
            t.Errorf("isEven(%d) = %v; expected %v", test.input, result, test.expected)
        }
    }
}

// Test for the ethMulti function
func TestEthMulti(t *testing.T) {
    tests := []struct {
        i, j     int
        expected int
    }{
        {17, 34, 578},
        {0, 34, 0},
        {17, 0, 0},
        {1, 1, 1},
        {2, 3, 6},
        {5, 5, 25},
        {-3, 4, -12},
        {3, -4, -12},
        {-3, -4, 12},
    }

    for _, test := range tests {
        result := ethMulti(test.i, test.j)
        if result != test.expected {
            t.Errorf("ethMulti(%d, %d) = %d; expected %d", test.i, test.j, result, test.expected)
        }
    }
}
