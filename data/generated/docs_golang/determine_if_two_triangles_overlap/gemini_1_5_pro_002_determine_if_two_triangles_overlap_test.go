package main

import (
	"testing"
)

func TestTriTri2D(t *testing.T) {
	tests := []struct {
		t1            *triangle
		t2            *triangle
		eps           float64
		allowReversed bool
		onBoundary    bool
		expected      bool
	}{
		// Overlapping triangles
		{&triangle{point{0, 0}, point{5, 0}, point{0, 5}}, &triangle{point{0, 0}, point{5, 0}, point{0, 6}}, 0.0, false, true, true},

		// Identical triangles
		{&triangle{point{0, 0}, point{0, 5}, point{5, 0}}, &triangle{point{0, 0}, point{0, 5}, point{5, 0}}, 0.0, true, true, true},

		// Non-overlapping triangles
		{&triangle{point{0, 0}, point{5, 0}, point{0, 5}}, &triangle{point{-10, 0}, point{-5, 0}, point{-1, 6}}, 0.0, false, true, false},

		// Overlapping triangles (different shapes)
		{&triangle{point{0, 0}, point{5, 0}, point{2.5, 5}}, &triangle{point{0, 4}, point{2.5, -1}, point{5, 4}}, 0.0, false, true, true},

		// Non-overlapping triangles (different shapes)
		{&triangle{point{0, 0}, point{1, 1}, point{0, 2}}, &triangle{point{2, 1}, point{3, 0}, point{3, 2}}, 0.0, false, true, false},

		// Overlapping triangles (one encompassing the other)
		{&triangle{point{0, 0}, point{1, 1}, point{0, 2}}, &triangle{point{2, 1}, point{3, -2}, point{3, 4}}, 0.0, false, true, true},

		// Single corner contact (onBoundary = true)
		{&triangle{point{0, 0}, point{1, 0}, point{0, 1}}, &triangle{point{1, 0}, point{2, 0}, point{1, 1}}, 0.0, false, true, true},

		// Single corner contact (onBoundary = false)
		{&triangle{point{0, 0}, point{1, 0}, point{0, 1}}, &triangle{point{1, 0}, point{2, 0}, point{1, 1}}, 0.0, false, false, false},


		// Reversed winding order
		{&triangle{point{0, 0}, point{0, 5}, point{5, 0}}, &triangle{point{0, 0}, point{5, 0}, point{0, 5}}, 0.0, true, true, true},

	}

	for i, test := range tests {
		actual := triTri2D(test.t1, test.t2, test.eps, test.allowReversed, test.onBoundary)
		if actual != test.expected {
			t.Errorf("Test %d: Expected %t, got %t", i+1, test.expected, actual)
		}
	}
}

