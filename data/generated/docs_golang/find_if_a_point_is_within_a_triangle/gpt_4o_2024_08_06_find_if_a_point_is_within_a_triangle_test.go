package main

import (
	"testing"
)

func TestAccuratePointInTriangle(t *testing.T) {
	tests := []struct {
		name     string
		x1, y1   float64
		x2, y2   float64
		x3, y3   float64
		x, y     float64
		expected bool
	}{
		{
			name:     "Point inside triangle",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        1, y: 1,
			expected: true,
		},
		{
			name:     "Point on edge of triangle",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        2.5, y: 0,
			expected: true,
		},
		{
			name:     "Point outside triangle",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        5, y: 5,
			expected: false,
		},
		{
			name:     "Point at vertex of triangle",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        0, y: 0,
			expected: true,
		},
		{
			name:     "Point very close to edge",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        2.5, y: 0.001,
			expected: true,
		},
		{
			name:     "Point very close to outside",
			x1:       0, y1: 0,
			x2:       5, y2: 0,
			x3:       0, y3: 5,
			x:        5.001, y: 5.001,
			expected: false,
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := accuratePointInTriangle(tt.x1, tt.y1, tt.x2, tt.y2, tt.x3, tt.y3, tt.x, tt.y)
			if result != tt.expected {
				t.Errorf("expected %v, got %v", tt.expected, result)
			}
		})
	}
}
