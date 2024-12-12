package main

import (
	"image"
	"image/color"
	"testing"
)

func TestHasNeighbor(t *testing.T) {
	// Initialize a small test image
	testWidth, testHeight := 10, 10
	g = image.NewGray(image.Rectangle{image.Point{0, 0}, image.Point{testWidth, testHeight}})

	// Set a seed point in the middle
	seedPoint := image.Point{5, 5}
	g.SetGray(seedPoint.X, seedPoint.Y, color.Gray{frost})

	tests := []struct {
		point    image.Point
		expected bool
	}{
		{image.Point{4, 4}, true},  // Diagonal neighbor
		{image.Point{5, 4}, true},  // Vertical neighbor
		{image.Point{4, 5}, true},  // Horizontal neighbor
		{image.Point{6, 6}, false}, // No neighbor
		{image.Point{0, 0}, false}, // Edge of the image, no neighbor
	}

	for _, test := range tests {
		result := hasNeighbor(test.point)
		if result != test.expected {
			t.Errorf("hasNeighbor(%v) = %v; want %v", test.point, result, test.expected)
		}
	}
}
