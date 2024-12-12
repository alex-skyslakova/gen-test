package main

import (
    "math"
    "testing"
)

func TestCholeskyDecomposition(t *testing.T) {
    // Test case 1
    a1 := &symmetric{3, []float64{
        25,
        15, 18,
        -5, 0, 11,
    }}
    expectedL1 := &lower{3, []float64{
        5,
        3, 3,
        -1, 1, 3,
    }}
    testCholeskyDecomposition(t, a1, expectedL1)

    // Test case 2
    a2 := &symmetric{4, []float64{
        18,
        22, 70,
        54, 86, 174,
        42, 62, 134, 106,
    }}
    expectedL2 := &lower{4, []float64{
        4.24264,
        5.18545, 6.56591,
        12.72792, 3.04604, 1.64974,
        9.89949, 1.62455, 1.84971, 1.39262,
    }}
    testCholeskyDecomposition(t, a2, expectedL2)
}

func testCholeskyDecomposition(t *testing.T, a *symmetric, expectedL *lower) {
    l := a.choleskyLower()
    if l.order != expectedL.order {
        t.Errorf("Order mismatch: got %d, expected %d", l.order, expectedL.order)
    }
    for i := 0; i < len(l.ele); i++ {
        if math.Abs(l.ele[i]-expectedL.ele[i]) > 1e-5 {
            t.Errorf("Element mismatch at index %d: got %f, expected %f", i, l.ele[i], expectedL.ele[i])
        }
    }
}
