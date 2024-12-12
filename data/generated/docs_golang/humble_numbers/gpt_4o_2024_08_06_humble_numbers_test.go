package main

import (
    "testing"
    "math/big"
    "reflect"
)

// Test the min function
func TestMin(t *testing.T) {
    a := big.NewInt(2)
    b := big.NewInt(3)
    result := min(a, b)
    if result.Cmp(a) != 0 {
        t.Errorf("Expected %v, got %v", a, result)
    }

    a = big.NewInt(5)
    b = big.NewInt(5)
    result = min(a, b)
    if result.Cmp(a) != 0 {
        t.Errorf("Expected %v, got %v", a, result)
    }
}

// Test the humble function for the first 50 humble numbers
func TestHumble(t *testing.T) {
    expected := []*big.Int{
        big.NewInt(1), big.NewInt(2), big.NewInt(3), big.NewInt(4), big.NewInt(5),
        big.NewInt(6), big.NewInt(7), big.NewInt(8), big.NewInt(9), big.NewInt(10),
        big.NewInt(12), big.NewInt(14), big.NewInt(15), big.NewInt(16), big.NewInt(18),
        big.NewInt(20), big.NewInt(21), big.NewInt(24), big.NewInt(25), big.NewInt(27),
        big.NewInt(28), big.NewInt(30), big.NewInt(32), big.NewInt(35), big.NewInt(36),
        big.NewInt(40), big.NewInt(42), big.NewInt(45), big.NewInt(48), big.NewInt(49),
        big.NewInt(50), big.NewInt(54), big.NewInt(56), big.NewInt(60), big.NewInt(63),
        big.NewInt(64), big.NewInt(70), big.NewInt(72), big.NewInt(75), big.NewInt(80),
        big.NewInt(81), big.NewInt(84), big.NewInt(90), big.NewInt(96), big.NewInt(98),
        big.NewInt(100), big.NewInt(105), big.NewInt(108), big.NewInt(112),
    }

    result := humble(50)

    if !reflect.DeepEqual(result, expected) {
        t.Errorf("Expected %v, got %v", expected, result)
    }
}

// Test the commatize function
func TestCommatize(t *testing.T) {
    tests := []struct {
        input    int
        expected string
    }{
        {1, "1"},
        {12, "12"},
        {123, "123"},
        {1234, "1,234"},
        {12345, "12,345"},
        {123456, "123,456"},
        {1234567, "1,234,567"},
    }

    for _, test := range tests {
        result := commatize(test.input)
        if result != test.expected {
            t.Errorf("For input %d, expected %s, got %s", test.input, test.expected, result)
        }
    }
}
