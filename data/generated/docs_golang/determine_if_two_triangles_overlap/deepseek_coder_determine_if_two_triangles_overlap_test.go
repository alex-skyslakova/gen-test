package main

import (
    "testing"
)

func TestTriTri2D(t *testing.T) {
    tests := []struct {
        t1, t2       *triangle
        eps          float64
        allowReversed bool
        onBoundary   bool
        expected     bool
    }{
        // Test cases from the task description
        {
            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 5.0}},
            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 6.0}},
            0.0, false, true, false,
        },
        {
            &triangle{point{0.0, 0.0}, point{0.0, 5.0}, point{5.0, 0.0}},
            &triangle{point{0.0, 0.0}, point{0.0, 5.0}, point{5.0, 0.0}},
            0.0, true, true, true,
        },
        {
            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 5.0}},
            &triangle{point{-10.0, 0.0}, point{-5.0, 0.0}, point{-1.0, 6.0}},
            0.0, false, true, false,
        },
        {
            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{2.5, 5.0}},
            &triangle{point{0.0, 4.0}, point{2.5, -1.0}, point{5.0, 4.0}},
            0.0, false, true, true,
        },
        {
            &triangle{point{0.0, 0.0}, point{1.0, 1.0}, point{0.0, 2.0}},
            &triangle{point{2.0, 1.0}, point{3.0, 0.0}, point{3.0, 2.0}},
            0.0, false, true, false,
        },
        {
            &triangle{point{0.0, 0.0}, point{1.0, 1.0}, point{0.0, 2.0}},
            &triangle{point{2.0, 1.0}, point{3.0, -2.0}, point{3.0, 4.0}},
            0.0, false, true, false,
        },
        // Optional test case
        {
            &triangle{point{0.0, 0.0}, point{1.0, 0.0}, point{0.0, 1.0}},
            &triangle{point{1.0, 0.0}, point{2.0, 0.0}, point{1.0, 1.0}},
            0.0, false, true, true,
        },
        {
            &triangle{point{0.0, 0.0}, point{1.0, 0.0}, point{0.0, 1.0}},
            &triangle{point{1.0, 0.0}, point{2.0, 0.0}, point{1.0, 1.0}},
            0.0, false, false, false,
        },
    }

    for _, test := range tests {
        result := triTri2D(test.t1, test.t2, test.eps, test.allowReversed, test.onBoundary)
        if result != test.expected {
            t.Errorf("Expected %v, got %v for triangles %v and %v with eps=%v, allowReversed=%v, onBoundary=%v",
                test.expected, result, test.t1, test.t2, test.eps, test.allowReversed, test.onBoundary)
        }
    }
}
