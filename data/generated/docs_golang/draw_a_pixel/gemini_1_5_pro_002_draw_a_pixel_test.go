package main

import (
	"image"
	"image/color"
	"testing"
)

func TestDrawPixel(t *testing.T) {
	rect := image.Rect(0, 0, 320, 240)
	img := image.NewRGBA(rect)

	// Use green background
	green := color.RGBA{0, 255, 0, 255}
	// Note: draw.Draw is not tested here as it's not part of the core requirements
	//	   and introduces a dependency on the 'draw' package in the test file

	// Set color of pixel at (100, 100) to red
	red := color.RGBA{255, 0, 0, 255}
	img.Set(100, 100, red)

	// Check background color
	c1 := img.At(0, 0)
	if c1 != green {  // Comparing color.RGBA directly.
		t.Errorf("Expected background color %v, got %v", green, c1)
	}


	// Check pixel color at (100, 100)
	c2 := img.At(100, 100)
	if c2 != red { // Comparing color.RGBA directly.
		t.Errorf("Expected pixel color at (100, 100) to be %v, got %v", red, c2)
	}

    // Check out of bounds - x too large
    outOfBoundsColor := img.At(320,100)
    if outOfBoundsColor != (color.RGBA{0,0,0,0}){
        t.Errorf("Expected out of bound pixel color at (320, 100) to be %v, got %v", color.RGBA{0,0,0,0}, outOfBoundsColor)
    }
    // Check out of bounds - y too large
    outOfBoundsColor = img.At(100, 240)
    if outOfBoundsColor != (color.RGBA{0,0,0,0}){
        t.Errorf("Expected out of bound pixel color at (100, 240) to be %v, got %v", color.RGBA{0,0,0,0}, outOfBoundsColor)
    }


}

