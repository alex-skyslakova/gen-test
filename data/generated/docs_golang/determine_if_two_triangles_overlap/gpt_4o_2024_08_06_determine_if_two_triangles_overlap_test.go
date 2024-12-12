package main

import (
	"testing"
)

func TestTrianglesOverlap(t *testing.T) {
	tests := []struct {
		t1, t2       *triangle
		allowReversed bool
		onBoundary    bool
		expected      bool
	}{
		{
			t1:            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 5.0}},
			t2:            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 6.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      true,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{0.0, 5.0}, point{5.0, 0.0}},
			t2:            &triangle{point{0.0, 0.0}, point{0.0, 5.0}, point{5.0, 0.0}},
			allowReversed: true,
			onBoundary:    true,
			expected:      true,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{0.0, 5.0}},
			t2:            &triangle{point{-10.0, 0.0}, point{-5.0, 0.0}, point{-1.0, 6.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      false,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{5.0, 0.0}, point{2.5, 5.0}},
			t2:            &triangle{point{0.0, 4.0}, point{2.5, -1.0}, point{5.0, 4.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      true,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{1.0, 1.0}, point{0.0, 2.0}},
			t2:            &triangle{point{2.0, 1.0}, point{3.0, 0.0}, point{3.0, 2.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      false,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{1.0, 1.0}, point{0.0, 2.0}},
			t2:            &triangle{point{2.0, 1.0}, point{3.0, -2.0}, point{3.0, 4.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      false,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{1.0, 0.0}, point{0.0, 1.0}},
			t2:            &triangle{point{1.0, 0.0}, point{2.0, 0.0}, point{1.0, 1.0}},
			allowReversed: false,
			onBoundary:    true,
			expected:      true,
		},
		{
			t1:            &triangle{point{0.0, 0.0}, point{1.0, 0.0}, point{0.0, 1.0}},
			t2:            &triangle{point{1.0, 0.0}, point{2.0, 0.0}, point{1.0, 1.0}},
			allowReversed: false,
			onBoundary:    false,
			expected:      false,
		},
	}

	for _, test := range tests {
		result := triTri2D(test.t1, test.t2, 0.0, test.allowReversed, test.onBoundary)
		if result != test.expected {
			t.Errorf("Expected overlap to be %v, but got %v for triangles %v and %v", test.expected, result, test.t1, test.t2)
		}
	}
}
