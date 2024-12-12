package main

import (
    "math"
    "math/rand"
    "testing"
)

func TestAvg(t *testing.T) {
    rand.Seed(42) // Seed for deterministic results

    tests := []struct {
        n        int
        expected float64
        tolerance float64
    }{
        {1, 1.0, 0.1},
        {2, 1.5, 0.1},
        {3, 1.8889, 0.1},
        {4, 2.2188, 0.1},
        {5, 2.5104, 0.1},
        {6, 2.7747, 0.1},
        {7, 3.0181, 0.1},
        {8, 3.2450, 0.1},
        {9, 3.4583, 0.1},
        {10, 3.6602, 0.1},
        {11, 3.8524, 0.1},
        {12, 4.0361, 0.1},
        {13, 4.2123, 0.1},
        {14, 4.3820, 0.1},
        {15, 4.5458, 0.1},
        {16, 4.7043, 0.1},
        {17, 4.8579, 0.1},
        {18, 5.0071, 0.1},
        {19, 5.1522, 0.1},
        {20, 5.2936, 0.1},
    }

    for _, tt := range tests {
        t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
            result := avg(tt.n)
            if math.Abs(result-tt.expected) > tt.tolerance {
                t.Errorf("avg(%d) = %f; expected %f (tolerance %f)", tt.n, result, tt.expected, tt.tolerance)
            }
        })
    }
}

func TestAna(t *testing.T) {
    tests := []struct {
        n        int
        expected float64
    }{
        {1, 1.0},
        {2, 1.5},
        {3, 1.8889},
        {4, 2.2188},
        {5, 2.5104},
        {6, 2.7747},
        {7, 3.0181},
        {8, 3.2450},
        {9, 3.4583},
        {10, 3.6602},
        {11, 3.8524},
        {12, 4.0361},
        {13, 4.2123},
        {14, 4.3820},
        {15, 4.5458},
        {16, 4.7043},
        {17, 4.8579},
        {18, 5.0071},
        {19, 5.1522},
        {20, 5.2936},
    }

    for _, tt := range tests {
        t.Run(fmt.Sprintf("n=%d", tt.n), func(t *testing.T) {
            result := ana(tt.n)
            if math.Abs(result-tt.expected) > 1e-4 {
                t.Errorf("ana(%d) = %f; expected %f", tt.n, result, tt.expected)
            }
        })
    }
}
