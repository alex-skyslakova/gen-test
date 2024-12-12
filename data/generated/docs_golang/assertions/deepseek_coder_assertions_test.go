package main

import (
    "testing"
)

func TestAssertion(t *testing.T) {
    // Test case where the variable is equal to 42
    x := 42
    if x != 42 {
        t.Errorf("Assertion failed: expected x to be 42, but got %d", x)
    }

    // Test case where the variable is not equal to 42
    x = 43
    defer func() {
        if r := recover(); r == nil {
            t.Errorf("Assertion did not panic as expected")
        }
    }()
    if x != 42 {
        panic(42)
    }
}
