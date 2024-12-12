package main

import (
	"testing"
	"math"
)

func TestCircles(t *testing.T) {
	tests := []struct {
		p1, p2 point
		r      float64
		wantC1 point
		wantC2 point
		wantCase string
	}{
		{point{0.1234, 0.9876}, point{0.8765, 0.2345}, 2.0, point{0.1234, 0.9876}, point{0.8765, 0.2345}, Two},
		{point{0.0000, 2.0000}, point{0.0000, 0.0000}, 1.0, point{0.0000, 1.0}, point{0.0000, 1.0}, Diam},
		{point{0.1234, 0.9876}, point{0.1234, 0.9876}, 2.0, point{0.1234, 0.9876}, point{0.1234, 0.9876}, Co},
		{point{0.1234, 0.9876}, point{0.8765, 0.2345}, 0.5, point{0.1234, 0.9876}, point{0.8765, 0.2345}, Far},
		{point{0.1234, 0.9876}, point{0.1234, 0.9876}, 0.0, point{0.1234, 0.9876}, point{0.1234, 0.9876}, CoR0},
	}

	for _, tt := range tests {
		t.Run(tt.wantCase, func(t *testing.T) {
			gotC1, gotC2, gotCase := circles(tt.p1, tt.p2, tt.r)
			if gotCase != tt.wantCase {
				t.Errorf("circles() gotCase = %v, want %v", gotCase, tt.wantCase)
			}
			if !pointsEqual(gotC1, tt.wantC1) || !pointsEqual(gotC2, tt.wantC2) {
				t.Errorf("circles() gotC1 = %v, gotC2 = %v, wantC1 = %v, wantC2 = %v", gotC1, gotC2, tt.wantC1, tt.wantC2)
			}
		})
	}
}

func pointsEqual(p1, p2 point) bool {
	return math.Abs(p1.x-p2.x) < 1e-9 && math.Abs(p1.y-p2.y) < 1e-9
}
