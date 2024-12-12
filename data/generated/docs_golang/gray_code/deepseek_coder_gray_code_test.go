package main

import (
    "testing"
)

func TestGrayCode(t *testing.T) {
    for b := 0; b < 32; b++ {
        g := enc(b)
        d := dec(g)
        if d != b {
            t.Errorf("For binary %d, expected decoded value %d, but got %d", b, b, d)
        }
    }
}
