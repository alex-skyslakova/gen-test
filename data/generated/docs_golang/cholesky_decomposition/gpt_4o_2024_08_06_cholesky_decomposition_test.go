package main

import (
    "math"
    "testing"
)

func TestCholeskyDecomposition(t *testing.T) {
    tests := []struct {
        name     string
        input    *symmetric
        expected *lower
    }{
        {
            name: "Example 1",
            input: &symmetric{3, []float64{
                25,
                15, 18,
                -5, 0, 11}},
            expected: &lower{3, []float64{
                5,
                3, 3,
                -1, 1, 3}},
        },
        {
            name: "Example 2",
            input: &symmetric{4, []float64{
                18,
                22, 70,
                54, 86, 174,
                42, 62, 134, 106}},
            expected: &lower{4, []float64{
                4.24264,
                5.18545, 6.56591,
                12.72792, 3.04604, 1.64974,
                9.89949, 1.62455, 1.84971, 1.39262}},
        },
    }

    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := tt.input.choleskyLower()
            if !compareLowerMatrices(result, tt.expected) {
                t.Errorf("choleskyLower() = %v, expected %v", result.ele, tt.expected.ele)
            }
        })
    }
}

func compareLowerMatrices(a, b *lower) bool {
    if a.order != b.order {
        return false
    }
    for i := range a.ele {
        if math.Abs(a.ele[i]-b.ele[i]) > 1e-5 {
            return false
        }
    }
    return true
}
