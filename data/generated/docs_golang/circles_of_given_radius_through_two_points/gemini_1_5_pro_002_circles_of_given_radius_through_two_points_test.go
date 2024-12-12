package main

import (
	"math"
	"testing"
)

func TestCircles(t *testing.T) {
	testCases := []struct {
		p1, p2 point
		r      float64
		c1, c2 point
		Case   string
	}{
		// Two distinct circles
		{point{0.1234, 0.9876}, point{0.8765, 0.2345}, 2.0, point{-0.2474707106907507, 1.8795292893092503}, point{1.497470710690751, -0.6295292893092497}, Two},
        //Points form a diameter
		{point{0.0000, 2.0000}, point{0.0000, 0.0000}, 1.0, point{0, 1}, point{0, 1}, Diam},
        //Coincident points, infinite circles
		{point{0.1234, 0.9876}, point{0.1234, 0.9876}, 2.0, point{}, point{}, Co},
        //Points too far apart
		{point{0.1234, 0.9876}, point{0.8765, 0.2345}, 0.5, point{}, point{}, Far},
        //Coincident points, r=0
		{point{0.1234, 0.9876}, point{0.1234, 0.9876}, 0.0, point{0.1234, 0.9876}, point{0.1234, 0.9876}, CoR0},
        //r=0, no circles
        {point{0.1234, 0.9876}, point{0.4567, 0.7890}, 0.0, point{0.1234, 0.9876}, point{0.4567, 0.7890}, R0},
	}

	for _, tc := range testCases {
		c1, c2, Case := circles(tc.p1, tc.p2, tc.r)
		if c1 != tc.c1 || c2 != tc.c2 || Case != tc.Case {
			t.Errorf("For p1=%v, p2=%v, r=%v:\nExpected c1=%v, c2=%v, Case=%v\nGot c1=%v, c2=%v, Case=%v",
				tc.p1, tc.p2, tc.r, tc.c1, tc.c2, tc.Case, c1, c2, Case)
		}
	}
}

//Helper function to compare floats with tolerance
func floatEquals(a, b float64) bool {
    tolerance := 1e-9
    return math.Abs(a-b) < tolerance
}


