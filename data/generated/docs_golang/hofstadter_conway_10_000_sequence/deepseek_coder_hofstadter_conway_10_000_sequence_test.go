package main

import (
    "testing"
)

func TestHofstadterConwaySequence(t *testing.T) {
    // Test the initial sequence values
    a := []int{0, 1, 1}
    x := 1
    n := 2
    mallow := 0

    // Test the sequence generation up to 2^4 (16)
    for p := 1; p < 4; p++ {
        max := 0.
        for nextPot := n * 2; n < nextPot; {
            n = len(a)
            x = a[x] + a[n-x]
            a = append(a, x)
            f := float64(x) / float64(n)
            if f > max {
                max = f
            }
            if f >= 0.55 {
                mallow = n
            }
        }
    }

    // Expected sequence values up to 2^4 (16)
    expectedSequence := []int{0, 1, 1, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 8, 8, 8}
    for i := 1; i < len(expectedSequence); i++ {
        if a[i] != expectedSequence[i] {
            t.Errorf("Expected a[%d] = %d, but got %d", i, expectedSequence[i], a[i])
        }
    }

    // Test the maxima of a(n)/n between successive powers of two up to 2^4 (16)
    expectedMaxima := []float64{0.5, 0.6, 0.6666666666666666}
    for i := 0; i < len(expectedMaxima); i++ {
        if max != expectedMaxima[i] {
            t.Errorf("Expected max between 2^%d and 2^%d to be %f, but got %f", i, i+1, expectedMaxima[i], max)
        }
    }

    // Test the value of n that would have won the prize
    expectedMallow := 16
    if mallow != expectedMallow {
        t.Errorf("Expected winning number to be %d, but got %d", expectedMallow, mallow)
    }
}
