package main

import (
    "testing"
)

func TestSMA(t *testing.T) {
    // Test case 1: Period 3
    sma3 := sma(3)
    expectedResults3 := []float64{1.0, 1.5, 2.0, 3.0, 4.0, 4.666666666666667, 4.333333333333333, 4.0, 3.0, 2.0}
    for i, x := range []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1} {
        result := sma3(x)
        if result != expectedResults3[i] {
            t.Errorf("Expected SMA(3) for input %f to be %f, but got %f", x, expectedResults3[i], result)
        }
    }

    // Test case 2: Period 5
    sma5 := sma(5)
    expectedResults5 := []float64{1.0, 1.5, 2.0, 2.5, 3.0, 3.8, 4.2, 4.6, 4.6, 3.4}
    for i, x := range []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1} {
        result := sma5(x)
        if result != expectedResults5[i] {
            t.Errorf("Expected SMA(5) for input %f to be %f, but got %f", x, expectedResults5[i], result)
        }
    }

    // Test case 3: Period 1
    sma1 := sma(1)
    expectedResults1 := []float64{1.0, 2.0, 3.0, 4.0, 5.0, 5.0, 4.0, 3.0, 2.0, 1.0}
    for i, x := range []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1} {
        result := sma1(x)
        if result != expectedResults1[i] {
            t.Errorf("Expected SMA(1) for input %f to be %f, but got %f", x, expectedResults1[i], result)
        }
    }

    // Test case 4: Period 0 (should return 0 for all inputs)
    sma0 := sma(0)
    expectedResults0 := []float64{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
    for i, x := range []float64{1, 2, 3, 4, 5, 5, 4, 3, 2, 1} {
        result := sma0(x)
        if result != expectedResults0[i] {
            t.Errorf("Expected SMA(0) for input %f to be %f, but got %f", x, expectedResults0[i], result)
        }
    }
}
