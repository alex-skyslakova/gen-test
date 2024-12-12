package main

import (
    "math"
    "testing"
)

func TestFib1000(t *testing.T) {
    fibNumbers := Fib1000()

    // Ensure the length of the Fibonacci sequence is 1000
    if len(fibNumbers) != 1000 {
        t.Errorf("Expected 1000 Fibonacci numbers, got %d", len(fibNumbers))
    }

    // Ensure the first 1000 Fibonacci numbers are correct
    expectedFibNumbers := []float64{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987}
    for i := 0; i < len(expectedFibNumbers); i++ {
        if fibNumbers[i] != expectedFibNumbers[i] {
            t.Errorf("Expected Fibonacci number at position %d to be %f, got %f", i, expectedFibNumbers[i], fibNumbers[i])
        }
    }
}

func TestShow(t *testing.T) {
    fibNumbers := Fib1000()
    var f [9]int

    for _, v := range fibNumbers {
        firstDigit := int(fmt.Sprintf("%g", v)[0] - '1')
        if firstDigit >= 0 && firstDigit < 9 {
            f[firstDigit]++
        }
    }

    // Ensure the observed frequencies match the expected Benford's Law distribution
    for i := 0; i < 9; i++ {
        observed := float64(f[i]) / float64(len(fibNumbers))
        expected := math.Log10(1 + 1/float64(i+1))

        if math.Abs(observed-expected) > 0.05 {
            t.Errorf("Digit %d: Observed frequency %.3f does not match expected frequency %.3f", i+1, observed, expected)
        }
    }
}
