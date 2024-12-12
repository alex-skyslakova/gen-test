package main

import (
	"image"
	"reflect"
	"testing"
)

func TestConvexHull(t *testing.T) {
	tests := []struct {
		name     string
		input    points
		expected points
	}{
		{
			name: "Example from documentation",
			input: points{
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
			input: points{
				{0, 0},
			},
			expected: points{
				{0, 0},
			},
		},
		{
			name: "Two points",
			input: points{
				{0, 0}, {1, 1},
			},
			expected: points{
				{0, 0}, {1, 1},
			},
		},
		{
			name: "Three collinear points",
			input: points{
				{0, 0}, {1, 1}, {2, 2},
			},
			expected: points{
				{0, 0}, {2, 2},
			},
		},
		{
			name: "Four points forming a square",
			input: points{
				{0, 0}, {0, 1}, {1, 1}, {1, 0},
			},
			expected: points{
				{0, 0}, {1, 0}, {1, 1}, {0, 1},
			},
		},
		{
			name: "Points forming a triangle",
			input: points{
				{0, 0}, {1, 1}, {0, 1},
			},
			expected: points{
				{0, 0}, {1, 1}, {0, 1},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := tt.input.ConvexHull()
			if !reflect.DeepEqual(result, tt.expected) {
				t.Errorf("ConvexHull() = %v, want %v", result, tt.expected)
			}
		})
	}
}

func TestCCW(t *testing.T) {
	tests := []struct {
		name     string
		a, b, c  image.Point
		expected bool
	}{
		{
			name:     "Counter-clockwise",
			a:        image.Point{0, 0},
			b:        image.Point{1, 0},
			c:        image.Point{0, 1},
			expected: true,
		},
		{
			name:     "Clockwise",
			a:        image.Point{0, 0},
			b:        image.Point{0, 1},
			c:        image.Point{1, 0},
			expected: false,
		},
		{
			name:     "Collinear",
			a:        image.Point{0, 0},
			b:        image.Point{1, 1},
			c:        image.Point{2, 2},
			expected: false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := ccw(tt.a, tt.b, tt.c)
			if result != tt.expected {
				t.Errorf("ccw(%v, %v, %v) = %v, want %v", tt.a, tt.b, tt.c, result, tt.expected)
			}
		})
	}
}
