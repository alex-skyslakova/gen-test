package main

import (
	"image"
	"reflect"
	"testing"
)

func TestConvexHull(t *testing.T) {
	tests := []struct {
		name     string
		points   points
		expected points
	}{
		{
			name: "Example case",
			points: points{
				{16, 3}, {12, 17}, {0, 6}, {-4, -6}, {16, 6},
				{16, -7}, {16, -3}, {17, -4}, {5, 19}, {19, -8},
				{3, 16}, {12, 13}, {3, -4}, {17, 5}, {-3, 15},
				{-3, -9}, {0, 11}, {-9, -3}, {-4, -2}, {12, 10},
			},
			expected: points{
				{-9, -3}, {-3, -9}, {19, -8}, {17, 5}, {12, 17}, {5, 19}, {-3, 15},
			},
		},
		{
			name: "Single point",
			points: points{
				{0, 0},
			},
			expected: points{
				{0, 0},
			},
		},
		{
			name: "Two points",
			points: points{
				{0, 0}, {1, 1},
			},
			expected: points{
				{0, 0}, {1, 1},
			},
		},
		{
			name: "Collinear points",
			points: points{
				{0, 0}, {1, 1}, {2, 2}, {3, 3},
			},
			expected: points{
				{0, 0}, {3, 3},
			},
		},
		{
			name: "Square shape",
			points: points{
				{0, 0}, {0, 1}, {1, 0}, {1, 1},
			},
			expected: points{
				{0, 0}, {1, 0}, {1, 1}, {0, 1},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := tt.points.ConvexHull()
			if !reflect.DeepEqual(result, tt.expected) {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}
