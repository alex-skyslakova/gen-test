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
    a := sum / 10
    g := math.Pow(prod, .1)
    h := 10 / sumr

    // Test the arithmetic mean
    if a != 5.5 {
        t.Errorf("Arithmetic mean (A) is incorrect, got: %f, want: %f.", a, 5.5)
    }

    // Test the geometric mean
    expectedG := math.Pow(3628800, .1)
    if g != expectedG {
        t.Errorf("Geometric mean (G) is incorrect, got: %f, want: %f.", g, expectedG)
    }

    // Test the harmonic mean
    expectedH := 10 / (1 + 1.0/2 + 1.0/3 + 1.0/4 + 1.0/5 + 1.0/6 + 1.0/7 + 1.0/8 + 1.0/9 + 1.0/10)
    if h != expectedH {
        t.Errorf("Harmonic mean (H) is incorrect, got: %f, want: %f.", h, expectedH)
    }

    // Test the inequality A >= G >= H
    if !(a >= g && g >= h) {
        t.Errorf("Inequality A >= G >= H is not satisfied, got: A = %f, G = %f, H = %f.", a, g, h)
    }
}
