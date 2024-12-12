package main

import (
	"testing"
)

type bearing float64

func (b2 bearing) Sub(b1 bearing) bearing {
	switch d := b2 - b1; {
	case d < -180:
		return d + 360
	case d > 180:
		return d - 360
	default:
		return d
	}
}

func TestAngleDifferenceBetweenTwoBearings(t *testing.T) {
	testCases := []struct {
		b1, b2 bearing
		want   bearing
	}{
		{20, 45, 25},
		{-45, 45, 90},
		{-85, 90, 175},
		{-95, 90, 185 - 360}, // -175
		{-45, 125, 170},
		{-45, 145, 190 - 360}, // -170
		{29.4803, -88.6381, -118.1184},
		{-78.3251, -159.036, -80.7109},
		// Optional extra test cases
		{-70099.74233810938, 29840.67437876723, 139.58304065761},
		{-165313.6666297357, 33693.9894517456, 19.6560814813},
		{1174.8380510598456, -154146.66490124757, -151.50295230772},
		{60175.77306795546, 42213.07192354373, -179.29814441173},
	}

	for _, tc := range testCases {
		t.Run("", func(t *testing.T) {
			got := tc.b2.Sub(tc.b1)
			if got != tc.want {
				t.Errorf("Sub(%v, %v) = %v; want %v", tc.b2, tc.b1, got, tc.want)
			}
		})
	}
}
