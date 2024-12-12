package main

import (
    "math"
    "testing"
)

func TestPythagoreanMeans(t *testing.T) {
    sum, sumr, prod := 0., 0., 1.
    for n := 1.; n <= 10; n++ {
        sum += n
        sumr += 1 / n
        prod *= n
    }
    a, g, h := sum/10, math.Pow(prod, .1), 10/sumr

    // Test arithmetic mean
    expectedA := 5.5
    if a != expectedA {
        t.Errorf("Arithmetic mean incorrect, got: %v, want: %v", a, expectedA)
    }

    // Test geometric mean
    expectedG := math.Pow(3628800, 0.1)
    if g != expectedG {
        t.Errorf("Geometric mean incorrect, got: %v, want: %v", g, expectedG)
    }

    // Test harmonic mean
    expectedH := 10 / (1/1 + 1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/8 + 1/9 + 1/10)
    if h != expectedH {
        t.Errorf("Harmonic mean incorrect, got: %v, want: %v", h, expectedH)
    }

    // Test inequality A >= G >= H
    if !(a >= g && g >= h) {
        t.Errorf("Inequality A >= G >= H not satisfied, A: %v, G: %v, H: %v", a, g, h)
    }
}
