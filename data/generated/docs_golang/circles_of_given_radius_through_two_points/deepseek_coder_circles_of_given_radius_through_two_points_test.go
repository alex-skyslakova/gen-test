package main

import (
    "testing"
)

func TestCircles(t *testing.T) {
    testCases := []struct {
        p1, p2 point
        r      float64
        expectedC1, expectedC2 point
        expectedCase string
    }{
        // Test case 1: Two distinct points with valid radius
        {
            point{0.1234, 0.9876}, point{0.8765, 0.2345}, 2.0,
            point{1.86311180165, 1.40801250786}, point{-0.86321180165, 0.56118749214},
            Two,
        },
        // Test case 2: Two points forming a diameter
        {
            point{0.0000, 2.0000}, point{0.0000, 0.0000}, 1.0,
            point{0.0000, 1.0000}, point{0.0000, 1.0000},
            Diam,
        },
        // Test case 3: Coincident points with valid radius
        {
            point{0.1234, 0.9876}, point{0.1234, 0.9876}, 2.0,
            point{0.0, 0.0}, point{0.0, 0.0},
            Co,
        },
        // Test case 4: Points too far apart to form circles
        {
            point{0.1234, 0.9876}, point{0.8765, 0.2345}, 0.5,
            point{0.0, 0.0}, point{0.0, 0.0},
            Far,
        },
        // Test case 5: Coincident points with zero radius
        {
            point{0.1234, 0.9876}, point{0.1234, 0.9876}, 0.0,
            point{0.1234, 0.9876}, point{0.1234, 0.9876},
            CoR0,
        },
    }

    for _, tc := range testCases {
        c1, c2, Case := circles(tc.p1, tc.p2, tc.r)

        if Case != tc.expectedCase {
            t.Errorf("Expected case %s, but got %s for points %v and %v with radius %f", tc.expectedCase, Case, tc.p1, tc.p2, tc.r)
        }

        switch Case {
        case CoR0, Diam:
            if c1 != tc.expectedC1 {
                t.Errorf("Expected center %v, but got %v for case %s", tc.expectedC1, c1, Case)
            }
        case Two:
            if c1 != tc.expectedC1 || c2 != tc.expectedC2 {
                t.Errorf("Expected centers %v and %v, but got %v and %v for case %s", tc.expectedC1, tc.expectedC2, c1, c2, Case)
            }
        }
    }
}
